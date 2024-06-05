/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication2;


import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafxapplication2.GameEngine.displayBox;
import static javafxapplication2.GameEngine.movePlayer;
import static javafxapplication2.GameEngine.positionX;
import static javafxapplication2.GameEngine.positionY;
import static javafxapplication2.GameEngine.setBox;
import static javafxapplication2.GameEngine.setPrize;
import static javafxapplication2.GameEngine.setTrap;
public class main extends Application {
       int[][] box = new int[10][10];
       int x;
       int y;
       int level;
       int trap;
       int prize;
       boolean gameOver;
       int stamina;
       char move;
       String condition;
       int score;
       final String[] con = new String[1];
       Scanner scanner = new Scanner(System.in);
       
       
       Pane root = new Pane();
       Pane leftPane = new Pane();
       Scene scene = new Scene(root);
       HBox hbox = new HBox(100);
       
    @Override
    public void start(Stage primaryStage) {
        
       
       
       
       score = 0;
       x = 9;
       y = 9;
       level = 1;
       trap = level + 4; // if (trap > 1 + prize level -> abourt increament
       prize = 10;
       stamina = 30;
       move = ' ';
       gameOver = false;
       
       
       //setting frame 0 as empty
       setBox(box);
       //setting prize as 1
       setPrize(box,prize);
       //setting traps as 2
       setTrap(box,trap);
       //setting person as 9
       box[x][y] = 9;
       
       
        
        root.setPrefSize(1000, 600); // Increased width to accommodate 100 rectangles
        setPane();
        
        
        
       
        
        
        //crete right pane and adding the button and text
        VBox vbox = new VBox(10);
        Button up = new Button("up");
        Button down = new Button("down");
        Button right = new Button("left");
        Button left = new Button("right");
        Text scoreText = new Text("Score : " + String.valueOf(score));
        Text levelText = new Text("Level : " + String.valueOf(level));
        Text staminaText = new Text("Stamina : " + String.valueOf(stamina));
        
        
        
        EventHandler<ActionEvent> upEvent = (ActionEvent event) -> {
            move = 'w';
            con[0] =  movePlayer(box, move, x, y);
            conCheck(con[0]);
            
            
            
        };
        
        
        EventHandler<ActionEvent> downEvent = (ActionEvent event) -> {
            move = 's';
            con[0] =  movePlayer(box, move, x, y);
            conCheck(con[0]);
            
          
        };
        
        
        EventHandler<ActionEvent> rightEvent = (ActionEvent event) -> {
            move = 'd';
            con[0] =  movePlayer(box, move, x, y);
            conCheck(con[0]);
        };
        
        EventHandler<ActionEvent> leftEvent = (ActionEvent event) -> {
            move = 'a';
            con[0] =  movePlayer(box, move, x, y);
            conCheck(con[0]);
        };
        
        // Set event handlers to buttons
        up.setOnAction(upEvent);
        down.setOnAction(downEvent);
        left.setOnAction(leftEvent);
        right.setOnAction(rightEvent);
        
        vbox.getChildren().addAll(up, down, left, right, scoreText, staminaText, levelText);
        // Create a Scene with the main Pane as the root node
        
        hbox.getChildren().addAll(leftPane,vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fallin");
        root.getChildren().addAll(hbox);
        primaryStage.show();
        
        
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void conCheck(String con){
        System.out.print(con);
        condition = con;
        if(condition.contains("dead")){
                gameOver = true;
            }
            if(condition.contains("fail")) {
            }
            if(condition.contains("prize")){
                score = score + 1;
                stamina = stamina -1; 
            }
            if(condition.contains("move")){
                stamina = stamina -1; 
            }
            x = positionX(condition) + x;
            y = positionY(condition) + y;
            
            setPane();
            
    }
    public void setPane(){
        
        Pane leftPane2 = new Pane();
        leftPane2.setPrefWidth(300); // 60% of the width
        leftPane2.setStyle("-fx-background-color: lightgray;");
        // Add rectangles to the left pane
        int rows = 10;
        int cols = 10;
        double rectWidth = 30;
        double rectHeight = 30;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
                
                    Rectangle rectangle = new Rectangle(rectWidth, rectHeight);
                    rectangle.setX(col * rectWidth);
                    rectangle.setY(row * rectHeight);
                    rectangle.setStroke(Color.BLACK);
                    
                    
                if(box[row][col] == 0){
                    rectangle.setFill(Color.WHITE);
                    leftPane2.getChildren().add(rectangle);
                }
                if(box[row][col] == 1){
                    rectangle.setFill(Color.GREEN);
                    leftPane2.getChildren().add(rectangle);
                }
                if(box[row][col] == 2){
                    rectangle.setFill(Color.RED);
                    leftPane2.getChildren().add(rectangle);
                }
                if(box[row][col] == 9){
                    rectangle.setFill(Color.BLACK);
                    leftPane2.getChildren().add(rectangle);
                }
                
            }
            
        }
        displayBox(box);
        leftPane = leftPane2;
        
    }
    
    
}
