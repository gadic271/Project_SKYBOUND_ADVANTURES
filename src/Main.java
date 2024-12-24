
import javax.swing.SwingUtilities;
import Mainthings.Engine;
import Mainthings.Platform;

class Main {
    private static int width = 10000;
    private static int height = 10000;
    public static void main(String[] args){
        final Engine game = new Engine(width- Platform.WIDTH,height);
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
            }
        });
    }
}