package game_interface;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 * Paddle class that applies inheritance to the sprite superclass to make a paddle for controlling the game
 * @author Michael Valverde
 */
public class Paddle extends Sprite{

    private int dx;


    public Paddle(){
        initPaddle();
    }

    /**
     * Initializes the paddle by loading the image of the paddle, getting the image dimensions and resetting the
     * the position of the paddle
     */
    private void initPaddle(){
        loadImage();
        getImageDimensions();
        resetState();

    }

    /**
     * loads the image of the paddle
     */
    private void loadImage(){
        var ii = new ImageIcon("src/Resources/paddle__.png");
        image = ii.getImage();
    }

    /**
     * loads a different image of the paddle to reduce the size of the paddle
     */
    public void changePaddleSize(String state){
        if(state == "LITTLE"){
            var ii = new ImageIcon("src/Resources/short_paddle__.png");
            image = ii.getImage();
            getImageDimensions();
        }else{
            var ii = new ImageIcon("src/Resources/paddle__.png");
            image = ii.getImage();
            getImageDimensions();
        }


    }

    /**
     * moves the paddle to the right or left according to the arrow key that is being pressed
     */
    public void move(){
        x += dx;

        if(x <= 0){
            x = 0;
        }

        if(x >= Commons.WIDTH - imageWidth){
            x = Commons.WIDTH - imageWidth;
        }

    }

    /**
     * gets the key that is being pressed and moves the paddle
     * @param e
     */
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            dx = -3;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 3;
        }
    }

    /**
     * gets the event when a key is released to stop the movement of the paddle
     * @param e
     */
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            dx = 0;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }

    /**
     * resets the coordinates of the paddle to a initial position determined by the developer
     */
    private void resetState(){
        x = Commons.INIT_PADDLE_X;
        y = Commons.INIT_PADDLE_Y;
    }


}

