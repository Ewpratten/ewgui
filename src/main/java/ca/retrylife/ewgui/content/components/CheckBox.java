package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.function.Consumer;
import java.awt.BasicStroke;

import ca.retrylife.ewgui.content.RenderUtil;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

/**
 * CheckBox component
 */
public class CheckBox extends Toggle {

    /**
     * Create a CheckBox
     * 
     * @param text     Text for the checkbox
     * @param onChange This is called with every state change
     */
    public CheckBox(Text text, Consumer<Boolean> onChange) {
        this(false, text, onChange);
    }

    /**
     * Create a CheckBox
     * 
     * @param enabledByDefault Is this box checked by default?
     * @param text             Text for the checkbox
     * @param onChange         This is called with every state change
     */
    public CheckBox(boolean enabledByDefault, Text text, Consumer<Boolean> onChange) {
        super(enabledByDefault, text, onChange);
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        super.origin = origin;

        // Determine size of the checkbox
        int checkBoxSize = Math.max(Math.min(30, getSize().getHeight()), getSize().getHeight() / 2);
        int topPadding = Math.max(0, (getSize().getHeight() - checkBoxSize) / 2);
        int checkGap = 3;

        System.out.println(checkBoxSize + " " + topPadding + " " + getSize().getHeight());

        // Get the color theme
        ColorSet colors = RenderUtil.getColorForState(super.currentState, style);

        // Render the checkbox border
        int borderSize = 1;
        gc.setStroke(new BasicStroke(borderSize));
        gc.setColor(colors.primary);
        gc.drawRect(origin.x, origin.y + topPadding, checkBoxSize - borderSize, checkBoxSize - borderSize);

        // Render the "check" if needed
        if (super.currentState.equals(ComponentState.PRESSED) || super.currentState.equals(ComponentState.FOCUSED)) {
            gc.fillRect(origin.x + checkGap, origin.y + topPadding + checkGap, checkBoxSize - checkGap * 2,
                    checkBoxSize - checkGap * 2);
        }

        // Determine text position
        Point textPose = new Point((origin.x + checkBoxSize) + (topPadding / 2),
                origin.y + ((checkBoxSize - topPadding) / 2));

        // Render the checkbox text
        RenderUtil.renderPositionedText(text, textPose, colors, getSize(), getMinHeight(), gc);

    }

}