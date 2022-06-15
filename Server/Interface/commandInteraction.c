/**
 * @author Isaac Herrera Monge
 * @version 1.0
 * This file is the in charge of the communication between the user and the server, a Command Line Interface (CLI) will
 * be shown when the user needs to interact. The input of the CLI is specified when the program is executed.
 */
#include "commandInteraction.h"

/**
 * This struct will store two values, the x and y position of the brick, the idea of the Brick structure is that we can
 * store the position of a brick and access it as a whole
 * @author Isaac Herrera Monge
 */
struct Brick{
    int xValue;
    int yValue;
};

/**
 * This struct will keep all of the values that are needed to send a JSON with the game details, the first int values
 * correspond to the points that are given to the player if they brick set block.
 * greenValue corresponds to the value for the green brick
 * yellowValue corresponds to the value for the yellow brick
 * orangeValue corresponds to the value for the orange brick
 * redValue corresponds to the value for the red brick
 * @author Isaac Herrera Monge
 */
struct gameDetails{

    int greenValue;         // Value of the Green Brick
    int yellowValue;        // Value of the Yellow Brick
    int orangeValue;        // Value of the Orange Brick
    int redValue;           // Value of the Red Brick

    struct Brick lifePoints;         // Position of the extra life points brick
    struct Brick extraBall;          // Position of the extra ball brick
    struct Brick doubleRacket;       // Position of the double racket brick
    struct Brick halfRacket;         // Position of the half racket brick
    struct Brick moreSpeed;          // Position of the more speed brick
    struct Brick lessSpeed;          // Position of the less speed brick

};

/**
 * It will receive the struct newGame and convert it into JSON, no library should be required, it is just string
 * management as we need no library, the JSON can be easily created and manipulated. Plus the JSON that sends the
 * game is just a simple string that is passed to the observer, therefore is not necessary.
 * @author Isaac Herrera Monge
 * @param newGame The struct that will be converted to JSON
 * @return a const char* that will contain a JSON of the  given gameDetails struct
 * @author Isaac Herrera Monge
 */
const char* convertJSON(struct gameDetails newGame){

    char JSON[512];

    sprintf(JSON, "{\n"
                  "    \"greenValue\": %d,\n"
                  "    \"yellowValue\": %d,\n"
                  "    \"orangeValue\": %d,\n"
                  "    \"redValue\": %d,\n"
                  "    \"lifePoints\":[%d,%d],\n"
                  "    \"extraBall\":[%d,%d],\n"
                  "    \"doubleRacket\":[%d,%d],\n"
                  "    \"halfRacket\":[%d,%d],\n"
                  "    \"moreSpeed\":[%d,%d],\n"
                  "    \"lessSpeed\":[%d,%d]\n"
                  "}",
                  newGame.greenValue, newGame.yellowValue,
                  newGame.orangeValue, newGame.redValue,
                  newGame.lifePoints.xValue, newGame.lifePoints.yValue,
                  newGame.extraBall.xValue,newGame.extraBall.yValue,
                  newGame.doubleRacket.xValue, newGame.doubleRacket.yValue,
                  newGame.halfRacket.xValue, newGame.halfRacket.yValue,
                  newGame.moreSpeed.xValue, newGame.moreSpeed.yValue,
                  newGame.lessSpeed.xValue, newGame.lessSpeed.yValue);

    char* result = JSON;
    return result;
}

/**
 * This function initializes the CLI so that the user can interact with the server, it also creates a default
 * gameDetails struct that will be sent if the user tells the CLI to "Send"
 * @return 0 if the game was executed correctly
 * @author Isaac Herrera Monge
 */
