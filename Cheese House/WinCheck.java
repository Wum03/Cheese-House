import java.util.ArrayList;
public class WinCheck {

    public static ArrayList<TTTSymbols> winChecker(TTTSymbols[][] symbols) {
        ArrayList<TTTSymbols> win;

        for (int i = 0; i < Main.SIZE; i++) {
            int x = i % Main.ROWS;
            int y = i / Main.ROWS;

            //diagonal bottom lelt top right
            win = winner(x, y, 1, -1, i, symbols);

            //diagonal top left bottom right
            if (win == null) {
                win = winner(x, y, 1, 1, i, symbols);
            }

            //horizontal
            if (win == null) {
                win = winner(x, y, 1, 0, i, symbols);
            }

            //vertical
            if (win == null) {
            }

            if (win != null) {
                return win;
            }

        }
        return null;
    }

    private static ArrayList<TTTSymbols> winner(int x, int y, int dX, int dY, int index, TTTSymbols[][] winSymbols) {
        ArrayList<TTTSymbols> win = new ArrayList<TTTSymbols>(Main.WIN);
        int type = -1;
        int countCheck = 0;

        while (countCheck < Main.ROWS && index < Main.SIZE && x >= 0 && x <= Main.ROWS - 1 
            && y >= 0 && y <= Main.ROWS - 1) {

            boolean winFind = false;
            TTTSymbols symbols = winSymbols[x][y];

            if (symbols != null) {
                if (type == -1) {
                    type = symbols.getType();
                }

                if (symbols.getType() == type) {
                    win.add(symbols);
                    winFind = true;
                }

            }

            if (!winFind && win.size() < Main.WIN){
                win.clear();
                type = -1;
            }

            x += dX;
            y += dY;
            countCheck++;

        }



        return win.size() >= Main.WIN ? win : null;
    }
    
}
