package MTmulticinta;

import java.util.ArrayList;
/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * Cinta.java - Clase para representar una cinta de una maquina de Turing que acepta multicinta
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class Cinta extends ArrayList<Character> {

	private static final long serialVersionUID = 1L;
	private CabezaLE cabezaLE; // Cabeza de lectura/escritura de la cinta
	
	/**
	 * Constructor de la cinta
	 */
	public Cinta () {
		super();
		this.cabezaLE = new CabezaLE();
	}

	/**
	 * Getter de la cabeza de L/E de la cinta
	 * @return Cabeza L/E de la cinta
	 */
	public CabezaLE getCabezaLE() {
		return cabezaLE;
	}

	/**
	 * Setter de la cabeza de L/E de la cinta
	 * @param cabezaLE Cabeza L/E para la cinta
	 */
	public void setCabezaLE(CabezaLE cabezaLE) {
		this.cabezaLE = cabezaLE;
	}

}
