package ca.retrylife.ewgui.content.components;

import ca.retrylife.ewgui.content.containers.Col;
import ca.retrylife.ewgui.datatypes.Text;

/**
 * A component with a label above it
 */
public class NamedComponent extends Col {
    
    /**
     * Create a Named Component
     * @param name Name
     * @param component Component
     */
    public NamedComponent(Text name, Component component){
        super(new Label(name), component);
    }
}