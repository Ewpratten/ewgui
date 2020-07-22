package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;

import ca.retrylife.ewgui.rendering.RenderUtil;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.datatypes.UserInput.MouseState;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

/**
 * Clickable buttons
 */
public class Button extends Component {

    // Button data
    private Text text;
    private Runnable onPressed;
    private ComponentState currentState = ComponentState.NORMAL;
    private Point origin = new Point(0, 0);

    /**
     * Create a Button
     * 
     * @param text      Text inside button
     * @param onPressed Something to be run when the button is pressed
     */
    public Button(Text text, Runnable onPressed) {
        this.text = text;
        this.onPressed = onPressed;
    }

    /**
     * Set the button text
     * 
     * @param text Button text
     */
    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        this.origin = origin;

        // Get button sizing
        int height = getSize().getHeightOrDefault(getMinHeight());
        int width = getSize().getWidth();

        // Get the current color theme
        ColorSet colors = RenderUtil.getColorForState(currentState, style);

        // Render BG
        gc.setColor(colors.bg);
        gc.fillRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);

        // Render border
        gc.setColor(colors.primary);
        gc.setStroke(new BasicStroke(3));
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

                // Run callback
                if (onPressed != null) {
                    onPressed.run();
                }
            }

        } else {
            currentState = ComponentState.NORMAL;
        }
    }

    @Override
    public int getMinHeight() {
        return Component.GLOBAL_DEFAULT_MIN_HEIGHT;
    }

}