package MTmulticinta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * MaquinaTuringMulticinta.java - Clase para representar una maquina de Turing que acepta multicinta
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class MaquinaTuringMulticinta {
	
	private ConjuntoEstados estados; // Estados de la maquina
	private ArrayList<Character> alfabeto; // Alfabeto de la maquina
	private ArrayList<Character> simbolosCinta; // Simbolos que aceptan las cintas
	private ArrayList<Cinta> cintas; // Cintas de la maquina
	private Character blanco; // Simbolo blanco
	private boolean existe;	 // True si la maquina ha sido correctamente cargada en el simulador o false en caso contrario
	
	/**
	 * Constructor por defecto la maquina de Turing
	 */
	public MaquinaTuringMulticinta() {
		this.estados = new ConjuntoEstados();
		this.alfabeto = new ArrayList<Character>();
		this.simbolosCinta = new ArrayList<Character>();
		this.cintas =  new ArrayList<Cinta>();
		this.blanco = '.';
		this.existe = false;
	}

	/**
	 * Contructor de la maquina de Turing a partir de un fichero
	 * @param nombreFichero Nombre del fichero que contiene la maquina de Turing
	 * @throws IOException Excepcion para el manejo de errores I/O
	 */
	public MaquinaTuringMulticinta(String nombreFichero) throws IOException {
		this.estados = new ConjuntoEstados();
		this.alfabeto = new ArrayList<Character>();
		this.simbolosCinta = new ArrayList<Character>();
		this.cintas =  new ArrayList<Cinta>();
		
		BufferedReader reader = new BufferedReader (new FileReader ("docs/" + nombreFichero));
		String linea;
		
		// Comentarios
		do {
			linea = reader.readLine();
		}
		while (linea.startsWith("#"));
		
		// Conjunto de estados
		String estadosFichero [] = linea.split(" ");
		for (int i = 0; i < estadosFichero.length; i++) {
			this.estados.add(new Estado (estadosFichero[i]));
		}
		
		// Simbolos alfabeto
		linea = reader.readLine();
		String simbolosAlfabetoFichero [] = linea.split(" ");
		for (int i = 0; i < simbolosAlfabetoFichero.length; i++) {
			this.alfabeto.add(simbolosAlfabetoFichero[i].charAt(0));
		}
		
		// Simbolos de la cinta
		linea = reader.readLine();
		String simbolosCintaFichero [] = linea.split(" ");
		for (int i = 0; i < simbolosCintaFichero.length; i++) {
			this.simbolosCinta.add(simbolosCintaFichero[i].charAt(0));
		}
		
		// Estado inicial
		linea = reader.readLine();
		String estadoInicialFichero [] = linea.split(" ");
		if (estadoInicialFichero.length > 1) {
			System.err.println("Existe más de un estado inicial");
			reader.close();
			return;
		}
		if (this.estados.contiene(estadoInicialFichero[0])) {
			Estado estadoInicial = this.estados.buscarEstado(estadoInicialFichero[0]);
			estadoInicial.setInicial();
		}
		else {
			System.err.println("El estado inicial no pertenece al conjunto de estados");
			reader.close();
			return;
		}
		
		// Simbolo blanco
		linea = reader.readLine();
		String blancoFichero [] = linea.split(" ");
		if (blancoFichero.length > 1) {
			System.err.println("Existe más de un simbolo blanco");
			reader.close();
			return;
		}
		this.blanco = blancoFichero[0].charAt(0);
		
		// Estados finales
		linea = reader.readLine();
		String estadoFinalFichero [] = linea.split(" ");
		for (int i = 0; i < estadoFinalFichero.length; i++)
		{
			if (this.estados.contiene(estadoFinalFichero[i]))
			{
				Estado estadoFinal = this.estados.buscarEstado(estadoFinalFichero[i]);
				estadoFinal.setFinal();
			}
			else
			{
				System.err.println("Uno de los estados finales no pertenece al conjunto de estados");
				reader.close();
				return;
			}
		}
		
		// Transiciones
		while (reader.ready()) {
			linea = reader.readLine();
			int posicionLectura = 0;
			String transicion [] = linea.split(" ");
			
			// Calcular el numero de cintas e incluirlas en el array de cintas
			int numCintas = (transicion.length - 2) / 3;
			if (numCintas != this.cintas.size()) // Para que solo lo haga una vez
				for (int i = 0; i < numCintas; i++) {
					this.cintas.add(new Cinta ());
				}
			
			// Estado origen
			if (!this.estados.contiene(transicion[posicionLectura])) {
				System.err.println("El estado de origen de la transicion no pertenece al conjunto "
						+ "de estados");
				reader.close();
				return;
			}
			Estado estadoOrigen = this.estados.buscarEstado(transicion[posicionLectura]);
			posicionLectura++;
			
			// Simbolos a leer
			ArrayList<Character> simbolosLectura = new ArrayList<Character>();
			for (int i = 0; i < numCintas; i++) {
				Character simbolo = transicion[posicionLectura].charAt(0);
				if (!this.simbolosCinta.contains(simbolo)) {
					System.err.println("El simbolo de lectura de la transicion no pertenece al alfabeto de la cinta");
					reader.close();
					return;
				}
				simbolosLectura.add(simbolo);
				posicionLectura++;
			}
			
			// Estado destino
			if (!this.estados.contiene(transicion[posicionLectura])) {
				System.err.println("El estado de destino de la transicion no pertenece al conjunto "
						+ "de estados");
				reader.close();
				return;
			}
			Estado estadoDestino = this.estados.buscarEstado(transicion[posicionLectura]);
			posicionLectura++;
			
			// Simbolos a escribir
			ArrayList<Character> simbolosEscritura = new ArrayList<Character>();
			for (int i = 0; i < numCintas; i++) {
				Character simboloEscribe = transicion[posicionLectura].charAt(0);
				if (!this.simbolosCinta.contains(simboloEscribe)) {
					System.err.println("El simbolo de escritura de la transicion no pertenece al alfabeto de la cinta");
					reader.close();
					return;
				}
				simbolosEscritura.add(simboloEscribe);
				posicionLectura++;
			}
			
			// Movimientos
			ArrayList<Character> movimientos = new ArrayList<Character>();
			for (int i = 0; i < numCintas; i++) {
				Character movimiento = transicion[posicionLectura].charAt(0);
				if (!this.cintas.get(0).getCabezaLE().esMovPermitido(movimiento)) {
					System.err.println("El movimiento de la transicion no es permitido");
					reader.close();
					return;
				}
				movimientos.add(movimiento);
				posicionLectura++;
			}
			
			// Crear la transicion e incluirla en las transiciones del estado
			Transicion aux = new Transicion (simbolosLectura, estadoDestino, simbolosEscritura, movimientos);
			estadoOrigen.addTransicion(aux);
		}
		
		reader.close();
		this.existe = true;		
	}
	
	/**
	 * Metodo para analizar una entrada determinada con la maquina de Turing cargada
	 * @param cadena Entrada a analizar
	 * @return true en caso de que se acepte la cadena
	 */
	public boolean analizarEntrada (String cadena) {
		for (int i = 0; i < this.cintas.size(); i++) {
			this.cintas.get(i).clear();
			CabezaLE.moverAInicio(this.cintas.get(i));
		}
		
		System.out.println("Analizando la cadena: " + cadena);
		
		// Comprobar que la cadena es valida para el alfabeto
		if (!cadenaValida(cadena))
			return false;
		
		// Introducir entrada en la primera cinta y un blanco a cada lado
		// si hay mas cintas introducir un blanco en ellas
		Cinta cintaEntrada = this.cintas.get(0);
		for (int i = 0; i < cadena.length(); i++) {
			cintaEntrada.add(cadena.charAt(i));
		}
		cintaEntrada.add(0, this.blanco);
		cintaEntrada.add(this.blanco);
		CabezaLE.moverAInicio(cintaEntrada);
		
		if (this.cintas.size() > 1)
			for (int i = 1; i < this.cintas.size(); i++)
				this.cintas.get(i).add(this.blanco);
		
		
		Estado estadoActual = this.estados.getEstadoInicial();
		ArrayList<Character> simbolosActuales = new ArrayList<Character>();
		for (int i = 0; i < this.cintas.size(); i++) {
			Cinta cinta = this.cintas.get(i);
			Character simbolo = cinta.get(cinta.getCabezaLE().getPosicion());
			simbolosActuales.add(simbolo);
		}
		
		Transicion transicionActual = estadoActual.buscarTransicion(simbolosActuales);
		
		// Mientras exista alguna transicion para el simbolo actual en el estado actual
		while (transicionActual != null) {			
			
			/* TEST  
			System.out.println(estadoActual + " " + transicionActual);	
			for (int i = 0; i < this.cintas.size(); i++) {
				System.out.println(this.cintas.get(i));
				System.out.println(this.cintas.get(i).getCabezaLE().getPosicion()); 
			}	*/
			
			for (int i = 0; i < this.cintas.size(); i++) {
				Cinta cintaI = this.cintas.get(i);
				CabezaLE cabezaCintaI = cintaI.getCabezaLE();
				cintaI.set(cabezaCintaI.getPosicion(), transicionActual.getSimboloEscritura(i));
				cabezaCintaI.mover(transicionActual.getMovimiento(i));
				if (cabezaCintaI.getPosicion() < 0) {
					cintaI.add(0, this.blanco);
					cabezaCintaI.setPosicion(0);
				}
				else if (cabezaCintaI.getPosicion() == cintaI.size())
					cintaI.add(this.blanco);
			}
			
			estadoActual = transicionActual.getSiguiente();			
			simbolosActuales.clear();
			for (int i = 0; i < this.cintas.size(); i++) {
				Cinta cinta = this.cintas.get(i);
				Character simbolo = cinta.get(cinta.getCabezaLE().getPosicion());
				simbolosActuales.add(simbolo);
			}
			transicionActual = estadoActual.buscarTransicion(simbolosActuales);
		}
		
		/* Mostrar cintas de principio a blanco  
		for (int i = 0; i < this.cintas.size(); i++) {
			this.cintas.get(i).getCabezaLE().moverAInicio(this.cintas.get(i));
		} */
		
		// Mostrar la cinta que contenia la entrada completa
		// System.out.println(this.cintas.get(0));
		
		for (int i = 0; i < this.cintas.size(); i++) {
			String salida = "Desde la cabeza L/E hasta el primer blanco la cinta " + (i + 1) + " contiene: ";
			int iteracion = 1;
			for (int j = this.cintas.get(i).getCabezaLE().getPosicion(); j < this.cintas.get(i).size(); j++) {
				if (this.cintas.get(i).get(j).equals(this.blanco))
					break;
				if (iteracion == 1) { // Para que el formato sea correcto la primera iteracion es distinta
					salida += this.cintas.get(i).get(j);
					iteracion++;
				}
				else
					salida += ", " + this.cintas.get(i).get(j);
			}
			System.out.println(salida);	
		}	
			
		if (estadoActual.esFinal())
			return true;
		
		return false;
					
	}

	/**
	 * Metodo que comprueba si la cadena de entrada es valida para el alfabeto definido
	 * @param cadena Cadena de entrada
	 * @return true en caso de que la entrada sea valida
	 */
	public boolean cadenaValida (String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			Character caracter = cadena.charAt(i);
			if (!this.alfabeto.contains(caracter))
				if (!caracter.equals(this.blanco))
					return false;
		}
		return true;
	}
	
	/**
	 * Metodo para comprobar si la MT ha sido cargada en el programa correctamente
	 * @return true en caso de que se haya cargado correctamente
	 */
	public boolean existe () {
		return this.existe;
	}
	
	/**
	 * Metodo para definir una MT como String
	 */
	public String toString () {
		String s = "";
		for (int i = 0; i < this.estados.size(); i++) {
			s += this.estados.get(i).getNombre() + " ";
		}
		s += "\n";
		for (int i = 0; i < this.alfabeto.size(); i++) {
			s += this.alfabeto.get(i) + " ";
		}
		s += "\n";
		for (int i = 0; i < this.simbolosCinta.size(); i++) {
			s += this.simbolosCinta.get(i) + " ";
		}
		s += "\n" + this.estados.getEstadoInicial() + "\n" + this.blanco + "\n";
		for (int i = 0; i < this.estados.size(); i++) {
			if (this.estados.get(i).esFinal())
				s += this.estados.get(i) + " ";
		}
		for (int i = 0; i < this.estados.size(); i++) {
			for (int j = 0; j < this.estados.get(i).numTransiciones(); j++)
				s += "\n" + this.estados.get(i).getNombre() + " " + this.estados.get(i).getTransicion(j);
		}
		
		return s;
	}
}
