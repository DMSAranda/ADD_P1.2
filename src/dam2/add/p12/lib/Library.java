package dam2.add.p12.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import dam2.add.p12.dao.Pregunta;
import dam2.add.p12.dao.Respuesta;
import dam2.add.p12.dao.Usuario;
import dam2.add.p12.pdf.EscribirPDF;
import dam2.add.p12.pdf.EscribirPDF2;
import dam2.add.p12.xls.LeerXLS;
import dam2.add.p12.xml.EscribirXML;
import dam2.add.p12.xml.LeerXML;

public class Library {

	public void menuPrincipal(ArrayList<Usuario> listado) throws IOException { // muestra menu principal

		Scanner teclado = new Scanner(System.in);
		String opcion;
		int opcion2;

		do {
			do {

				System.out.println("Elige una opción:");
				System.out.println();
				System.out.println("1 - Jugar");
				System.out.println("2 - Añadir Pregunta");
				System.out.println("3 - Importar Preguntas");
				System.out.println("4 - Ver Records");
				System.out.println("5 - Instrucciones"); // instrucciones del juego Y sistema de puntuación
				System.out.println("0 - Salir");
				System.out.println();
				System.out.print("Opcion elegida: ");
				opcion = teclado.nextLine();
				System.out.println();
				
				try {
					opcion2 = Integer.parseInt(opcion);

					if (opcion2 < 0 || opcion2 > 5) {
						System.out.println("Introduce un valor entre 1 y 5.");
					}

				} catch (NumberFormatException excepcion) {

					System.out.println();
					System.out.println("Introduce un valor numérico.");
					System.out.println();
					opcion2 = -1;
				}


			} while (opcion2 < 0 || opcion2 > 5);

			switch (opcion2) {

			case 0:

				break;

			case 1:

				Usuario user = juego();

				guardarRecords(user);

				boolean mostrar = menuFinal(user);

				break;

			case 2:

				insertarPregunta();

				System.out.println();

				break;

			case 3:

				LeerXLS lector = new LeerXLS();

				LeerXML lector2 = new LeerXML();

				EscribirXML escritor = new EscribirXML();

				ArrayList<Pregunta> caja1 = lector.readXLS();

				ArrayList<Pregunta> caja2 = lector2.readXML();

				ArrayList<Pregunta> union = new ArrayList<Pregunta>();
				union.addAll(caja2);
				union.addAll(caja1);

				Ficheros archivo = new Ficheros();

				archivo.resetPreguntas();

				escritor.writeXML(union);

				break;

			case 4:

				imprimirRecords();

				System.out.println();

				break;

			case 5:

				EscribirPDF pdf = new EscribirPDF();

				pdf.writePDF();

				break;

			}

		} while (opcion2 != 0);

	}

