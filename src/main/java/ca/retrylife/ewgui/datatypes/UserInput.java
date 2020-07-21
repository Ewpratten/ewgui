package ca.retrylife.ewgui.datatypes;

import java.awt.Point;

public class UserInput {

    public enum MouseState{
        NONE, LCLICK, MCLICK, RCLICK
    }

    /**
     * User input data
     * @param mouseState Current mouse state
     * @param mouseLoc Mouse position
     * @param kbd Last keypress
     */
    public UserInput(MouseState mouseState, Point mouseLoc, char kbd){
        
    }
    
}