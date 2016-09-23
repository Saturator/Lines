package com.linesengine.game;

import com.linesengine.engine.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.*;
import java.awt.Graphics;

//tämä classi ei ole täysin omatekemä, apua täältä:
//https://www.youtube.com/watch?v=1gir2R7G9ws
//passaako tämä 3rd party kirjaston käytöstä vai olisiko suotavampaa luoda oma luuppirakenne?
public class Game extends Canvas implements Runnable
{
    GameProject project;
    
    private Thread thread;
    private boolean running = false;
    
    public Game(String projectName)
    {
        this.project = new GameProject(projectName, this);
        this.project.addScene(new GameScene("test", this.project));
        this.project.createWindow();
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
    
    //move this to a GameUpdate class
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
        //now we only get scene 0
        this.project.getScene(0).updateScene();
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
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.dispose();
        bs.show();
    }
}
