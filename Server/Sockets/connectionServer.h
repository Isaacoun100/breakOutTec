#include <netinet/in.h>
#include <sys/socket.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#ifndef SERVER_CONNECTIONSERVER_H
#define SERVER_CONNECTIONSERVER_H

/**
 * Send a message through the socket to the client in the port 6969
 * @param message The message that is going to be sent
 * @author Isaac Herrera Monge
 */
extern void sendMessage(char* message);

/**
 * Initializes the connection that the Game will use to set change the game state, it will try to open a socket
 * for the Observer to connect in the port 6969.
 * @return 0 if the connection was successful, 1 if not.
 * @author Isaac Herrera Monge
 */
extern int startConnection();

/**
 * Receives the messages sent by the client
 * @return the message sent
 */
const char* receiveMessages();

/**
 * Terminates and closes the server in port 6969
 * @author Isaac Herrera Monge
 */
void stopConnection();

#endif //SERVER_CONNECTIONSERVER_H
