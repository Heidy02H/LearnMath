package projekt;

import javafx.geometry.Pos;
import javafx.scene.Scene;

// I learned about making alert boxes from this tutorial https://www.youtube.com/watch?v=SpL3EToqaXA&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=5


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {
	
	public static void display(String title, String message) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
//		Alert info = new Alert();
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		
		Button button = new Button("Jätka");
		button.setOnAction(e -> {
			window.close();
		});
		
		VBox layout = new VBox();
		layout.getChildren().addAll(label, button);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		
//		info.setHeaderText("INFORMATION");
//		info.setContentText(text);
//		info.showAndWait();
	}

}
