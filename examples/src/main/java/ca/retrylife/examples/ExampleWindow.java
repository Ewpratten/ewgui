package ca.retrylife.examples;

import ca.retrylife.ewgui.Window;
import ca.retrylife.ewgui.WindowBuilder;
import ca.retrylife.ewgui.Window.ConfigurationFlags;
import ca.retrylife.ewgui.content.containers.Col;
import ca.retrylife.ewgui.content.containers.Row;
import ca.retrylife.ewgui.content.components.Label;
import ca.retrylife.ewgui.datatypes.Text;

public class ExampleWindow {
    public static void main(String[] args) {
        new ExampleWindow();
    }

    public ExampleWindow() {

        // Content
        // @formatter:off
        Col content = new Col(
            new Row(
                new Label(new Text("Hello, world")),
                new Label(new Text("There are two of us!"))
            )
        );
        // @formatter:on

        // Create a window
        Window window = new WindowBuilder("Example window").withConfiguration(ConfigurationFlags.EXIT_ON_CLOSE)
                .withContent(content).build();
    }
}