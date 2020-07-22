package ca.retrylife.ewgui.theming.styles;

import java.awt.Color;

import ca.retrylife.ewgui.theming.Style;

public class DefaultStyle extends Style {

    @Override
    public Color getBackgroundColor() {
        return new Color(0xf5, 0xf5, 0xf5);
    }

    @Override
    public ColorSet getNormal() {
        // new Color(0x97, 0xe8, 0xff)
        return new ColorSet(new Color(0x68, 0x68, 0x68),  new Color(0x6c, 0x9b, 0xbc), new Color(0xc9, 0xc9, 0xc9));
    }

    @Override
    public ColorSet getFocused() {
        return new ColorSet(new Color(0x6c, 0x9b, 0xbc), new Color(0x97, 0xe8, 0xff), new Color(0xc9, 0xef, 0xfe));
    }

    @Override
    public ColorSet getPressed() {
        return new ColorSet(new Color(0x36, 0x8b, 0xaf),  new Color(0x6c, 0x9b, 0xbc), new Color(0x97, 0xe8, 0xff));
    }

    @Override
    public ColorSet getDisabled() {
        return new ColorSet(new Color(0xae, 0xb7, 0xb8), new Color(0xe6, 0xe9, 0xe9), new Color(0xe6, 0xe9, 0xe9));
    }

}