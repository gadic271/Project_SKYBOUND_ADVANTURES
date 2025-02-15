import javax.swing.SwingUtilities;

import Vizual.FiveCircleTail;
import Vizual.Keys;
import Mainthings.Engine;
import Mainthings.Platform;

class Main {
    private static int width = 600;
    private static int height = 500;
    public static void main(String[] args){
        final Engine game = new Engine(width-Platform.WIDTH,height);
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                Keys gui = new Keys(game,width,height);
                gui.setCircleTailDrawer(new FiveCircleTail());
            }
        });
    }
}