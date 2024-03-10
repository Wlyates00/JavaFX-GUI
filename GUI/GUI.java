package gui;

import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the graphical user interface (GUI) of the application.
 * It extends the JavaFX Application class and implements the main window and canvas window.
 */
public class GUI extends Application {
    // Add Button
    private Button button1;
    // Remove Button
    private Button button2;
    // Pause Button
    private Button button3;
    // Change Dir. Button
    private Button button4;
    // Show Button
    private Button button5;
    // Exit ALL Button
    private Button button6;
    // Hide Button
    private Button button7;
    // Exit Canvas Button
    private Button button8;
    private CheckBox checkBoxBird;
    private CheckBox checkBoxRocket;
    private CheckBox checkBoxUFO;
    private DrawData data;
    private Stage stage;
    private Stage mainStage;
    private Group canvas;
    final static int BUTTON_WIDTH = 200;
    final static int BUTTON_HEIGHT = 100;

    /**
     * Default constructor for the GUI class. Initializes the DrawData object.
     */
    public GUI(){
        // A data instance
        data = new DrawData();
    }

    /**
     * The main entry point for the JavaFX application.
     * @param stage The primary stage of this application.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Opening main window
        createMainWindow();
        // Opening canvas window
        createCanvasWindow();
    }

    /**
     * Creates the main window with action buttons.
     */
    private void createMainWindow(){
        //Creating Add Button
        button1 = new Button("Add");
        //Setting Add Action to button
        button1.setOnMousePressed(event -> {
            // Create and add shape to canvas
            addDrawing();
        });
        //Sizing button
        buttonSizing(button1);


        //Creating Remove button
        button2 = new Button("Remove");
        //Setting Remove Action to button
        button2.setOnMousePressed(event -> {
            // Remove drawing logic
            if (!data.drawingStack.isEmpty()) {
                // Grabbing last drawing from stack
                AnimatedDrawing lastDrawing = data.drawingStack.pop();
                // Stopping animation
                lastDrawing.stopAnimation();
                //Removing from canvas
                canvas.getChildren().remove(lastDrawing.getNodes());
            }
        });
        //Sizing button
        buttonSizing(button2);

        //Creating
        button3 = new Button("Pause");
        //Sizing button
        buttonSizing(button3);

        //Setting Pause Action to button
        button3.setOnMouseClicked(event -> {
            // Pausing logic
            if (data.direction != 0) {
                //Updating previous direction
                data.prevDirection = data.direction;
                //Updating direction
                data.direction = 0;
            }
            else{
                //Updating direction
                data.direction = data.prevDirection;
                //Updating previous direction
                data.prevDirection = 0;
            }
        });

        //Creating Change Dir. button
        button4 = new Button(" Change \nDirection");
        //Sizing button
        buttonSizing(button4);
        //Setting Change Dir. Action to button
        button4.setOnMouseClicked(event -> {
            // Change direction logic (CANNOT CHANGE WHILE PAUSED)
            if (data.direction == 1){
                //Update direction
                data.direction = -1;
                //Update previous direction
                data.prevDirection = 1;
            } else if (data.direction == -1) {
                //Update direction
                data.direction = 1;
                //Update previous direction
                data.prevDirection = -1;
            }
        });

        //Creating Show button
        button5 = new Button("Show");
        //Sizing button
        buttonSizing(button5);
        //Setting Show Action to button
        button5.setOnMouseClicked(event -> {
            //Un-pausing animation
            if (data.direction == 0){
                //Update direction
                data.direction = data.prevDirection;
                //Update previous direction
                data.prevDirection = 0;
            }

            // Open minimized canvas if it is not closed
            if (stage != null) stage.setIconified(false);

            // If stage is closed create a new empty canvas
            if (!stage.isShowing()) {
                createCanvasWindow();
            }
        });

        //Creating Exit button
        button6 = new Button("Exit");
        //Sizing button
        buttonSizing(button6);
        //Setting Exit Action to button
        button6.setOnMouseClicked(event -> {
            //Close canvas
            stage.close();
            //Close mainStage
            mainStage.close();
        });

        // Creating and moving Checkbox
        checkBoxBird = new CheckBox("Bird");
        // Adding checkbox action
        checkBoxBird.setOnMouseClicked(event -> {
            data.bird = !data.bird;
        });
        FlowPane.setMargin(checkBoxBird, new Insets(0, 225, 0, 0));

        // Creating and moving Checkbox
        checkBoxRocket = new CheckBox("Rocket");
        // Adding checkbox action
        checkBoxRocket.setOnMouseClicked(event -> {
            data.rocket = !data.rocket;
        });
        FlowPane.setMargin(checkBoxRocket, new Insets(0, 225, 0, 0));

        // Creating checkbox
        checkBoxUFO = new CheckBox("UFO");
        // Adding checkbox action
        checkBoxUFO.setOnMouseClicked(event -> {
            data.ufo = !data.ufo;
        });


        // Creating a Flow Pane
        FlowPane flowPane = new FlowPane();

        // Adding all the nodes to the flow pane
        flowPane.getChildren().addAll(button1, button2, button3, button4, button5,button6,checkBoxBird, checkBoxRocket, checkBoxUFO);

        // Creating a scene object
        Scene scene = new Scene(flowPane ,600, 225);

        // Creating main stage
        mainStage = new Stage();

        // Constraining the main screen to not go smaller or bigger than certain size
        mainStage.setMinWidth(616);
        mainStage.setMinHeight(265);
        mainStage.setMaxWidth(616);
        mainStage.setMaxHeight(265);

        // Starting main window position
        mainStage.setX(0);
        mainStage.setY(300);

        //Setting title to the Stage
        mainStage.setTitle("GUI Project");

        //Adding scene to the stage
        mainStage.setScene(scene);

        //Displaying the contents of the stage
        mainStage.show();
    }

