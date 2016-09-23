package com.linesengine.engine;

import com.linesengine.game.Game;
import java.awt.*;
import javax.swing.*;

public class GameWindow extends Canvas
{   
    public String title;
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 12 * 9;
    
    //TODO: add some mutator methods
    public GameWindow(String title, GameProject project)
    {
        this.title = title;
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(project.game);
        frame.setVisible(true);
        project.game.start();
    }
}
