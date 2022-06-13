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

extern void *startConversation();
void newMessage(char* message);
int connectObserver();
void concludeConnection();

#endif
