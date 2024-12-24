package Mainthings;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Engine {
    static int highScoresKept = 5;
    private int WINDOW_WIDTH;
    private int WINDOW_HEIGHT;
    private int scrollUpLimit;
    private int distanceBetweenPlatforms;
    private final long timerDelay = 100;

    private int variance;
    private ExecutorService pool = Executors.newFixedThreadPool(1);
    private Hero hero;
    private boolean hasStarted = false;
    private boolean gameOver = false;
    private ActionListener gameOverListener;
    LinkedList<Platform> visiblePlatforms;
    public Engine(int width, int height) {

        WINDOW_HEIGHT = height;
        scrollUpLimit = WINDOW_HEIGHT * 4 / 5;
        WINDOW_WIDTH = width;
        variance = WINDOW_WIDTH / 10;
        distanceBetweenPlatforms = Hero.maxDistance;
        init();
    }
    public void init() {
        initPlatforms();
        initHero();
        hasStarted = false;
        gameOver = false;
    }

    private void initHero() {
        Platform first = visiblePlatforms.get(0);
        hero = new Hero(first.getX(), first.getY());
    }

    public Hero getHero() {
        return hero;
    }

    public LinkedList<Platform> getPlatForms() {
        return visiblePlatforms;
    }

    private boolean hasCollided(Platform p) {
        int x = p.getX();
        int y = p.getY();
        int cx = hero.getX();
        int cy = hero.getY();
        int r = Hero.r;
        if (x - Platform.WIDTH / 2 < cx && x + Platform.WIDTH / 2 > cx
                && y - Platform.HEIGHT / 2 < cy - r
                && y + Platform.HEIGHT / 2 > cy - r) {
            return true;
        } else
            return false;
    }

    private void initPlatforms() {
        visiblePlatforms = new LinkedList<Platform>();
        Random gen = new Random();
        int y = gen.nextInt(distanceBetweenPlatforms / 2);
        y += distanceBetweenPlatforms / 2;
        int x = WINDOW_WIDTH / 2;
        visiblePlatforms.add(new Platform(x, y));
        int ydiff;
        int xdiff;
        while (y < WINDOW_HEIGHT) {
            ydiff = gen.nextInt(distanceBetweenPlatforms / 2);
            ydiff += (distanceBetweenPlatforms / 2);

            y += ydiff;
            xdiff = gen.nextInt(variance * 2);
            x = (x + xdiff - variance) % WINDOW_WIDTH;
            if (x < 0)
                x += WINDOW_WIDTH;
            visiblePlatforms.add(new Platform(x, y));
        }
    }


    private void refillPlatforms() {
        Platform last;
        last = visiblePlatforms.get(visiblePlatforms.size() - 1);
        int ydiff;
        int xdiff;
        Random gen = new Random();
        int x;
        while (last.getY() < WINDOW_HEIGHT) {
            ydiff = gen.nextInt(distanceBetweenPlatforms / 2);
            ydiff += (distanceBetweenPlatforms / 2);
            xdiff = gen.nextInt(variance * 2);
            x = (last.getX() + xdiff - variance) % WINDOW_WIDTH;
            if (x < 0)
                x += WINDOW_WIDTH;
            last = new Platform(x, last.getY() + ydiff);
            visiblePlatforms.add(last);
        }
    }

    private void updateBallPos() {
        hero.updateX(WINDOW_WIDTH);
        if (hero.getY() < 0) {
            gameOverListener.actionPerformed(null);
            this.gameOver = true;
        } else if (hero.isMovingDown()) {
            while (hero.isMovingDown() && hero.shouldMoveY()) {
                for (Platform p : visiblePlatforms) {
                    if (hasCollided(p)) {
                        p.doCollisionAction();
                        hero.bounce();
                        break;
                    } else if (p.getY() - Platform.HEIGHT / 2 > hero.getY()
                            + Hero.r) {
                        break;
                    }
                }
                hero.updateSmoothY();
            }
        } else
            hero.updateRoughY();
        hero.resetYStepCounter();
        hero.updateDy();
    }

    private void updatePlatforms() {
        int y = hero.getY();
        if (y > scrollUpLimit) {
            int diff = y - scrollUpLimit;
            hero.setY(scrollUpLimit);
            Iterator<Platform> iter = visiblePlatforms.iterator();
            Platform p;
            while (iter.hasNext()) {
                p = iter.next();
                if (p.getY() > diff) {
                    p.scroll(diff);
                } else {
                    iter.remove();
                }
            }
        }
        refillPlatforms();
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void registerGameOverListener(ActionListener a) {
        this.gameOverListener = a;
    }

    void updateAll() {
        synchronized (this) {
            if (hasStarted) {
                if (!gameOver) {
                    updateBallPos();
                    updatePlatforms();
                }

            }
        }
    }

    public void moveLeft() {
        synchronized (this) {
            if (hasStarted) {
                hero.moveLeft();
            }
        }
    }

    public void moveRight() {
        synchronized (this) {
            if (hasStarted) {
                hero.moveRight();
            }
        }
    }


    public void start() {
        synchronized (this) {
            if (hasStarted == false)
                hasStarted = true;
            else
                init();
        }
    }
}
