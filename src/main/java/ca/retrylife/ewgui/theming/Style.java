package ca.retrylife.ewgui.theming;

import java.awt.Color;

public abstract class Style {

    public abstract Color getBackgroundColor();

    public abstract Color getNormalColor();

    public abstract Color getNormalSecondaryColor();

    public abstract Color getFocusedColor();

    public abstract Color getFocusedSecondaryColor();

    public abstract Color getPressedColor();

    public abstract Color getPressedSecondaryColor();

    public abstract Color getDisabledColor();

    public abstract Color getDisabledSecondaryColor();
}