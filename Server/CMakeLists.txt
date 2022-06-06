cmake_minimum_required(VERSION 3.22)
project(Server C)

set(CMAKE_C_STANDARD 17)

add_executable(Server main.c Sockets/connectionServer.c Sockets/connectionServer.h JSON/format.c JSON/format.h Interface/commandInteraction.c Interface/commandInteraction.h)
