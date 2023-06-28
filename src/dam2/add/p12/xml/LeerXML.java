package dam2.add.p12.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import dam2.add.p12.dao.Pregunta;

public class LeerXML {					//DUDA PARA UTF-8 DEL EXCEL??????????????
	

	public ArrayList<Pregunta> readXML() {

		// Se crea un SAXBuilder para poder parsear el archivo

		SAXBuilder builder = new SAXBuilder();

		File xmlFile = new File("./ficheros/preguntas.xml");

		ArrayList<Pregunta> Preguntas = new ArrayList<Pregunta>();

		try {

			// Se crea el documento a traves del archivo
			Document document = builder.build(xmlFile);

			// Se obtiene la raiz
			Element rootNode = document.getRootElement();

			// Se obtiene la lista de hijos del nodo raiz

			List<Element> lista_preguntas = rootNode.getChildren("pregunta");

			for (int i = 0; i < lista_preguntas.size(); i++) {

				Pregunta x = new Pregunta();

				Element pregunta = (Element) lista_preguntas.get(i);

				String question = pregunta.getChildText("texto");

				String answer1 = pregunta.getChildText("respuesta1");

				String answer2 = pregunta.getChildText("respuesta2");

				String answer3 = pregunta.getChildText("respuesta3");

				String correct = pregunta.getChildText("correcta");

				x.setQuestion(question);
				x.setAnswer1(answer1);
				x.setAnswer2(answer2);
				x.setAnswer3(answer3);
				x.setCorrect(correct);

				Preguntas.add(x);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Preguntas;
	}

}
