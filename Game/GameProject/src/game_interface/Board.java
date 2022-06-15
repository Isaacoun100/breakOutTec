package game_interface;

import JSON_Management.TypeConversion;
import GameUtils.GameDetails;
import java_socket.GameClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Board class that applies inheritance of the JPanel class in order to show the graphics of the game
 * it also contains the game logic and the socket architecture that allows to read and send messages from/to the server
 * @author Michael Valverde
 */

public class Board extends JPanel{
    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;
    private int score = 0;
    private int level = 1;
    private int gameLives;
    private int redBricks = 0;
    private int orangeBricks = 0;
    private int yellowBricks = 0;
    private int greenBricks = 0;
    private LinkedList<Ball> balls;

    static GameDetails gameDetails;

    public Board() {
        initBoard();
    }

    public static void updateGameDetails(){
        try {
            gameDetails = dataToJSON();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static GameDetails dataToJSON() throws ParseException {
        JSONObject jsonObject;
        GameDetails gameDetails;
        jsonObject  = TypeConversion.stringToJSONObject(GameClient.getInstance().readData());
        gameDetails = TypeConversion.makeGameDetailsObject(jsonObject);
        return gameDetails;
    }

    /**
     * Initializes the board of the game, starts the thread for the socket and the key listener, sets up configurations previously stated in the Commons Interface
     */
    private void initBoard() {


        GameUtils.Brick lifepoints = new GameUtils.Brick();
        GameUtils.Brick extraBall = new GameUtils.Brick();
        GameUtils.Brick doubleRacket = new GameUtils.Brick();
        GameUtils.Brick halfRacket = new GameUtils.Brick();
        GameUtils.Brick moreSpeed = new GameUtils.Brick();
        GameUtils.Brick lessSpeed = new GameUtils.Brick();

        lifepoints.setxValue(0);
        lifepoints.setyValue(7);

        extraBall.setxValue(1);
        extraBall.setyValue(7);

        doubleRacket.setxValue(2);
        doubleRacket.setyValue(7);

        halfRacket.setxValue(3);
        halfRacket.setyValue(7);

        moreSpeed.setxValue(4);
        moreSpeed.setyValue(7);

        lessSpeed.setxValue(5);
        lessSpeed.setyValue(7);

        gameDetails = new GameDetails(lifepoints,extraBall,doubleRacket,halfRacket,moreSpeed,lessSpeed);

        gameDetails.setGreenValue(5);
        gameDetails.setYellowValue(10);
        gameDetails.setOrangeValue(15);
        gameDetails.setRedValue(20);

        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        //Using Singleton method
        GameClient.getInstance().start();

        gameInit();
    }

    /**
     * Initializes the game, it sets up an array with a specific number of bricks
     * calls a paddle, it sets up a list with a ball and arranges the bricks in their respective positions
     *
     */
    private void gameInit(){
        bricks = new Brick[Commons.NUMBER_OF_BRICKS];
        //ball = new Ball();
        paddle = new Paddle();
        balls = new LinkedList<Ball>();
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        gameLives = balls.size();

        int k = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 14; j++){
                bricks[k] = new Brick(j * 65 + 40, i * 25 + 50,i,j);
                k++;
            }
        }
        timer = new Timer(20, new GameCycle());
        timer.start();
    }

