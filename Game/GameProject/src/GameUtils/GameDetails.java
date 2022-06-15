package GameUtils;

/**
 * @author Naheem J.
 * GameDetails class used for containing all the important information regarding the game, contains the integer values,
 * and the brick values with their corresponding coordinates
 */
public class GameDetails {

    private int greenValue;
    private int yellowValue;
    private int orangeValue;
    private int redValue;

    private Brick lifepoints;
    private Brick extraBall;
    private Brick doubleRacket;
    private Brick halfRacket;
    private Brick moreSpeed;
    private Brick lessSpeed;

    /**
     * Constructor for the GameDetails class used for initializing the attributes
     * @param lifepoints the life points attribute
     * @param extraBall the extra ball attribute
     * @param doubleRacket the doubleRacket attribute
     * @param halfRacket the halfRacket attribute
     * @param moreSpeed the moreSpeed attribute
     * @param lessSpeed the lessSpeed attribute
     */
    public GameDetails(Brick lifepoints, Brick extraBall, Brick doubleRacket, Brick halfRacket, Brick moreSpeed, Brick lessSpeed) {
        this.lifepoints = lifepoints;
        this.extraBall = extraBall;
        this.doubleRacket = doubleRacket;
        this.halfRacket = halfRacket;
        this.moreSpeed = moreSpeed;
        this.lessSpeed = lessSpeed;
    }

    /**
     * Getter for greenValue variable
     * @return greenValue
     */
    public int getGreenValue() {
        return greenValue;
    }

    /**
     * Setter for greenValue
     * @param greenValue
     */
    public void setGreenValue(int greenValue) {
        this.greenValue = greenValue;
    }

    /**
     * Getter for yellowValue variable
     * @return the yValue variable
     */
    public int getYellowValue() {
        return yellowValue;
    }

    /**
     * Setter for yellowValue
     * @param yellowValue the yellowValue variable
     */
    public void setYellowValue(int yellowValue) {
        this.yellowValue = yellowValue;
    }

    /**
     * Getter for orangeValue variable
     * @return the orangeValue variable
     */
    public int getOrangeValue() {
        return orangeValue;
    }

    /**
     * Setter for the orangeValue variable
     * @param orangeValue the orangeValue variable
     */
    public void setOrangeValue(int orangeValue) {
        this.orangeValue = orangeValue;
    }

    /**
     * Getter for the redValue variable
     * @return the redValue variable
     */
    public int getRedValue() {
        return redValue;
    }

    /**
     * Setter for the redValue variable
     * @param redValue the redValue variable
     */
    public void setRedValue(int redValue) {
        this.redValue = redValue;
    }

    /**
     * Getter for the lifepoints variable
     * @return the lifepoints variable
     */
    public Brick getLifepoints() {
        return lifepoints;
    }

    /**
     * Getter for extraBall variable
     * @return the extraball variable
     */
    public Brick getExtraBall() {
        return extraBall;
    }

    /**
     * Getter for the doubleRacket variable
     * @return the doubleRacket variable
     */
    public Brick getDoubleRacket() {
        return doubleRacket;
    }

    /**
     * Getter for the halfRacket
     * @return the halfRacket variable
     */
    public Brick getHalfRacket() {
        return halfRacket;
    }

    /**
     * Getter for the moreSpeed variable
     * @return the moreSpeed variable
     */
    public Brick getMoreSpeed() {
        return moreSpeed;
    }

    /**
     * Getter for the lessSpeed variable
     * @return the lessSpeed variable
     */
    public Brick getLessSpeed() {
        return lessSpeed;
    }
}
