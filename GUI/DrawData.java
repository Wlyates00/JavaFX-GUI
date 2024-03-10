package gui;

import java.util.Stack;

/**
 * Represents the data associated with the drawings in the GUI.
 */
public class DrawData {

    /** The direction of the drawings on the canvas. */
    public int direction = 1;

    /** The previous direction of the drawings on the canvas. */
    public int prevDirection = 0;

    /** Stack to manage the drawing objects. */
    Stack<AnimatedDrawing> drawingStack;

    /** Flag indicating whether the bird drawing is enabled. */
    boolean bird = false;

    /** Flag indicating whether the rocket drawing is enabled. */
    boolean rocket = false;

    /** Flag indicating whether the UFO drawing is enabled. */
    boolean ufo = false;

    /**
     * Constructs a new DrawData object with an empty drawing stack.
     */
    DrawData() {
        drawingStack = new Stack<>();
    }
}