    /**
     * Method that allows to render components that are passed as a parameter
     * @param g graphic to show on screen
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

        if(inGame){
            drawObjects(g2d);
            showGameMetrics(g2d);
        }else{
            gameFinished(g2d);
        }
        Toolkit.getDefaultToolkit().sync();

    }

    /**
     * Method that can draw objects on screen, the objects are passed as a parameter
     * @param g2d graphic to show on screen
     */
    private void drawObjects(Graphics2D g2d){
        for (int i = 0; i < balls.size(); i++){
            if(!balls.get(i).isDestroyed()) {
                g2d.drawImage(balls.get(i).getImage(), balls.get(i).getX(), balls.get(i).getY(), balls.get(i).getImageWidth(), balls.get(i).getImageHeight(), this);

            }
        }

        g2d.drawImage(paddle.getImage(),paddle.getX(),paddle.getY(),paddle.getImageWidth(),paddle.getImageHeight(),this);

        for(int i = 0; i < Commons.NUMBER_OF_BRICKS; i++){
            if(!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getImageWidth(), bricks[i].getImageHeight(),
                        this);

            }
        }

    }

    /**
     * clears the screen and draws a string with a message alerting the user that the game has finished
     * @param g2d graphic to draw on screen
     */
    private void gameFinished(Graphics2D g2d){
        var font = new Font("Verdana", Font.BOLD,18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message, (Commons.WIDTH - fontMetrics.stringWidth(message))/2, Commons.WIDTH / 2);
    }

    /**
     * draws the game metrics like lives, current level and current score in a string at the top of the screen
     * @param g2d graphic to draw on screen
     */
    private void showGameMetrics(Graphics2D g2d){
        var font = new Font("Verdana", Font.BOLD,18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString("Score: " + String.valueOf(score), (Commons.WIDTH - fontMetrics.stringWidth(String.valueOf(score)))/2, 20);
        g2d.drawString("Lives: " + String.valueOf(gameLives), (Commons.WIDTH - fontMetrics.stringWidth(String.valueOf(gameLives)))/3, 20);
        g2d.drawString("Level: " + String.valueOf(level), (Commons.WIDTH - fontMetrics.stringWidth(String.valueOf(level)))/5, 20);
    }


    /**
     * Class that manages the movement of the paddle according if the arrow keys have been pressed or released
     */
    private class TAdapter extends KeyAdapter{
        @Override
        /**
         * When the key is released the paddle does not move
         */
        public void keyReleased(KeyEvent e){
            paddle.keyReleased(e);
        }

        @Override
        /**
         * when an arrow key is being pressed the paddle moves to the right or left
         */
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    /**
     * Creates a game cycle that implements an action listener in order to make changes during the runtime of the game
     */
    private class GameCycle implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            doGameCycle();
        }
    }

    /**
     * Sets up the game cycle for the game, allows the elements of the balls and paddle to move according to the user actions
     * calls the method to check for collisions and keeps refreshing the window using the method repaint
     */
    private void doGameCycle(){

        for(int i = 0; i < balls.size(); i++){
            balls.get(i).move();
        }

        for(int i=0; i<Commons.NUMBER_OF_BRICKS; i++){
            if(Objects.equals(bricks[i].getBrickType(), "RED"))
                bricks[i].setPoints(gameDetails.getRedValue());
            if(Objects.equals(bricks[i].getBrickType(), "ORANGE"))
                bricks[i].setPoints(gameDetails.getOrangeValue());
            if(Objects.equals(bricks[i].getBrickType(), "YELLOW"))
                bricks[i].setPoints(gameDetails.getYellowValue());
            if(Objects.equals(bricks[i].getBrickType(), "GREEN"))
                bricks[i].setPoints(gameDetails.getGreenValue());

            if(gameDetails.getLifepoints().getxValue() == bricks[i].getRow() && gameDetails.getLifepoints().getyValue() == bricks[i].getColumn()){
                bricks[i].setPowerUp("BALL");
            }else if(gameDetails.getExtraBall().getxValue() == bricks[i].getRow() && gameDetails.getExtraBall().getyValue() == bricks[i].getColumn()){
                bricks[i].setPowerUp("BALL");
            }else if(gameDetails.getDoubleRacket().getxValue() == bricks[i].getRow() && gameDetails.getDoubleRacket().getyValue() == bricks[i].getColumn()){
                bricks[i].setPowerUp("NORMAL_RACKET");
            }else if(gameDetails.getHalfRacket().getxValue() == bricks[i].getRow() && gameDetails.getHalfRacket().getyValue() == bricks[i].getColumn()){
                bricks[i].setPowerUp("HALF_RACKET");
            }else if (gameDetails.getMoreSpeed().getxValue() == bricks[i].getRow() && gameDetails.getMoreSpeed().getyValue() == bricks[i].getColumn()){
                bricks[i].setPowerUp("MORE_SPEED");
            }else if(gameDetails.getLessSpeed().getxValue() == bricks[i].getRow() && gameDetails.getLessSpeed().getyValue() == bricks[i].getColumn()){
                bricks[i].setPowerUp("LESS_SPEED");
            }
        }

        paddle.move();

        checkCollision();
        repaint();
    }

    /**
     * method to stop the game cycle, this method is called when the user has lost the game or when the user wins the game
     */
    private void stopGame(){
        inGame = false;
        timer.stop();
    }

    /**
     * checks for collisions between sprites, checks if a ball has touched a brick, the edges of the window, or the paddle
     * makes changes to the game according to the collision being performed
     */
    private void checkCollision(){
        for(int i = 0; i < balls.size(); i++){
            if(balls.get(i).getRectangle().getMaxY() > Commons.BOTTOM_EDGE){
                balls.get(i).setY(0);
                balls.get(i).setDestroyed(true);
                gameLives -= 1;
                if(gameLives <= 0){
                    stopGame();
                }
            }

            for(int m = 0, j = 0; m < Commons.NUMBER_OF_BRICKS; m++){
                if(bricks[m].isDestroyed()){

                    j++;
                }

                if(j == Commons.NUMBER_OF_BRICKS){
                    message = "Victory";
                    stopGame();
                }
            }


            if(!balls.get(i).isDestroyed()){
                if((balls.get(i).getRectangle()).intersects((paddle.getRectangle()))){
                    int paddlePos = (int) paddle.getRectangle().getMinX();
                    int ballPos = (int) balls.get(i).getRectangle().getMinX();

                    int first = paddlePos + 8;
                    int second = paddlePos + 16;
                    int third = paddlePos + 24;
                    int fourth = paddlePos + 32;

                    if(ballPos < first){
                        balls.get(i).setXDir(-3);
                        balls.get(i).setYDir(-3);
                    }
                    if(ballPos >= second && ballPos < third){
                        balls.get(i).setXDir(0);
                        balls.get(i).setYDir(-3);
                    }
                    if(ballPos >= third && ballPos < fourth){
                        balls.get(i).setXDir(3);
                        balls.get(i).setYDir(-3 * balls.get(i).getYDir());
                    }
                    if(ballPos > fourth){
                        balls.get(i).setXDir(3);
                        balls.get(i).setYDir(-3);
                    }
                }
            }


            for(int k = 0; k < Commons.NUMBER_OF_BRICKS; k++){
                if((balls.get(i).getRectangle()).intersects((bricks[k].getRectangle()))){
                    int ballLeft = (int) balls.get(i).getRectangle().getMinX();
                    int ballHeight = (int) balls.get(i).getRectangle().getHeight();
                    int ballWidth = (int) balls.get(i).getRectangle().getWidth();
                    int ballTop = (int) balls.get(i).getRectangle().getMinY();

                    var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                    var pointLeft = new Point(ballLeft - 1, ballTop);
                    var pointTop = new Point(ballLeft, ballTop - 1);
                    var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                    if(!bricks[k].isDestroyed()){
                        if(bricks[k].getRectangle().contains(pointRight)){
                            balls.get(i).setXDir(-1);
                        }else if(bricks[k].getRectangle().contains(pointLeft)){
                            balls.get(i).setXDir(1);
                        }

                        if(bricks[k].getRectangle().contains(pointTop)){
                            balls.get(i).setYDir(1);
                        }else if(bricks[k].getRectangle().contains(pointBottom)){
                            balls.get(i).setYDir(-1);
                        }

                        score += bricks[k].getPoints();

                        checkLevel(k);

                        if(bricks[k].getPowerUp() == "BALL"){
                            balls.add(new Ball());
                            gameLives--;
                        }else if(bricks[k].getPowerUp() == "NORMAL_RACKET"){
                            paddle.changePaddleSize("NORMAL");
                        }else if (bricks[k].getPowerUp() == "HALF_RACKET"){
                            paddle.changePaddleSize("LITTLE");
                        }else if(bricks[k].getPowerUp() == "MORE_SPEED"){
                            balls.get(i).setSpeed(2);
                        }else if(bricks[k].getPowerUp() == "LESS_SPEED"){
                            balls.get(i).setSpeed(1);
                        }

                        bricks[k].setDestroyed(true);
                        GameClient.getInstance().sendData(bricks[k].getBrickCoordinates());

                    }

                }
            }
        }


    }

    private void checkLevel(int index){

        String brickType = bricks[index].getBrickType();
        if(brickType == "RED"){
            redBricks += 1;
            System.out.println("Red bricks " + redBricks);
        }else if(brickType == "ORANGE"){
            orangeBricks += 1;
            System.out.println("orange bricks " + orangeBricks);
        }else if(brickType == "YELLOW"){
            yellowBricks += 1;
            System.out.println("yellow bricks " + yellowBricks);
        }else if(brickType == "GREEN"){
            greenBricks += 1;

            System.out.println("green bricks " + greenBricks);
        }

        if(redBricks == Commons.RED_BRICKS){
            level += 1;
        }else if(orangeBricks == Commons.ORANGE_BRICKS){
            level += 1;
        }else if(yellowBricks == Commons.YELLOW_BRICKS){
            level += 1;
        }else if(greenBricks == Commons.GREEN_BRICKS){
            level += 1;
        }

    }












}
