package ca.retrylife.ewgui.content.components;

import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.rendering.Renderable;

/**
 * A renderable component
 */
public abstract class Component implements Renderable {

    public static final int GLOBAL_DEFAULT_MIN_HEIGHT = 60;

    /**
     * The state of the component
     */
    public enum ComponentState {
        NORMAL, FOCUSED, PRESSED, DISABLED
    }

    // Component size
    private Size<Integer> size = new Size<Integer>(0, (Integer) Size.AUTO);

    // Component enabled
    private boolean enabled = true;

    public Component() {

    }

    /**
     * Setter for the component size. This should be called by the component's
     * parent
     * 
     * @param size Size
     */
    public void setSize(Size<Integer> size) {
        this.size = size;
    }

    /**
     * Getter for the component size
     * 
     * @return Component size
     */
    public Size<Integer> getSize() {
        return this.size;
    }

    /**
     * Get the component's minimum height in px
     * 
     * @return Min height
     */
    public abstract int getMinHeight();

    /**
     * Set if the component is enabled (enabled by default)
     * 
     * @param enabled Enabled
     */
    public Component setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get if the component is enabled
     * 
     * @return Enabled
     */
    public boolean getEnabled() {
        return this.enabled;
    }

}