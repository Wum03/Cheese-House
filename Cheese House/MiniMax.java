import java.util.ArrayList;

public class MiniMax {

    private int bestMove = 0;

    public int getBestMove(TTTSymbols[][] symbols, int requester) {
    bestMove = 0;
    minimax(symbols, requester, true, 0);

    return bestMove;
    }
                         // PlaceTTT instead of Marker.
    private int minimax(TTTSymbols[][] symbols, int requester, boolean requesterMove, int depth) {

        int winner = WinCheck.getWinType(symbols);
        if (winner >= 0 ||getSymbolsPlacedSize(symbols) == Main.SIZE ) {               // GETMARKERSPLACED?.
            return getFieldScore(symbols, requester, depth); // GETFIELDSCORE ?.
        }

        ArrayList<Integer> scores = new ArrayList<Integer>();
        int[] openMoves = getOpenSpotIndexes(symbols);
        int score = 0;

        for (int i = 0; i <openMoves.length; i++) {
            int x = openMoves[i] % Main.ROWS;
            int y = openMoves[i] / Main.ROWS;

            if(! requesterMove){
                depth++;
            }

            int symbol = requesterMove ? requester : requester + 1;
            symbols [x][y] = new TTTSymbols(symbol);
            score = minimax(symbols, requester, !requesterMove, depth);
            scores.add(score);
            symbols [x][y] = null;

        }

        int scoreIndex = 0;
        if(requesterMove) {
            scoreIndex = getMax(scores);
        } 

        else {
            scoreIndex = getMin(scores);
        }
        bestMove = openMoves[scoreIndex];   
        return scores.get(scoreIndex);
    }

    private int getMax(ArrayList<Integer> scores) {
        int index= 0;
        int max = 0;
        for(int i = 0; i < scores.size(); i++){
            if (scores.get(i) >= max) {
                index = i;
                max = scores.get(i);
            }
        }
        return index;

    }

    private int getMin(ArrayList<Integer> scores) {
        int index= 0;
        int min = 0;
        for(int i = 0; i < scores.size(); i++){
            if (scores.get(i) <= min) {
                index = i;
                min = scores.get(i);
            }
        }
        return index;

    }




    private int getFieldScore(TTTSymbols[][] symbols, int requester, int depth) {
        ArrayList<TTTSymbols> match = WinCheck.winChecker(symbols);
        if(match == null) {
            return 0;
        }

        if (match.get(0).getType() == requester) {
            return Main.SIZE - depth;
        }

        return (Main.SIZE * -1) + depth;
    }
    private int[] getOpenSpotIndexes(TTTSymbols[][] symbols) {
        int[] openSpots = new int[Main.SIZE - getSymbolsPlacedSize(symbols)];
        int openSpotIndex = 0;
        
        for (int x = 0; x < symbols.length; x++) {
            for (int y = 0; y < symbols[x].length; y++) {
                if(symbols[x][y] == null) {
                    openSpots[openSpotIndex] = (y * Main.ROWS) + x;
                    openSpotIndex++;
                }
            }

        }
        return openSpots;
    }







    private int getSymbolsPlacedSize(TTTSymbols[][] symbols) {
        int result = 0;

        for  (int i = 0; i < symbols.length; i++) {
            for (int j = 0; j < symbols[i].length; j++) {
                if (symbols[i][j] != null) {
                    result ++;
                }

            }
        }

        return result;
    }   
}