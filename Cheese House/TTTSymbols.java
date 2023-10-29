import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class TTTSymbols implements ITTTObj{

    private BufferedImage symbol;

    private int x;
    private int y;
    private int startX;
    private int startY;
    private int type;
    private int size;

    public TTTSymbols(int x, int y, int startX, int startY, int size, int type) {
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.startY = startY;
        this.size = size;

        this.type = type % 2;

        String symbolType = this.type == 0 ? "cheese" : "house";

        try {
            symbol = ImageIO.read(new File("Cheese House/big_XO/" + symbolType + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }    

    }

 


    public TTTSymbols(int type) {
        this.type = type % 2;

    }

    public TTTSymbols(int x, int y, int type){
        this.x = x;
        this.y= y;
        this.type = type %2;
        String symbolType = this.type == 0 ? "cheese" : "house";

        try {
            symbol = ImageIO.read(new File("Cheese House/big_XO/" + symbolType + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }




    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render(Graphics2D graphicsRender) {
        int size = this.size / Main.ROWS;
        if (Main.GoUltimate){
            graphicsRender.drawImage(symbol, startX + x * size, startY + y * size, size, size, null);
        } else {
            graphicsRender.drawImage(symbol, x, y, size, size, null);
        }
    }




    public int getType() {
        return type;
    }
    
}
