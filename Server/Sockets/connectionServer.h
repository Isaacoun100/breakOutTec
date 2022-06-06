#include <netinet/in.h>
#include <sys/socket.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#ifndef SERVER_CONNECTIONSERVER_H
#define SERVER_CONNECTIONSERVER_H

extern int startConnection();
void receiveMessages();
extern void sendMessage(char* message);

#endif //SERVER_CONNECTIONSERVER_H
