import java.awt.Color;

import javax.swing.*;



public class Main {

    public static int WIDTH = 640;
    public static int HEIGHT = 640;
    public static int ROWS = 3;
    public static int WIN = 3;
    public static int SIZE = ROWS * ROWS;
    public static boolean EnableAI = true;
    public static boolean GoUltimate = false;
    //if rows < win then error message...?
    


    public static void main(String[] args) {

        if (ROWS < WIN) {
            System.out.println("win variable cannot be larger than rows variable");
            return;
        }
        if (EnableAI && ROWS > 3) {
            System.out.println("disable AI to play on larger sized grids");
            return;  
        }

        JFrame frame = new JFrame("Tic Tac Toe");
        TTTGame game = new TTTGame(new Color(121, 59, 49)); //121
        
    

        frame.add(game);
        frame.addMouseListener(game);
        frame.addMouseMotionListener(game);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
