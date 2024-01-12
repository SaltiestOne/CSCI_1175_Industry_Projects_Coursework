package industryProjects;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise33_09Server extends Application {
  private TextArea history = new TextArea();
  private TextArea newMessage = new TextArea();
  private static TextArea nameBox = new TextArea();
 
  public void start(Stage primaryStage) throws IOException {
    
	  history.setWrapText(true);
	  newMessage.setWrapText(true);
	  history.setDisable(true);
    

	ServerSocket server = new ServerSocket(7007);
	Socket socket = server.accept();
	DataInputStream input = new DataInputStream(socket.getInputStream());
	DataOutputStream output = new DataOutputStream(socket.getOutputStream());
	
	
	BorderPane pane0 = new BorderPane();
	pane0.setTop(new Label("Name"));
	pane0.setCenter(nameBox);
	nameBox.setPrefHeight(16);
    
    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(history));
    history.maxWidthProperty().bind(primaryStage.widthProperty().subtract(40));
    
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(newMessage));
    newMessage.maxWidthProperty().bind(primaryStage.widthProperty().subtract(40));
    
    Button submit = new Button("Submit");
    submit.setOnAction(e -> {
		
    	try {
	    	
			String message = message(newMessage.getText());
	    	
	    	newMessage.clear();
	    	
	    	history.appendText(message);
	    	
			output.writeUTF(message);
		} catch (IOException e1) {
			
			System.out.print(e1.getLocalizedMessage());
		}});
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane0, pane1, pane2, submit);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 400, 400);
    primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    
    new Thread(() -> {
    
    	while (true) {
    		
    		try {
    	
    			history.appendText(input.readUTF());
    	
    		} catch (IOException ex) {
    	
    			System.out.print(ex.getLocalizedMessage());
    		} 		
    	}
    }).start();

   
  }

  public static void main(String[] args) {
    launch(args);
  }
  
  public static String message(String message) {
	  
	  
	  
	  if (nameBox.getText().trim().equals("")) {
		  
		  return (("Anon: " + message.trim()) + "\n");
	  } else {
		  
		  return ((nameBox.getText().trim() + ":  " + message.trim()) + "\n");
	  }
	  
  }
}
