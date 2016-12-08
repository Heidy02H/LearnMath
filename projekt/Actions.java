package projekt;

/**
 * This class provides the actions for button clicks
 * 
 * Idea from Peeter Fridolins presentation in the previous lesson.
 * 
 * @author Heidi Koppel
 *
 */
public class Actions {

	/**
	 * When this action is triggered the program will close
	 */
	public static void clickCloseButton() {
		Main.window.close();
	}

	/**
	 * When this action is triggered the program stores the user's chosen
	 * formulas into the QandA object. Next it then chooses a question to ask
	 * from the QandA. Then it creates a new scene with the previous information
	 * in mind. Finally it starts the new scene
	 * 
	 * If no formulas are chosen it will display an alert box telling the user
	 * to make a choice.
	 */
	public static void clickBeginButton() {
		try {
			Main.handleOptions(Main.box1, Main.box2, Main.box3, Main.box4);
			Main.chooseQandA(Main.choice);
			Main.createNewScene2();
			Main.window.setScene(Main.scene2);
		} catch (Exception e) {
			Alert.display("Viga!", "Palun vali vähemalt üks valemite pakett", false);
		}
	}

	/**
	 * When this action is triggered the program will validate the users answer
	 * Next it will create a new scene with the previous info. Finally it starts
	 * the new scene
	 */
	public static void clickValidateButton() {
		String userAnswer = Main.answer.getText();
		Main.status = Main.validateAnswer(userAnswer, Main.choice.theAnswer);
		Main.createNewScene3(Main.status);
		Main.window.setScene(Main.scene3);

	}

	/**
	 * When this action is triggered the program chooses a new question to ask.
	 * Then creates the a new scene with that question. Finally starts the scene
	 */
	public static void clickContinueButton() {

		Main.chooseQandA(Main.choice);
		Main.createNewScene2();
		Main.window.setScene(Main.scene2);

	}

}
