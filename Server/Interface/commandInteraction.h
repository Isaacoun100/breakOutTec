
#include "../Sockets/connectionServer.h"
#include "../Sockets/connectionObserver.h"
#include <stdbool.h>
#include <pthread.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>

#ifndef SERVER_COMMANDINTERACTION_H
#define SERVER_COMMANDINTERACTION_H

struct Brick;
struct gameDetails;

extern int startConsole();
const char* convertJSON(struct gameDetails newGame);

#endif
