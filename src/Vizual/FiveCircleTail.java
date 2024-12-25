package Vizual;

import java.awt.Color;
import java.awt.Graphics;

import Mainthings.Hero;

public class FiveCircleTail extends ExampleDrawer {
    private final int numCircleTails = 7;
    private int start = 0;

    private int[] lastY = new int[numCircleTails];
    private int[] lastX = new int[numCircleTails];

    public FiveCircleTail() {
        for (int i = 0; i < numCircleTails; i++) {
            lastX[i] = -1;
            lastY[i] = -1;
        }
    }

    @Override
    public void drawBallWithTail(Graphics g, Hero b) {
        int r = Hero.r;
        int cx = b.getX();
        int cy = b.getY();
        lastX[start] = cx;
        lastY[start] = cy;
        int curr;
        for (int i = 0; i < numCircleTails; i++) {
            curr = (i + start + 1) % numCircleTails;
            if (lastX[curr] != -1 && lastY[curr] != -1) {
                if (curr == start)
                    drawCircleWithBorder(g, new Color(i * 255 / numCircleTails,
                            0, 0), lastX[curr], lastY[curr], r * i
                            / numCircleTails);
                else
                    drawCircle(g, new Color(i * 255 / numCircleTails, 0, 0),
                            lastX[curr], lastY[curr], r * i / numCircleTails);
            }
        }
        start = (start + 1) % numCircleTails;
    }

}