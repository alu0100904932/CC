package MTsimple;

import java.util.ArrayList;
import java.util.Iterator;

public class Estado {	
	
	private String nombre;
	private boolean inicial;
	private boolean estadoFinal;
	private ArrayList<Transicion> transiciones;
	
	public Estado(String nombre) {
		this.nombre = nombre;
		this.inicial = false;
		this.estadoFinal = false;
		this.transiciones = new ArrayList<Transicion>();
	}
	
	public void addTransicion (Transicion transicion) {
		this.transiciones.add(transicion);
	}
	
	public Transicion buscarTransicion (Character simbolo) {
		Iterator<Transicion> it = this.transiciones.iterator();
		while (it.hasNext()) {
			Transicion transicion = it.next();
			if (transicion.getSimboloLectura().equals(simbolo))
				return transicion;
		}
		return null;
	}
	
	public int numTransiciones () {
		return this.transiciones.size();
	}
	
	public Transicion getTransicion (int numero) {
		return this.transiciones.get(numero);
	}
	
	public void mostrarTransiciones () {
		for (int i = 0; i < this.transiciones.size(); i++) {
			System.out.println(this.nombre + " " + this.transiciones.get(i));
		}
	}
	
	public String toString () {
		return this.nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean esInicial() {
		return inicial;
	}

	public void setInicial() {
		this.inicial = true;
	}
	
	public boolean esFinal() {
		return this.estadoFinal;
	}

	public void setFinal() {
		this.estadoFinal = true;
	}

	/*public ArrayList<Transicion> buscarTransiciones(String simbolo, String simboloPop) {
		ArrayList<Transicion> transicionesPosibles = new ArrayList<Transicion>();
		Iterator<Transicion> it = this.transiciones.iterator();
		while (it.hasNext()) {
			Transicion transicion = it.next();
			if ((transicion.getSimbolo().equals(simbolo) || transicion.getSimbolo().equals(EPSILON)) && transicion.getSimboloPop().equals(simboloPop))
				transicionesPosibles.add(transicion);
		}
		return transicionesPosibles;		
	}*/

}
