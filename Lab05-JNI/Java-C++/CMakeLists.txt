cmake_minimum_required(VERSION 3.13)
project(Java_C__)

set(CMAKE_CXX_STANDARD 14)

include_directories( "C:/Program Files/Java/jdk1.8.0_152/include" )
include_directories( "C:/Program Files/Java/jdk1.8.0_152/include/win32" )


add_library(Java_C__ SHARED HelloJNI.cpp HelloJNI.h)