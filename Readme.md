# GUI Project

This project is a very simple JavaFX GUI application that allows users to add, remove, pause, and change the direction of animated drawings on a canvas. The drawings include UFOs, rockets, and birds.

## Classes

### `GUI`

- **Description:** This class represents the main graphical user interface (GUI) of the application. It provides buttons for adding, removing, pausing, and changing the direction of animated drawings. It also includes checkboxes for selecting different types of drawings (bird, rocket, UFO).

- **Usage:**
  - Launches the main window with buttons and checkboxes.
  - Creates and manages the canvas window for drawing.

### `AnimatedDrawing`

- **Description:** This abstract class serves as the base class for all animated drawings. It includes methods for updating, redrawing, and getting nodes. It also provides functionality for starting and stopping animation.

- **Usage:**
  - Extended by specific drawing classes like `DrawUFO`, `DrawRocket`, and `DrawBird`.

### `DrawUFO`

- **Description:** This class represents the drawing of a UFO on the canvas. It includes methods for drawing the UFO, updating its position, and redrawing it.

- **Usage:**
  - Used as part of the animated drawings in the GUI.

### `DrawRocket`

- **Description:** This class represents the drawing of a rocket on the canvas. It includes methods for drawing the rocket, updating its position, and redrawing it.

- **Usage:**
  - Used as part of the animated drawings in the GUI.

### `DrawBird`

- **Description:** This class represents the drawing of a bird on the canvas. It includes methods for drawing the bird, updating its position, and redrawing it.

- **Usage:**
  - Used as part of the animated drawings in the GUI.

### `DrawData`

- **Description:** This class holds data related to the drawings, including the direction, drawing stack, and booleans for bird, rocket, and UFO.

- **Usage:**
  - Used to store and manage drawing-related data.

### `MultiShapeTest`

- **Description:** This class serves as the entry point for launching the GUI application. It contains the main method that initiates the launch of the GUI.

- **Usage:**
  - Executed to start the GUI application.

## How to Run

1. Install Javafx-sdk-21.0.3 dependency (javafx-sdk-21.0.2)
2. From the Project2 directory compile **ALL** the Java files using `javac -cp ".;REPLACE_WITH_PATH_TO_DEPENDENCY\lib\*" gui/*.java`.

```
// An example classpath to the dependency library
javac -cp ".;C:\Program Files\Java\javafx-sdk-21.0.2\lib\*" gui/*.java
```

3. Run the main class from the Project2 directory using `java -cp ".;REPLACE_WITH_PATH_TO_DEPENDENCY\lib\*" gui/MultiShapeTest` to launch the GUI application.

```
// Running the compiled classes with an example path to the dependency library
java -cp ".;C:\Program Files\Java\javafx-sdk-21.0.2\lib\*" gui/MultiShapeTest
```

4. Interact with the GUI to add, remove, and manipulate animated drawings on the canvas.
