#include "connectionObserver.h"

struct sockaddr_in sockAddress;
int addSize = sizeof(sockAddress),
        observerSocket, serverID,
        ident = 1, Port=8080;

void *startConversation(){
    connectObserver();
    char message[1024];
    while(strcmp(message,"EXIT")!=0){
        strcpy(message, receiveMessages());
        newMessage(message);
    }
    concludeConnection();
}

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

void newMessage(char* message){
    send(observerSocket, message, strlen(message), 0);
}

void concludeConnection(){
    close(observerSocket);
    shutdown(observerSocket, SHUT_RDWR);
}