/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam2.add.p12.lib;

import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.File;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */

public class Ficheros {

	static File records = new File("./ficheros/records.txt");
	static File preguntas = new File("./ficheros/preguntas.xml");

	DataInputStream leer;
	DataOutputStream escribir;

	public void crearFicheros() throws IOException {

		if (records.exists()) {

		} else {
			try {
				records.createNewFile();

			} catch (IOException ex) {
				Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	public void resetPreguntas() {

		if (preguntas.exists()) {

			preguntas.delete();
			
			try {
				preguntas.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}

}
