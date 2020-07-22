package ca.retrylife.ewgui.content.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;

import java.util.function.Consumer;

import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.datatypes.UserInput.MouseState;
import ca.retrylife.ewgui.rendering.RenderUtil;
import ca.retrylife.ewgui.theming.Style;
import ca.retrylife.ewgui.theming.Style.ColorSet;

/**
 * A slider that can slide between two values
 * 
 * @param <T> Number type of the slider values
 */
public class Slider<T extends Number> extends Component {

    // Data
    protected T value;
    protected T minValue;
    protected T maxValue;
    private Consumer<T> onChange;
    protected ComponentState currentState = ComponentState.NORMAL;
    protected Point origin = new Point(0, 0);
    private T possibleNewValue;

    /**
     * Create a slider with a default range of 0-100
     * 
     * @param onChange To be run when the slider changes value
     */
    @SuppressWarnings("unchecked")
    public Slider(Consumer<T> onChange) {
        // This is a little bit of magic that lets you set a default of an unknown type.
        // Since ints are castable to Integers, and both Integer and T extend
        // java.lang.Number,
        // we can double-cast an int to a T.
        this((T) (Integer) 0, (T) (Integer) 100, onChange);
    }

    /**
     * Create a Slider
     * 
     * @param minValue Minimum value
     * @param maxValue Maximum value
     * @param onChange To be run when the slider changes value
     */
    public Slider(T minValue, T maxValue, Consumer<T> onChange) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        setValue(minValue);
        this.onChange = onChange;

    }

    /**
     * Set the current value
     * 
     * @param value Value
     */
    public void setValue(T value) {
        if (getEnabled()) {
            this.value = value;
            this.possibleNewValue = value;
        }
    }

    /**
     * Get the value
     * 
     * @return Value
     */
    public T getValue() {
        return this.value;
    }

    @Override
    public void render(Point origin, Graphics2D gc, Style style) {
        this.origin = origin;

        // Determine box height
        int boxHeight = Math.max(Math.min(30, getSize().getHeight()), getSize().getHeight() / 2);
        int topPadding = Math.max(0, (getSize().getHeight() - boxHeight) / 2);
        int innerGap = 5;

        // Get the color set
        ColorSet colors = RenderUtil.getColorForState(currentState, style);

        // Render the box BG
        // This ignores the color set
        if (currentState.equals(ComponentState.DISABLED)) {
            gc.setColor(style.getDisabled().bg);
        } else {
            gc.setColor(style.getNormal().bg);
        }

        gc.fillRect(origin.x, origin.y + topPadding, getSize().getWidth(), boxHeight);

        // Render the border
        gc.setColor(colors.primary);
        gc.setStroke(new BasicStroke(1));
        gc.drawRect(origin.x, origin.y + topPadding, getSize().getWidth(), boxHeight);

        // Calculate the value -> pixels for real value and for possible new value
        int realValuePX = calculatePixelsForValue(this.value);
        int possibleValuePX = calculatePixelsForValue(this.possibleNewValue);

        // Render the fill bar for the real value
        gc.setColor(style.getNormal().secondary);
        gc.fillRect(origin.x + 1, origin.y + innerGap + topPadding, realValuePX, boxHeight - (innerGap * 2));

        // If the possible value is not the same as the real one, render it ontop
        if (this.possibleNewValue.doubleValue() != this.value.doubleValue()) {
            gc.setColor(colors.secondary);
            gc.fillRect(origin.x + 1, origin.y + innerGap + topPadding, possibleValuePX, boxHeight - (innerGap * 2));
        }

    }

    protected int calculatePixelsForValue(T value) {
        int basePX = 3;
        double percentFull = (value.doubleValue() - minValue.doubleValue())
                / (maxValue.doubleValue() - minValue.doubleValue());
        return (int) (getSize().getWidth() * percentFull) + basePX;
    }

    @SuppressWarnings("unchecked")
    protected T interpolateValue(Point origin, Size<Integer> size, int px) {

        // Calculate percent full
        double percentage = ((double) px - origin.getX()) / (double) size.getWidth();

        // Calculate adjusted fill
        double adjustedFill = (maxValue.doubleValue() - minValue.doubleValue()) * percentage;

        // Re-adjust fill
        return (T) (Number) (adjustedFill + minValue.doubleValue());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void acceptInput(UserInput input) {

        // Skip User input if disabled
        if (!getEnabled()) {
            return;
        }

        // Handle hover
        if (input.getPoint().x > origin.x && input.getPoint().x < origin.x + getSize().getWidth()
                && input.getPoint().y > origin.y && input.getPoint().y < origin.y + getSize().getHeight()) {
            currentState = ComponentState.FOCUSED;

            // Calculate the possible value based on mouse position
            this.possibleNewValue = interpolateValue(origin, getSize(), input.getPoint().x);

            // Handle click
            if (input.getState().equals(MouseState.LCLICK)) {
                currentState = ComponentState.PRESSED;

                // Set the value
                this.value = this.possibleNewValue;

                // Run callback
                if (onChange != null) {

                    // Here, we do some runtime type-casting magic.
                    // Java gets weird with Number objects and math sometimes
                    // See https://docs.oracle.com/javase/8/docs/api/java/lang/Number.html
                    switch (this.minValue.getClass().getTypeName()) {
                        case "java.lang.Byte":
                            onChange.accept((T) (Byte) this.value.byteValue());
                            break;
                        case "java.lang.Short":
                            onChange.accept((T) (Short) this.value.shortValue());
                            break;
                        case "java.lang.Integer":
                            onChange.accept((T) (Integer) this.value.intValue());
                            break;
                        case "java.lang.Long":
                            onChange.accept((T) (Long) this.value.longValue());
                            break;
                        case "java.lang.Double":
                            onChange.accept((T) (Double) this.value.doubleValue());
                            break;
                        case "java.lang.Float":
                            onChange.accept((T) (Float) this.value.floatValue());
                            break;
                    }
                }
            }

        } else {
            currentState = ComponentState.NORMAL;
            this.possibleNewValue = value;
        }
    }

    @Override
    public Component setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        // Set the component state
        if (enabled) {
            currentState = ComponentState.NORMAL;
        } else {
            currentState = ComponentState.DISABLED;
        }

        return this;
    }

    @Override
    public int getMinHeight() {
        return Component.GLOBAL_DEFAULT_MIN_HEIGHT;
    }

}