package dam2.add.p12.xls;

import java.io.File;
import java.util.ArrayList;

import dam2.add.p12.dao.Pregunta;
import jxl.Sheet;
import jxl.Workbook;

public class LeerXLS {

	public ArrayList<Pregunta> readXLS() {

		File fichero = new File("./ficheros/preguntas.xls");

		ArrayList<Pregunta> Preguntas = new ArrayList<Pregunta>();

		try {
			Workbook w = Workbook.getWorkbook(fichero);

			// Se lee la primera hoja de la excel
			Sheet sheet = w.getSheet(0);

			for (int f = 0; f < sheet.getRows(); f++) {

				Pregunta question = new Pregunta();

				question.setQuestion(sheet.getCell(0, f).getContents());
				question.setAnswer1(sheet.getCell(1, f).getContents());
				question.setAnswer2(sheet.getCell(2, f).getContents());
				question.setAnswer3(sheet.getCell(3, f).getContents());
				question.setCorrect(sheet.getCell(4, f).getContents());

				Preguntas.add(question);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Preguntas;
	}

}
