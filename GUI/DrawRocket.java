package gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * This class represents a Rocket drawing and extends the AnimatedDrawing class
 */
public class DrawRocket extends AnimatedDrawing{
    private int x,y;

    private Group rocket;
    DrawData data;

    /**
     * Constructs a DrawRocket object with initial parameters.
     * @param x The initial x-coordinate for the Rocket.
     * @param y The initial y-coordinate for the Rocket.
     * @param data The DrawRocket object containing shared data for animations.
     */
    DrawRocket(int x, int y, DrawData data)
    {
        // X-coordinate
        this.x = x;
        // Y-coordinate
        this.y = y;
        // Data Instance
        this.data = data;
    }

    /**
     * Draws the Rocket to a group.
     * @param canvas The Group representing the Rocket that will be drawn.
     * @param canvasWidth The width of the canvas.
     * @param canvasHeight The height of the canvas.
     * @return The Group containing the Rocket shapes.
     */
    public Group draw(Group canvas, double canvasWidth, double canvasHeight) {
        // A group for the rocket
        rocket = new Group();

        // Width of drawing
        double width = 30;
        double height = 100;

        // Create body of rocket
        Rectangle body = new Rectangle(x + 0, y + 0, width, height);

        // Creating the cone of the rocket
        Polygon cone = new Polygon(x+15, y - 20,
                x+40, y + 10,
                x-10, y + 10
        );

        // Create wings of the rocket
        Rectangle wing = new Rectangle(x + 30, y + 75, 10, 40);
        Rectangle wing2 = new Rectangle(x - 10, y + 75, 10, 40);

        // Create the window
        Circle window = new Circle(x + 15,y + 40,10);

        // Color shapes
        cone.setFill(Color.ORANGE);
        body.setFill(Color.BLACK);
        wing.setFill(Color.GRAY);
        wing2.setFill(Color.GRAY);
        window.setFill(Color.CYAN);

        // Add shapes to rocket group
        rocket.getChildren().addAll(body, cone, wing, wing2,window);

        // Push rocket to the back layer
        rocket.toBack();

        // Adding all the shapes to the UFO group
        return rocket;
    }

    /**
     * Updates the position of the Rocket based on the direction and canvas boundaries.
     * @param canvas The Group representing the canvas where the Rocket is drawn.
     * @param deltaTime The time passed since the last update.
     */
    @Override
    public void update(Group canvas, double deltaTime){
        this.x = data.direction + x % 800;
        this.y = data.direction + y % 750;

        if (this.x < 0) x+=800;
        if (this.y < 0) y+=750;

        redraw(canvas);
    }

    /**
     * Removes and redraws the Rocket in the updated position based on the animation.
     * @param canvas The Group representing the canvas where the Rocket is drawn.
     */
    @Override
    public void redraw(Group canvas) {
        // Remove previous frame
        canvas.getChildren().remove(rocket);

        // Draw new UFO and add it to the canvas
        rocket = draw(canvas, 800, 800);

        canvas.getChildren().add(rocket);
        rocket.toBack();
    }

    /**
     * Returns the Rocket group holding the drawn shapes
     * @return Rocket group
     */
    @Override
    public Group getNodes() {
        return rocket;
    }
}
