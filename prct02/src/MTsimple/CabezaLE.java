package MTsimple;

import java.util.ArrayList;

public class CabezaLE {
	
	private static final Character DERECHA = 'R';
	private static final Character IZQUIERDA = 'L';
	private static final Character PARAR = 'S';
	
	private int posicion;
	private ArrayList<Character> movimientosPermitidos;
	
	public CabezaLE () {
		this.posicion = 1;
		this.movimientosPermitidos = new ArrayList<Character>();
		this.movimientosPermitidos.add(DERECHA);
		this.movimientosPermitidos.add(IZQUIERDA);
		this.movimientosPermitidos.add(PARAR);
	}
	
	public void mover (Character movimiento) {
		if (movimiento.equals(DERECHA))
			this.posicion++;
		else if (movimiento.equals(IZQUIERDA))
			this.posicion--;
	}
	
	public void moverAInicio () {
		this.posicion = 1;
	}
	
	public boolean esMovPermitido (Character movimiento) {
		if (this.movimientosPermitidos.contains(movimiento))
			return true;
		return false;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
}
