package ca.retrylife.ewgui.theming;

import java.awt.Color;

/**
 * Base class for themes/styles
 */
public abstract class Style {

    /**
     * A set of colors
     */
    public class ColorSet {

        /**
         * Primary color
         */
        public Color primary;

        /**
         * Secondary color
         */
        public Color secondary;

        /**
         * Component BG color
         */
        public Color bg;

        /**
         * Create a ColorSet
         * 
         * @param primary   Primary Color
         * @param secondary Secondary Color
         * @param bg        BG Color
         */
        public ColorSet(Color primary, Color secondary, Color bg) {
            this.primary = primary;
            this.secondary = secondary;
            this.bg = bg;
        }
    }

    /**
     * Get the window BG color
     * 
     * @return BG color
     */
    public abstract Color getBackgroundColor();

    /**
     * Get the normal color set
     * 
     * @return Normal ColorSet
     */
    public abstract ColorSet getNormal();

    /**
     * Get the focused color set
     * 
     * @return Focused ColorSet
     */
    public abstract ColorSet getFocused();

    /**
     * Get the pressed color set
     * 
     * @return Pressed ColorSet
     */
    public abstract ColorSet getPressed();

    /**
     * Get the disabled color set
     * 
     * @return Disabled ColorSet
     */
    public abstract ColorSet getDisabled();
}