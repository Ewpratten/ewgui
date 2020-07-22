package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.function.Consumer;
import java.awt.BasicStroke;

import ca.retrylife.ewgui.content.RenderUtil;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.datatypes.UserInput.MouseState;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

public class Toggle extends Component {

    // Toggle data
    private Text text;
    private Consumer<Boolean> onToggle;
    private boolean toggle = false;
    private ComponentState currentState = ComponentState.NORMAL;
    private Point origin = new Point(0, 0);

    public Toggle(Text text, Consumer<Boolean> onToggle) {
        this.text = text;
        this.onToggle = onToggle;
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        this.origin = origin;

        // Get Toggle sizing
        int height = (getSize().getHeight() != Size.AUTO) ? getSize().getHeight() : getMinHeight();
        int width = getSize().getWidth();

        // Get the current color theme
        ColorSet colors = RenderUtil.getColorForState(currentState, style);

        // Render BG
        gc.setColor(colors.bg);
        gc.fillRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);

        // Render border
        gc.setColor(colors.secondary);
        gc.setStroke(new BasicStroke(1));
        gc.drawRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);

        // Render text
        RenderUtil.renderCentredText(text, origin, getSize(), colors, getMinHeight(), gc);

    }

    @Override
    public void acceptInput(UserInput input) {

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
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        // Set the component state
        if (enabled) {
            currentState = ComponentState.NORMAL;
        } else {
            currentState = ComponentState.DISABLED;
        }
    }

    @Override
    public int getMinHeight() {
        return Component.GLOBAL_DEFAULT_MIN_HEIGHT;
    }

}