package MTsimple;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * Transicion.java - Clase para representar una transicion
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class Transicion {
	
	private Character simboloLectura; // Simbolo con el que se realiza la transicion	
	private Estado siguiente; // Estado de destino de la transicion
	private Character simboloEscritura; // Simbolo que se escribe en la cinta
	private Character movimiento; // Movimiento que realiza la cabeza de L/E
	
	public Transicion (Character simboloLectura, Estado siguiente, Character simboloEscritura, Character movimiento) {
		this.simboloLectura = simboloLectura;
		this.siguiente = siguiente;
		this.simboloEscritura = simboloEscritura;
		this.movimiento = movimiento;		
	}
	
	/**
	 * Funcion para mostrar una transicion
	 */
	public String toString () {
		return this.simboloLectura + " " + this.siguiente + " " + this.simboloEscritura + " " + this.movimiento;
	}
	
	// Getters and Setters
	
	public Character getSimboloLectura() {
		return simboloLectura;
	}

	public void setSimboloLectura(Character simboloLectura) {
		this.simboloLectura = simboloLectura;
	}

	public Estado getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(Estado siguiente) {
		this.siguiente = siguiente;
	}

	public Character getSimboloEscritura() {
		return simboloEscritura;
	}

	public void setSimboloEscritura(Character simboloEscritura) {
		this.simboloEscritura = simboloEscritura;
	}

	public Character getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Character movimiento) {
		this.movimiento = movimiento;
	}
}
