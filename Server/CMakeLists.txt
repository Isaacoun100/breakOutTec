cmake_minimum_required(VERSION 3.22)
project(Server C)

set(CMAKE_C_STANDARD 17)

add_executable(Server main.c Sockets/connectionServer.c Sockets/connectionServer.h Interface/commandInteraction.c Interface/commandInteraction.h Sockets/connectionObserver.c Sockets/connectionObserver.h)
