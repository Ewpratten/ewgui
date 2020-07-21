package ca.retrylife.ewgui.content.containers;

import java.awt.Graphics2D;
import java.awt.Point;

import ca.retrylife.ewgui.content.components.Component;
import ca.retrylife.ewgui.datatypes.Size;

/**
 * A row of components
 */
public class Row extends Component {

    // Containing components
    private Component[] components;

    // Sizing
    private static final int PADDING_PX = 10;

    /**
     * Create a component Row
     * 
     * @param components All children
     */
    public Row(Component... components) {

        // Set all components
        this.components = components;

    }

    @Override
    public void render(Point origin, Graphics2D gc) {

        int currentWidth = (int) origin.getX() + PADDING_PX;
        int maxHeight = getMinHeight();

        for (Component component : components) {
            // Get the component's height
            int componentHeight = component.getMinHeight();

            // Determine how much vertical padding to provide
            int topPadding = Math.max(0, (maxHeight - componentHeight) / 2);

            // Render the component at it's place
            component.render(new Point(currentWidth, (int)origin.getY() + topPadding), gc);

            // Add to the current width
            currentWidth += component.getSize().getWidth() + PADDING_PX;
        }
    }

    @Override
    public void setSize(Size<Integer> size) {
        super.setSize(size);

        // Determine the component sizes
        int totalSize = size.getWidth() - (PADDING_PX * (components.length + 1));
        Integer widthPerComponent = totalSize / components.length;
        Integer heightPerComponent = (size.getHeight() != Size.AUTO)? size.getHeight() - (PADDING_PX * 2) : getMinHeight();

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

        // Find the largest of all the components
        int height = 0;

        for (Component component : components) {
            int componentHeight = component.getMinHeight();

            if (componentHeight > height) {
                height = componentHeight;
            }
        }

        return height;
    }

}