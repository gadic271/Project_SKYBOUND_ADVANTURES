package Mainthings;

public class Platform {
    private int x;
    private int y;
    public static int WIDTH = 50;
    public static int HEIGHT = 10;

    Platform(int x, int y) {
        if (x< 0) throw new RuntimeException("HOW");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void scroll(int diff) {
        this.y -= diff;
    }

    @Override
    public String toString() {
        return "Platform(" + x +","+ y+")";
    }
    public void doCollisionAction() {
    }
}

