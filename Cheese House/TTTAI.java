import java.awt.Graphics2D;
import java.util.ArrayList;

public class TTTAI implements ITTTObj {

    private TTTMiniMax tttminimax;
    private TTTGrid tttgrid;
    private TTTGrid bigGrid; //main
    private TTTUltimate ultimate;

    private int interTime = 5;
    private int currentTime;
    private boolean hasTimerStarted;

    private int currentTurn = 0;



    public TTTAI(TTTGrid grid, TTTUltimate ultimate) {
        this.bigGrid = grid;
        tttminimax = new TTTMiniMax();
        this.ultimate = ultimate;
    }

    public void makeMove(){
        currentTime = 0;
        hasTimerStarted = true;
    }

    public void setTurn(TTTGrid grid, int turn) {
        this.tttgrid = grid;
        this.currentTurn = turn;
    }


    @Override
    public void update(float deltaTime) {
        if (!hasTimerStarted) {
            return;
        }

        currentTime += deltaTime;
        if (currentTime >= interTime) {
            tttgrid.setSymbolIndex(currentTurn);
            int move = tttminimax.getBestMove(tttgrid.getSymbols(), currentTurn);
            int x = move % Main.ROWS;
            int y = move/ Main.ROWS;
            tttgrid.placeSymbol(move);

            ultimate.setActiveGrid(x, y);
            hasTimerStarted = false;

            if(tttgrid.getWinner() >= 0) {
                bigGrid.placeSymbol(tttgrid.getX(), tttgrid.getY(), tttgrid.getWinner());
            }
        }
 

    }

    @Override
    public void render (Graphics2D graphicsRender) {

    }

    public boolean isMoving(){
        return hasTimerStarted;
    }

    public void makeMove(ArrayList<TTTGrid> grids, boolean multipleGridsCurrently, int turn) {
        this.currentTurn = turn;
        if(multipleGridsCurrently) {
            int bestBigGridMove = tttminimax.getBestMove(bigGrid.getSymbols(),turn);
            setTurn(grids.get(bestBigGridMove), turn);


        }
        else {
            for (TTTGrid tttgrid : grids) {
                if(tttgrid.isActive()) {
                    setTurn(tttgrid, turn);
                }
            }

        }
        makeMove();
    }


    
}
