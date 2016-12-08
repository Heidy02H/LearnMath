package projekt;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This program helps the user learn math formulas. The user can choose which
 * formulas they want to learn.
 * 
 * The formulas that the users answers correctly will be displayed more and more
 * infrequently and will disappear after 5 correct answers.
 * 
 * A formula that is answered incorrectly will start appearing more frequently.
 * 
 * If the user is on a winning streak of 10 correct answers in a row he gets a
 * prize in the form of a funny/motivational alert.
 * 
 * If the user continues to answer all formulas correctly the game will end with
 * an congratulatory message.
 * 
 * I used the series of tutorials about the basics of JavaFX that can be found
 * here: https://www.youtube.com/watch?v=FLkOX4Eez6o&t=10s
 * 
 * @author Heidi Koppel
 */

public class Main extends Application {

	// Declaring variables that can also be used in other classes.
	static Stage window;
	static Scene scene1, scene2, scene3;
	static Button button1, button2, button3, button4;
	static TextField answer;
	static boolean status;
	static int rightCount = 0;
	static int streak = 0;

	// Creates QandA instance to store the selected formulas answers and odds.
	static ArrayList<String> questions = new ArrayList<String>();
	static ArrayList<String> answers = new ArrayList<String>();
	static ArrayList<Integer> odds = new ArrayList<Integer>();
	static QandA choice = new QandA(questions, answers, odds);

	// Creates CheckBoxes
	static CheckBox box1 = new CheckBox("Tuletised");
	static CheckBox box2 = new CheckBox("Trigonomeetria põhivalemid");
	static CheckBox box3 = new CheckBox("3-ga korrutamine");
	static CheckBox box4 = new CheckBox("Test");

