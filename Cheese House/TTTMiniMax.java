import java.util.ArrayList;

public class TTTMiniMax {

    private int bestMove = 0;

    public int getBestMove(TTTSymbols[][] symbols, int requester) {
    bestMove = 0;
    tttminimax(symbols, requester, true, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    return bestMove;
    }
                        
    private int tttminimax(TTTSymbols[][] symbols, int requester, boolean requesterMove, int depth, int alpha, int beta) {

        int winner = WinCheck.getWinType(symbols);
        if (winner >= 0 ||getSymbolsPlacedSize(symbols) == Main.SIZE ) {               
            return getFieldScore(symbols, requester, depth); 
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
            score = tttminimax(symbols, requester, !requesterMove, depth, alpha, beta);
            scores.add(score);
            symbols [x][y] = null;

            if(requesterMove) {
                int maxVal = Math.max(Integer.MIN_VALUE, score);
                alpha = Math.max(alpha, maxVal);
                if (alpha > beta) {
                    return maxVal;
                }
            } else {
                int minVal = Math.min(Integer.MAX_VALUE, score);
                beta = Math.min(beta, minVal);
                if(beta < alpha) {
                    return minVal;
                }
            }
            

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