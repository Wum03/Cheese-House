import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TTTUltimate implements ITTTObj {

    private ArrayList<TTTGrid> grids = new ArrayList<TTTGrid>(Main.SIZE);
    private TTTGrid bigGrid;
    private TTTAI tttai;


    private int symbolIndex = 0;


    public TTTUltimate() {
        for (int i = 0; i < Main.SIZE; i++) {
            int x = i % Main.ROWS;
            int y = i / Main.ROWS;

            grids.add(new TTTGrid(x, y, Main.WIDTH / Main.ROWS));
        }

        bigGrid = new TTTGrid(0, 0, Main.WIDTH, false, false);
        
        tttai = new TTTAI(bigGrid, this);
        


        
    }


    @Override
    public void update(float deltaTime) {
    
        for (TTTGrid tttGrid : grids) {
            tttGrid.update(deltaTime);
            
        }
        
        tttai.update(deltaTime);
        bigGrid.update(deltaTime);
    }

    @Override
    public void render(Graphics2D graphicsRender) {
        for (TTTGrid tttGrid : grids) {
            tttGrid.render(graphicsRender);
        }

        bigGrid.render(graphicsRender);

        if (bigGrid.gameEnded()) {
            drawEndGameOverlay(graphicsRender);

        }

    }


    private void drawEndGameOverlay(Graphics2D graphicsRender) {
        
        graphicsRender.setColor(new Color(0, 0, 0, (int) (400 * 0.5f)));

        graphicsRender.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        graphicsRender.setColor(Color.WHITE);

        int winner = bigGrid.getWinner();

        if (winner == -1) {
            graphicsRender.drawString("Cheese Housing", 200, 240);
            
            
        } else {
            graphicsRender.drawString(winner == 0 ? "Cheese" : "House", 280, 280);

        }
        
        graphicsRender.drawString("click again to restart...", 200, 340);

       
    }


    public void mouseReleased(MouseEvent e) {
        if (bigGrid.gameEnded()){
            reset();
            return;
        }
        

        int currentX = -1;
        int currentY = -1;
        for (TTTGrid tttGrid : grids) {
            PlaceTTT selectedPlace = tttGrid.mouseReleased(e, symbolIndex);
            if (selectedPlace != null) {
                currentX = selectedPlace.getxIn();
                currentY = selectedPlace.getyIn();
                symbolIndex++;

                if (tttGrid.getWinner() >= 0) {
                    bigGrid.placeSymbol(tttGrid.getX(), tttGrid.getY(), tttGrid.getWinner());
                }


            }
            
        }     
        
        boolean multipleGridsCurrently = setActiveGrid(currentX, currentY);


        if(symbolIndex % 2 == 1) {
            tttai.makeMove(grids, multipleGridsCurrently, symbolIndex);
            symbolIndex++;
        }
    }


    public boolean setActiveGrid(int currentX, int currentY) {
        if (currentX >= 0 && currentY >= 0) {
            for (TTTGrid tttGrid : grids) {
                if (tttGrid.getX() == currentX && tttGrid.getY() == currentY) {
                    if (tttGrid.gameEnded()) {
                        setGridsActive();
                        return true;

                    }

                    tttGrid.setActive(true);
                } else {
                    tttGrid.setActive(false);
                }
                
            }
        }
        return false;
    }


    private void setGridsActive() {
        for (TTTGrid tttGrid : grids) {
            tttGrid.setActive(!tttGrid.gameEnded());
        }
    }


    public void mouseMoved(MouseEvent e) {
        if (bigGrid.gameEnded()|| tttai.isMoving()) {
            return;
        }
        for (TTTGrid tttGrid : grids) {
            tttGrid.mouseMoved(e);
            
        }      
    }

    
    private void reset() {
        for (TTTGrid tttGrid : grids) {
            tttGrid.reset();
        }
        bigGrid.reset();
        symbolIndex = 0;
    }

}