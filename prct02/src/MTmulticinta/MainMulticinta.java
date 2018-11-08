package MTmulticinta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Practica 2 - Maquina de Turing <p>
 * MainMulticinta.java - Clase para ejecutar el simulador de una maquina de Turing que acepta multicinta
 * @author Jesus Ramos Alvarez - alu0100904932@ull.edu.es 
 */
public class MainMulticinta {
	
	private static final int SALIR = 4; // Constante con la opcion del menu para salir del programa
	
	/**
	 * Metodo principal del programa
	 * @param args Argumentos que recibe el programa
	 * @throws IOException Excepcion para el manejo de errores I/O
	 */
	public static void main (String [] args) throws IOException {
		
		MaquinaTuringMulticinta maquinaTuringMulti = new MaquinaTuringMulticinta ();
		int opcion;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Menu
		do {		
			System.out.println();
			System.out.println("Simulador de máquinas de Turing");
			System.out.println("Elija una opción:");
			System.out.println("1.- Cargar una máquina de Turing por fichero");
			System.out.println("2.- Comprobar una cadena");
			System.out.println("3.- Mostrar máquina de Turing");
			System.out.println("4.- Salir");
			opcion = Integer.parseInt(br.readLine());	
			
			switch (opcion) {
				case 1: try {
							System.out.println("Introduzca el nombre del fichero");
							String nombreFichero = br.readLine();
							maquinaTuringMulti = new MaquinaTuringMulticinta (nombreFichero);
						}
						catch (FileNotFoundException e) {
							e.printStackTrace();
							System.err.println("El archivo debe estar en la carpeta docs");		
						}						
						break;
				case 2: if (!maquinaTuringMulti.existe()) {
							System.out.println("Es necesario cargar una máquina de Turing");
						}
						else {
							System.out.print("Introduzca una cadena: ");
							String cadena = br.readLine();
							boolean aceptada = maquinaTuringMulti.analizarEntrada(cadena);
							if (aceptada)
								System.out.println("La entrada es aceptada");
							else
								System.out.println("La entrada NO es aceptada");
						}
						break;
				case 3: if (!maquinaTuringMulti.existe()) {
							System.out.println("Es necesario cargar una máquina de Turing");
						}
						else
							System.out.println(maquinaTuringMulti); break;
				case 4: break;
				default: System.out.println("Opción incorrecta"); break;
			}
		} while (opcion != SALIR);
		
		br.close();
	}
}