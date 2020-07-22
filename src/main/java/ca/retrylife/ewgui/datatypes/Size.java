package ca.retrylife.ewgui.datatypes;

/**
 * A Size represents anywhere from a 1D to a 3D size
 * 
 * @param <T> Quantity type
 */
public class Size<T> {

    // Use this to specify an automatic size in a dimension
    public static Object AUTO = null;

    // Size data
    private T width;
    private T height;
    private T depth;

    /**
     * Create a size with an automatic height and depth
     * 
     * @param width Width
     */
    public Size(T width) {
        this(width, null);
    }

    /**
     * Create a size with an automatic depth
     * 
     * @param width  Width
     * @param height Height
     */
    public Size(T width, T height) {
        this(width, height, null);
    }

    /**
     * Create a size
     * 
     * @param width  Width
     * @param height Height
     * @param depth  Depth
     */
    public Size(T width, T height, T depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    /**
     * Get the width
     * 
     * @return Width
     */
    public T getWidth() {
        return this.width;
    }

    /**
     * Get the height
     * 
     * @return Height
     */
    public T getHeight() {
        return this.height;
    }

    /**
     * Get the height, or default to a value if it is auto
     * 
     * @param def Default value
     * @return Height or default
     */
    public T getHeightOrDefault(T def) {
        if (getHeight() == Size.AUTO) {
            return def;
        }
        return getHeight();
    }

    /**
     * Get the depth
     * 
     * @return Depth
     */
    public T getDepth() {
        return this.depth;
    }

    /**
     * Get the depth, or default to a value if it is auto
     * 
     * @param def Default value
     * @return Depth or default
     */
    public T getDepthOrDefault(T def) {
        if (getDepth() == Size.AUTO) {
            return def;
        }
        return getDepth();
    }
}