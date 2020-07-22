package ca.retrylife.ewgui.rendering;

import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;

import ca.retrylife.ewgui.content.components.Component.ComponentState;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

/**
 * A util class for rendering things
 */
public class RenderUtil {

    /**
     * Render centred text
     * 
     * @param text        Text to render
     * @param origin      Origin point (top left of box)
     * @param boundingBox The allowed size to render in
     * @param colors      The theme's color set
     * @param minHeight   The minimum height of the component
     * @param gc          The Graphics2D context
     */
    public static void renderCentredText(Text text, Point origin, Size<Integer> boundingBox, ColorSet colors,
            int minHeight, Graphics2D gc) {

        // TODO: Handle font setting

        // Get font info
        FontMetrics fm = gc.getFontMetrics();

        // Determine the text size
        Rectangle2D bounds = fm.getStringBounds(text.getText(), gc);
        int height = (int) bounds.getHeight();
        int width = (int) bounds.getWidth();

        // Get the allowed height and width
        int maxWidth = boundingBox.getWidth();
        int maxHeight = boundingBox.getHeightOrDefault(minHeight);

        // Determine string X/Y
        int x = (int) origin.getX() + Math.max(0, ((maxWidth - width) / 2));
        int y = (int) origin.getY() + Math.max(0, ((maxHeight - height) / 2)) + height;

        // Render text
        gc.setStroke(new BasicStroke(1));
        gc.setColor(colors.primary);
        gc.drawString(text.getText(), x, y);

    }

    /**
     * Render text at a position
     * 
     * @param text        Text to render
     * @param position    Position to render at
     * @param colors      ColorSet to use
     * @param gc          Graphics context
     */
    public static void renderPositionedText(Text text, Point position, ColorSet colors,  Graphics2D gc) {

        // TODO: Handle font setting

        // Get font info
        FontMetrics fm = gc.getFontMetrics();

        // Determine the text size
        Rectangle2D bounds = fm.getStringBounds(text.getText(), gc);
        int height = (int) bounds.getHeight();

        // Determine string X/Y
        int x = position.x;
        int y = position.y + (height / 2);

        // Render text
        gc.setStroke(new BasicStroke(1));
        gc.setColor(colors.primary);
        gc.drawString(text.getText(), x, y);
    }

    /**
     * From a {@link ca.retrylife.ewgui.content.components.Component.ComponentState}
     * get the correct color from the selected {@link Style}
     * 
     * @param state State
     * @param style Style
     * @return Correct color set to use
     */
    public static ColorSet getColorForState(ComponentState state, Style style) {
        switch (state) {
            case DISABLED:
                return style.getDisabled();
            case FOCUSED:
                return style.getFocused();
            case NORMAL:
                return style.getNormal();
            case PRESSED:
                return style.getPressed();
            default:
                return style.getNormal();

        }
    }

}