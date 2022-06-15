package game_observer;

import game_interface.Board;
import game_interface.Brick;
import game_interface.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Observer extends JPanel {
    private Timer timer;
    private String message = "Game Over";
    private Brick[] bricks;
    private boolean inGame = true;
    private int score = 0;
    private int level = 1;
    private int gameLives;

    public Observer() {
        initObserver();
    }

    private void initObserver() {
        //addKeyListener(new Board.TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));
        observerInit();

    }

    private void observerInit() {
        bricks = new Brick[Commons.NUMBER_OF_BRICKS];
        gameLives = 3;
        score = 0;
        level = 1;

        int k = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 14; j++){
                bricks[k] = new Brick(j * 65 + 40, i * 25 + 50,i,j);
                k++;
            }
        }

        timer = new Timer(20, new Observer.GameCycle());
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

        if(inGame){
            drawObjects(g2d);
            //showGameMetrics(g2d);
        }else{
            //gameFinished(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d){

        for(int i = 0; i < Commons.NUMBER_OF_BRICKS; i++){
            if(!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getImageWidth(), bricks[i].getImageHeight(),
                        this);
            }
        }
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            doGameCycle();
        }
    }

    private void doGameCycle() {
        checkCollision();
        repaint();
    }

    private void checkCollision() {
        for(int i = 0; i < bricks.length; i++){
            //Se deberia eliminar los bricks aqui
        }
    }


}
