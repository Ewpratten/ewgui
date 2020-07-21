package ca.retrylife.ewgui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import ca.retrylife.ewgui.content.containers.Container;


public class Window extends JComponent {
    private static final long serialVersionUID = -3278610621086270623L;

    // Window frame
    private JFrame frame;

    public enum ConfigurationFlags{
        EXIT_ON_CLOSE, NO_RESIZE, SPAWN_CENTRE
    }

    protected Window(String title, Container navBar, Container content, ConfigurationFlags ... flags) {

        // Configure frame
        this.frame = new JFrame(title);
        frame.add(this);

        for (ConfigurationFlags flag : flags) {

            // Handle resize flag
            if (flag.equals(ConfigurationFlags.NO_RESIZE)) {
                frame.setResizable(false);
            } else {
                frame.setResizable(true);
            }
        }

        // Show the window
        frame.setVisible(true);

    }
    
    @Override
    public void paint(Graphics arg0) {
        Graphics2D gc = (Graphics2D) arg0;


    }
    
}