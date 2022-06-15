#include "connectionServer.h"

// Struct for the socket address
struct sockaddr_in address;

// Global variables
int addrlen = sizeof(address),
        new_socket, server_fd,
        opt = 1, valread, PORT=6969;

// Message buffer
char buffer[1024] = { 0 };

/**
 * Initializes the connection that the Game will use to set change the game state, it will try to open a socket
 * for the Observer to connect in the port 6969.
 * @return 0 if the connection was successful, 1 if not.
 * @author Isaac Herrera Monge
 */
int startConnection(){

    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("couldn't create the file descriptor");
        return false;
    }

    // Forcefully attaching socket to the port 8080
    if (setsockopt(server_fd, SOL_SOCKET,
                   SO_REUSEADDR | SO_REUSEPORT, &opt,
                   sizeof(opt))) {
        perror("setsockopt");
        return 1;
    }

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    // Forcefully attaching socket to the PORT
    if (bind(server_fd, (struct sockaddr*)&address,
             sizeof(address))
        < 0) {
        perror("Couldn't bind correctly");
        return 1;
    }
    if (listen(server_fd, 3) < 0) {
        perror("Unable to listen");
        return 1;
    }

    if ((new_socket = accept(server_fd, (struct sockaddr*)&address, (socklen_t*)&addrlen)) < 0) {
        perror("Unable to accept");
        return 1;
    }

    return 0;
}

/**
 * Receives the messages sent by the client
 * @return the message sent
 */
const char* receiveMessages(){
    valread = read(new_socket, buffer, 1024);
    return buffer;
}

/**
 * Send a message through the socket to the client in the port 6969
 * @param message The message that is going to be sent
 * @author Isaac Herrera Monge
 */
void sendMessage(char* message){
    send(new_socket, message, strlen(message), 0);
}

/**
 * Terminates and closes the server in port 6969
 * @author Isaac Herrera Monge
 */
void stopConnection(){
    close(new_socket);
    shutdown(server_fd, SHUT_RDWR);
}