package ca.retrylife.ewgui.content;

import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;

import ca.retrylife.ewgui.content.components.Component.ComponentState;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.theming.Style;

public class RenderUtil {

    public static void renderCentredText(Text text, Point origin, Size<Integer> boundingBox, ComponentState state,
            int minHeight, boolean enabled, Graphics2D gc, Style style) {

        // TODO: Handle font setting

        // Get font info
        FontMetrics fm = gc.getFontMetrics();

        // Determine the text size
        Rectangle2D bounds = fm.getStringBounds(text.getText(), gc);
        int height = (int) bounds.getHeight();
        int width = (int) bounds.getWidth();

        // Get the allowed height and width
        int maxWidth = boundingBox.getWidth();
        int maxHeight = (boundingBox.getHeight() != null) ? boundingBox.getHeight() : minHeight;

        // Determine string X/Y
        int x = (int) origin.getX() + Math.max(0, ((maxWidth - width) / 2));
        int y = (int) origin.getY() + Math.max(0, ((maxHeight - height) / 2)) + height;

        // Render text
        gc.setColor((getEnabled()) ? style.getNormalColor() : style.getDisabledColor());
        gc.drawString(this.text.getText(), x, y);

    }

    public static Color getColorForState(ComponentState state, Style style) {
        switch (state) {
            case DISABLED:
                return style.getDisabledColor();
            case FOCUSED:
                break;
            case NORMAL:
                break;
            case PRESSED:
                break;
            default:
                break;

        }
    }

}