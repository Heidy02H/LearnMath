package projekt;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class creates alerts for the user.
 * 
 * I learned about making alert boxes from this tutorial on Youtube
 * https://www.youtube.com/watch?v=SpL3EToqaXA&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=5
 * 
 * @author heidikoppel
 *
 */

public class Alert {
	/**
	 * Method for creating and displaying the alert.
	 * 
	 * @param title
	 *            Title of the alert box
	 * @param message
	 *            Message to the user
	 * @param boolean
	 *            If true then a random funny image is loaded into the alert box
	 */

	public static void display(String title, String message, boolean image) {

		Stage window = new Stage();
		window.setMinWidth(250);

		// Blocks user interaction with other windows while this alert box is
		// open
		window.initModality(Modality.APPLICATION_MODAL);

		// Sets title of window
		window.setTitle(title);

		// Creates and sets the congratulatory message
		Label infoLabel = new Label();
		infoLabel.setText(message);

		// Creates a button for closing the alert and continuing the game
		Button button = new Button("Jätka");
		button.setOnAction(e -> {
			window.close();
		});

		// Sets the layout of the alert box.
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10));
		layout.getChildren().addAll(infoLabel, button);
		layout.setAlignment(Pos.CENTER);

		
		// Chooses a random cat picture as the prize and displays it.
		if (image == true) {
			String[] catList = { "Cats/grumpymath.jpg", "Cats/cat1.jpg", "Cats/monocat.jpg", "Cats/rollcat.jpg" };

			int index = (int) (Math.random() * catList.length);
			System.out.println(index);

			Image cat = new Image(catList[index]);
			ImageView iv1 = new ImageView();
			iv1.setImage(cat);
			layout.getChildren().add(iv1);
		}

		// Starts the alert scene
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("file:///Users/heidikoppel/Documents/GitHub/Project/projekt/Form.css");
		window.setScene(scene);
		window.showAndWait();
	}

}
