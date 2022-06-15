package game_observer;

import game_interface.Board;

import javax.swing.*;

public class ObserverTrigger extends JFrame {


    public ObserverTrigger(){
        initUIObserver();
    }

    private void initUIObserver() {
        add(new Observer());
        setTitle("Breakout Game Observer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }
}
