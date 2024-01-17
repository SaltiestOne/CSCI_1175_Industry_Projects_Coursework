package industryProjects;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise31dash20 extends Application {   
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    
	  TabPane shapes = new TabPane();
	  HBox orientations = new HBox(15);
    
	  Tab tab1 = new Tab("Line");
    
	  StackPane pane1 = new StackPane();
	  pane1.getChildren().add(new Line(10, 10, 80, 80));
	  tab1.setContent(pane1);
    
	  Tab tab2 = new Tab("Rectangle");
	  tab2.setContent(new Rectangle(10, 10, 200, 200));
	  
	  Tab tab3 = new Tab("Circle");
	  tab3.setContent(new Circle(50, 50, 20));    
    
	  Tab tab4 = new Tab("Ellipse");
	  tab4.setContent(new Ellipse(10, 10, 100, 80));
    
	  shapes.getTabs().addAll(tab1, tab2, tab3, tab4);

	  ToggleGroup orientationGroup = new ToggleGroup();
	  
	  RadioButton top = new RadioButton("Top");
	  top.setSelected(true);
	  top.setToggleGroup(orientationGroup);
	  top.setOnAction(e -> {
		  
		  shapes.setSide(Side.TOP);});
	  
	  RadioButton left = new RadioButton("Left");
	  left.setToggleGroup(orientationGroup);
	  left.setOnAction(e -> {
		  
		  shapes.setSide(Side.LEFT);});
	  
	  RadioButton right = new RadioButton("Right");
	  right.setToggleGroup(orientationGroup);
	  right.setOnAction(e -> {
		  
		  shapes.setSide(Side.RIGHT);});
	  
	  RadioButton bottom = new RadioButton("Bottom");
	  bottom.setToggleGroup(orientationGroup);
	  bottom.setOnAction(e -> {
		  
		  shapes.setSide(Side.BOTTOM);});
  
	  orientations.setLayoutY(250);
	  orientations.getChildren().addAll(new Label("Orientations:"), top, left, right, bottom);
    
	  primaryStage.setScene(new Scene(new Pane(shapes, orientations), 400, 275));  
	  primaryStage.setTitle("DisplayFigure"); // Set the window title
	  // Place the scene in the window
	  primaryStage.show(); // Display the window
  }

  
  public static void main(String[] args) {
    launch(args);
  }
}