package Vizual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Mainthings.Hero;
import Mainthings.Engine;
import Mainthings.Platform;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = -1000316276088237911L;
    private Engine e;
    private int height;
    private int width;
    private ExampleDrawer emoteDrawer;
    private BufferedImage background;

    GamePanel(Engine engine, int width, int height) {
        this.width = width;
        this.e = engine;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        setExampleDrawer(new Example());
        try {
            background = ImageIO.read(Keys.backgroundURL);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void setExampleDrawer(ExampleDrawer d) {
        synchronized (e) {
            emoteDrawer = d;
            d.setDimensions(width, height);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        synchronized (e) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, width, height, 0, 0,
                    Math.min(background.getWidth(), width),
                    Math.min(background.getHeight(), height), null);
            int top, left;
            for (Platform p : e.getPlatForms()) {
                left = p.getX();
                top = height - p.getY() - Platform.HEIGHT / 2;
                g.setColor(Color.GREEN);
                g.fillRect(left, top, Platform.WIDTH, Platform.HEIGHT);
            }
            g.setColor(Color.RED);
            Hero b = e.getHero();
            emoteDrawer.drawHeroWithTail(g, b);
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(Platform.WIDTH / 2));
            g.drawRect(0, 0, width, height);
        }
    }

}