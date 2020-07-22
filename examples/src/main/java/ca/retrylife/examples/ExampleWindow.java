package ca.retrylife.examples;

import ca.retrylife.ewgui.Window;
import ca.retrylife.ewgui.WindowBuilder;
import ca.retrylife.ewgui.Window.ConfigurationFlags;
import ca.retrylife.ewgui.content.containers.Col;
import ca.retrylife.ewgui.content.containers.Row;
import ca.retrylife.ewgui.content.components.Component;
import ca.retrylife.ewgui.content.components.Button;
import ca.retrylife.ewgui.content.components.CheckBox;
import ca.retrylife.ewgui.content.components.Label;
import ca.retrylife.ewgui.content.components.NamedComponent;
import ca.retrylife.ewgui.content.components.ProgressBar;
import ca.retrylife.ewgui.content.components.Slider;
import ca.retrylife.ewgui.content.components.Toggle;
import ca.retrylife.ewgui.datatypes.Text;

public class ExampleWindow {
    public static void main(String[] args) {
        new ExampleWindow();
    }

    public ExampleWindow() {

        // A counter for the time
        ProgressBar<Integer> secondsCounter = new ProgressBar<>(0, 60);

        // @formatter:off

        // A row that needs to be controlled by a button
        Row row2 = new Row(
            new Label(new Text("Welcome to the second row!")),
            new Button(
                new Text("I hope you like it here"), 
                () -> {
                    System.out.println("This button does things too");
                }
            ),
            new Slider<Integer>(
                (value) -> {
                    System.out.println(String.format("Slider is now: %d", value));
                }
            ),
            secondsCounter
        );

        // The main content panel
        Component content = new Col(
            new Row(
                new Label(new Text("Hello, world")),
                new Label(new Text("There are two of us!")),
                new Button(
                    new Text("I am a button!"),
                    () -> {
                        System.out.println("The button has been pressed");
                    }
                ),
                new Toggle(
                    new Text("You can toggle me"),
                    (on) -> {
                        if (on) {
                            System.out.println("beep");
                        }else{
                            System.out.println("boop");
                        }
                    }
                ),
                new CheckBox(
                    new Text("Check me out"),
                    (on) -> {
                        if (on) {
                            System.out.println("I have been checked");
                        } else {
                            System.out.println("I have been unchecked");
                        }
                    }
                )    
            ),
            row2,
            new Row(
                new CheckBox(
                    true,
                    new Text("Enable Row 2"),
                    (enabled) -> {
                        row2.setEnabled(enabled);
                    }
                ),
                new NamedComponent(
                    new Text("I have a name"), 
                    new Slider<Double>(
                        0.0,
                        1.0,
                        (value) -> {
                            // Do nothing with the value
                        }
                    )
                )
            )
        );
        // @formatter:on

        // Create a window
        Window window = new WindowBuilder("Example window").withConfiguration(ConfigurationFlags.EXIT_ON_CLOSE)
                .withContent(content).build();

        // Event loop to update the seconds counter
        window.setPreRenderLoop(() -> {
            secondsCounter.setValue((int) (System.currentTimeMillis() / 1000) % 60);
        });
    }
}