	public Usuario juego() { // devuelve los aciertos

		Usuario user = new Usuario();

		LeerXML Preguntas = new LeerXML();

		ArrayList<Pregunta> listadoPreguntas = Preguntas.readXML();
		
		ArrayList<Respuesta> listadoRespuestas = new ArrayList<Respuesta>();

		Scanner teclado = new Scanner(System.in);
		String eleccion;
		int eleccion2;
		int aciertos = 0;
		int fallos = 0;

		try {

			for (int i = 0; i < listadoPreguntas.size(); i++) {

				Pregunta question = listadoPreguntas.get(i);
				
				Respuesta answer = new Respuesta();
				
				
				System.out.print(question.getQuestion() + "\n");
				System.out.println();
				System.out.print("1 - " + question.getAnswer1() + "\n");
				System.out.print("2 - " + question.getAnswer2() + "\n");
				System.out.print("3 - " + question.getAnswer3() + "\n");
				System.out.println();

				do {
					System.out.print("Elige tu respuesta: ");
					eleccion = teclado.nextLine();

					try {
						eleccion2 = Integer.parseInt(eleccion);

						if (eleccion2 < 1 || eleccion2 > 3) {
							System.out.println("Introduce un valor entre 1 y 3.");
						}

					} catch (NumberFormatException excepcion) {

						System.out.println();
						System.out.println("Introduce un valor numérico.");
						System.out.println();
						eleccion2 = 0;
					}

				} while (eleccion2 < 1 || eleccion2 > 3);

				System.out.println();
				
				answer.setQuestion(question.getQuestion());
				answer.setAnswer(eleccion);
				answer.setCorrect(question.getCorrect());
				listadoRespuestas.add(answer);						//COMO DEVOLVER EL USUARIO Y EL ARRAY DE RESPUESTAS?????????????

				if (eleccion.equals(question.getCorrect())) {
					aciertos++;
					if (aciertos == 1) {
						System.out.print("Correcto, has conseguido " + aciertos + " acierto \n");
					} else {
						System.out.print("Correcto, has conseguido " + aciertos + " aciertos \n");
					}
					System.out.println();
				} else {
					if (aciertos == 1) {
						System.out.print("Has fallado, la respuesta correcta es la " + question.getCorrect()
								+ ". Hasta ahora llevas " + aciertos + " acierto. \n");
					} else {
						System.out.print("Has fallado, la respuesta correcta es la " + question.getCorrect()
								+ ". Hasta ahora llevas " + aciertos + " aciertos. \n");
					}

					System.out.println();
				}
			}

			user.setNombre(estableceNombre());
			user.setApellidos(estableceApellidos());
			user.setAciertos(Integer.toString(aciertos));

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

		return user;

	}

	public String estableceNombre() {

		String nombre;
		boolean correcto = false;
		Scanner teclado = new Scanner(System.in);

		do {
			System.out.print("Introduce nombre: ");
			nombre = teclado.nextLine();
			if (isNumeric(nombre) == true) {
				correcto = false;
			} else {
				// nombre = formatoNombre(nombre); para evitar mayusculas en la primera letra
				correcto = true;
			}
		} while (correcto != true);

		return nombre;
	}

	public String estableceApellidos() {

		String apellido;
		boolean correcto = false;
		Scanner teclado = new Scanner(System.in);

		do {
			System.out.print("Introduce apellidos: ");
			apellido = teclado.nextLine();
			if (isNumeric(apellido) == true) {
				correcto = false;
			} else {
				// apellido = formatoNombre(apellido); para evitar mayusculas en la primera
				// letra
				correcto = true;
			}
		} while (correcto != true);

		return apellido;

	}

	public Pregunta agregarPregunta() {

		boolean numerico = false;

		Pregunta trivi = new Pregunta();
		Scanner teclado = new Scanner(System.in);

		System.out.print("Introduce pregunta: ");
		trivi.setQuestion(teclado.nextLine());

		System.out.print("Introduce respuesta1: ");
		trivi.setAnswer1(teclado.nextLine());

		System.out.print("Introduce respuesta2: ");
		trivi.setAnswer2(teclado.nextLine());

		System.out.print("Introduce respuesta3: ");
		trivi.setAnswer3(teclado.nextLine());

		do {
			try {
				System.out.println();
				System.out.print("Introduce correcta: ");
				trivi.setCorrect(teclado.nextLine());
				Integer.parseInt(trivi.getCorrect());
				numerico = true;

			} catch (NumberFormatException excepcion) {

				System.out.println();
				System.out.println("Introduce un valor numérico.");
				numerico = false;
			}

		} while (numerico != true);

		return trivi;
	}

	public void insertarPregunta() {

		Pregunta trivi = agregarPregunta();

		ArrayList<Pregunta> aux = new ArrayList<Pregunta>();

		EscribirXML escribir = new EscribirXML();

		LeerXML leer = new LeerXML();

		aux = leer.readXML();

		aux.add(trivi);

		escribir.writeXML(aux);

	}

	public static String formatoNombre(String cadena) {

		String[] separadaPorEspacios = cadena.split(" ");
		StringBuilder sb = new StringBuilder();

		for (int indice = 0; indice < separadaPorEspacios.length; indice++) {
			String palabra = separadaPorEspacios[indice];

			// De la palabra, primero agregar la primera letra ya convertida a mayúscula
			char primeraLetra = palabra.charAt(0);
			sb.append(Character.toUpperCase(primeraLetra));

			// Luego agregarle "lo sobrante" de la palabra
			sb.append(palabra.substring(1));

			// Y si no es el último elemento del arreglo, le añadimos un espacio
			if (indice < separadaPorEspacios.length - 1) {
				sb.append(" ");
			}
		}
		// Finalmente regresamos la cadena
		return sb.toString();
	}

	public boolean comprobarUsuarios(Usuario user, ArrayList<Usuario> listado) throws IOException { // para usuario y
																									// clave del login

		boolean existe = false;

		for (Usuario var : listado) {

			if (user.getNombre().equalsIgnoreCase(var.getNombre()) && user.getApellidos().equals(var.getApellidos()))
				existe = true;

		}

		return existe;
	}

	public ArrayList<Usuario> leerUsuarios() throws IOException {
		// metodo que devuelve un array de usuarios

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String entrada = "./ficheros/records.txt";

		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(entrada)));

			String linea = br.readLine();

			// se lee linea a linea
			// si es null es que ha llegado al final del fichero

