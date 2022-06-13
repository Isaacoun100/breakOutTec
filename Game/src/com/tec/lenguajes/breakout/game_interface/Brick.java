package com.tec.lenguajes.breakout.game_interface;

import javax.swing.ImageIcon;
import java.awt.*;

public class Brick extends Sprite{

    private boolean destroyed;
    private int row;
    private int column;
    private String powerUp;

    public Brick(int x, int y,int row,int column){
        initBrick(x,y,row,column);
    }

    private void initBrick(int x, int y, int row,int column){
        this.x = x;
        this.y = y;
        this.row = row;
        this.column = column;
        destroyed = false;
        loadImage();
        getImageDimensions();
    }

    private void loadImage(){

        switch (row){

            case 0:
                var ii = new ImageIcon("src/Resources/red_brick__.png");
                image = ii.getImage();
                break;
            case 1:
                ii = new ImageIcon("src/Resources/red_brick__.png");
                image = ii.getImage();
                break;
            case 2:
                ii = new ImageIcon("src/Resources/orange_brick__.png");
                image = ii.getImage();
                break;
            case 3:
                ii = new ImageIcon("src/Resources/orange_brick__.png");
                image = ii.getImage();
                break;
            case 4:
                ii = new ImageIcon("src/Resources/yellow_brick__.png");
                image = ii.getImage();
                break;
            case 5:
                ii = new ImageIcon("src/Resources/yellow_brick__.png");
                image = ii.getImage();
                break;
            case 6:
                ii = new ImageIcon("src/Resources/green_brick__.png");
                image = ii.getImage();
                break;
            case 7:
                ii = new ImageIcon("src/Resources/green_brick__.png");
                image = ii.getImage();


        }
    }

    boolean isDestroyed(){
        return destroyed;
    }

    void setDestroyed(boolean val){
        destroyed = val;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void getBrickCoordinates(){
        System.out.println("Row: " + getRow() + " Column: " + getColumn());
    }

    public String getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(String powerUp) {
        this.powerUp = powerUp;
    }
}
