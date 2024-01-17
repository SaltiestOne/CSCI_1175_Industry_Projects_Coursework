package industryProjects;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class Exercise31dash17 extends Application {

	
	
	public void start(Stage investStage) {
		
		TextField amount = new TextField();
		TextField years = new TextField();
		TextField rate = new TextField();
		TextField returnOnInvestment = new TextField();
		Text desc = new Text();
		MenuBar options = new MenuBar();
		
		VBox inputs = new VBox();
		
		inputs.getChildren().addAll((new Label("Amount:")), amount, (new Label("Years:")),
				years, (new Label("Interest Rate:")), rate);
		
		inputs.setSpacing(15);
		inputs.setLayoutX(20);		
		inputs.setLayoutY(30);
		
		returnOnInvestment.setLayoutX(260);
		returnOnInvestment.setLayoutY(230);
		
		desc.setLayoutX(returnOnInvestment.getLayoutX());
		desc.setLayoutY(returnOnInvestment.getLayoutY() - 15);
		
		
		options.setLayoutX(returnOnInvestment.getLayoutX()); 
		
		Menu actions = new Menu("Options");
		options.getMenus().add(actions);
		
		MenuItem calc = new MenuItem("Calculate");calc.setOnAction(e -> {
			
			try {
		
				returnOnInvestment.setText(("$") + returnOnInvestment(
				(Double.parseDouble(amount.getText())), 
				(Double.parseDouble(rate.getText())),
				(Integer.parseInt(years.getText()))));
			} catch (NumberFormatException nf) {
		
				returnOnInvestment.setText("Invalid input.");
			}});
		
		amount.setOnAction(e -> {
			years.requestFocus();});
		years.setOnAction(e -> {
			rate.requestFocus();});
		rate.setOnAction(e -> {
			calc.fire();});
		
		MenuItem clear = new MenuItem("Clear");
		clear.setOnAction(e -> {
			
			amount.setText("");
			rate.setText("");
			years.setText("");});
		
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> {
			System.exit(0);});
		
		
		actions.getItems().add(calc);
		actions.getItems().add(clear);
		actions.getItems().add(exit);
		
		Pane pane = new Pane(options, inputs, returnOnInvestment, desc);
		
		investStage.setTitle("Investment Calculator");
		investStage.setScene(new Scene(pane, 500, 300));
		investStage.show();
		
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public static double returnOnInvestment(double amount, double rate, int years) {
		
		double result = (amount * (Math.pow((1 + rate), (years * 12))));
		
		return (((double)((int)(result * 100)))/100);
	}
	

}
