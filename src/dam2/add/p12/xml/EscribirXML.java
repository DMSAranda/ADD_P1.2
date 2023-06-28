package dam2.add.p12.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import dam2.add.p12.dao.Pregunta;

public class EscribirXML {

	public void writeXML(ArrayList<Pregunta> preguntas) {

		String docNuevoStr = "";

		try {

			Document docNuevo = new Document();

			Element nodoRaiz = new Element("juego");
			docNuevo.addContent(nodoRaiz);

			try {

				for (int i = 0; i < preguntas.size(); i++) {

					Pregunta question = preguntas.get(i);

					Element nodoPregunta = new Element("pregunta");
					nodoRaiz.addContent(nodoPregunta);

					Element nodoTexto = new Element("texto");
					nodoPregunta.addContent(nodoTexto);

					Element nodoRespuesta1 = new Element("respuesta1");
					nodoPregunta.addContent(nodoRespuesta1);

					Element nodoRespuesta2 = new Element("respuesta2");
					nodoPregunta.addContent(nodoRespuesta2);

					Element nodoRespuesta3 = new Element("respuesta3");
					nodoPregunta.addContent(nodoRespuesta3);

					Element nodoCorrecta = new Element("correcta");
					nodoPregunta.addContent(nodoCorrecta);

					nodoTexto.setText(question.getQuestion());
					nodoRespuesta1.setText(question.getAnswer1());
					nodoRespuesta2.setText(question.getAnswer2());
					nodoRespuesta3.setText(question.getAnswer3());
					nodoCorrecta.setText(question.getCorrect());
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Datos fuera de límites");
			}

			// Vamos a serializar el XML

			Format format = Format.getPrettyFormat();

			// Creamos el serializador con el formato deseado
			XMLOutputter xmloutputter = new XMLOutputter(format);

			// Serializamos nuestro nuevo document
			docNuevoStr = xmloutputter.outputString(docNuevo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Para llevar el XML a fichero

		FileWriter fichero = null;
		try {
			fichero = new FileWriter("./ficheros/preguntas.xml");
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(docNuevoStr);
			fichero.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No existe el fichero");
		}
	}
}
