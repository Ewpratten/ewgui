package ca.retrylife.ewgui.content.containers;

import java.awt.Graphics2D;
import java.awt.Point;

import ca.retrylife.ewgui.content.components.Component;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.theming.Style;

public class Col extends Component {
    // Containing components
    private Component[] components;

    // Sizing
    private static final int PADDING_PX = 10;

    /**
     * Create a component Row
     * 
     * @param components All children
     */
    public Col(Component... components) {

        // Set all components
        this.components = components;

    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {

        int currentHeight = (int) origin.getY() + PADDING_PX;

        synchronized (gc) {
            for (Component component : components) {

                // Render the component at it's place
                component.render(new Point((int)origin.getX(), currentHeight), gc, style);

                // Add to the current width
                currentHeight += component.getSize().getHeight() + PADDING_PX;
            }
        }
    }

    @Override
    public void setSize(Size<Integer> size) {
        super.setSize(size);

        // Determine the component sizes
        int totalSize = size.getWidth() - (PADDING_PX * (components.length + 1));
        Integer widthPerComponent = totalSize / Math.max(1, components.length);
        Integer heightPerComponent = (size.getHeight() != Size.AUTO) ? size.getHeight() - (PADDING_PX * 2)
                : getMinHeight();

        for (Component component : components) {
            // Set size
            component.setSize(new Size<Integer>(widthPerComponent, heightPerComponent));
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        // Set all children
        for (Component component : components) {
            component.setEnabled(enabled);
        }
    }

    @Override
    public int getMinHeight() {

        // Find the sum of all component heights and padding
        int height = PADDING_PX;

        for (Component component : components) {
            height += component.getMinHeight() + PADDING_PX;
        }

        return height;
    }
}