/**
* Name: Ethan Broaddus
* Pennkey: ebroad
*
* Execution: java Sudoku [filename]
*
* Description: As the main game file, it animates the games and organizes the game
*              flow using the drawing functions established in Board.java. Takes in
*              the .txt file responsible for populating the game board. Also uses
*              some of its own PennDraw functions to make additions to the game board
*              and user interface experience.
**/

// Sudoku class declaration
public class Sudoku {

    /**
    * main function of the class, handles game flow and user interface
    * @param args inputs from command line
    */
    public static void main(String[] args) {
        
        // this value will become false if the game is to end
        boolean gameState = true;

        // read in the values from the input text file
        String filename = args[0];
        Board board = new Board(filename);

        // set canvas size
        PennDraw.setCanvasSize(680, 680);
        
        // if the input text file produces an invalid board configuration, end the
        // game and tell user to try again.
        if (board.checkInput() == false) {
            gameState = false;
            PennDraw.clear(PennDraw.BLACK);
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.setFontSize(40);
            PennDraw.text(0.5, 0.6, "Invalid Board");
            PennDraw.setFontSize(30);
            PennDraw.text(0.5, 0.4, "Please check text file input and try again!");
        }

        // enable animation
        PennDraw.enableAnimation(30);

        // boolean dictates if the menu is open or not
        boolean menu = true;
        
        // primary game loop --> will exit once gameState becomes false
        while (gameState) {
            
            // menu trigger --> will open if space is pressed
            if (PennDraw.hasNextKeyTyped()) {
                char input = PennDraw.nextKeyTyped();
                if (input == 32) {
                    menu = true;
                }
            }

            // loop that dictates the menu screen --> will exit once space is pressed
            while (menu) {
                board.drawBoard();
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.5, 0.5, 0.4, 0.4);
                PennDraw.setFontSize(30);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(0.5, 0.7, "Welcome to Sudoku!");
                PennDraw.setFontSize(15);
                PennDraw.text(0.5, 0.6, "To start the game or access this menu");
                PennDraw.text(0.5, 0.57, "while in the game press Space");
                PennDraw.text(0.5, 0.5, 
                    "To input numbers onto the board, simply click");
                PennDraw.text(0.5, 0.47, 
                    "on the desired cell and it will highlight yellow,");
                PennDraw.text(0.5, 0.44, "meaning you can type a number from 1-9");
                PennDraw.text(0.5, 0.37, 
                    "To remove a number, click on the cell and press Space");
                PennDraw.text(0.5, 0.30, 
                    "Once all of the tiles have been filled in without");
                PennDraw.text(0.5, 0.25, 
                    "repeating numbers in rows, columns, or boxes, you win!!");
                PennDraw.text(0.5, 0.18, 
                    "Incorrect rows, columns, or boxes will outline in blue!");
                if (PennDraw.hasNextKeyTyped()) {
                    char input = PennDraw.nextKeyTyped();
                    if (input == 32) {
                        menu = false;
                        PennDraw.clear();
                    }
                }

                PennDraw.advance();
            }

            // check for cell click and allow user to input desired value on board
            if (PennDraw.mousePressed()) {
                
                // boolean used to stay in the loop until the input is provided
                boolean userInput = true;
                
                // primary loop for user input handling
                while (userInput) {
                    
                    // get the mouse position and turn values into row/col values
                    double mPosX = (PennDraw.mouseX() * 680 - 25) / 70.0;
                    double mPosY = (680 - PennDraw.mouseY() * 680 - 25) / 70.0;
                    
                    if (mPosX <= 9.0 && mPosX >= 0.0 && mPosY <= 9.0 && mPosY >= 0.0)
                        {
                        // convert to int for indexing
                        int colClicked = (int) mPosX;
                        int rowClicked = (int) mPosY;
                        
                        // keep user in the loop until an input is registered
                        boolean ui = true;
                        while (ui) {
                            
                            // highlight the current cell (also provides input to
                            // char array)
                            board.highlightCell(rowClicked, colClicked);

                            // retreive the board array, change it, then reset it
                            char[][] currBoard = board.getBoard();
                            if (PennDraw.hasNextKeyTyped()) {
                                char input = PennDraw.nextKeyTyped();
                                if ((input > 48 && input < 58) || input == 32) {
                                    currBoard[rowClicked][colClicked] = input;
                                    board.setBoard(currBoard);
                                }
                                ui = false;
                                userInput = false;
                            }

                            // advance the animation
                            PennDraw.advance();
                        }
                    }

                    // if there is no click, dont enter the loop
                    else {
                        userInput = false;
                    }
                }
            }

            // redraw the board as it was and check for errors or win condition
            PennDraw.clear();
            boolean result = board.checkBoard();
            board.drawBoard();

            // enter this loop if the game is won
            while (result) {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.5, 0.5, 0.4, 0.4);
                PennDraw.setFontSize(30);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(0.5, 0.7, "You have won Sudoku!");
                PennDraw.text(0.5, 0.6, "Congrats!");
                PennDraw.setFontSize(15);
                PennDraw.text(0.5, 0.5, "Press Space to exit the game");
                if (PennDraw.hasNextKeyTyped()) {
                    char input = PennDraw.nextKeyTyped();
                    if (input == 32) {
                        gameState = false;
                        result = false;
                        PennDraw.clear(PennDraw.BLACK);
                    }
                }

                // advance the animation
                PennDraw.advance();
            }
                
            // advance the animation
            PennDraw.advance();
        }

        // disable animation
        PennDraw.disableAnimation();
    }
}