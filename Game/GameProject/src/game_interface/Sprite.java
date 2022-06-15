package game_interface;

import javax.swing.*;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Super class Sprite, allows representation of a paddle, bricks, and balls inside the game
 * @author Michael Valverde
 */
public class Sprite {

    int x;
    int y;
    int imageWidth;
    int imageHeight;
    Image image;

    protected void SetX(int x){
        this.x = x;

    }

    /**
     * obtiene el valor de la coordenada en x
     * @return value in x-axis
     */
    public int getX(){
        return x;
    }

    /**
     * Allows to change the new value in the y coordinate
     * @param y, new value in y coordinate
     */
    protected void setY(int y){
        this.y = y;
    }

    /**
     * Gets the value in y-axis
     * @return value in y-axis
     */
    public int getY(){
        return y;
    }

    /**
     * Gets the width of the image used to represent an object like a paddle, brick or ball
     * @return width of the given image
     */
    public int getImageWidth(){
        return imageWidth;
    }

    /**
     * Gets the height of the image used to represent an object like a paddle, brick or ball
     * @return height of the given image
     */
    public int getImageHeight(){
        return imageHeight;
    }

    /**
     * Gets the image used to represent an object from the game
     * @return image
     */
    public Image getImage(){
        return image;
    }

    /**
     * Gets the dimensions of the image or rectangle in order to make a rectangle and determine collisions between sprites
     * @return
     */
    Rectangle getRectangle(){
        return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));

    }

    /**
     * Get dimensions of the image, widht and height
     */
    void getImageDimensions(){
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }

}
