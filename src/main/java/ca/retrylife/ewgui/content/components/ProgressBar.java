package ca.retrylife.ewgui.content.components;

import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Graphics2D;

import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.rendering.RenderUtil;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

/**
 * A bar showing progress
 * 
 * @param <T> Value type
 */
public class ProgressBar<T extends Number> extends Slider<T> {

    /**
     * Create a ProgressBar
     * 
     * @param minValue Minimum value
     * @param maxValue Maximum value
     */
    public ProgressBar(T minValue, T maxValue) {
        super(minValue, maxValue, (x) -> {
        });
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        super.origin = origin;

        // Determine box height
        int boxHeight = Math.max(Math.min(30, getSize().getHeight()), getSize().getHeight() / 2);
        int topPadding = Math.max(0, (getSize().getHeight() - boxHeight) / 2);
        int innerGap = 5;

        // Get the color set
        ColorSet colors = RenderUtil.getColorForState(super.currentState, style);

        // Render the border
        gc.setColor(colors.primary);
        gc.setStroke(new BasicStroke(1));
        gc.drawRect(origin.x, origin.y + topPadding, getSize().getWidth(), boxHeight);

        // Calculate the value -> pixels
        int realValuePX = calculatePixelsForValue(super.value);

        // Render the fill bar for the real value
        if (super.currentState.equals(ComponentState.DISABLED)) {
            gc.setColor(style.getBackgroundColor());
        } else {
            gc.setColor(style.getNormal().secondary);
        }
        gc.fillRect(origin.x + 1, origin.y + innerGap + topPadding, realValuePX, boxHeight - (innerGap * 2));

    }

    @Override
    public void acceptInput(UserInput input) {
        // Do nothing here to stop user from modifying the value
    }
}