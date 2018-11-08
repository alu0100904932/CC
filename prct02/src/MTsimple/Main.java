package MTsimple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static final int SALIR = 3;
	
	public static void main (String [] args) throws IOException {
		
		MaquinaTuring maquinaTuring = new MaquinaTuring ();
		int opcion;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do {		
			System.out.println("Simulador de máquinas de Turing");
			System.out.println("Elija una opción:");
			System.out.println("1.- Cargar una máquina de Turing por fichero");
			System.out.println("2.- Comprobar una cadena");
			System.out.println("3.- Salir");
			opcion = Integer.parseInt(br.readLine());	
			
			switch (opcion) {
				case 1: try {
							System.out.println("Introduzca el nombre del fichero");
							String nombreFichero = br.readLine();
							maquinaTuring = new MaquinaTuring (nombreFichero);
						}
						catch (FileNotFoundException e) {
							e.printStackTrace();
							System.err.println("El archivo debe estar en la carpeta docs");		
						}						
						break;
				case 2: if (!maquinaTuring.existe()) {
							System.out.println("Es necesario cargar una máquina de Turing");
						}
						else {
							System.out.print("Introduzca una cadena: ");
							String cadena = br.readLine();
							//System.out.print("¿Mostrar la traza? (Si/No): ");
							//String respuesta = br.readLine();
							//if (respuesta.equals("Si"))
								//maquinaTuring.setTraza(true);
							boolean aceptada = maquinaTuring.analizarEntrada(cadena);
							if (aceptada)
								System.out.println("La entrada es aceptada");
							else
								System.out.println("La entrada NO es aceptada");
						}
						break;
				case 3: break;
				default: System.out.println("Opción incorrecta"); break;
			}
		} while (opcion != SALIR);
		
		br.close();
	}
}