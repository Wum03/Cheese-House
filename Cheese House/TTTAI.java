import java.awt.Graphics2D;

public class AI implements ITTTObj {

    private MiniMax minimax;
    private TTTGrid grid;

    public AI(TTTGrid grid) {
        this.grid = grid;
        minimax = new MiniMax();

    }

    public void makeMove(){
        grid.placeSymbol(minimax.getBestMove(grid.getSymbols(), grid.getTurn()));
    }


    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render (Graphics2D graphicsRender) {

    }
    
}
