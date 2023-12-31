import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TTTGrid implements ITTTObj {
    
    private int lineSize = 4;
    private int symbolIndex = 0;
    private int symbolsPlaced = 0;

    private boolean gameEnd = false;
    private boolean isActive = true;
    private boolean fadeIn = false;
    private int winner = -1;

    private ArrayList<PlaceTTT> places = new ArrayList<PlaceTTT>(Main.SIZE);
    private ArrayList<TTTSymbols> winMessage;
    private TTTSymbols[][] symbols;
    

    private int x;
    private int y;
    private int startX;
    private int startY;
    private int size;

    private boolean drawGrid = true;
    private boolean drawActive = true;
    
    public TTTGrid() {

        symbols =  new TTTSymbols[Main.ROWS][Main.ROWS];
        
        for (int i = 0; i < Main.SIZE; i++) {
            int xIn = i % Main.ROWS;
            int yIn = i / Main.ROWS;
            int size = Main.WIDTH / Main.ROWS;
            places.add(new PlaceTTT(xIn * size, yIn * size, xIn, yIn, size, size));
        }

        reset();

    }

    public TTTGrid(int x, int y, int size, boolean drawGrid, boolean drawActive) {
        this(x, y, size);

        this.drawGrid = drawGrid;
        this.drawActive = drawActive;


    }

    public TTTGrid(int x, int y, int size) {
        this.size = size;
        this.x = x;
        this.y = y;
        startX = x * size;
        startY = y * size;

        symbols = new TTTSymbols[Main.ROWS][Main.ROWS];
        

        for (int i = 0; i < Main.SIZE; i++) {
            int xIn = i % Main.ROWS;
            int yIn = i / Main.ROWS;
            int cellSize = size / Main.ROWS;

            places.add(new PlaceTTT(startX + xIn * cellSize, startY + yIn * cellSize, xIn, yIn, cellSize, cellSize));
            
        }

        reset();
    }

   
    @Override
    public void update(float deltaTime) {
        if (Main.GoUltimate){
            for (PlaceTTT placeTTT : places) {
             placeTTT.update(deltaTime);
            }
        
        
            for (int x = 0; x < symbols.length; x++) {
                for (int y = 0; y < symbols.length; y++) {
                    if (symbols[x][y] == null) {
                        continue;
                    }
    
                    symbols[x][y].update(deltaTime);
                    
                }
            }
            } else {
                for (PlaceTTT placeTTT : places) {
                    placeTTT.update(deltaTime);

                    
                }
                for (int x = 0; x < symbols.length; x++){
                    for (int y = 0; y < symbols.length; y++){
                        if (symbols[x][y] == null) {
                            continue;
                        }

                        symbols[x][y].update(deltaTime);
                    }
                }
            }
    }

    @Override
    public void render(Graphics2D graphicsRender) {
        if (isActive && drawActive) {
            drawActive(graphicsRender);
        }
        if (Main.GoUltimate){
        for (PlaceTTT placeTTT : places) {
            placeTTT.render(graphicsRender);
        }


        
            for (int x = 0; x < symbols.length; x++) {
                for (int y = 0; y < symbols.length; y++) {
                    if (symbols[x][y] == null) {
                        continue;
                    }
    
                    symbols[x][y].render(graphicsRender);
                    
                }
                
            }
            } else {
                renderTTTGrid(graphicsRender);
                for (PlaceTTT placeTTT : places) {
                    placeTTT.render(graphicsRender);
                    
                }
                for (int x = 0; x < symbols.length; x++){
                    for (int y = 0; y < symbols.length; y++){
                        if (symbols[x][y] == null) {
                            continue;
                            
                        }

                        symbols[x][y].render(graphicsRender);
                        
                    }
                }
                
               
            }

        if (Main.GoUltimate) {
            if (drawGrid) {
                renderTTTGrid(graphicsRender);

            }
        } else {
           renderTTTGrid(graphicsRender);
            
        }
        
       
    }

    private void drawActive(Graphics2D graphicsRender) {
        graphicsRender.setColor(new Color(255, 244, 0));
        graphicsRender.fillRect(startX, startY, size, size);

        graphicsRender.setColor(Color.WHITE);
    }


    private void renderTTTGrid(Graphics2D graphicsRender) {
        graphicsRender.setColor(new Color(248, 208, 48));
        if (Main.GoUltimate) {
        int rowSize = size / Main.ROWS;
        for (int i = 0; i < Main.ROWS + 1; i++) {
            int outsideSize = lineSize;
            if (i == 0 || i == Main.ROWS) {
                outsideSize *= 2;

            }
            graphicsRender.fillRect(startX + i * rowSize - (outsideSize / 2), startY, outsideSize, size);
            graphicsRender.fillRect(startX,startY + i * rowSize - (outsideSize / 2), size, outsideSize);

            
        }
    } else {
            int rowsize = Main.WIDTH / Main.ROWS;
                for (int x = 0; x < Main.ROWS + 1; x++) {
                    graphicsRender.fillRect(x * rowsize- (lineSize / 2), 0, lineSize, Main.WIDTH);
                    for (int y = 0; y < Main.ROWS + 1; y++) {
                      graphicsRender.fillRect(0, y * rowsize - (lineSize / 2), Main.WIDTH, lineSize);
     
                    }
                }
            }
        graphicsRender.setColor(Color.WHITE);

        if (gameEnd) {
            drawEndGameOverlay(graphicsRender);
        }
    
        

    }

    

    private void drawEndGameOverlay(Graphics2D graphicsRender) {
        
        if (!Main.GoUltimate) {
        graphicsRender.setColor(new Color(0, 0, 0, (int) (400 * 0.5f)));

        graphicsRender.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        graphicsRender.setColor(Color.WHITE);


        
        if (winner == -1) {
            graphicsRender.drawString("Cheese Housing", 200, 240);
            
            
        } else {
            graphicsRender.drawString(winner == 0 ? "Cheese" : "House", 280, 280);

        }
        
        graphicsRender.drawString("click again to restart...", 200, 340);

    }
    }

    public void mouseMoved(MouseEvent e) {
        if (Main.GoUltimate){
            if (!isActive) {
                return;
            }
        } else {
            if(gameEnded()) {
                return;
            }
        }

        for (PlaceTTT placeTTT : places) {
            placeTTT.checkCollision(e.getX(), e.getY() - 30);
            
        }
    }


    public PlaceTTT mouseReleased(MouseEvent e, int symbolIndex) {
        setSymbolIndex(symbolIndex);
        return mouseReleased(e);
    }

    public PlaceTTT mouseReleased(MouseEvent e) {
        if (Main.GoUltimate) {
        for (PlaceTTT placeTTT : places) {
            if (placeTTT.isActive()) {

                placeTTT.set(true);
                
                int x = placeTTT.getxIn();
                int y = placeTTT.getyIn();
                placeSymbol(x, y);

                return placeTTT;
                
            }
        }
        return null;
    } else {
        setSymbolIndex(symbolIndex);
        for (PlaceTTT placeTTT : places) {
            if (placeTTT.isActive()) {
                placeTTT.set(true);
                int x = placeTTT.getxIn();
                int y = placeTTT.getyIn();

                symbols[x][y] = new TTTSymbols(x, y, symbolIndex);
                
                symbolIndex++;

                ArrayList<TTTSymbols> winLine = WinCheck.winChecker(symbols);
                if (winLine != null){
                    winner = winLine.get(0).getType();
                    
                    gameEnd = true;

                } else if (symbolIndex >= Main.SIZE) {
                    
                    gameEnd = true;
                }
            }
        }
        } return null;
        
        

    }

    public void placeSymbol(int moveIndex) {
        placeSymbol(moveIndex % Main.ROWS, moveIndex / Main.ROWS);
    }

    
    public void placeSymbol(int x, int y, int symbolIndex) {
        setSymbolIndex(symbolIndex);
        placeSymbol(x, y);


    }

    private void placeSymbol(int x, int y) {
        symbols[x][y] = new TTTSymbols(x, y, startX, startY, size, symbolIndex);

        symbolIndex++;
        symbolsPlaced++;

        winMessage = WinCheck.winChecker(symbols);
        if (winMessage != null) {
            winner = winMessage.get(0).getType();
            gameEnd = true;

        } else if (symbolsPlaced >= Main.SIZE) {
            gameEnd = true;
        }
        
    }

    

    public void reset() {
        for (int x = 0; x < symbols.length; x++) {
            for (int y = 0; y < symbols.length; y++) {
                symbols[x][y] = null;
                
            }
            
        }

        for (PlaceTTT placeTTT : places) {
            placeTTT.set(false);
        }

        gameEnd = false;
        winner = -1;
        symbolIndex = 0;
        symbolsPlaced = 0;
        isActive = true;
    
    }

    public boolean gameEnded() {
        return gameEnd;
    }

    public TTTSymbols[][] getSymbols() {
        return symbols;
    }

    public int getWinner() {
        return winMessage == null ? -1 : winMessage.get(0).getType();
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getTurn() {   
        return symbolIndex % 2;
    }

    public boolean isActive() {
      
        return isActive;
    
    }

    public void setSymbolIndex(int symbolIndex){
        this.symbolIndex = symbolIndex;
    }


    

    
}