			while (linea != null) {
				// se lee cada linea, separando en 3 string nombre y pass y metiendolo en
				// objeto usuario
				Usuario user = new Usuario();

				String[] partes = linea.split(":");
				String name = partes[0];
				String surname = partes[1];
				String hits = partes[2];

				user.setNombre(name);
				user.setApellidos(surname);
				user.setAciertos(hits);

				lista.add(user);

				linea = br.readLine(); // luego se agrega al array
			}

			br.close();

		} catch (IOException errorDeFichero) {
			System.out.println("Ha habido problemas: " + errorDeFichero.getMessage());
		} catch (Exception e) {
			System.out.println("Ha habido problemas: " + e.getMessage());
		} finally {

		}

		return lista; // se devuelve

	}

	public void escribirUsuarios(ArrayList<Usuario> aux) throws IOException { // metodo que pisa el fichero
		// reescribiendo con una lista

		String salida = "./ficheros/records.txt";

		BufferedWriter bw = null;

		try {

			bw = new BufferedWriter(new FileWriter(new File(salida)));

			/*
			 * Iterator<Usuario> it = lista2.iterator();
			 * 
			 * while (it.hasNext()) { bw.write(it.next().getNombre() + ":" +
			 * it.next().getPassword() + ":" + it.next().isBloqueado() + "\n"); //
			 * escribimos // nombre:clave:bloqueado }
			 */

			for (int i = 0; i < aux.size(); i++) {

				Usuario user = aux.get(i);

				bw.write(user.getNombre() + ":" + user.getApellidos() + ":" + user.getAciertos() + "\n");

			}

		} catch (IOException errorDeFichero) {
			System.out.println("Ha habido problemas: " + errorDeFichero.getMessage());
		} finally {
			try {

				bw.close();
			} catch (IOException e) {
// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void imprimirRecords() throws IOException {

		ArrayList<Usuario> aux = new ArrayList<Usuario>();

		aux = leerUsuarios();

		Collections.sort(aux, new Comparador());

		try {

			for (int i = 0; i < aux.size(); i++) {

				Usuario user = aux.get(i);

				System.out.print(
						user.getNombre() + " " + user.getApellidos() + " - Aciertos: " + user.getAciertos() + "\n");

			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

	}

	public void guardarRecords(Usuario user) throws IOException {

		ArrayList<Usuario> aux = leerUsuarios();

		boolean existe = comprobarUsuarios(user, aux);

		try {

			if (existe == true) {

				for (int i = 0; i < aux.size(); i++) {

					Usuario user2 = aux.get(i);

					if (user.getNombre().equalsIgnoreCase(user2.getNombre())
							&& user.getApellidos().equalsIgnoreCase(user2.getApellidos())) {

						if (Integer.parseInt(user.getAciertos()) > Integer.parseInt(user2.getAciertos())) {

							user2.setAciertos(user.getAciertos());
							System.out.println();
							System.out.println("Nuevo Record guardado.");
						}

						else if (user2.getAciertos().equals("3")) {
							System.out.println();
							System.out.println("Tienes el máximo de aciertos");
						}

						else {
							System.out.println();
							System.out.println("No has batido tu record.");
						}

					}

				}
			} else {
				aux.add(user);
				System.out.println();
				System.out.println("Nuevo record guardado.");
			}

			System.out.println();

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

		escribirUsuarios(aux);

	}

	public static boolean isNumeric(String cadena) {

		boolean resultado;
		// metodo que comprueba si una cadena es numerica
		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

	public static boolean esDecimal(String cadena) {
		// metodo que comprueba si una cadena es decimal
		boolean resultado;

		try {
			Double.parseDouble(cadena);
			resultado = true;

		} catch (NumberFormatException nfe) {
			resultado = false;
		}

		return resultado;
	}

	public boolean menuFinal(Usuario user) throws IOException {

		boolean respuesta = false;
		Scanner teclado = new Scanner(System.in);
		String opcion;
		int opcion2;
				
		do {
			System.out.println();
			System.out.print("¿Quieres ver un informe con los resultados?: ");
			System.out.print("1 - Si");
			System.out.print("2 - No");
			opcion = teclado.nextLine();
			
			try {
				opcion2 = Integer.parseInt(opcion);

				if (opcion2 < 1 || opcion2 > 2) {
					System.out.println("Introduce un valor entre 1 y 2.");
				}

			} catch (NumberFormatException excepcion) {

				System.out.println();
				System.out.println("Introduce un valor numérico.");
				System.out.println();
				opcion2 = 0;
			}
			
			if (opcion2 == 1) {
				
				EscribirPDF2 pdf = new EscribirPDF2();
				
				pdf.writePDF(user);
				
				
				
			}
 
			
		}while(opcion2 != 2);
		
		return respuesta;
	}
	
	

}
