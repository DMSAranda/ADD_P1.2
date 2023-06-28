package dam2.add.p12;

import java.io.IOException;
import java.util.ArrayList;

import dam2.add.p12.dao.Usuario;
import dam2.add.p12.lib.Ficheros;
import dam2.add.p12.lib.Library;

class App {

	public static void main(String... args) throws IOException {

		Ficheros fichero = new Ficheros();

		fichero.crearFicheros();

		Library libreria = new Library();

		ArrayList<Usuario> listado = new ArrayList<Usuario>();

		libreria.menuPrincipal(listado);
		
		//PRUEBA2

	}

}