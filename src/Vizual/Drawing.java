package Vizual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
public final class Drawing {
    private Drawing() {

    }

    static void drawCircleWithBorder(Graphics g, Color c, int cx, int cy, int r) {

        int left = cx - r;
        int top = cy - r;
        g.setColor(c);
        g.fillOval(left, top, 2 * r, 2 * r);
        g.setColor(Color.black);
        g.drawOval(left, top, 2*r, 2*r);
    }

    static void drawCircle(Graphics g, Color c, int cx, int cy, int r) {

        int left = cx - r;
        int top = cy - r;
        g.setColor(c);
        g.fillOval(left, top, 2 * r, 2 * r);
    }

}