package ca.retrylife.ewgui.datatypes;

import java.awt.Point;

/**
 * Struct for user input data
 */
public class UserInput {

    public enum MouseState {
        NONE, LCLICK, MCLICK, RCLICK
    }

    private MouseState state;
    private Point loc;
    private char kbd;

    /**
     * User input data
     * 
     * @param mouseState Current mouse state
     * @param mouseLoc   Mouse position
     * @param kbd        Last keypress
     */
    public UserInput(MouseState mouseState, Point mouseLoc, char kbd) {
        this.state = mouseState;
        this.loc = mouseLoc;
        this.kbd = kbd;
    }

    /**
     * Get the mouse's current state
     * 
     * @return State
     */
    public MouseState getState() {
        return this.state;
    }

    /**
     * Get the mouse's current position
     * 
     * @return Point
     */
    public Point getPoint() {
        return this.loc;
    }

    /**
     * Get the current keyboard char
     * 
     * @return KBD char
     */
    public char getKBD() {
        return this.kbd;
    }

}