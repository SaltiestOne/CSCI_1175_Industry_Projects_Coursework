package industryProjects;

import javafx.animation.PathTransition; 
import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagRisingAnimation extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane
		Pane pane = new Pane();
	
		// Add an image view and add it to pane
		Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/4/42/Animated-Flag-USA.gif");
		ImageView imageView = new ImageView(image);
		imageView.setY(500);
		pane.getChildren().add(imageView);
		
		
		// Create a path transition
		new Thread(new Runnable() {
			
			public void run() {
				
				try {
					
					while (imageView.getY() >= 30) {
						
						imageView.setY(imageView.getY() - 10);
						
						Thread.sleep(200);
					}
				} catch (InterruptedException ex) {
									
				}
			}
		}).start(); // Start animation
		
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 300, 700); 
		primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}