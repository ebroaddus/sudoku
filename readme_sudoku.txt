/**********************************************************************                                              
 *  Final Project (Sudoku)
 **********************************************************************/

Name: Ethan Broaddus
PennKey: ebroad
Hours to complete assignment: 24

/**********************************************************************
 *  Please list all help, collaboration, and outside resources
 *  you used here. 
 *
 *  If you did not get any help in outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/

I did not receive any help outside of TA office hours. I did not collaborate with
anyone, and I did not use any resources beyond the standard course materials.

/**********************************************************************
 *  How to run the Sudoku program:
 **********************************************************************/

In order to run the Sudoku program, you simply need to type the following into the
command line after downloading all of the files:

java Sudoku [filename]

    > where [filename] is a .txt file that represents the initial game board input.

The format of the .txt file is extremely important. The Sudoku game is on a 9x9
grid, where each cell has a value in it from 1-9 (or nothing). The text file must
consist of 9 lines, each with a length of 9 (modeling the 9x9 board). To represent
an empty space, use spaces instead of any of the 1-9 values (or any other value).
A sample text file has been provided in order to give the user an idea of what it
should look like.

/**********************************************************************
 *  The purpose of Board.java:
 **********************************************************************/

As described in the header of the file, the purpose of Board.java is to handle the
main board 9x9 array, including handling cell input during the game and drawing board
elements, and checks for correct file input, input errors during the game
(duplicates), and win condition.

/**********************************************************************
 *  The purpose of Sudoku.java:
 **********************************************************************/

As the main game file, it animates the games and organizes the game flow using the
drawing functions established in Board.java. Takes in the .txt file responsible for
populating the game board. Also uses some of its own PennDraw functions to make
additions to the game board and user interface experience.
