package ca.retrylife.ewgui.datatypes;

public class Text {

    // Data
    private String txt;
    private Font font;

    /**
     * Create Text with the default font
     * 
     * @param txt Text
     */
    public Text(String txt) {
        this(txt, Font.DEFAULT);
    }

    /**
     * Create Text with a custom font
     * 
     * @param txt  Text
     * @param font Font
     */
    public Text(String txt, Font font) {
        this.txt = txt;
        this.font = font;
    }

    /**
     * Get the text
     * 
     * @return Text
     */
    public String getText() {
        return this.txt;
    }

    /**
     * Get the font
     * 
     * @return Font
     */
    public Font getFont() {
        return this.font;
    }
}