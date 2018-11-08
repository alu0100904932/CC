package MTsimple;

import java.util.ArrayList;
import java.util.Iterator;

public class ConjuntoEstados extends ArrayList<Estado> {

	private static final long serialVersionUID = 1L;
	
	public ConjuntoEstados () {
		super();
	}
	
	public boolean contiene (String nombre) {
		Iterator<Estado> it = this.iterator();
		while (it.hasNext()) {
			if (it.next().getNombre().equals(nombre))
				return true;
		}
		return false;
	}
	
	public Estado buscarEstado (String nombre) {
		Iterator<Estado> it = this.iterator();
		while (it.hasNext()) {
			Estado aux = it.next();
			if (aux.getNombre().equals(nombre))
				return aux;
		}
		return null;
	}

	public Estado getEstadoInicial() {
		Iterator<Estado> it = this.iterator();
		while (it.hasNext()) {
			Estado aux = it.next();
			if (aux.esInicial())
				return aux;
		}
		return null;
	}
	
	public Estado getEstadoFinal() {
		Iterator<Estado> it = this.iterator();
		while (it.hasNext()) {
			Estado aux = it.next();
			if (aux.esFinal())
				return aux;
		}
		return null;
	}
}
