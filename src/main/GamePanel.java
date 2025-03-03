package main;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        // importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }
        
    // private void importImg() {
    //     InputStream is = getClass().getResourceAsStream("/res/cuphead_idle_0002.png");

    //     try {
    //         img = ImageIO.read(is);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     } finally {
    //         try {
    //             is.close();
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
        
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

        System.out.println("size: " + GAME_WIDTH + " | " + GAME_HEIGHT);
    } 


    public void updateGame() {

    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
