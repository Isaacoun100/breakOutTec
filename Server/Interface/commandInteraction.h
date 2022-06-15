
#include "../Sockets/connectionServer.h"
#include "../Sockets/connectionObserver.h"
#include <stdbool.h>
#include <pthread.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>

#ifndef SERVER_COMMANDINTERACTION_H
#define SERVER_COMMANDINTERACTION_H

/**
 * This struct will store two values, the x and y position of the brick, the idea of the Brick structure is that we can
 * store the position of a brick and access it as a whole
 * @author Isaac Herrera Monge
 */
struct Brick;

/**
 * This struct will keep all of the values that are needed to send a JSON with the game details, the first int values
 * correspond to the points that are given to the player if they brick set block.
 * greenValue corresponds to the value for the green brick
 * yellowValue corresponds to the value for the yellow brick
 * orangeValue corresponds to the value for the orange brick
 * redValue corresponds to the value for the red brick
 * @author Isaac Herrera Monge
 */
struct gameDetails;

/**
 * This function initializes the CLI so that the user can interact with the server, it also creates a default
 * gameDetails struct that will be sent if the user tells the CLI to "Send"
 * @return 0 if the game was executed correctly
 */
extern int startConsole();

/**
 * It will receive the struct newGame and convert it into JSON, no library should be required, it is just string
 * management as we need no library, the JSON can be easily created and manipulated. Plus the JSON that sends the
 * game is just a simple string that is passed to the observer, therefore is not necessary.
 * @author Isaac Herrera Monge
 * @param newGame The struct that will be converted to JSON
 * @return a const char* that will contain a JSON of the  given gameDetails struct
 */
const char* convertJSON(struct gameDetails newGame);

#endif
