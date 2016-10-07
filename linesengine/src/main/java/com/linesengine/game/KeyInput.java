package com.linesengine.game;

import java.awt.event.*;

public class KeyInput extends KeyAdapter
{
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        System.out.println(key);
        if(key == 27) System.exit(0);
    }
}