    /**
     * Creates the canvas window for drawings.
     */
    private void createCanvasWindow(){
        // Creating group for canvas layout
        canvas = new Group();

        // Creating scene using group layout
        Scene scene = new Scene(canvas ,800, 800);

        // Creating canvas stage
        stage = new Stage();

        //Creating Hide Button
        button7 = new Button("Hide");

        //Sizing button
        button7.setMinHeight(50);
        button7.setMinWidth(400);
        button7.setTranslateY(750);

        //Setting action to button
        button7.setOnMouseClicked(event -> {
            // Minimize Canvas
            stage.setIconified(true);
            // Pause animation
            if (data.direction != 0) {
                //Update previous direction
                data.prevDirection = data.direction;
                //Update direction
                data.direction = 0;
            }
        });

        // Creating Exit Button
        button8 = new Button("Exit");

        //Sizing button
        button8.setMinHeight(50);
        button8.setMinWidth(400);
        button8.setTranslateX(400);
        button8.setTranslateY(750);

        //Setting action to button
        button8.setOnMouseClicked(event -> {
            // Minimize Canvas
            stage.close();
        });

        // Adding buttons to canvas group
        canvas.getChildren().addAll(button7, button8);

        // Constraining the main screen to not go smaller or bigger than certain size
        stage.setResizable(false);


        // Starting main window position
        stage.setX(600);
        stage.setY(0);

        //Setting title to the Stage
        stage.setTitle("Canvas Drawing Window");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    /**
     * Adds a drawing to the canvas based on the selected checkboxes.
     */
    private void addDrawing(){
        if (data.rocket) {
            // New random instance
            Random random = new Random();

            // Generating random coordinates within the canvas bounds
            int randomX = random.nextInt(0, 800);
            int randomY = random.nextInt(0, 800);

            // Draw a rocket
            AnimatedDrawing rocket = new DrawRocket(randomX, randomY, data);

            // Animate Rocket
            rocket.animate(canvas, 120);

            // Add rocket to drawingStack
            data.drawingStack.add(rocket);
        }
        if (data.ufo) {
            // New random instance
            Random random = new Random();

            // Generating random coordinates within the canvas bounds
            int randomX = random.nextInt(0, 800);
            int randomY = random.nextInt(0, 800);

            // Draw a ufo
            AnimatedDrawing ufo = new DrawUFO(randomX, randomY, data);

            // Animate ufo
            ufo.animate(canvas, 120);

            // Add ufo to drawingStack
            data.drawingStack.add(ufo);
        }
        if (data.bird) {
            // New random instance
            Random random = new Random();

            // Generating random coordinates within the canvas bounds
            int randomX = random.nextInt(0, 800);
            int randomY = random.nextInt(0, 800);

            // Draw a bird
            AnimatedDrawing bird = new DrawBird(randomX, randomY, data);

            // Animate bird
            bird.animate(canvas, 120);

            // Add bird to drawingStack
            data.drawingStack.add(bird);
        }
    }

    /**
     * Sets the size and style of a button.
     * @param button The button to be sized and styled.
     */
    private void buttonSizing(Button button){
        //Sizing buttons
        button.setMinHeight(BUTTON_HEIGHT);
        button.setMinWidth(BUTTON_WIDTH);

        // Set font size
        button.setStyle("-fx-font-size:25");
        // Wrap long text
        button.setWrapText(true);
    }
}