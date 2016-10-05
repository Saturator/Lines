package com.linesengine.game;

import java.awt.Color;
import java.util.Random;

public class RNG
{
    public static Color getRandomColor()
    {
        Random ran = new Random();
        int n = ran.nextInt(4);
        switch(n)
        {
            case 0: return Color.GREEN;
            case 1: return Color.BLUE;
            case 2: return Color.RED;
            case 3: return Color.YELLOW;
            default: return Color.DARK_GRAY;
        }
    }
}
