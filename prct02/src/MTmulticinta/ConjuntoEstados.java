package MTmulticinta;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * ConjuntoEstados.java - Clase para representar el conjunto de estados de una maquina de Turing que acepta multicinta
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class ConjuntoEstados extends ArrayList<Estado> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor del conjunto de estados, usa el mismo que ArrayList
	 */
	public ConjuntoEstados () {
		super();
	}
	
	/**
	 * Metodo para comprobar si, a partir de su nombre, un estado pertenece a un conjunto de estados
	 * @param nombre Nombre del estado
	 * @return true en caso de que el estado pertenezca al conjunto
	 */
	public boolean contiene (String nombre) {
		Iterator<Estado> it = this.iterator();
		while (it.hasNext()) {
			if (it.next().getNombre().equals(nombre))
				return true;
		}
		return false;
	}
	
	/**
	 * Metodo para obtener un estado del conjunto a partir de su nombre
	 * @param nombre Nombre del estado
	 * @return Estado a obtener o null en caso de que no exista alguno con el nombre
	 */
	public Estado buscarEstado (String nombre) {
		Iterator<Estado> it = this.iterator();
		while (it.hasNext()) {
			Estado aux = it.next();
			if (aux.getNombre().equals(nombre))
				return aux;
		}
		return null;
	}

	/**
	 * Metodo para obtener el estado inicial de un conjunto de estados
	 * @return El estado inicial o null en caso de que no exista
	 */
	public Estado getEstadoInicial() {
		Iterator<Estado> it = this.iterator();
		while (it.hasNext()) {
			Estado aux = it.next();
			if (aux.esInicial())
				return aux;
		}
		return null;
	}
}
