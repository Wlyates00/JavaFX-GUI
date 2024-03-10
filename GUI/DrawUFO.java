package gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;


/**
 * This class represents a UFO drawing and extends the AnimatedDrawing class
 */
public class DrawUFO extends AnimatedDrawing {
    // The x-coordinate
    private int x;
    // The y-coordinate
    private int y;
    // The UFO group
    private Group ufo;
    //Data
    DrawData data;

    /**
     * Constructs a DrawUFO object with initial parameters.
     * @param x The initial x-coordinate for the UFO.
     * @param y The initial y-coordinate for the UFO.
     * @param data The DrawData object containing shared data for animations.
     */
    DrawUFO(int x, int y, DrawData data)
    {
        // X-coordinate for the UFO
        this.x = x;
        this.y = y;
        this.data = data;
    }

    /**
     * Draws the UFO to a group.
     * @param canvas The Group representing the UFO that will be drawn.
     * @param canvasWidth The width of the canvas.
     * @param canvasHeight The height of the canvas.
     * @return The Group containing the UFO shapes.
     */
    public Group draw(Group canvas, double canvasWidth, double canvasHeight) {
        // Group for the ufo
        ufo = new Group();

        double width = 100;
        double height = 30;

        // Creating the Dome of the UFO
        Circle dome = new Circle(x + width-150, y + height / 5, width / 2);

        // Creating the body of the UFO
        Ellipse ellipse = new Ellipse(x + width-150, y + height, width, height);

        // Creating the UFO legs
        Rectangle leg1 = new Rectangle(x + width-150, y + height + 30, width / 1.5, height / 5);
        Rectangle leg2 = new Rectangle(x + width - 210, y + height + 30, width / 1.5, height / 5);

        // Rotate legs
        leg1.setRotate(70);
        leg2.setRotate(-70);

        // Color shapes
        leg1.setFill(Color.BLACK);
        dome.setFill(Color.CYAN);
        ellipse.setFill(Color.RED);

        // Add to group
        ufo.getChildren().addAll(dome, leg1, leg2, ellipse);

        // Push to the back layer
        ufo.toBack();

        // Adding all the shapes to the UFO group
        return ufo;
    }

    /**
     * Updates the position of the UFO based on the direction and canvas boundaries.
     * @param canvas The Group representing the canvas where the UFO is drawn.
     * @param deltaTime The time passed since the last update.
     */
    @Override
    public void update(Group canvas, double deltaTime){
        this.x = data.direction + x % 800;
        this.y = data.direction + y % 750;

        if (this.x < 0) x+=800;
        if (this.y < 0) y+=750;

        //System.out.println(x + "     "+y);
        redraw(canvas);
    }

    /**
     * Removes and redraws the UFO in the updated position based on the animation.
     * @param canvas The Group representing the canvas where the UFO is drawn.
     */
    @Override
    public void redraw(Group canvas) {
        // Remove previous frame
        canvas.getChildren().remove(ufo);

        // Draw new UFO and add it to the canvas
        ufo = draw(canvas, 800, 800);

        canvas.getChildren().add(ufo);
        ufo.toBack();
    }

    /**
     * Returns the UFO group holding the drawn shapes
     * @return UFO group
     */
    @Override
    public Group getNodes() {
        return ufo;
    }
}