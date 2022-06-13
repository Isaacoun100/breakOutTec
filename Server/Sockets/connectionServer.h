#include <netinet/in.h>
#include <sys/socket.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#ifndef SERVER_CONNECTIONSERVER_H
#define SERVER_CONNECTIONSERVER_H

extern void sendMessage(char* message);
extern int startConnection();
const char* receiveMessages();
void stopConnection();

#endif //SERVER_CONNECTIONSERVER_H
