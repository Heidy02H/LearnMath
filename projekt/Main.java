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

	static Stage window;
	static Scene scene1, scene2, scene3;
	static Button button1, button2, button3, button4;
	static TextField answer;
	static int status;
	static int rightCount = 0;
	static int streak = 0;

	// Creating QandA instance to store the selected formulas answers and odds.
	static ArrayList<String> questions = new ArrayList<String>();
	static ArrayList<String> answers = new ArrayList<String>();
	static ArrayList<Integer> odds = new ArrayList<Integer>();
	static QandA choice = new QandA(questions, answers, odds);
	
	// Create CheckBoxes
	static CheckBox box1 = new CheckBox("Tuletised");
	static CheckBox box2 = new CheckBox("Trigonomeetria põhivalemid");
	static CheckBox box3 = new CheckBox("Põhiintegraalid");
	static CheckBox box4 = new CheckBox("Testiks");
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		// Title for scene 1
		window.setTitle("Õpime valemeid");

		// Instruction in scene 1
		Label instruction = new Label("Palun vali milliseid valemeid soovid õppida.");

//		// Create CheckBoxes
//		CheckBox box1 = new CheckBox("Tuletised");
//		CheckBox box2 = new CheckBox("Trigonomeetria põhivalemid");
//		CheckBox box3 = new CheckBox("Põhiintegraalid");
//		CheckBox box4 = new CheckBox("Testiks");

		// Creates button for choosing the formulas which the user wants to
		// learn and loads them in.
		button1 = new Button();
		button1.setText("Alusta");
		button1.setOnAction(e -> Actions.clickBeginButton());

		// Creates Layout for first scene and adds the elements to it
		VBox layout1 = new VBox(10);
		layout1.setPadding(new Insets(10));
		layout1.getChildren().addAll(instruction, box1, box2, box3, box4, button1);

		// Creates first scene
		scene1 = new Scene(layout1, 450, 200);
		scene1.getStylesheets().add("file:///Users/heidikoppel/Documents/GitHub/Project/projekt/Form.css");

		// Starts the first scene
		primaryStage.setScene(scene1);
		primaryStage.show();

	}

	// Method for creating scene number 2 with info from the first scene.
	// Idea to create the new scene in a method:
	// http://stackoverflow.com/questions/32940574/how-do-i-transfer-data-from-one-scene-to-another-in-javafx
	public static void createNewScene() {

		// Creates the label that displays the question
		Label question = new Label();
		question.getStyleClass().add("label-question");
		question.setText(choice.theQuestion);

		// Creates the text field for the user's answer
		answer = new TextField();

		// Creates button for submitting answer
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

		scene2 = new Scene(layout2, 450, 200);
		scene2.getStylesheets().add("file:///Users/heidikoppel/Documents/GitHub/Project/projekt/Form.css");
	}

	// Method that creates scene number 3 with info from the second scene
	public static void createNewScene2(int status) {

		// Creates the label that displays the question and correct answer.
		Label question = new Label();
		question.getStyleClass().add("label-question");
		question.setText(choice.theQuestion + " " + choice.theAnswer);

		// Creates the label that displays the result
		Label evaluation = new Label();
		evaluation.getStyleClass().add("label-evaluation");
		

		// Creates the text which displays if the answer was correct
		Text counter = new Text();

		// Displays the text that tells the user they have mastered a formula if
		// applicable
		Text removeQ = new Text();
		
		VBox layout3 = new VBox(10);
		layout3.setPadding(new Insets(10));
		layout3.setAlignment(Pos.CENTER);

		// Set 
		int sum = 0;
		if (status == 1) {
			evaluation.setText("Õige vastus");
			rightCount++;
			streak++;
			choice.odds.set(choice.theIndex, choice.odds.get(choice.theIndex) - 1);

			if (choice.odds.get(choice.theIndex) == 0) {
				removeQ.setText("Seda vastust sa tead ja rohkem seda valemit ei küsita");
				layout3.getChildren().add(removeQ);
			}

			for (int i : choice.odds) {
				sum += i;
			}

			if (streak % 10 == 0) {
				Alert.display("Auhind!", "Tubli! Sa oled vastanud 10 korda järjest õigesti.", true);
			}
			
			if (sum == 0) {
				ResultBox.display();
			}
			counter.setText("Kogu õigete vastuste arv: " + rightCount);

		} else {
			evaluation.setText("Vale vastus");
			streak = 0;
			choice.odds.set(choice.theIndex, choice.odds.get(choice.theIndex) + 1);
		}

		// Creates button for submitting answer and continuing the game.
		button4 = new Button();
		button4.setText("Jätka");
		button4.setOnAction(e -> Actions.clickContinueButton());

		// Creates button for Exiting the program
		button3 = new Button();
		button3.setText("Lõpeta");
		button3.setOnAction(e -> Actions.clickCloseButton());

		//Adding remaining elements to layout
		layout3.getChildren().addAll(question, evaluation, counter, button4, button3);

		scene3 = new Scene(layout3, 450, 200);
		scene3.getStylesheets().add("file:///Users/heidikoppel/Documents/GitHub/Project/projekt/Form.css");
	}

	// Method for handling the Checkboxes
	public static QandA handleOptions(CheckBox box1, CheckBox box2, CheckBox box3, CheckBox box4) throws Exception {
		
		CheckBox[] c = {box1, box2, box3, box4};
		int counter = 1;
		
		for(CheckBox box: c) {
			if(box.isSelected() == true) {
				String file = "/Users/heidikoppel/Documents/GitHub/Project/valemid"+ counter + ".csv";
				Loader.readFromCsv(choice, file);
			}
			counter += 1;
			
		}
		return choice;
	}


	// Assigns the index, question and correct answer for one round of the game
	public static QandA chooseQandA(QandA list) {
		list.theIndex = choice.randomIndex();
		list.theAnswer = list.answers.get(list.theIndex);
		list.theQuestion = list.questions.get(list.theIndex);
		return list;
	}

	// Validates the users answer by first putting the answer in lower case and
	// removing all whitespace
	// and then comparing to the correct answer.
	public static int validateAnswer(String answer, String value) {
		String trimAnswer = answer.replaceAll("\\s+", "").toLowerCase();
		if (trimAnswer.equals(value) == true)
			return 1;
		else
			return -1;
	}
}
