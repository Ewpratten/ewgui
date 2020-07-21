package ca.retrylife.ewgui.theming.styles;

import java.awt.Color;

import ca.retrylife.ewgui.theming.Style;

public class DefaultStyle extends Style {

    @Override
    public Color getBackgroundColor() {
        return new Color(0xf5, 0xf5, 0xf5);
    }

    @Override
    public Color getNormalColor() {
        return new Color(0x68, 0x68, 0x68);
    }

    @Override
    public Color getNormalSecondaryColor() {
        return new Color(0xc9, 0xc9, 0xc9);
    }

    @Override
    public Color getFocusedColor() {
        return new Color(0x6c, 0x9b, 0xbc);
    }

    @Override
    public Color getFocusedSecondaryColor() {
        return new Color(0x6c, 0x9b, 0xbc);
    }

    @Override
    public Color getPressedColor() {
        return new Color(0x6c, 0x9b, 0xbc);
    }

    @Override
    public Color getPressedSecondaryColor() {
        return new Color(0x6c, 0x9b, 0xbc);
    }

    @Override
    public Color getDisabledColor() {
        return new Color(0x6c, 0x9b, 0xbc);
    }

    @Override
    public Color getDisabledSecondaryColor() {
        return new Color(0x6c, 0x9b, 0xbc);
    }

}