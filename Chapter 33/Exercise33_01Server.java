package industryProjects;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();



  public void start(Stage primaryStage) throws IOException {
    
	  ta.setWrapText(true);
	  ta.setPrefWidth(400);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    
    new Thread(() -> {
    	
    	try {
    		
    		 ServerSocket receiver = new ServerSocket(777);
    		 Socket socket = receiver.accept();
    		 DataInputStream input = new DataInputStream(socket.getInputStream());
    		
    		while (true) {
    			
    			double annualInterest = input.readDouble();
	
    			int years = input.readInt();
			
    			double loanAmount = input.readDouble();
			
    			
    			Platform.runLater(() -> {
    				
    					Loan loan = new Loan(annualInterest, years, loanAmount);
				
    					String message = ("The total cost of this loan is $" +
						toDollar(loan.getTotalPayment()) + ", at $" +
						toDollar(loan.getMonthlyPayment()) + " per month.");
				
    					ta.setText(message);
				
    					try {
							new DataOutputStream(socket.getOutputStream()).writeUTF(message);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    					
    			});
    		
    			
    			
    		}
    		
    		
    		
    	} catch (IOException ex) {
    		
    		System.out.print(ex.getLocalizedMessage());
    		
    	}}).start();
    
    
    
  }
    
  
  public static void main(String[] args) {
    launch(args);
  }
  
  
  public double toDollar(double amount) {
	  
	  return ((double)((int)(amount * 100)) / 100);
  }
}
