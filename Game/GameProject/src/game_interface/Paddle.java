package game_interface;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite{

    private int dx;

    public Paddle(){
        initPaddle();
    }

    private void initPaddle(){
        loadImage();
        getImageDimensions();
        resetState();

    }

    private void loadImage(){
        var ii = new ImageIcon("src/Resources/paddle__.png");
        image = ii.getImage();
    }

    private void changePaddleSize(){
        var ii = new ImageIcon("src/Resources/short_paddle__.png");
        image = ii.getImage();

    }

    public void move(){
        x += dx;

        if(x <= 0){
            x = 0;
        }

        if(x >= Commons.WIDTH - imageWidth){
            x = Commons.WIDTH - imageWidth;
        }

    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            dx = -3;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 3;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            dx = 0;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }

    private void resetState(){
        x = Commons.INIT_PADDLE_X;
        y = Commons.INIT_PADDLE_Y;
    }


}

