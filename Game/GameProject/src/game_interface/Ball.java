package game_interface;

import javax.swing.ImageIcon;


/**
 * Class ball the applies inheritance to the sprite class
 * @author Michael Valverde
 */
public class Ball extends Sprite{
    private int xdir;
    private int ydir;
    private boolean destroyed;


    public Ball(){
        initBall();
    }

    /**
     * method that allows to initialize a ball
     */
    private void initBall(){
        xdir = 1;
        ydir = -1;
        destroyed = false;
        loadImage();
        getImageDimensions();
        resetState();
    }

    /**
     * loads the image for the ball
     */
    private void loadImage(){
        var ii = new ImageIcon("src/Resources/ball_.png");
        image = ii.getImage();
    }

    /**
     * allows to move the ball in any direction
     */
    public void move(){
        if(!destroyed){
            x += xdir;
            y += ydir;

            if(x == 0){
                setXDir(3);

            }
            if(x == Commons.WIDTH - imageWidth){
                System.out.println(imageWidth);
                setXDir(-3);

            }

            if(y == 0){
                setYDir(3);
            }

        }

    }

    /**
     * resets the coordinates of the ball to the initial coordinates given by the developer
     */
    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    /**
     * sets a different direction for the x-axis
     * @param x : new direction of the ball for the x-axis
     */
    void setXDir(int x) {

        xdir = x;
    }

    /**
     * sets a different direction for the y-axis
     * @param y : new direction of the ball for the y-axis
     */
    void setYDir(int y) {

        ydir = y;
    }

    /**
     * gets the direction for the y-axis
     * @return integer that represents the y direction
     */
    int getYDir() {

        return ydir;
    }

    /**
     * allows to check if the ball is destroyed
     * @return boolean value, true if the ball is destroyed, false if the ball is not destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }


    /**
     * sets a boolean value for the ball, true if the ball is destroyed, false if the ball is not destroyed
     * @param destroyed boolean value for destroying a ball
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
