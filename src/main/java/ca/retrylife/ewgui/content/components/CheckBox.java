package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.function.Consumer;

import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.theming.Style;

/**
 * CheckBox component
 */
public class CheckBox extends Component {

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

    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {

    }

    @Override
    public int getMinHeight() {
        return Component.GLOBAL_DEFAULT_MIN_HEIGHT;
    }

}