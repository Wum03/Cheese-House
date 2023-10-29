import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TTTGame extends PanelTTT implements MouseListener, MouseMotionListener {

    private TTTUltimate ultimate;
    private TTTGrid tttGrid;
    
    public TTTGame(Color color) {
        super(color);
        if (Main.GoUltimate) {
            ultimate = new TTTUltimate();
        } else {
            tttGrid = new TTTGrid();
        }
        

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (Main.GoUltimate) {
            ultimate.update(deltaTime);
        } else {
            tttGrid.update(deltaTime);
        }
        
        //update elements
    }

    @Override
    public void render() {
        super.render();

        if (Main.GoUltimate) {
            ultimate.render(graphicsRender);
        } else {
            tttGrid.render(graphicsRender);
        }

        
        super.clear();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        if (Main.GoUltimate) {
            ultimate.mouseMoved(e);
        } else {
            tttGrid.mouseMoved(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (Main.GoUltimate) {
            ultimate.mouseReleased(e);
        } else {
            tttGrid.mouseReleased(e);
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
}
