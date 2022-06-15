package game_interface;

import javax.swing.JFrame;
import java.awt.EventQueue;

/**
 * Breakout class that triggers the initialization of the game
 * @author Michael Valverde
 */
public class Breakout extends JFrame{

    public Breakout(){
        initUI();
    }

    /**
     * Sets up the window that contains the game and calls the board of the game
     */
    private void initUI(){
        add(new Board());
        setTitle("Breakout Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

}
