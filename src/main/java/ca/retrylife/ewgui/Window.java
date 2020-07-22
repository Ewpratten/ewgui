package ca.retrylife.ewgui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.retrylife.ewgui.content.components.Component;
import ca.retrylife.ewgui.datatypes.Size;
import ca.retrylife.ewgui.datatypes.UserInput;
import ca.retrylife.ewgui.datatypes.UserInput.MouseState;
import ca.retrylife.ewgui.theming.Style;

public class Window extends JComponent {
    private static final long serialVersionUID = -3278610621086270623L;

    // Window frame
    private JFrame frame;

    // Components
    private Component navbar;
    private Component content;

    // Window style
    private Style style;

    // User input
    private UserInput input;
    private Thread inputThread;
    private MouseState latestMouseState = MouseState.NONE;

    // Running
    private Timer paintTimer;
    private boolean running = false;

    // pre-render
    private Runnable preRenderAction;

    public enum ConfigurationFlags {
        EXIT_ON_CLOSE, NO_RESIZE, SPAWN_CENTRE
    }

    protected Window(String title, Component navbar, Component content, Style style, ConfigurationFlags... flags) {

        // Configure frame
        this.frame = new JFrame(title);
        frame.add(this);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(800, 600));

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

        // Set theme
        this.style = style;

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

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Do a little math to solve click state
                switch (e.getModifiersEx() / 1024) {
                    case MouseEvent.BUTTON1:
                        latestMouseState = MouseState.LCLICK;
                        break;
                    case MouseEvent.BUTTON2:
                        latestMouseState = MouseState.MCLICK;
                        break;
                    case MouseEvent.BUTTON3:
                        latestMouseState = MouseState.RCLICK;
                        break;
                }

            }
        });

        // Configure input thread
        inputThread = new Thread(this::handleUserInput);
        paintTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                repaint();
            }
        });

        // Show the window
        frame.setVisible(true);
        running = true;
        inputThread.start();
        paintTimer.start();

    }

    /**
     * Set an action to be run before each render
     * @param action Pre-render action
     */
    public void setPreRenderLoop(Runnable action){
        this.preRenderAction = action;
    }

    /**
     * Hide the window and stop inputs
     */
    public void hide() {
        frame.setVisible(false);
        paintTimer.stop();
        running = false;
    }

    /**
     * Show the window and start inputs
     */
    public void show() {
        frame.setVisible(true);
        running = true;
        inputThread.start();
        paintTimer.start();
    }

    private void resize(Size<Integer> size) {
        navbar.setSize(new Size<Integer>(size.getWidth(), navbar.getSize().getHeight()));
        content.setSize(new Size<Integer>(size.getWidth(), content.getSize().getHeight()));
    }

    @Override
    public void paint(Graphics arg0) {
        Graphics2D gc = (Graphics2D) arg0;

        // Handle pre-render
        if(this.preRenderAction != null){
            this.preRenderAction.run();
        }

        // Tracker for height
        int currentHeight = 0;

        synchronized (gc) {
            // Fill the BG
            gc.setBackground(style.getBackgroundColor());

            // Render navbar
            navbar.render(new Point(0, currentHeight), gc, style);

            // Add navbar height to current
            currentHeight += navbar.getMinHeight();

            // Render content
            content.render(new Point(0, currentHeight), gc, style);
        }

    }

    private void handleUserInput() {

        while (running) {
            // Get mouse position
            Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(mouseLoc, this);

            // Get mouse click
            MouseState mouseState = latestMouseState;
            latestMouseState = MouseState.NONE;

            // Create input object
            input = new UserInput(mouseState, mouseLoc, '\0');

            // Call children with input data
            navbar.acceptInput(input);
            content.acceptInput(input);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {

            }
        }
    }

}