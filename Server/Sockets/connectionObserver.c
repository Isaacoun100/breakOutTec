#include "connectionObserver.h"

// Struct for the socket address
struct sockaddr_in sockAddress;

//Global variables
int addSize = sizeof(sockAddress),
        observerSocket, serverID,
        ident = 1, Port=8080;

/**
 * Waits for a message from the Game socket to send it into the Observer socket to inform the Observer of the game state
 * @return void function
 * @author Isaac Herrera Monge
 */
void *startConversation(){
    connectObserver();
    char message[1024];
    while(strcmp(message,"EXIT")!=0){
        strcpy(message, receiveMessages());
        newMessage(message);
    }
    concludeConnection();
}

/**
 * Initializes the connection that the Observer will use to set the state in the observer, it will try to open a socket
 * for the Observer to connect in the port 8080.
 * @return 0 if the connection was successful, 1 if not.
 * @author Isaac Herrera Monge
 */
int connectObserver(){
    // Creating socket file descriptor
    if ((serverID = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("couldn't create the file descriptor");
        return false;
    }

    // Forcefully attaching socket to the port 8080
    if (setsockopt(serverID, SOL_SOCKET,
                   SO_REUSEADDR | SO_REUSEPORT, &ident,
                   sizeof(ident))) {
        perror("setsockopt");
        return 1;
    }

    sockAddress.sin_family = AF_INET;
    sockAddress.sin_addr.s_addr = INADDR_ANY;
    sockAddress.sin_port = htons(Port);

    // Forcefully attaching socket to the PORT
    if (bind(serverID, (struct sockaddr*)&sockAddress,
             sizeof(sockAddress))
        < 0) {
        perror("Couldn't bind correctly");
        return 1;
    }
    if (listen(serverID, 3) < 0) {
        perror("Unable to listen");
        return 1;
    }

    if ((observerSocket = accept(serverID, (struct sockaddr*)&sockAddress, (socklen_t*)&addSize)) < 0) {
        perror("Unable to accept");
        return 1;
    }
    
    return 0;
}

/**
 * Send a message through the socket to the client in the port 8080
 * @param message The message that is going to be sent
 * @author Isaac Herrera Monge
 */
void newMessage(char* message){
    send(observerSocket, message, strlen(message), 0);
}

/**
 * Terminates and closes the server in port 8080
 * @author Isaac Herrera Monge
 */
void concludeConnection(){
    close(observerSocket);
    shutdown(observerSocket, SHUT_RDWR);
}