package ca.retrylife.ewgui.rendering;

import java.awt.Point;

import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.theming.Style;

import java.awt.Graphics2D;

public interface Renderable {

    /**
     * Render this component
     * 
     * @param origin The top left coordinate of this component on the screen. Start
     *               drawing here.
     * @param gc     Graphics
     * @param style  Window style
     */
    public void render(Point origin, Graphics2D gc, Style style);

    /**
     * Accept user input
     * 
     * @param input User input
     */
    public default void acceptInput(UserInput input) {
    }

}