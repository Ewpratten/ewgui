package ca.retrylife.ewgui.content.components;

import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.rendering.Renderable;

/**
 * A renderable component
 */
public abstract class Component implements Renderable {

    // Component size
    private Size<Integer> size;

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

}