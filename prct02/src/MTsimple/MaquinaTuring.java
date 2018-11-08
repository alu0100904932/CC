package MTsimple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MaquinaTuring {
	
	private ConjuntoEstados estados;
	private ArrayList<Character> alfabeto;
	private ArrayList<Character> simbolosCinta;
	private Cinta cinta;
	private CabezaLE cabezaLE;
	private Character blanco;
	private boolean existe;
	private boolean traza;	
	
	public MaquinaTuring() {
		this.estados = new ConjuntoEstados();
		this.alfabeto = new ArrayList<Character>();
		this.simbolosCinta = new ArrayList<Character>();
		this.cinta =  new Cinta();
		this.cabezaLE = new CabezaLE ();
		this.blanco = '.';
		this.existe = false;
		this.traza = false;
	}

	public MaquinaTuring(String nombreFichero) throws IOException {
		this.estados = new ConjuntoEstados();
		this.alfabeto = new ArrayList<Character>();
		this.simbolosCinta = new ArrayList<Character>();
		this.cinta =  new Cinta();
		this.cabezaLE = new CabezaLE ();
		this.traza = false;
		
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
		
		// Estado final
		linea = reader.readLine();
		String estadoFinalFichero [] = linea.split(" ");
		for (int i = 0; i < estadoFinalFichero.length; i++)
		{
			if (this.estados.contiene(estadoFinalFichero[i]))
			{
				Estado estadoFinal = this.estados.buscarEstado(estadoFinalFichero[0]);
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
			String transicion [] = linea.split(" ");
			if (transicion.length > 5) {
				System.err.println("La transicion no tiene el formato correcto");
				reader.close();
				return;
			}
			// transicion [0] = estado origen
			if (!this.estados.contiene(transicion[0])) {
				System.err.println("El estado de origen de la transicion no pertenece al conjunto "
						+ "de estados");
				reader.close();
				return;
			}
			Estado estadoOrigen = this.estados.buscarEstado(transicion[0]);
			// transicion [1] = simbolo lee
			Character simbolo = transicion[1].charAt(0);
			if (!this.simbolosCinta.contains(simbolo)) {
				System.err.println("El simbolo de lectura de la transicion no pertenece al alfabeto de la cinta");
				reader.close();
				return;
			}
			// transicion [2] = estado destino
			if (!this.estados.contiene(transicion[2])) {
				System.err.println("El estado de destino de la transicion no pertenece al conjunto "
						+ "de estados");
				reader.close();
				return;
			}
			Estado estadoDestino = this.estados.buscarEstado(transicion[2]);
			// transicion [3] = simbolo escribe
			Character simboloEscribe = transicion[3].charAt(0);
			if (!this.simbolosCinta.contains(simboloEscribe)) {
				System.err.println("El simbolo de escritura de la transicion no pertenece al alfabeto de la cinta");
				reader.close();
				return;
			}
			// transicion [4] = movimiento
			Character movimiento = transicion[4].charAt(0);
			if (!this.cabezaLE.esMovPermitido(movimiento)) {
				System.err.println("El movimiento de la transicion no es permitido");
				reader.close();
				return;
			}
			Transicion aux = new Transicion (simbolo, estadoDestino, simboloEscribe, movimiento);
			estadoOrigen.addTransicion(aux);
		}
		
		reader.close();
		this.existe = true;		
	}
	
	public boolean analizarEntrada (String cadena) {
		this.cinta.clear();
		this.cabezaLE.moverAInicio();
		
		System.out.println("Analizando la cadena: " + cadena);
		
		// Comprobar que la cadena es valida para el alfabeto
		if (!cadenaValida(cadena))
			return false;
		
		// Introducir entrada en la cinta y un blanco a cada lado
		for (int i = 0; i < cadena.length(); i++) {
			cinta.add(cadena.charAt(i));
		}
		this.cinta.add(0, this.blanco);
		this.cinta.add(this.blanco);
		
		Estado estadoActual = this.estados.getEstadoInicial();
		Character simboloActual = this.cinta.get(this.cabezaLE.getPosicion());
		Transicion transicionActual = estadoActual.buscarTransicion(simboloActual);
		
		// Mientras exista alguna transicion para el simbolo actual en el estado actual
		while (transicionActual != null) {			
			
			/* TEST
			System.out.println(estadoActual);
			System.out.println(simboloActual);
			System.out.println(this.cinta);
			System.out.println(this.cabezaLE.getPosicion());
			System.out.println(transicionActual);  */
			
			this.cinta.set(this.cabezaLE.getPosicion(), transicionActual.getSimboloEscritura());
			this.cabezaLE.mover(transicionActual.getMovimiento());
			if (this.cabezaLE.getPosicion() < 0)
				this.cinta.add(0, this.blanco);
			else if (this.cabezaLE.getPosicion() == this.cinta.size())
				this.cinta.add(this.blanco);
			
			estadoActual = transicionActual.getSiguiente();			
			simboloActual = this.cinta.get(this.cabezaLE.getPosicion());
			transicionActual = estadoActual.buscarTransicion(simboloActual);
		}
		
		String salida = "Desde la cabeza L/E hasta el primer blanco la cinta contiene: ";
		int iteracion = 1;
		for (int i = this.cabezaLE.getPosicion(); i < this.cinta.size(); i++) {
			if (this.cinta.get(i).equals(this.blanco))
				break;
			if (iteracion == 1) { // Para que el formato sea correcto la primera iteracion es distinta
				salida += this.cinta.get(i);
				iteracion++;
			}
			else
				salida += ", " + this.cinta.get(i);
		}
		System.out.println(salida);		
		if (estadoActual.esFinal())
			return true;
		
		return false;
					
	}

	public boolean cadenaValida (String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			Character caracter = cadena.charAt(i);
			if (!this.alfabeto.contains(caracter))
				if (!caracter.equals(this.blanco))
					return false;
		}
		return true;
	}
	
	public boolean existe () {
		return this.existe;
	}

	public boolean mostrarTraza() {
		return traza;
	}

	public void setTraza(boolean traza) {
		this.traza = traza;
	}
	
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
		s += "\n" + this.estados.getEstadoInicial() + "\n" + this.blanco + "\n" + this.estados.getEstadoFinal() + "\n";
		for (int i = 0; i < this.estados.size(); i++) {
			for (int j = 0; j < this.estados.get(i).numTransiciones(); j++)
				s += this.estados.get(i).getNombre() + " " + this.estados.get(i).getTransicion(j) + "\n";
		}
		return s;
	}
}
