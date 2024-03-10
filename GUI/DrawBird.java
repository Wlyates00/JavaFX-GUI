package gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * This class represents a Bird drawing and extends the AnimatedDrawing class
 */
public class DrawBird extends AnimatedDrawing{
    // X and Y-Coordinate
    private int x,y;
    private Group bird;

    /**
     * Constructs a DrawBird object with initial parameters.
     * @param x The initial x-coordinate for the UFO.
     * @param y The initial y-coordinate for the UFO.
     * @param data The DrawData object containing shared data for animations.
     */
    DrawBird(int x, int y, DrawData data)
    {
        // X-coordinate for the UFO
        this.x = x;
        // Y-coordinate for the UFO
        this.y = y;
        // Data instance
        this.data = data;
    }

    /**
     * Draws the Bird to a group.
     * @param canvas The Group representing the Bird that will be drawn.
     * @param canvasWidth The width of the canvas.
     * @param canvasHeight The height of the canvas.
     * @return The Group containing the Bird shapes.
     */
    public Group draw(Group canvas, double canvasWidth, double canvasHeight) {
        // A group for a bird
        bird = new Group();

        // Creating 2 circles for the body and eye
        Circle body = new Circle(x, y,50);
        Circle eye = new Circle(x + 50, y - 80,5);

        // Creating 5 rectangles for the legs, feet and beak
        Rectangle beak = new Rectangle(x + 65, y - 75, 20, 5);
        Rectangle leg = new Rectangle(x + 10, y + 40, 5, 35);
        Rectangle leg2 = new Rectangle(x - 20, y + 40, 5, 35);
        Rectangle foot = new Rectangle(x - 20, y + 75, 15, 5);
        Rectangle foot2 = new Rectangle(x + 10, y + 75, 15, 5);

        // Creating 1 circle for the head
        Circle head = new Circle(x + 35, y - 70,30);


        // Coloring shapes
        eye.setFill(Color.BLACK);
        beak.setFill(Color.ORANGE);
        leg.setFill(Color.BLACK);
        leg2.setFill(Color.BLACK);
        foot.setFill(Color.BLACK);
        foot2.setFill(Color.BLACK);
        body.setFill(Color.LIGHTSKYBLUE);
        head.setFill(Color.LIGHTSKYBLUE);

        // Adding shapes to group
        bird.getChildren().addAll(foot, foot2, leg, leg2, body, head, beak, eye);

        // Pushing to the back layer
        bird.toBack();

        // Adding all the shapes to the UFO group
        return bird;
    }

    /**
     * Updates the position of the Bird based on the direction and canvas boundaries.
     * @param canvas The Group representing the canvas where the Bird is drawn.
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
     * Removes and redraws the Bird in the updated position based on the animation.
     * @param canvas The Group representing the canvas where the Bird is drawn.
     */
    @Override
    public void redraw(Group canvas) {
        // Remove previous frame
        canvas.getChildren().remove(bird);

        // Draw new UFO and add it to the canvas
        bird = draw(canvas, 800, 800);

        canvas.getChildren().add(bird);
        bird.toBack();
    }

    /**
     * Returns the Bird group holding the drawn shapes
     * @return Bird group
     */
    @Override
    public Group getNodes() {
        return bird;
    }
}
