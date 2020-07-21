package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.theming.Style;

/**
 * Labels are printable text
 */
public class Label extends Component {

    // Text
    private Text text;

    /**
     * Create a label
     * 
     * @param text Text
     */
    public Label(Text text) {
        setText(text);
    }

    /**
     * Set new label text
     * 
     * @param text Text
     */
    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {

        // TODO: Handle font setting

        // Get font info
        FontMetrics fm = gc.getFontMetrics();

        // Determine the text size
        Rectangle2D bounds = fm.getStringBounds(this.text.getText(), gc);
        int height = (int) bounds.getHeight();
        int width = (int) bounds.getWidth();

        // Get the allowed height and width
        Size<Integer> maxSize = getSize();
        int maxWidth = maxSize.getWidth();
        int maxHeight = (maxSize.getHeight() != null) ? maxSize.getHeight() : getMinHeight();

        // Determine string X/Y
        int x = (int) origin.getX() + Math.max(0, ((maxWidth - width) / 2));
        int y = (int) origin.getY() + Math.max(0, ((maxHeight - height) / 2)) + height;

        // Render text
        gc.setColor((getEnabled())? style.getNormalColor() : style.getDisabledColor());
        gc.drawString(this.text.getText(), x, y);

    }

    @Override
    public int getMinHeight() {
        return 50;
    }

}