package projekt;

import java.util.ArrayList;
import java.lang.Math;

/**
 * An object to store the formulas chosen by the user and the corresponding
 * answers and odds.
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

	/**
	 * Constructor for QandA object which stores questions and corresponding
	 * answers and odds.
	 * 
	 * @param q
	 *            List of questions
	 * @param a
	 *            List of answers
	 * @param o
	 *            List of odds
	 */
	public QandA(ArrayList<String> q, ArrayList<String> a, ArrayList<Integer> o) {
		questions = q;
		answers = a;
		odds = o;
	}

	/**
	 * Method for adding questions, answers and odds to the corresponding lists.
	 * 
	 * @param question
	 *            A question you want to add as a String
	 * @param answer
	 *            An answer you want to add as a String
	 * @param odd
	 *            An odd you want to add as an integer.
	 */
	public void add(String question, String answer, int odd) {
		questions.add(question);
		answers.add(answer);
		odds.add(odd);
	}

	/**
	 * Method for choosing a random number to be used as an index considering
	 * the individual odds of each question
	 * 
	 * @return index
	 */
	public int randomIndex() {
		int sum = 0;
		for (int i : odds) {
			sum += i;
		}
		int random = (int) (Math.random() * sum);
		int newSum = odds.get(0);
		int index = 0;

		while (random >= newSum) {
			index += 1;
			newSum += odds.get(index);
		}
		return index;
	}
}
