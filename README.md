# WordSearchSolverV2

This is a redo of the kata for Pillar Technologies. The algorithms and data structures used are almost identical to the ones used 
in V1. This time instead of using static utility methods the more traditional object approach is used. There are a lot fewer tests 
but the coverage is mostly the same. Going through and revising a kata was a very informative process. Looking at old code with the
chance to improve it is a great way to improve craftsmanship.

To build use the command 'gradle build' with command line in the project directory.

To run use the command 'java -jar build\libs\WordSearchSolver.jar [filename]' with command line in the project directory. 

Currently the only valid file is a txt file with the following format:

DOG

D,O,G

C,A,T

A,B,C

The continuation of this project would be to create a full word search website using this project as its basis for a solution
against user input.
