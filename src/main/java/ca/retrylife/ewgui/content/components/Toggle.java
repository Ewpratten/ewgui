package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.function.Consumer;
import java.awt.BasicStroke;

import ca.retrylife.ewgui.rendering.RenderUtil;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.datatypes.UserInput.MouseState;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

/**
 * CheckBox component
 */
public class Toggle extends Component {

    // Toggle data
    protected Text text;
    private Consumer<Boolean> onToggle;
    private boolean toggle = false;
    protected ComponentState currentState = ComponentState.NORMAL;
    protected Point origin = new Point(0, 0);

    /**
     * Create a Toggle
     * 
     * @param text     Text inside toggle button
     * @param onToggle Consumer to accept state changes
     */
    public Toggle(Text text, Consumer<Boolean> onToggle) {
        this(false, text, onToggle);
    }

    /**
     * Create a Toggle
     * 
     * @param enabledByDefault Is this toggle toggled by default?
     * @param text             Text inside toggle button
     * @param onToggle         Consumer to accept state changes
     */
    public Toggle(boolean enabledByDefault, Text text, Consumer<Boolean> onToggle) {
        this.text = text;
        this.onToggle = onToggle;
        this.toggle = enabledByDefault;
    }

    /**
     * Set the toggle text
     * 
     * @param text Toggle text
     */
    public void setText(Text text) {
        this.text = text;
    }

    /**
     * Set the toggle state
     * 
     * @param toggle Is the toggle toggled?
     */
    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        this.origin = origin;

        // Get Toggle sizing
        int height = getSize().getHeightOrDefault(getMinHeight());
        int width = getSize().getWidth();

        // Get the current color theme
        ColorSet colors = RenderUtil.getColorForState(currentState, style);

        // Render BG
        gc.setColor(colors.bg);
        gc.fillRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);

        // Render border
        gc.setColor(colors.primary);
        gc.setStroke(new BasicStroke(1));
        gc.drawRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);

        // Render text
        RenderUtil.renderCentredText(text, origin, getSize(), colors, getMinHeight(), gc);

    }

    @Override
    public void acceptInput(UserInput input) {

        // Don't handle User Input if disabled
        if (!getEnabled()) {
            this.currentState = ComponentState.DISABLED;
            return;
        }

        // Handle hover
        if (input.getPoint().x > origin.x && input.getPoint().x < origin.x + getSize().getWidth()
                && input.getPoint().y > origin.y && input.getPoint().y < origin.y + getSize().getHeight()) {
            currentState = ComponentState.FOCUSED;

            // Handle click
            if (input.getState().equals(MouseState.LCLICK)) {
                currentState = ComponentState.PRESSED;

                // Toggle the toggle
                toggle = !toggle;

                // Run callback
                if (onToggle != null) {
                    onToggle.accept(toggle);
                }
            }

        } else {
            if (toggle) {
                currentState = ComponentState.PRESSED;
            } else {
                currentState = ComponentState.NORMAL;
            }
        }
    }

    @Override
    public int getMinHeight() {
        return Component.GLOBAL_DEFAULT_MIN_HEIGHT;
    }

}