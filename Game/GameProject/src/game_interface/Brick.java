package game_interface;

import javax.swing.ImageIcon;
import java.awt.*;


/**
 * Brick class that applies inheritance to the sprite superclass to make bricks for the game
 * @author Michael Valverde
 */
public class Brick extends Sprite{

    private boolean destroyed;
    private int row;
    private int column;
    private String powerUp;
    private int points;


    public Brick(int x, int y,int row,int column){
        initBrick(x,y,row,column);
    }

    /**
     * Method that initializes the brick, giving certain parameters
     * @param x position in x in the window
     * @param y position in y in the window
     * @param row row in which the brick is placed
     * @param column column in which the brick is placed
     */
    private void initBrick(int x, int y, int row,int column){
        this.x = x;
        this.y = y;
        this.row = row;
        this.column = column;
        this.points = 5;
        destroyed = false;
        loadImage();
        getImageDimensions();
    }

    /**
     * Loads an image according to the row that the brick is placed, according to the project requirements
     * the bricks change its color in different rows so this method allows the requirement to be fulfilled
     */
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

    /**
     * Checks if the brick is destroyed
     * @return true if the brick is destroyed, false if the brick remains in the game
     */
    boolean isDestroyed(){
        return destroyed;
    }

    /**
     * Method that can change the destroyed status of the brick
     * @param val boolean that can change the status, true if the brick has been hit by the ball or false if not
     */
    void setDestroyed(boolean val){
        destroyed = val;
    }

    /**
     * gets row of the brick
     * @return integer that represents a row
     */
    public int getRow() {
        return row;
    }

    /**
     * sets the row of the brick
     * @param row integer that represents a row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * gets column of the brick
     * @return integer that represents a column
     */
    public int getColumn() {
        return column;
    }

    /**
     * sets column of the brick
     * @param column integer that represents a column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * gets both integers of the row and column that the brick is placed in
     * @return String with coordinates information
     */
    public String getBrickCoordinates(){
        System.out.println("Row: " + getRow() + " Column: " + getColumn());
        return "Row: " + getRow() + " Column: " + getColumn();
    }

    /**
     * gets the power up that was assigned by the server
     * @return
     */
    public String getPowerUp() {
        return powerUp;
    }

    /**
     * sets the power up that the brick is meant to have
     * @param powerUp String that represents a power up
     */
    public void setPowerUp(String powerUp) {
        this.powerUp = powerUp;
    }

    /**
     * gets the points that the server assigned to a brick
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * sets the points that the brick is meant to have according to the server
     * @param points integer that represents the points of a specific brick
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
