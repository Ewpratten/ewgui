package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;

import ca.retrylife.ewgui.datatypes.Text;

public class Label extends Component {

    // Text
    private Text text;

    public Label(Text text) {
        setText(text);
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void render(Point origin, Graphics2D gc) {

    }

}