	/**
	 * Starts the program
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	/**
	 * Starts Java by calling first scene
	 * 
	 * @param primaryStage
	 *            The stage
	 * @throws Exception
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		createNewScene1(primaryStage);
	}

	/**
	 * Creates the first scene
	 * 
	 * @param primaryStage
	 */
	public static void createNewScene1(Stage primaryStage) {
		window = primaryStage;

		// Title for scene 1
		window.setTitle("Õpime valemeid");

		// Instruction in scene 1
		Label instruction = new Label("Palun vali milliseid valemeid soovid õppida.");

		// Creates button for selecting the formulas which the user wants to
		// learn and loads them into the game.
		button1 = new Button();
		button1.setText("Alusta");
		button1.setOnAction(e -> Actions.clickBeginButton());

		// Creates Layout for first scene and adds the elements to it
		VBox layout1 = new VBox(10);
		layout1.setPadding(new Insets(10));
		layout1.getChildren().addAll(instruction, box1, box2, box3, box4, button1);

		// Creates first scene
		scene1 = new Scene(layout1, 450, 350);
		scene1.getStylesheets().add("file:///Users/heidikoppel/Documents/GitHub/Project/projekt/Form.css");

		// Starts the first scene
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	/**
	 * Method for creating scene number 2 with info from the first scene. Idea
	 * to create the new scene in a method:
	 * http://stackoverflow.com/questions/32940574/how-do-i-transfer-data-from-one-scene-to-another-in-javafx
	 */
	public static void createNewScene2() {

		// Creates the label that displays the question
		Label question = new Label();
		question.getStyleClass().add("label-question");
		question.setText(choice.theQuestion);

		// Creates the text field for the user's answer
		answer = new TextField();
		answer.setMaxSize(200, 150);

		// Creates button for submitting and validating the answer
		button2 = new Button();
		button2.setText("Vasta");
		button2.setOnAction(e -> Actions.clickValidateButton());

		// Creates button for Exiting the program
		button3 = new Button();
		button3.setText("Lõpeta");
		button3.setOnAction(e -> Actions.clickCloseButton());

		// Layout for second scene
		VBox layout2 = new VBox(10);
		layout2.setPadding(new Insets(10));
		layout2.setAlignment(Pos.CENTER);
		layout2.getChildren().addAll(question, answer, button2, button3);

		// Creates second scene
		scene2 = new Scene(layout2, 450, 350);
		scene2.getStylesheets().add("file:///Users/heidikoppel/Documents/GitHub/Project/projekt/Form.css");
	}

	/**
	 * Method that creates scene number 3 with info from the second scene
	 * 
	 * @param status
	 *            Status of the users answer. Either true or false
	 */
	public static void createNewScene3(boolean status) {

		// Creates the label that displays the question and correct answer.
		Label question = new Label();
		question.getStyleClass().add("label-question");
		question.setText(choice.theQuestion + " " + choice.theAnswer);

		// Creates the label that displays the result
		Label result = new Label();
		result.getStyleClass().add("label-evaluation");

		// Creates the text which displays if the answer was correct
		Text counter = new Text();

		// Displays the text that tells the user they have mastered a formula if
		// applicable
		Text removeQ = new Text();

		// Creates the layout for this scene
		VBox layout3 = new VBox(10);
		layout3.setPadding(new Insets(10));
		layout3.setAlignment(Pos.CENTER);

		// Updates all the values according to the user's answer
		update(status, result, removeQ, layout3, counter);

		// Creates button for continuing the game.
		button4 = new Button();
		button4.setText("Jätka");
		button4.setOnAction(e -> Actions.clickContinueButton());

		// Adding remaining elements to layout
		layout3.getChildren().addAll(question, result, counter, button4, button3);

		scene3 = new Scene(layout3, 450, 350);
		scene3.getStylesheets().add("file:///Users/heidikoppel/Documents/GitHub/Project/projekt/Form.css");
	}

	/**
	 * Sets the text which tells the user if they were correct or not. Also
	 * updates the overall right answer count and streak count. Updates the odds
	 * of the question. Prompts the streak alert if applicable Prompts the game
	 * over alert if applicable.
	 * 
	 * @param status
	 *            Status of the answer.
	 * @param evaluation
	 *            Label for displaying the status.
	 * @param removeQ
	 *            Text for displaying the info about removing a formula from
	 *            circulation.
	 * @param layout3
	 *            Layout where we can add removeQ if applicable.
	 * @param counter
	 *            Text for displaying the number of correct answers given.
	 */
	public static void update(boolean status, Label evaluation, Text removeQ, VBox layout3, Text counter) {
		int sum = 0;
		if (status == true) {
			evaluation.setText("Õige vastus");
			rightCount++;
			streak++;
			choice.odds.set(choice.theIndex, choice.odds.get(choice.theIndex) - 1);

			if (choice.odds.get(choice.theIndex) == 0) {
				removeQ.setText("Seda vastust sa tead ja rohkem seda valemit ei küsita");
				layout3.getChildren().add(removeQ);
			}

			if (streak % 5 == 0) {
				Alert.display("Auhind!", "Tubli! Sa oled vastanud 10 korda järjest õigesti.", true);
			}

			for (int i : choice.odds) {
				sum += i;
			}

			if (sum == 0) {
				GameOverAlert.display();
			}
			counter.setText("Kogu õigete vastuste arv: " + rightCount);

		} else {
			evaluation.setText("Vale vastus");
			streak = 0;
			choice.odds.set(choice.theIndex, choice.odds.get(choice.theIndex) + 1);
		}
	}

	/**
	 * Method for handling the Check boxes
	 * 
	 * @param box1
	 *            First check box
	 * @param box2
	 *            Second check box
	 * @param box3
	 *            Third check box
	 * @param box4
	 *            Fourth check box
	 * @return QandA
	 * @throws Exception
	 *             If no boxes are chosen.
	 */
	public static QandA handleOptions(CheckBox box1, CheckBox box2, CheckBox box3, CheckBox box4) throws Exception {

		CheckBox[] c = { box1, box2, box3, box4 };
		int counter = 1;

		for (CheckBox box : c) {
			if (box.isSelected() == true) {
				String file = "/Users/heidikoppel/Documents/GitHub/Project/valemid" + counter + ".csv";
				Loader.readFromCsv(choice, file);
			}
			counter += 1;

		}
		return choice;
	}

	/**
	 * Assigns the index, question and correct answer for one round of the game
	 * 
	 * @param list
	 *            a QandA object.
	 * @return the updated QandA object.
	 */
	public static QandA chooseQandA(QandA list) {
		list.theIndex = choice.randomIndex();
		list.theAnswer = list.answers.get(list.theIndex);
		list.theQuestion = list.questions.get(list.theIndex);
		return list;
	}

	/**
	 * Validates the users answer by first putting the answer in lower case and
	 * removing all whitespace and then comparing to the correct answer.
	 * 
	 * @param answer
	 *            The users answer.
	 * @param value
	 *            The correct answer.
	 * @return boolean true or false.
	 */
	public static boolean validateAnswer(String answer, String value) {
		String trimAnswer = answer.replaceAll("\\s+", "").toLowerCase();
		return trimAnswer.equals(value);
	}
}
