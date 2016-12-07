package projekt;

import java.util.ArrayList;
import java.lang.Math;

/**
 * An object to store the formulas chosen by the user and the corresponding answers and odds.
 * 
 * @author Heidi Koppel
 *
 */
public class QandA {

	ArrayList<String> questions;
	ArrayList<String> answers;
	ArrayList<Integer> odds;

	String theQuestion;
	String theAnswer;
	int theIndex;

	// Constructor
	public QandA(ArrayList<String> q, ArrayList<String> a, ArrayList<Integer> o) {
		questions = q;
		answers = a;
		odds = o;
	}
	
	// Adds the questions, answers and odds to the lists within the QandA
	public void add(String question, String answer, int odd) {
		questions.add(question);
		answers.add(answer);
		odds.add(odd);
	}
	
	// Method for choosing a random number to be used as an index considering the individual 
	//odds of each question
	public int randomIndex() {
		int sum = 0;
		for (int i: odds) {
			sum += i;
		}
		int random = (int)(Math.random() *sum);
		int newSum = odds.get(0);
		int index = 0;
		
		while (random >= newSum) {
			index += 1;
			newSum += odds.get(index);
		}
		return index;
	}

}
