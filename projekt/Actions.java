package projekt;

public class Actions {

	public static void clickCloseButton() {
		Main.window.close();
	}

	public static void clickBeginButton() {
		try {
			Main.handleOptions(Main.box1, Main.box2, Main.box3, Main.box4);
			Main.chooseQandA(Main.choice);
			Main.createNewScene();
			Main.window.setScene(Main.scene2);
		} catch (Exception e) {
			Alert.display("Viga!", "Palun vali vähemalt üks valemite pakett", false);
		}

	}
	
	public static void clickValidateButton() {
		String userAnswer = Main.answer.getText();
		Main.status = Main.validateAnswer(userAnswer, Main.choice.theAnswer);
		Main.createNewScene2(Main.status);
		Main.window.setScene(Main.scene3);
		
	}
	
	public static void clickContinueButton() {
		
		Main.chooseQandA(Main.choice);
		Main.createNewScene();
		Main.window.setScene(Main.scene2);
		
	}

}
