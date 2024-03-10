package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

/**
 * An abstract class representing an animated drawing in the GUI.
 * Subclasses must implement methods to update, redraw, and retrieve nodes.
 */
public abstract class AnimatedDrawing{
    DrawData data;
    boolean isAnimating = true;

    /**
     * Updates the animated drawing position based on the animate handle method.
     * @param canvas The Group representing the canvas where the drawing is displayed.
     * @param deltaTime The time elapsed since the last frame in seconds.
     */
    public abstract void update(Group canvas, double deltaTime);

    /**
     * Removes and redraws the animated drawing on the canvas.
     * @param canvas The Group representing the canvas where the drawing is displayed.
     */
    public abstract void redraw(Group canvas);

    /**
     * Gets the group associated with the animated drawing.
     * @return The Group containing the nodes of the drawing.
     */
    public abstract Group getNodes();

    /**
     * Stops the animation of the drawing.
     */
    public void stopAnimation(){
        isAnimating = false;
    }

    /**
     * Animates the drawing on the canvas with a specified frame rate.
     * @param canvas The Group representing the canvas where the drawing is displayed.
     * @param frameRate The desired frame rate for the animation.
     */
    public void animate(Group canvas, double frameRate) {
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            private double frameDuration = 1.0 / frameRate; // Calculate frame duration
            private double accumulatedTime = 0;

            @Override
            public void handle(long currentTime) {
                if (!isAnimating) { // Check if animation should stop
                    stop();
                    return;
                }
                if (lastTime == 0) {
                    lastTime = currentTime;
                    return;
                }

                // Calculate elapsed time since last frame
                double deltaTime = (currentTime - lastTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
                lastTime = currentTime;

                // Accumulate time and update while accumulated time is greater than frame duration
                accumulatedTime += deltaTime;
                while (accumulatedTime >= frameDuration) {
                    update(canvas, frameDuration);
                    accumulatedTime -= frameDuration;
                }
            }
        };

        timer.start();
    }
}
