package projekt;

import java.util.Arrays;

public class QandA {
	
	String[] questions;
	String[] answers;
	
	
	//Constructor
	public QandA(String[] q, String[] a) {
		questions = q;
		answers = a;
	}

	
	public String questionsToString() {
		return Arrays.toString(questions);	
	}
	
	public String answersToString() {
		return Arrays.toString(answers);
	}
	
	public String randomQuestion() {
		int rand = (int) (Math.random() * (questions.length + 1) );
		
		return questions[rand];
		
	}
}
