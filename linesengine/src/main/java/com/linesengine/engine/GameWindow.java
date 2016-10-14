package com.linesengine.engine;

import com.linesengine.game.BoxPrimitive;
import com.linesengine.game.CirclePrimitive;
import com.linesengine.game.Game;
import com.linesengine.math.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * GameWindow is the window we play our GameProject within.
 */

public class GameWindow extends JPanel
{   
    public String title;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH / 12 * 9;
    
    private MouseEvent pressEvent;
    private MouseEvent relEvent;
    private MouseEvent lastRel;
    private KeyEvent keyPress;
    private Vector2 start;
    private Vector2 end;
    private GameProject project;
    private boolean circles;
    private int size = 50;
    int test = 0;
    /**
     * Creates a new game window.
     * @param title title of the window
     * @param project game project to be linked to the window
     */
    //TODO: add some mutator methods
    public GameWindow(String title, GameProject project)
    {
        this.project = project;
        this.title = title;
        
        JFrame frame = new JFrame(title); 
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  
        this.setFocusable(true);
        this.requestFocus();
        
        this.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
                pressEvent = e;
           }
           @Override
           public void mouseReleased(MouseEvent e) {
                relEvent = e;
           }
        });
        
        this.addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e) {
               keyPress = e;
               System.out.println(e);
           }
        });
       
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        //System.out.println("painting");
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 1000, 1000);
        if(pressEvent != null)
        {
            //g.setColor(Color.white);
            //g.fillRect(pressEvent.getX() - 50, pressEvent.getY() - 50, 100, 100);
            this.start = new Vector2(pressEvent.getX(), pressEvent.getY());
        }
        if(relEvent != null && relEvent != lastRel)
        {
            if(circles)
            {
                float radius = size;
                end = new Vector2(relEvent.getX() - (radius/2), relEvent.getY() - (radius/2));
                start = new Vector2(start.x - (radius/2), start.y - (radius/2));
                PhysicsBody body = new CircleBody(radius/2, start);
                Vector2 diff = new Vector2(end.x - start.x, end.y - start.y);
                diff.multiply(0.1f);
                body.setVelocity(diff);
                GameObject go = new CirclePrimitive("dodo", body, Color.white, radius);
                this.project.getScene(0).addGameObject(go);
            }
            else
            {
                System.out.println("went to else");
                end = new Vector2(relEvent.getX(), relEvent.getY());
                start = new Vector2(start.x, start.y);
                BoxBody body = new BoxBody(size);
                body.move(start);
                Vector2 diff = new Vector2(end.x - start.x, end.y - start.y);
                diff.multiply(0.1f);
                body.setVelocity(diff);
                GameObject go = new BoxPrimitive("boxy", body, Color.CYAN);
                this.project.getScene(0).addGameObject(go);       
            }
            lastRel = relEvent;
        }
        if(keyPress != null) 
        {
            if(keyPress.getKeyChar() == 'x') this.project.getScene(0).clearAllObjects();
            if(keyPress.getKeyChar() == 'c') circles = !circles;
            if(keyPress.getKeyChar() == 's') size -= 5;
            if(keyPress.getKeyChar() == 'd') size += 5;
            keyPress = null;
        }
            
        
        this.project.getScene(0).render(g);
        g.dispose();
    }
}
