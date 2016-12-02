package projekt;

// Used the following series tutorials on Youtube to learn about JavaFX https://www.youtube.com/watch?v=FLkOX4Eez6o&t=10s


import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Valemid extends Application {

	Stage window;
	Scene scene1, scene2, scene3;
	Button button1, button2, button3, button4;
	TextField answer;
	boolean status;
	int rightCount = 0;
	int streak = 0;
	
	
	// Creating my QandA for selection.
	ArrayList<String> questions = new ArrayList<String>();
	ArrayList<String> answers = new ArrayList<String>();
	QandA choice = new QandA(questions, answers);

	public static void main(String[] args) throws Exception {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Õpime valemeid");
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
				chooseQandA(choice);
				createNewScene();
				primaryStage.setScene(scene2);
					
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Palun vali vähemalt üks valemite pakett");
			}
		});
		
		
		// Layout1

		VBox layout1 = new VBox(10);
		layout1.setPadding(new Insets(10));
		layout1.getChildren().addAll(instruction, box1, box2, box3, box4, button1);
			
		// First scene

		scene1 = new Scene(layout1, 500, 300);
		
		primaryStage.setScene(scene1);
		primaryStage.show();

	}
	
	//Creating new scene . 
	// Idea from http://stackoverflow.com/questions/32940574/how-do-i-transfer-data-from-one-scene-to-another-in-javafx
	
	public void createNewScene() {
		Label question = new Label();
		
	    question.setText(choice.theQuestion + " " + choice.theAnswer);
	    
	    TextField answer = new TextField();
	    
	    
	    // button for submitting answer
	    button2 = new Button();
	    button2.setText("Vasta");
	    
	    button2.setOnAction(e -> {
	    	String userAnswer = answer.getText(); 	    	
	    	status = validateAnswer(userAnswer, choice.theAnswer);
	    	System.out.println(status);
	    	createNewScene3(status);
	    	window.setScene(scene3);
	    	
	    	
	    });
	    
	    // button for Exiting the program
	    
	    button3 = new Button();
	    button3.setText("Lõpeta");
	    
	    button3.setOnAction(e -> {
	    	window.close();
	    });

	 // Layout2
		
	 	VBox layout2 = new VBox(10);
	 	layout2.setPadding(new Insets(10));
	 	layout2.getChildren().addAll(question, answer, button2, button3);
	 	
	 	scene2 = new Scene(layout2, 500, 300);
	 	
	 	
	}
	
	public void createNewScene3(boolean status) {
		
		Label question = new Label();
		
	    question.setText(choice.theQuestion + " " + choice.theAnswer);
	    
	    Label hinnang =new Label();
	    Text counter = new Text();
	    
	    if (status == true) {
	    	hinnang.setText("Õige vastus");
	    	rightCount++;
	    	counter.setText("Õigete vastuste arv: " + rightCount);
	    	
	    }
	    else {
	    	hinnang.setText("Vale vastus");
	    }
	    
	    // button for submitting answer
	    button4 = new Button();
	    button4.setText("Jätka");
	    
	    button4.setOnAction(e -> {
	    	chooseQandA(choice);
	    	createNewScene();
	    	window.setScene(scene2);
	    		
	    });
	    
	    // button for Exiting the program
	    
	    button3 = new Button();
	    button3.setText("Lõpeta");
	    
	    button3.setOnAction(e -> {
	    	window.close();
	    });

	 // Layout2
		
	 	VBox layout3 = new VBox(10);
	 	layout3.setPadding(new Insets(10));
	 	layout3.getChildren().addAll(question, hinnang, counter, button4, button3);
	 	
	 	scene3 = new Scene(layout3, 500, 300);
	 	
		
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
	
	public QandA chooseQandA(QandA list) {
		
		System.out.println("Size of questions when askQuestions" + list.questions.size());
		
		int rand = list.randomNumber();
		
		System.out.println("Random number gen" + rand);
		
		list.theAnswer = list.answers.get(rand);
		list.theQuestion = list.questions.get(rand);
		
		return list;
	}
	
	public boolean validateAnswer(String answer ,String value) {
		
		System.out.println("algne " + answer);
		System.out.println("Vastus: " + answer.replaceAll("\\s+","").toLowerCase());
		System.out.println("Tegelik vastus " + value );

		return answer.replaceAll("\\s+","").toLowerCase().equals(value);
		
		
	}
	
	
		

}
