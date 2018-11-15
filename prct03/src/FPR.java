/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 3 - Funciones primitivas recursivas (FPR) <p>
 * FPR.java - Definicion de FPRs y calculo de la potencia de dos numeros introducidos como parametros del programa
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class FPR {
	
	private static final int PARAMETROS = 2;
	
	/**
	 * FPR para la potencia
	 * @param x Base
	 * @param y Exponente
	 * @return Resultado
	 */
	public static int potencia (int x, int y) {
		if (y == 0)
			return 1;
		else {
			return multiplicacion(potencia(x, y - 1), x);
		}
	}
	
	/**
	 * FPR para la multiplicacion
	 * @param x Multiplicando
	 * @param y Multiplicador
	 * @return Resultado
	 */
	public static int multiplicacion(int x, int y) {
		if (y == 0)
			return zero(x);
		else {
			return suma(multiplicacion(x, y - 1), x);
		}
	}

	/**
	 * FPR para la suma
	 * @param x Sumando
	 * @param y Sumando
	 * @return Resultado
	 */
	public static int suma(int x, int y) {
		if (y == 0)
			return x;
		else
			return sucesor(suma(x, y - 1));
	}

	/**
	 * FPR para el sucesor
	 * @param x Numero
	 * @return Sucesor del numero
	 */
	public static int sucesor(int x) {
		return x + 1;
	}

	/**
	 * FPR zero
	 * @param x Numero
	 * @return Cero
	 */
	public static int zero(int x) {
		return 0;
	}
	
	/**
	 * Calculo de la potencia a partir de dos numeros parametros del programa.
	 * Muestra el resultado por consola.
	 * @param args Base y exponente de la potencia a calcular
	 */
	public static void main(String[] args) {
		if (args.length != PARAMETROS) {
			System.err.println("El número de parámetros introducidos es incorrecto");
			System.exit(-1);
		}
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		System.out.println("Cálculo de potencia como una función primitiva recursiva:");
		System.out.println();
		System.out.println("\t" + x + "^" + y + " = " + potencia(x, y));
	}

}
