package com.linesengine.game;

import com.linesengine.engine.*;
import com.linesengine.math.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.*;
import java.awt.Graphics;
import java.util.Timer;

//tämä classi ei ole täysin omatekemä, apua täältä:
//https://www.youtube.com/watch?v=1gir2R7G9ws
//passaako tämä 3rd party kirjaston käytöstä vai olisiko suotavampaa luoda oma luuppirakenne?

//pistä pit testit ja checkstylet tänne
//https://htmlpreview.github.io
public class Game extends Canvas implements Runnable
{
    GameProject project;
    
    private Thread thread;
    private boolean running = false;
    
    public Game(String projectName)
    {
        this.project = new GameProject(projectName, this);
        this.project.addScene(new GameScene("test"));
        this.project.createWindow();
        this.addKeyListener(new KeyInput());
        this.addMouseListener(new MouseInput(this.project));
    }
    
    public static void main(String[] args)
    {
        new Game("linesengine");
    }
    
    public synchronized void start()
    {
        this.thread = new Thread(this);
        this.thread.start();
        this.running = true;
    }
    
    public synchronized void stop()
    {
        try
        {
           this.thread.join();
           this.running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //TODO: move this to a GameTime class
    public void run()
    {
        long lastTime = System.nanoTime();
        float amountOfTicks = 60f;
        float ns = 1000000000 / amountOfTicks;
        float delta = 0f;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >= 1)
            {
                tick();
                delta--;   
            }
            if(running)
            {
                //tick(delta);
                render();
            }
            
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick()
    {
        //TODO: we only get scene 0 atm, change it after the scene is up and running
        //tick moves the physics forward when deltaTime is 1, so we have a fixed physics update
        this.project.getScene(0).tick();
    }
    
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(2);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 1000, 1000);
        this.project.getScene(0).render(g);
        g.dispose();
        bs.show();
    }
}
