package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;

import ca.retrylife.ewgui.datatypes.Text;

/**
 * Labels are printable text
 */
public class Label extends Component {

    // Text
    private Text text;

    /**
     * Create a label
     * @param text Text
     */
    public Label(Text text) {
        setText(text);
    }

    /**
     * Set new label text
     * @param text Text
     */
    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void render(Point origin, Graphics2D gc) {

    }

    @Override
    public int getMinHeight() {
        return 50;
    }

}