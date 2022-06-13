package game_interface;

import javax.swing.*;
import java.awt.Image;
import java.awt.Rectangle;


public class Sprite {

    int x;
    int y;
    int imageWidth;
    int imageHeight;
    Image image;
    Image newImg;
    ImageIcon imgIcon;

    protected void SetX(int x){
        this.x = x;

    }

    int getX(){
        return x;
    }

    protected void setY(int y){
        this.y = y;
    }

    int getY(){
        return y;
    }

    int getImageWidth(){
        return imageWidth;
    }

    int getImageHeight(){
        return imageHeight;
    }

    Image getImage(){
        return image;
    }

    Rectangle getRectangle(){
        return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));

    }

    void getImageDimensions(){
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }

}
