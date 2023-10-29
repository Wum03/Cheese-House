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

    private float scale = 2;
    private boolean animateIn;
    private float scaleSpeed = 0.1f;

    public TTTSymbols(int x, int y, int startX, int startY, int size, int type) {
       
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.startY = startY;
        this.size = size;
        this.animateIn = true;

        this.type = type % 2;
         if (Main.GoUltimate) {

        
        String symbolType = this.type == 0 ? "cheese" : "house";

        try {
            symbol = ImageIO.read(new File("Cheese House/big_XO/" + symbolType + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    }

 


    public TTTSymbols(int type) {
        if (Main.GoUltimate) {
        this.type = type % 2;
        }
    }

    public TTTSymbols(int x, int y, int type){
        this.x = x;
        this.y= y;
        this.type = type %2;
       
        if (!Main.GoUltimate) {
        String symbolType = this.type == 0 ? "cheese" : "house";
        
        try {
            symbol = ImageIO.read(new File("Cheese House/big_XO/" + symbolType + ".png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    }




    @Override
    public void update(float deltaTime) {
        if(animateIn) {
            scaleIn(deltaTime);
        }
    }

    private void scaleIn(float deltaTime) {
        scale -= scaleSpeed * deltaTime;

        if(scale < 1) {
            scale =1;
            animateIn = false;
        }
    }




    @Override
    public void render(Graphics2D graphicsRender) {
        int size = this.size / Main.ROWS;

        int symbolSize = (int)(size * scale);
        int pivot = (size - symbolSize) /2;

        if (Main.GoUltimate){
            graphicsRender.drawImage(symbol, startX + x * size + pivot, startY + y * size + pivot, symbolSize, symbolSize, null);
        } else {
            graphicsRender.drawImage(symbol, x * Main.WIDTH / Main.ROWS, y * Main.WIDTH / Main.ROWS, Main.WIDTH / Main.ROWS, Main.WIDTH / Main.ROWS, null);
           
        }

    }




    public int getType() {
        return type;
    }
    
}
