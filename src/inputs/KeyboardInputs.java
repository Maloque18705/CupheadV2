package inputs;

import java.awt.event.KeyListener;

import main.GamePanel;

import java.awt.event.KeyEvent;
import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                break;
        }    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(false);
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);;
                break;
        }    
    }
    
}
