package com.tec.lenguajes.breakout.game_interface;

import javax.swing.ImageIcon;

public class Ball extends Sprite{
    private int xdir;
    private int ydir;


    public Ball(){
        initBall();
    }

    private void initBall(){
        xdir = 1;
        ydir = -1;
        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage(){
        var ii = new ImageIcon("src/Resources/ball_.png");
        image = ii.getImage();
    }

    public void move(){
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

    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    void setXDir(int x) {

        xdir = x;
    }

    void setYDir(int y) {

        ydir = y;
    }

    int getYDir() {

        return ydir;
    }


}
