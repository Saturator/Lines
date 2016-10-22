package com.linesengine.game;

import com.linesengine.engine.*;
import com.linesengine.math.*;
import java.awt.event.*;

/**
 * The game logic class, mostly dealing with reactions to input.
 */
public class Game implements MouseListener, KeyListener
{
    private GameProject project;
    
    private Vector2 startPt;
    private Vector2 endPt;
    private boolean alreadyReleased = false;
    private boolean spawnCircles = false;
    
    private float size = 50;
    
    public Game()
    {
        this.project = new GameProject("linesengine");
        this.project.addScene(new GameScene("test"));
        this.project.setMouseInput(this);
        this.project.setKeyInput(this);
        this.project.run();
    }
    
    public static void main(String[] args)
    {
        new Game();
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        alreadyReleased = false;
        startPt = new Vector2(e.getX(), e.getY());
        System.out.println(startPt);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(!alreadyReleased)
        {
            endPt = new Vector2(e.getX(), e.getY());
            
            if(!spawnCircles)
            {
                Box box = new Box(size, startPt);
                endPt.subtract(startPt);
                endPt.multiply(0.1f);
                box.physicsBody.setVelocity(endPt);
                project.getScene(0).addGameObject(box);
            }
            else
            {
                endPt = new Vector2(endPt.x - (size/2), endPt.y - (size/2));
                startPt = new Vector2(startPt.x - (size/2), startPt.y - (size/2));
                Circle circle = new Circle(size, startPt);
                endPt.subtract(startPt);
                endPt.multiply(0.1f);
                circle.physicsBody.setVelocity(endPt);
                this.project.getScene(0).addGameObject(circle);
            }
            alreadyReleased = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyChar() == 'c') spawnCircles = !spawnCircles;
        else if(e.getKeyChar() == 'x') this.project.getScene(0).clearAllObjects();
        else if(e.getKeyChar() == 'd') size += 5;
        else if(e.getKeyChar() == 's') size -= 5;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
