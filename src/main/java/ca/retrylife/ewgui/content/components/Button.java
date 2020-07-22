package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;

import ca.retrylife.ewgui.content.RenderUtil;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

public class Button extends Component {

    // Button data
    private Text text;
    private Runnable onPressed;
    private ComponentState currentState = ComponentState.NORMAL;
    private Point origin = new Point(0, 0);

    public Button(Text text, Runnable onPressed) {
        this.text = text;
        this.onPressed = onPressed;
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        this.origin = origin;

        // Get button sizing
        int height = (getSize().getHeight() != Size.AUTO) ? getSize().getHeight() : getMinHeight();
        int width = getSize().getWidth();

        // Get the current color theme
        ColorSet colors = RenderUtil.getColorForState(currentState, style);

        // Render BG
        gc.setColor(colors.bg);
        gc.fillRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);

        // Render border
        gc.setColor(colors.secondary);
        gc.setStroke(new BasicStroke(3));
        gc.drawRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);

        // Render text
        RenderUtil.renderCentredText(text, origin, getSize(), colors, getMinHeight(), gc);

    }

    @Override
    public void acceptInput(UserInput input) {

        System.out.println(input.getPoint().y + " " + getSize().getHeight());
        
        // Handle hover
        if (input.getPoint().x > origin.x && input.getPoint().x < origin.x + getSize().getWidth() && input.getPoint().y > origin.y && input.getPoint().y < origin.y + getSize().getHeight()) {
            currentState = ComponentState.FOCUSED;
        } else {
            currentState = ComponentState.NORMAL;
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