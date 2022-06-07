#include "commandInteraction.h"

struct Brick{
    int xValue;
    int yValue;
};

union gameDetails{

    int greenValue,         // Value of the Green Brick
    yellowValue,        // Value of the Yellow Brick
    orangeValue,        // Value of the Orange Brick
    redValue;           // Value of the Red Brick

    struct Brick lifePoints,    // Position of the extra life points brick
    extraBall,          // Position of the extra ball brick
    doubleRacket,       // Position of the double racket brick
    halfRacket,         // Position of the half racket brick
    moreSpeed,          // Position of the more speed brick
    lessSpeed;          // Position of the less speed brick

};

int startConsole(){

    int newValue,PORT = 6969; // Default server port if none was indicated
    bool validPORT, isFirst;
    char command[128];

    union gameDetails newGame;

    //Default brick values
    newGame.greenValue = 5;
    newGame.yellowValue = 10;
    newGame.orangeValue = 15;
    newGame.redValue = 20;

    // Default bonus brick position
    newGame.lifePoints.xValue = 0;
    newGame.lifePoints.yValue = 7;

    newGame.extraBall.xValue = 1;
    newGame.extraBall.yValue = 7;

    newGame.doubleRacket.xValue = 2;
    newGame.doubleRacket.yValue = 7;

    newGame.halfRacket.xValue = 3;
    newGame.halfRacket.yValue = 7;

    newGame.moreSpeed.xValue = 4;
    newGame.lessSpeed.yValue = 7;


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
                "Status              →   Retrieves the data of the current game \n"
                "Help                →   To read the instructions again \n" ,

                *portInput = "Please specify the port you want the server to be hosted in \n"
                             "the port must be over 1024, if none specified 6969 will be selected \n" "PORT: ",
                *brickValue = "Please indicate the new value of the brick \n" "New Value for the ",
                *bonusBrickX = "Please indicate the x coordinate value for the bonus \n"
                              "Value must be in the range of 0 and 13 \n" "X coordinate: ",
                *bonusBrickY = "Please indicate the Y coordinate value for the bonus \n"
                           "Value must be in the range of 0 and 13 \n" "Y coordinate: ";

    printf("%s", instructions);

    while(strcmp(command,"Exit") != 0){

        printf("Breakout Console~# : ");
        scanf("%s", &command);

        if(strcmp(command,"ChangeGreenBrick")==0){

            printf("%s Green Brick: ", brickValue);
            scanf("%d", &newValue);
            newGame.greenValue = newValue;

        }

        else if(strcmp(command,"ChangeYellowBrick")==0){

            printf("%s Yellow Brick: ", brickValue);
            scanf("%d", &newValue);
            newGame.yellowValue = newValue;

        }
        else if(strcmp(command,"ChangeOrangeBrick")==0){

            printf("%s Orange Brick", brickValue);
            scanf("%d", &newValue);
            newGame.orangeValue = newValue;

        }
        else if(strcmp(command,"BrickLifePoints")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.lifePoints.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.lifePoints.yValue = newValue;

        }
        else if(strcmp(command,"BallBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.extraBall.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.extraBall.yValue = newValue;

        }
        else if(strcmp(command,"DoubleRacketBrick")==0){
            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.doubleRacket.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.doubleRacket.yValue = newValue;

        }
        else if(strcmp(command,"HalfRacketBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.halfRacket.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.halfRacket.yValue = newValue;

        }
        else if(strcmp(command,"FasterBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.moreSpeed.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.moreSpeed.yValue = newValue;

        }
        else if(strcmp(command,"SlowerBrick")==0){

            printf("%s", bonusBrickX);
            scanf("%d", &newValue);
            newGame.lessSpeed.xValue = newValue;

            printf("%s", bonusBrickY);
            scanf("%d", &newValue);
            newGame.lessSpeed.yValue = newValue;

        }

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

        else if(strcmp(command,"Send")==0){
            isFirst=false;

            while(!isFirst){
                printf("%s",portInput);
                scanf("%d", &PORT);
                if(PORT>1024)
                    isFirst=true;

            }

            printf("The game server will be hosted in %d \n", PORT);
        }
        else if(strcmp(command,"Help")==0)
            printf("%s", instructions);
        else
            printf("Sorry, command not found \n");
    }

    return 0;
}

/**
 * This function checks if the given input is a number or not
 * Credits to https://www.codegrepper.com/code-examples/c/how+to+check+if+a+string+is+a+number+c
 * @param s Char to check
 * @return 1 if the Char only contains numbers, 0 if it doesn't
 */
int isNumber(char s[])
{
    for (int i = 0; s[i]!= '\0'; i++)
    {
        if (isdigit(s[i]) == 0) return 0;
    }

    return 1;
}