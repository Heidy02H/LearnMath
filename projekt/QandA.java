package projekt;

import java.util.ArrayList;
import java.lang.Math;

public class QandA {

	ArrayList<String> questions;
	ArrayList<String> answers;

	String theQuestion;
	String theAnswer;

	// Constructor
	public QandA(ArrayList<String> q, ArrayList<String> a) {
		questions = q;
		answers = a;
	}

	public void add(String question, String answer) {
		questions.add(question);
		answers.add(answer);
	}

	public String getQuestion(int n) {
		return questions.get(n);
	}

	// Random index of the questions in QandA
	public int randomNumber() {
		return (int) (Math.random() * (questions.size()));
	}

}
