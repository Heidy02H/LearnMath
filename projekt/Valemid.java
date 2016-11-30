package projekt;


import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.opencsv.CSVReader;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Valemid extends Application {

	Stage window;
	Scene scene1, scene2;
	Button button1, button2;
	
	// Creating my QandA.
	ArrayList<String> questions = new ArrayList<String>();
	ArrayList<String> answers = new ArrayList<String>();
	QandA choice = new QandA(questions, answers);

	public static void main(String[] args) throws Exception {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Õpime valemeid");
		Label instruction = new Label("Palun vali milliseid valemeid soovid õppida.");

		// Create CheckBoxes
		CheckBox box1 = new CheckBox("1ga korrutamine");
		CheckBox box2 = new CheckBox("2ga korrutamine");
		CheckBox box3 = new CheckBox("3ga korrutamine");
		CheckBox box4 = new CheckBox("4ga korrutamine");

		// Button 1

		button1 = new Button();
		button1.setText("Alusta");

		button1.setOnAction(e -> {
			try {
				handleOptions(box1, box2, box3, box4);
				askQuestion(choice);
				createNewScene();
				primaryStage.setScene(scene2);
					
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		// Button 2
		
		button2 = new Button();
		button2.setText("Valmis");
		
		
		
		// Question and answer
		
		Label question = new Label("");
		TextField answer = new TextField();
		
		
		// Layout1

		VBox layout1 = new VBox(10);
		layout1.setPadding(new Insets(10));
		layout1.getChildren().addAll(instruction, box1, box2, box3, box4, button1);
		
		// Layout2
		
//		VBox layout2 = new VBox(10);
//		layout2.setPadding(new Insets(10));
//		layout2.getChildren().addAll(question, answer, button2);
//		
		// First scene

		scene1 = new Scene(layout1, 500, 500);
		
		primaryStage.setScene(scene1);
		primaryStage.show();
		
		
		// Second scene
		
//		scene2 = new Scene(layout2, 400, 400);
		

	}
	
	//Creating new scene . 
	// Idea from http://stackoverflow.com/questions/32940574/how-do-i-transfer-data-from-one-scene-to-another-in-javafx
	
	public void createNewScene() {
		Label question = new Label();
	    question.setText(askQuestion(choice));
	    
	    TextField answer = new TextField();
	    
	    // button for submitting answer
	    button2 = new Button();
	    button2.setText("Vasta");
	    

	 // Layout2
		
	 	VBox layout2 = new VBox(10);
	 	layout2.setPadding(new Insets(10));
	 	layout2.getChildren().addAll(question, answer, button2);
	 	
	 	scene2 = new Scene(layout2, 400, 400);
	}

	// Handle Checkboxes
	private QandA handleOptions(CheckBox box1, CheckBox box2, CheckBox box3, CheckBox box4) throws Exception {
		
		if (box1.isSelected()) {
			String file = "/Users/heidikoppel/Documents/GitHub/Project/korrutamine1.csv";
			readInChoice(choice, file);
			System.out.println("a " + choice.questions.size());

		}
		if (box2.isSelected()) {
			String file = "/Users/heidikoppel/Documents/GitHub/Project/korrutamine2.csv";
			readInChoice(choice, file);
			System.out.println("b " +choice.questions.size());

		}
		if (box3.isSelected()) {
			String file = "/Users/heidikoppel/Documents/GitHub/Project/korrutamine3.csv";
			readInChoice(choice, file);

		}
		if (box4.isSelected()) {
			String file = "/Users/heidikoppel/Documents/GitHub/Project/korrutamine4.csv";
			readInChoice(choice, file);

		}
		
		
		return choice;
	}

	// Put the formulas that the user chooses into a QandA object.
	public static void readInChoice(QandA choice, String file) throws Exception {

		CSVReader reader = new CSVReader(new FileReader(file));

		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			String[] a = nextLine[0].split(";");
			choice.add(a[0], a[1]);
		}
		
		System.out.println(choice.questions);

		reader.close();
		
	

	}
	
	// Choose a question to ask
	
	public String askQuestion(QandA list) {
		
		System.out.println("Size of questions when askQuestions" + list.questions.size());
		
		int rand = list.randomNumber();
		
		System.out.println("Random number gen" + rand);
		
		
		
		return list.getQuestion(rand) ;
	}
	
	

}
