package Objects;

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

    public GameDetails(Brick lifepoints, Brick extraBall, Brick doubleRacket, Brick halfRacket, Brick moreSpeed, Brick lessSpeed) {
        this.lifepoints = lifepoints;
        this.extraBall = extraBall;
        this.doubleRacket = doubleRacket;
        this.halfRacket = halfRacket;
        this.moreSpeed = moreSpeed;
        this.lessSpeed = lessSpeed;
    }


    public int getGreenValue() {
        return greenValue;
    }

    public void setGreenValue(int greenValue) {
        this.greenValue = greenValue;
    }

    public int getYellowValue() {
        return yellowValue;
    }

    public void setYellowValue(int yellowValue) {
        this.yellowValue = yellowValue;
    }

    public int getOrangeValue() {
        return orangeValue;
    }

    public void setOrangeValue(int orangeValue) {
        this.orangeValue = orangeValue;
    }

    public int getRedValue() {
        return redValue;
    }

    public void setRedValue(int redValue) {
        this.redValue = redValue;
    }

    public Brick getLifepoints() {
        return lifepoints;
    }

    public Brick getExtraBall() {
        return extraBall;
    }

    public Brick getDoubleRacket() {
        return doubleRacket;
    }

    public Brick getHalfRacket() {
        return halfRacket;
    }

    public Brick getMoreSpeed() {
        return moreSpeed;
    }

    public Brick getLessSpeed() {
        return lessSpeed;
    }

    @Override
    public String toString() {
        return "GameDetails{" +
                "greenValue=" + greenValue +
                ", yellowValue=" + yellowValue +
                ", orangeValue=" + orangeValue +
                ", redValue=" + redValue +
                ", lifepoints=" + lifepoints +
                ", extraBall=" + extraBall +
                ", doubleRacket=" + doubleRacket +
                ", halfRacket=" + halfRacket +
                ", moreSpeed=" + moreSpeed +
                ", lessSpeed=" + lessSpeed +
                '}';
    }
}
