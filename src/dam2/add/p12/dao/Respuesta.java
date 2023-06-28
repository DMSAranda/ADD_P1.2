package dam2.add.p12.dao;

public class Respuesta {
	
	private String question;

	private String answer;
	
	private String correct;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Respuesta() {
		super();
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public Respuesta(String question, String answer, String correct) {
		super();
		this.question = question;
		this.answer = answer;
		this.correct = correct;
	}
	
	

}
