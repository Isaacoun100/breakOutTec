#include "commandInteraction.h"

int startConsole(){

    int PORT = 6969; // Default server port if none was indicated
    bool validPORT, isFirst;
    char command;

    printf("\"Welcome to the game server console \n"
           "Thanks, the server will be hosted in the port %d \n"
           "Welcome to the server console, please read the following instructions\n"
           "ChangeGreenBrick    →   To change the green brick value\n"
           "ChangeYellowBrick   →   To change the yellow brick value\n"
           "ChangeOrangeBrick   →   To change the orange brick value\n"
           "BrickLifePoints     →   To add a life in a specific brick\n"
           "BallBrick           →   To add a ball to a specific brick\n"
           "DoubleRacketBrick   →   To add a double racket bonus\n"
           "HalfRacketBrick     →   To add a half racket bonus\n"
           "FasterSpeedBrick    →   To add a more speed bonus\n"
           "SlowerBrick         →   To add a less speed bonus\n", PORT);

    // Fix comparing error
    while(strcmp(command,"Exit") != 0){

        // Use switch to use the console

        if(strcmp(command,"Send") && !isFirst ){

            //Correct this

            while(!validPORT){
                printf("Please specify the port you want the server port to be hosted in, please ensure the port is open \n"
                       "0   →   If you want to use the default port \n");
                scanf("%d", &PORT); // Server where the game is going to be hosted, 6969 is default

                if(PORT==0 || isNumber(PORT)==1 )
                    validPORT=1; // Use default port
            }

            isFirst=true;

        }
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