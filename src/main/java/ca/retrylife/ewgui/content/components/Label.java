package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

import ca.retrylife.ewgui.content.RenderUtil;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

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

        // Get the current color set
        ColorSet colors = RenderUtil.getColorForState((getEnabled()) ? ComponentState.NORMAL : ComponentState.DISABLED,
                style);

        // Render the text
        RenderUtil.renderCentredText(text, origin, getSize(), colors, getMinHeight(), gc);

    }

    @Override
    public int getMinHeight() {
        return Component.GLOBAL_DEFAULT_MIN_HEIGHT;
    }

}