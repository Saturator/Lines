package com.linesengine.engine;

import java.awt.*;
import javax.swing.*;

/**
 * GameWindow is the window we play our GameProject within.
 */
public class GameWindow extends JPanel
{   
    public String title;
    public Color background;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH / 12 * 9;
    private final GameProject project;
    
    JLabel fps;
    
    /**
     * Creates a new game window.
     * @param title title of the window
     * @param project game project to be linked to the window
     */
    public GameWindow(String title, GameProject project)
    {
        this.project = project;
        this.title = title;
        this.background = Color.BLACK;
        
        JFrame frame = new JFrame(title); 
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  
        this.setFocusable(true);
        this.requestFocus();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void setBakcgroundColor(Color c)
    {
        this.background = c;
    }
    
    /**
     * Renders the background and calls a scene to render all of its objects.
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(background);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        this.project.getScene(0).render(g);
        g.dispose();
    }
}
