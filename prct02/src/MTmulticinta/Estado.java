package MTmulticinta;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * Estado.java - Clase para representar un estado de una maquina de Turing que acepta multicinta
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class Estado {	
	
	private String nombre; // Nombre del estado
	private boolean inicial; // true si el estado es inicial
	private boolean estadoFinal; // true si el estado es final o de aceptacion
	private ArrayList<Transicion> transiciones; // Lista de transiciones del estado
	
	/**
	 * Constructor de un estado a partir de un nombre determinado
	 * @param nombre Nombre dado al estado
	 */
	public Estado(String nombre) {
		this.nombre = nombre;
		this.inicial = false;
		this.estadoFinal = false;
		this.transiciones = new ArrayList<Transicion>();
	}
	
	/**
	 * Metodo para agregar una transicion a un estado
	 * @param transicion Transicion a agregar al estado
	 */
	public void addTransicion (Transicion transicion) {
		this.transiciones.add(transicion);
	}
	
	/**
	 * Metodo para buscar si el estado tiene una transicion para los simbolos solicitados
	 * @param simbolos Conjunto de simbolos para los que buscar la transicion
	 * @return La transion encontrada o null en caso de que no se encuentre
	 */
	public Transicion buscarTransicion (ArrayList<Character> simbolos) {
		Iterator<Transicion> it = this.transiciones.iterator();
		while (it.hasNext()) {
			Transicion transicion = it.next();
			int contador = 0;
			for (int i = 0; i < simbolos.size(); i++) {
				if (transicion.getSimboloLectura(i).equals(simbolos.get(i)))
					contador++;
			}
			if (contador == simbolos.size())
				return transicion;
		}
		return null;
	}
	
	/**
	 * Metodo que devuelve el numero de transiciones de un estado
	 * @return Numero de transiciones del estado
	 */
	public int numTransiciones () {
		return this.transiciones.size();
	}
	
	/**
	 * Metodo que devuelve una transicion segun su posicion en la lista de transiciones del estado
	 * @param numero Posicion de la transicion dentro de la lista de transiciones
	 * @return La transicion en la posicion solicitada
	 */
	public Transicion getTransicion (int numero) {
		return this.transiciones.get(numero);
	}
	
	/**
	 * Metodo que muestra todas las transiciones de un estado
	 */
	public void mostrarTransiciones () {
		for (int i = 0; i < this.transiciones.size(); i++) {
			System.out.println(this.nombre + " " + this.transiciones.get(i));
		}
	}
	
	/**
	 * Metodo para formatear el estado en un String
	 */
	public String toString () {
		return this.nombre;
	}

	/**
	 * Getter del nombre del estado
	 * @return Nombre del estado
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre del estado
	 * @param nombre Nombre del estado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que comprueba si un estado es el inicial
	 * @return true en caso de que el estado sea inicial
	 */
	public boolean esInicial() {
		return inicial;
	}

	/**
	 * Metodo para establecer un estado como estado inicial
	 */
	public void setInicial() {
		this.inicial = true;
	}
	
	/**
	 * Metodo para comprobar si un estado es de aceptacion
	 * @return true en caso de que el estado sea de aceptacion
	 */
	public boolean esFinal() {
		return this.estadoFinal;
	}

	/**
	 * Metodo para establecer un estado como estado final o de aceptacion
	 */
	public void setFinal() {
		this.estadoFinal = true;
	}

}
