#include <netinet/in.h>
#include <sys/socket.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "connectionServer.h"

#ifndef SERVER_CONNECTIONOBSERVER_H
#define SERVER_CONNECTIONOBSERVER_H

/**
 * Waits for a message from the Game socket to send it into the Observer socket to inform the Observer of the game state
 * @return void function
 * @author Isaac Herrera Monge
 */
extern void *startConversation();

/**
 * Send a message through the socket to the client in the port 8080
 * @param message The message that is going to be sent
 * @author Isaac Herrera Monge
 */
void newMessage(char* message);

/**
 * Initializes the connection that the Observer will use to set the state in the observer, it will try to open a socket
 * for the Observer to connect in the port 8080.
 * @return 0 if the connection was successful, 1 if not.
 * @author Isaac Herrera Monge
 */
int connectObserver();

/**
 * Terminates and closes the server in port 8080
 * @author Isaac Herrera Monge
 */
void concludeConnection();

#endif
