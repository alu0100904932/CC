package MTmulticinta;

import java.util.ArrayList;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * Transicion.java - Clase para representar una transicion de una maquina de Turing que acepta multicinta
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class Transicion {
	
	private ArrayList<Character> simbolosLectura; // Simbolo con el que se realiza la transicion	
	private Estado siguiente; // Estado de destino de la transicion
	private ArrayList<Character> simbolosEscritura; // Simbolo que se escribe en la cinta
	private ArrayList<Character> movimientos; // Movimiento que realiza la cabeza de L/E
	
	/**
	 * Constructor de una transicion
	 * @param simbolosLectura Simbolos que se leen y con los que se realiza la transicion
	 * @param siguiente Estado de destino de la transicion
	 * @param simbolosEscritura Simbolos que escribir en las cintas
	 * @param movimientos Movimientos que realiza la cabeza de L/E de las cintas
	 */
	public Transicion (ArrayList<Character> simbolosLectura, Estado siguiente, ArrayList<Character> simbolosEscritura, ArrayList<Character> movimientos) {
		this.simbolosLectura = simbolosLectura;
		this.siguiente = siguiente;
		this.simbolosEscritura = simbolosEscritura;
		this.movimientos = movimientos;		
	}
	
	/**
	 * Metodo para definir una Transicion como String
	 */
	public String toString () {
		String s = "";
		for (int i = 0; i < this.simbolosLectura.size(); i++)
			s += this.simbolosLectura.get(i) + " ";
		s += this.siguiente + " ";
		for (int i = 0; i < this.simbolosEscritura.size(); i++)
			s += this.simbolosEscritura.get(i) + " ";
		for (int i = 0; i < this.movimientos.size(); i++)
			s += this.movimientos.get(i) + " ";
		return s;
	}
	
	/**
	 * Getter para un simbolo de lectura de su conjunto	
	 * @param pos Posicion en la que se encuentra el simbolo
	 * @return Simbolo
	 */
	public Character getSimboloLectura(int pos) {
		return this.simbolosLectura.get(pos);
	}

	/**
	 * Setter para un simbolo de lectura en una posicion del conjunto
	 * @param pos Posicion de insercion
	 * @param simboloLectura Simbolo
	 */
	public void setSimboloLectura(int pos, Character simboloLectura) {
		this.simbolosLectura.set(pos, simboloLectura);
	}

	/**
	 * Getter del estado de destino de una transicion
	 * @return Estado de destino
	 */
	public Estado getSiguiente() {
		return siguiente;
	}
	
	/**
	 * Setter para el estado de destino de una transicion
	 * @param siguiente Estado de destino
	 */
	public void setSiguiente(Estado siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * Getter para un simbolo de escritura de su conjunto	
	 * @param pos Posicion en la que se encuentra el simbolo
	 * @return Simbolo
	 */
	public Character getSimboloEscritura(int pos) {
		return this.simbolosEscritura.get(pos);
	}

	/**
	 * Setter para un simbolo de escritura en una posicion del conjunto
	 * @param pos Posicion de insercion
	 * @param simboloEscritura Simbolo
	 */
	public void setSimboloEscritura(int pos, Character simboloEscritura) {
		this.simbolosEscritura.set(pos, simboloEscritura);
	}

	/**
	 * Getter para un movimiento de su conjunto	
	 * @param pos Posicion en la que se encuentra el movimiento
	 * @return Movimiento
	 */
	public Character getMovimiento(int pos) {
		return movimientos.get(pos);
	}

	/**
	 * Setter para un movimiento en una posicion del conjunto
	 * @param pos Posicion de insercion
	 * @param movimiento Movimiento
	 */
	public void setMovimiento(int pos, Character movimiento) {
		this.movimientos.set(pos, movimiento);
	}
}
