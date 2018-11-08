package MTmulticinta;

import java.util.ArrayList;
/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * CabezaLE.java - Clase para representar la cabeza de L/E de una cinta de una maquina de Turing
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class CabezaLE {
	
	private static final Character DERECHA = 'R'; // Constante para definir el movimiento hacia la derecha
	private static final Character IZQUIERDA = 'L'; // Constante para definir el movimiento hacia la izquierda
	private static final Character PARAR = 'S'; // Constante para definir el movimiento de parada
	
	private int posicion; // Posicion de la cinta
	private ArrayList<Character> movimientosPermitidos; // Lista de movimientos permitidos por la cinta
	
	/**
	 * Constructor de la cabeza de L/E
	 */
	public CabezaLE () {
		this.posicion = 1;
		this.movimientosPermitidos = new ArrayList<Character>();
		this.movimientosPermitidos.add(DERECHA);
		this.movimientosPermitidos.add(IZQUIERDA);
		this.movimientosPermitidos.add(PARAR);
	}
	
	/**
	 * Metodo para mover la cabeza de L/E
	 * @param movimiento Movimiento que debe realizar la cabeza de L/E
	 */
	public void mover (Character movimiento) {
		if (movimiento.equals(DERECHA))
			this.posicion++;
		else if (movimiento.equals(IZQUIERDA))
			this.posicion--;
	}
	
	/**
	 * Metodo para mover al inicio de una cinta su cabeza de L/E <p>
	 * En caso de que la cinta contenga algun elemento a parte del blanco
	 * la posicion inicial sera la de su primer elemento.
	 * @param cinta Cinta que tiene la cabeza de L/E a mover
	 */
	public static void moverAInicio (Cinta cinta) {
		if (cinta.size() > 1)
			cinta.getCabezaLE().setPosicion(1);
		else
			cinta.getCabezaLE().setPosicion(0);
	}
	
	/**
	 * Metodo que comprueba si un movimiento es permitido por la cabeza de L/E
	 * @param movimiento Movimiento a comprobar
	 * @return true si el movimiento es permitido
	 */
	public boolean esMovPermitido (Character movimiento) {
		if (this.movimientosPermitidos.contains(movimiento))
			return true;
		return false;
	}

	/**
	 * Getter de la posicion actual de la cabeza de L/E
	 * @return Posicion actual de la cabeza de L/E
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * Setter de la posicion para la cabeza de L/E
	 * @param posicion Posicion para la cabeza de L/E
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
}
