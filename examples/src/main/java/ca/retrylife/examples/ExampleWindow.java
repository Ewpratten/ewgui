package ca.retrylife.examples;

import ca.retrylife.ewgui.Window;
import ca.retrylife.ewgui.WindowBuilder;
import ca.retrylife.ewgui.Window.ConfigurationFlags;
import ca.retrylife.ewgui.content.containers.Col;
import ca.retrylife.ewgui.content.containers.Row;
import ca.retrylife.ewgui.content.components.Component;
import ca.retrylife.ewgui.content.components.Button;
import ca.retrylife.ewgui.content.components.Label;
import ca.retrylife.ewgui.datatypes.Text;

public class ExampleWindow {
    public static void main(String[] args) {
        new ExampleWindow();
    }

    public ExampleWindow() {

        // Content
        // @formatter:off
        Component content = new Row(
            new Label(new Text("Hello, world")),
            new Label(new Text("There are two of us!")),
            new Button(
                new Text("I am a button!"),
                () -> {
                    System.out.println("The button has been pressed");
                }
            )
            
        );
        // @formatter:on

        // Create a window
        Window window = new WindowBuilder("Example window").withConfiguration(ConfigurationFlags.EXIT_ON_CLOSE)
                .withContent(content).build();
    }
}