int startConsole(){

    int newValue,       // Response writen in the CLI
        PORT = 6969;    // Default server port if none was indicated
    bool hasStarted,     // Indicates if the game has already started
         isObserving;    // Indicates if the observer has already been created
    char command[128];  // Message writen in the CLI

    struct gameDetails newGame;

    //Default brick values
    newGame.greenValue = 5;
    newGame.yellowValue = 10;
    newGame.orangeValue = 15;
    newGame.redValue = 20;

    // Default life points brick position
    newGame.lifePoints.xValue = 0;
    newGame.lifePoints.yValue = 7;

    // Default extra ball brick position
    newGame.extraBall.xValue = 1;
    newGame.extraBall.yValue = 7;

    // Default double racket brick position
    newGame.doubleRacket.xValue = 2;
    newGame.doubleRacket.yValue = 7;

    // Default half racket brick position
    newGame.halfRacket.xValue = 3;
    newGame.halfRacket.yValue = 7;

    // Default more speed brick position
    newGame.moreSpeed.xValue = 4;
    newGame.moreSpeed.yValue = 7;

    // Default less speed brick position
    newGame.lessSpeed.xValue = 5;
    newGame.lessSpeed.yValue = 7;


    // Instructions that are shown to the user
    const char *instructions =
                "Welcome to the server console, please read the following instructions\n"
                "ChangeGreenBrick    →   To change the green brick value\n"
                "ChangeYellowBrick   →   To change the yellow brick value\n"
                "ChangeOrangeBrick   →   To change the orange brick value\n"
                "ChangeRedBrick      →   To change the red brick value\n"
                "BrickLifePoints     →   To add a life in a specific brick\n"
                "BallBrick           →   To add a ball to a specific brick\n"
                "DoubleRacketBrick   →   To add a double racket bonus\n"
                "HalfRacketBrick     →   To add a half racket bonus\n"
                "FasterBrick         →   To add a more speed bonus\n"
                "SlowerBrick         →   To add a less speed bonus\n"
                "Send                →   To send the data and start the game \n"
                "Observer            →   Starts the connection with the Observer\n"
                "OpenSocket          →   Initializes the connection\n"
                "Status              →   Retrieves the data of the current game \n"
                "Help                →   To read the instructions again \n" ,

                *brickValue = "Please indicate the new value of the brick \n" "New Value for the ",
                *bonusBrickX = "Please indicate the x coordinate value for the bonus \n"
                              "Value must be in the range of 0 and 13 \n" "X coordinate: ",
                *bonusBrickY = "Please indicate the Y coordinate value for the bonus \n"
                           "Value must be in the range of 0 and 13 \n" "Y coordinate: ";

    printf("%s", instructions);

    //Exit the console with exit
    while(strcmp(command,"Exit") != 0){

        printf("Breakout Console~# : ");
        scanf("%s", &command);

        // Change the green brick
        if(strcmp(command,"ChangeGreenBrick")==0){

            printf("%s Green Brick: ", brickValue);
            scanf("%d", &newValue);
            newGame.greenValue = newValue;
        }

        // Change Yellow Brick
        else if(strcmp(command,"ChangeYellowBrick")==0){

            printf("%s Yellow Brick: ", brickValue);
            scanf("%d", &newValue);
            newGame.yellowValue = newValue;

        }

        //Change Orange Brick
        else if(strcmp(command,"ChangeOrangeBrick")==0){

            printf("%s Orange Brick: ", brickValue);
            scanf("%d", &newValue);
            newGame.orangeValue = newValue;

        }

        //Change Red Brick
        else if(strcmp(command,"ChangeRedBrick")==0){

            printf("%s Red Brick: ", brickValue);
            scanf("%d", &newValue);
            newGame.redValue = newValue;

        }

        //Change Life Brick Position
        else if(strcmp(command,"BrickLifePoints")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.lifePoints.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.lifePoints.yValue = newValue;

        }

        //Change Ball Brick Position
        else if(strcmp(command,"BallBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.extraBall.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.extraBall.yValue = newValue;

        }

        //Change Double Racket Brick Position
        else if(strcmp(command,"DoubleRacketBrick")==0){
            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.doubleRacket.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.doubleRacket.yValue = newValue;

        }

        //Change Half Racket Position
        else if(strcmp(command,"HalfRacketBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.halfRacket.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.halfRacket.yValue = newValue;

        }

        //Change Faster Brick Position
        else if(strcmp(command,"FasterBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.moreSpeed.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.moreSpeed.yValue = newValue;

        }

        //Change Slower Brick Position
        else if(strcmp(command,"SlowerBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.lessSpeed.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.lessSpeed.yValue = newValue;

        }

        //Return the game status
        else if(strcmp(command,"Status")==0){
            printf("Game will be hosted in the port: %d \n"
                   "The value for the green brick is: %d \n"
                   "The value for the yellow brick is: %d \n"
                   "The value for the orange brick is: %d \n"
                   "The value for the red brick is: %d \n"
                   "The life point brick is in [ %d , %d ] \n"
                   "The extra ball brick is in [ %d , %d ] \n"
                   "The double racket brick is in [ %d , %d ] \n"
                   "The half racket brick is in [ %d , %d ] \n"
                   "The faster ball brick is in [ %d , %d ] \n"
                   "The slower ball is in [ %d , %d ] \n"
                   ,PORT, newGame.greenValue,newGame.yellowValue
                   ,newGame.orangeValue ,newGame.redValue
                   ,newGame.lifePoints.xValue, newGame.lifePoints.yValue
                   ,newGame.extraBall.xValue, newGame.extraBall.yValue
                   ,newGame.doubleRacket.xValue, newGame.doubleRacket.yValue
                   ,newGame.halfRacket.xValue, newGame.halfRacket.yValue
                   ,newGame.moreSpeed.xValue, newGame.moreSpeed.yValue
                   ,newGame.lessSpeed.xValue, newGame.lessSpeed.yValue);
        }

        //Activate the observer
        else if(strcmp(command,"Observer")==0){

            if(isObserving)
                printf("The observer is active\n");
            else if(!hasStarted)
                printf("Please send the server settings before starting the observer\n");
            else{
                pthread_t thread_id;
                pthread_create(&thread_id, NULL, startConversation, NULL);
            }
        }

        else if(strcmp(command,"OpenSocket")==0){
            startConnection();
            printf("Connection started\n");
            hasStarted=true;
        }

        // Sends through the socket the JSON
        else if(strcmp(command,"Send")==0){

            char* settings = convertJSON(newGame);

            if(hasStarted)
                sendMessage(settings);
            else
                printf("Server has not started\n");

        }

        // Give the instructions
        else if(strcmp(command,"Help")==0)
            printf("%s", instructions);

        // Finalizes the communication
        else if(strcmp(command, "Exit")==0)
            stopConnection();
        else
            printf("Sorry, command not found \n");
    }

    return 0;
}
