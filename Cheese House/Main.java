import java.awt.Color;

import javax.swing.*;

/*/Instructions to make changes

The AI can be turned on and off; this can be done by changing EnableAI from True to False.
The amount of Rows can be changed, this is only possible when the AI is turned OFF; this can be done by changing the value of ROWS.
In case the AI is turned ON the amount of rows can only be decreased and the WIN value can be decreased.
If you change the ROWS, it might be handy to also change the amount in a row to win. But this is not needed.
The amount you need to get in a row to win; change the value of WIN.
It is possible to play the normal tictac toe; this is done by disabling GoUltimate and the AI has to be turned OFF.
*/

public class Main {

    public static int WIDTH = 640;
    public static int HEIGHT = 640;
    public static int ROWS = 3;
    public static int WIN = 3;
    public static int SIZE = ROWS * ROWS;
    public static boolean EnableAI = false;
    public static boolean GoUltimate = true;
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
