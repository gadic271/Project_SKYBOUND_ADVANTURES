package Vizual;

import java.awt.Color;
import java.awt.Graphics;

import Mainthings.Hero;

public class Example extends ExampleDrawer {

    @Override
    public void drawHeroWithTail(Graphics g, Hero b) {
        int r = Hero.r;
        int cx = b.getX();
        int cy = b.getY();
        drawHero(g, Color.RED, cx, cy, r);
    }

}