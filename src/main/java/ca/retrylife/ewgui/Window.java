package ca.retrylife.ewgui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;

import ca.retrylife.ewgui.content.components.Component;
import ca.retrylife.ewgui.content.containers.Row;
import ca.retrylife.ewgui.datatypes.Size;

public class Window extends JComponent {
    private static final long serialVersionUID = -3278610621086270623L;

    // Window frame
    private JFrame frame;

    // Components
    private Component navbar;
    private Component content;

    public enum ConfigurationFlags {
        EXIT_ON_CLOSE, NO_RESIZE, SPAWN_CENTRE
    }

    protected Window(String title, Component navbar, Component content, ConfigurationFlags... flags) {

        // Configure frame
        this.frame = new JFrame(title);
        frame.add(this);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(800,600));

        for (ConfigurationFlags flag : flags) {

            // Handle resize flag
            if (flag.equals(ConfigurationFlags.NO_RESIZE)) {
                frame.setResizable(false);
            } else {
                frame.setResizable(true);
            }

            // Handle exit on close
            if (flag.equals(ConfigurationFlags.EXIT_ON_CLOSE)) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }

        // Set containers
        this.navbar = navbar;
        this.content = content;

        // Resize handler
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                // Convert size types
                int width = (int) e.getComponent().getSize().getWidth();
                int height = (int) e.getComponent().getSize().getHeight();

                // Call resize
                resize(new Size<Integer>(width, height));

            }
        });

        // Show the window
        frame.setVisible(true);

    }

    private void resize(Size<Integer> size) {
        navbar.setSize(new Size<Integer>(size.getWidth(), navbar.getSize().getHeight()));
        content.setSize(new Size<Integer>(size.getWidth(), content.getSize().getHeight()));
    }

    @Override
    public void paint(Graphics arg0) {
        Graphics2D gc = (Graphics2D) arg0;

        // Tracker for height
        int currentHeight = 0;

        synchronized (gc) {
            // Render navbar
            navbar.render(new Point(0, currentHeight), gc);

            // Add navbar height to current
            currentHeight += navbar.getMinHeight();

            // Render content
            content.render(new Point(0, currentHeight), gc);
        }

        try{
            Thread.sleep(20);
        } catch (InterruptedException e) {
            
        }

    }

}