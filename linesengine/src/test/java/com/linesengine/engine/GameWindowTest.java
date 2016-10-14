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
        GameWindow w = new GameWindow("w", p);
        System.out.println(w);
        assertEquals(w.title, "w");
    }
}
