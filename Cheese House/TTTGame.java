import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TTTGame extends PanelTTT implements MouseListener, MouseMotionListener {

    private TTTUltimate ultimate;
    
    public TTTGame(Color color) {
        super(color);

        ultimate = new TTTUltimate();

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        ultimate.update(deltaTime);
        //update elements
    }

    @Override
    public void render() {
        super.render();

        ultimate.render(graphicsRender);
        //update elements

        
        super.clear();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        ultimate.mouseMoved(e);
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

        ultimate.mouseReleased(e);
        
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
