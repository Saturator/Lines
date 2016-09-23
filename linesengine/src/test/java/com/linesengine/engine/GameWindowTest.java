package com.linesengine.engine;

import org.junit.Test;
import static org.junit.Assert.*;
import com.linesengine.game.Game;

public class GameWindowTest
{
    @Test
    public void constructorTest1()
    {
        Game g = new Game("game");
        GameProject p = new GameProject("p", g);
        p.createWindow();
        System.out.println(p.window);
        assertEquals(p.window.title, "p");
    }
}
