package Vizual;

import java.awt.Color;
import java.awt.Graphics;

import Mainthings.Hero;
import Mainthings.Platform;

public abstract class ExampleDrawer {
    protected int width;
    protected int height;

    protected void drawCircle(Graphics g, Color c, int cx, int cy, int r) {
        g.setColor(c);
        int newCx = cx+ Platform.WIDTH / 2;
        int newCy = height - cy;
        Drawing.drawCircle(g, c, newCx, newCy, r);
    }

    protected void drawCircleWithBorder(Graphics g, Color c, int cx, int cy, int r) {

        int newCx = cx + Platform.WIDTH / 2;
        int newCy = height - cy;
        Drawing.drawCircleWithBorder(g, c, newCx, newCy, r);
    }

    void setDimensions(int width, int height) {
        this.height = height;
        this.width = width;
    }

    /**
     * drawBallWithTail is a method that is meant to draw the ball with a tail.
     * this can be implemented in many different ways, in order to provide as
     * many types of tails as possible
     *
     * @param g
     *            a graphics canvas for the image to be drawn on
     * @param b
     *            the ball that is supposed to be drawn
     */
    abstract void drawBallWithTail(Graphics g, Hero b);


}