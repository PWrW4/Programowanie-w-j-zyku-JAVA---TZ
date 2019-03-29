cmake_minimum_required(VERSION 3.13)
project(Java_C__)

set(CMAKE_CXX_STANDARD 14)

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/../Java-JNI")

include_directories( "C:/Program Files/Java/jdk1.8.0_152/include" )
include_directories( "C:/Program Files/Java/jdk1.8.0_152/include/win32" )

add_library(Java_C__ SHARED lab5_DotProduct.cpp lab5_DotProduct.h )

set_target_properties(Java_C__ PROPERTIES OUTPUT_NAME "dotProduct")
set_target_properties(Java_C__ PROPERTIES PREFIX "")