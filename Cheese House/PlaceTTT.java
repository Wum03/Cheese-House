import java.awt.Color;
import java.awt.Graphics2D;

public class PlaceTTT implements ITTTObj {

    private Color touchColor = new Color(228, 74, 60);
    private boolean fadeIn = false;
    private boolean placed = false;
    private float alpha = 0f;
    private float fadeSpeed = 0.1f;

    private int x;
    private int y;
    private int xIn;
    private int yIn;
    private int width;
    private int height;

    public PlaceTTT(int x, int y, int xIn, int yIn, int width, int height) {
        this.x = x;
        this.y = y;
        this.xIn = xIn;
        this.yIn = yIn;
        this.width = width;
        this.height = height;
        
    }



    @Override
    public void update(float deltaTime) {

        if (fadeIn && alpha != 1) {
            alpha += fadeSpeed;
            if (alpha > 1) {
                alpha = 1;
            }
        } else if (!fadeIn && alpha != 0) {
            alpha -= fadeSpeed;
            if (alpha < 0) {
                alpha = 0;
            }

        }
    }

    @Override
    public void render(Graphics2D graphicsRender) {

        graphicsRender.setColor(new Color(touchColor.getRed(), touchColor.getGreen(), touchColor.getBlue(),(int)(alpha*225)));

        graphicsRender.fillRect(x, y, width, height);

        graphicsRender.setColor(Color.WHITE);

    }
     
    public void checkCollision(int x, int y) {
        if (placed) {
            return;
        }
        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            fadeIn = true;

        } else {
            fadeIn = false;
        }

    }

    public int getxIn() {
        return xIn;
    }


    public int getyIn() {
        return yIn;
    }

    public boolean isActive() {
        return fadeIn;
    }    

    public void set(boolean isSet) {
        placed = isSet;

        if (isSet) {
            fadeIn = false;
        }
    }
}
