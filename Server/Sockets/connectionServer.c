#include "connectionServer.h"


struct sockaddr_in address;
int addrlen = sizeof(address),
        new_socket, server_fd,
        opt = 1, valread, PORT;

int startConnection(){

    char* hello = "Successfully connected to the server";

    printf("Please indicate the port you want to create the communication\n");
    scanf("%d", &PORT);
    printf("Connecting with the port %d , please wait \n", PORT);

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

    sendMessage(hello);
    receiveMessages();

    close(new_socket);
    shutdown(server_fd, SHUT_RDWR);
    return 0;
}

void receiveMessages(){
    char buffer[1024] = { 0 };

    while(strcmp(buffer,"EXIT\n")!=0){
        valread = read(new_socket, buffer, 1024);
        printf("%s", buffer);
    }
}

void sendMessage(char* message){
    send(new_socket, message, strlen(message), 0);
}
