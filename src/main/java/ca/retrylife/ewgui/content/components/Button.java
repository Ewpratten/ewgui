package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;

import ca.retrylife.ewgui.datatypes.Text;
import ca.retrylife.ewgui.theming.Style;

public class Button extends Component {

    // Button data
    private Text text;
    private Runnable onPressed;

    public Button(Text text, Runnable onPressed) {
        this.text = text;
        this.onPressed = onPressed;
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        
        // Get button sizing
        int height = (getSize().getHeight() != null) ? getSize().getHeight() : getMinHeight();
        int width = getSize().getWidth();

        

    }

    @Override
    public int getMinHeight() {
        return 50;
    }
    
}