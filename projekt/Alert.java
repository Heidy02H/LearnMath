package projekt;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

// I learned about making alert boxes from this tutorial https://www.youtube.com/watch?v=SpL3EToqaXA&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=5


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {
	
	public static void display(String title, String message) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
	
		window.setTitle(title);
		window.setMinWidth(250);
		
		
		Label label = new Label();
		label.setText(message);
		
		String[] catList = {
			"Cats/grumpymath.jpg", 
			"Cats/cat1.jpg",
			"Cats/monocat.jpg",
			"Cats/rollcat.jpg"	
		};
		
		int index = (int) (Math.random() * catList.length);
		
		Image cat = new Image(catList[index]);
		
		ImageView iv1 = new ImageView();
        iv1.setImage(cat);
		
		Button button = new Button("Jätka");
		button.setOnAction(e -> {
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10));
		layout.getChildren().addAll(label, iv1, button);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	

}
