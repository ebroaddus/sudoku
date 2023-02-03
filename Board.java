/**
* Name: Ethan Broaddus
* Pennkey: ebroad
*
* Description: Handles the main board 9x9 array, including handling cell input during
*              the game and drawing board elements, and checks for correct file
*              input, input errors during the game (duplicates), and win condition.
**/

// import correct exception library
import java.util.NoSuchElementException;

// Board class
public class Board {
    
    // sole instance variable
    private char[][] board;
    private boolean populate;

    // Board constructor
    public Board(String filename) {
        
        // initialize populate instance variable
        populate = true;

        // populate board by reading in the input file
        In inStream = new In(filename);
        board = new char[9][9];
        char holder = 32;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9 + 1; j++) {
                
                // try statement for reading in values
                try {
                    if (!(i == 8 && j == 9)) {
                        holder = inStream.readChar();
                    }
                    if (j < 9) {
                        board[i][j] = holder;
                    }
                }

                // if an error is caught, populate will be false and therefore the
                // the game will not start (see checkInput() function)
                catch (NoSuchElementException e) {
                    populate = false;
                }
            }
        }
    }
    
    /**
    * checks if the player has made any errors or won the game
    * @return boolean value representing if the game has been won or not
    */
    public boolean checkBoard() {

        // check row and column
        for (int x = 0; x < 9; x++) {
            for (int i = 0; i < 9; i++) {
                for (int j = i + 1; j < 9; j++) {
                    
                    // check row duplicates
                    if (board[x][i] == board[x][j] && board[x][i] < 58 &&
                        board[x][i] > 48 && board[x][j] < 58 && board[x][j] > 48) {
                        
                        // if there are duplicates, highlight them in red and make
                        // the row outlined in blue.
                        PennDraw.setPenColor(PennDraw.RED);
                        PennDraw.filledRectangle((60.5 + i * 70) / 680.0, (619.5 -
                            (x * 70)) / 680.0, 34 / 680.0, 34.0 / 680.0);
                        PennDraw.filledRectangle((60.5 + j * 70) / 680.0, (619.5 -
                            (x * 70)) / 680.0, 34 / 680.0, 34 / 680.0);
                        PennDraw.setPenRadius(0.006);
                        PennDraw.setPenColor(PennDraw.BLUE);
                        PennDraw.rectangle(0.5, (619.5 - x * 70) / 680.0, 313 /
                            680.0, 33 / 680.0);
                        return false;
                    }

                    // check column duplicates
                    if (board[i][x] == board[j][x] && board[i][x] < 58 &&
                        board[i][x] > 48 && board[j][x] < 58 && board[j][x] > 48) {
                        
                        // if there are duplicates, highlight them in red and make
                        // the column outlined in blue.
                        PennDraw.setPenColor(PennDraw.RED);
                        PennDraw.filledRectangle((60.5 + (x * 70)) / 680.0, (619.5 -
                            (i * 70)) / 680.0, 34 / 680.0, 34 / 680.0);
                        PennDraw.filledRectangle((60.5 + (x * 70)) / 680.0, (619.5 -
                            (j * 70)) / 680.0, 34 / 680.0, 34 / 680.0);
                        PennDraw.setPenRadius(0.006);
                        PennDraw.setPenColor(PennDraw.BLUE);
                        PennDraw.rectangle((60 + x * 70) / 680.0, 0.5, 33 / 680.0,
                            313 / 680.0);
                        return false;
                    }
                }
            }
        }

        // check box
        char[] boxArr = new char[9];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int c = 0; // counter
                for (int i = 3 * x; i < 3 * (x + 1); i++) {
                    for (int j = 3 * y; j < 3 * (y + 1); j++) {
                        boxArr[c] = board[i][j];
                        c++;
                    }
                }

                // check duplicates in box
                for (int i = 0; i < 9; i++) {
                    for (int j = i + 1; j < 9; j++) {
                        if (boxArr[i] == boxArr[j] && boxArr[i] < 58 && boxArr[i] >
                            48 && boxArr[j] < 58 && boxArr[j] > 48) {
                            
                            // if there are duplicates, highlight them in red and
                            // make the box outlined in blue.
                            PennDraw.setPenColor(PennDraw.RED);
                            PennDraw.filledRectangle((60.5 + ((y * 3 + i % 3) *
                                70)) / 680.0, (619.5 - ((x * 3 +
                                ((int) Math.floor(i / 3))) * 70)) / 680.0, 34 /
                                680.0, 34 / 680.0);
                            PennDraw.filledRectangle((60.5 + ((y * 3 + j % 3) *
                                70)) / 680.0, (619.5 - ((x * 3 +
                                ((int) Math.floor(j / 3))) * 70)) / 680.0, 34 /
                                680.0, 34 / 680.0);
                            PennDraw.setPenRadius(0.006);
                            PennDraw.setPenColor(PennDraw.BLUE);
                            PennDraw.rectangle((130 + (y * 210)) / 680.0, (550 - (x *
                                210)) / 680.0, 103 / 680.0, 103 / 680.0);
                            return false;
                        }
                    }
                }
            }
        }

        // check win
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                // if any of the cells are empty, the game is not yet won
                if (board[i][j] == 32) {
                    return false;
                }
            }
        }

        // in the case that there are no errors and every cell is filled, the game is
        // won.
        return true;
    }

    /**
    * checks if the input file is a valid starting board for the game
    * @return boolean value representing if the input is invalid or not
    */
    public boolean checkInput() {
        
        // check that all cells have a valid value in them
        if (populate == false) {
            return false;
        }

        // check for row and column duplicates
        for (int x = 0; x < 9; x++) {
            for (int i = 0; i < 9; i++) {
                for (int j = i + 1; j < 9; j++) {
                    // check row duplicates
                    if (board[x][i] == board[x][j] && board[x][i] < 58 &&
                        board[x][i] > 48 && board[x][j] < 58 && board[x][j] > 48) {
                        return false;
                    }
                    // check column duplicates
                    if (board[i][x] == board[j][x] && board[i][x] < 58 &&
                        board[i][x] > 48 && board[j][x] < 58 && board[j][x] > 48) {
                        return false;
                    }
                }
            }
        }

        // check for box duplicates
        char[] boxArr = new char[9];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int c = 0; // counter
                for (int i = 3 * x; i < 3 * (x + 1); i++) {
                    for (int j = 3 * y; j < 3 * (y + 1); j++) {
                        boxArr[c] = board[i][j];
                        c++;
                    }
                }

                // check duplicates in box
                for (int i = 0; i < 9; i++) {
                    for (int j = i + 1; j < 9; j++) {
                        if (boxArr[i] == boxArr[j] && boxArr[i] < 58 && boxArr[i] >
                            48 && boxArr[j] < 58 && boxArr[j] > 48) {
                            return false;
                        }
                    }
                }
            }
        }
            
        return true;
    }

    /**
    * draws the values from the board array onto the canvas (visualizes the board)
    */
    public void drawBoard() {
        
        // draw gridlines
        PennDraw.setPenColor(PennDraw.BLACK);
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                PennDraw.setPenRadius(0.005);
            }
            else {
                PennDraw.setPenRadius();
            }
            PennDraw.line((i * 70 + 25) / 680.0, 25 / 680.0, (i * 70 + 25) /
                680.0, 1 - 25 / 680.0);
            PennDraw.line(25 / 680.0, (i * 70 + 25) / 680.0, 1 - 25 / 680.0, (i *
                70 + 25) / 680.0);
        }

        // draw board values on the canvas
        PennDraw.setPenRadius();
        PennDraw.setFontSize(50);
        PennDraw.setFontBold();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                PennDraw.text((60 + (j * 70)) / 680.0, (610 - (i * 70)) / 680.0, "" +
                    board[i][j]);
            }
        }
    }

    /**
    * highlights the desired input cell if clicked and allows the user to input
    * a desired value in the game
    * @param row is an int representing the row that has been clicked on
    * @param col is an int representing the column that has been clicked on
    */
    public void highlightCell(int row, int col) {

        // draw yellow rectangle and input value on the clicked cell
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledRectangle((60.5 + (col * 70)) / 680.0, (619.5 - (row * 70)) /
            680.0, 34 / 680.0, 34 / 680.0);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text((60.0 + (col * 70.0)) / 680.0, (610.0 - (row * 70.0)) / 680.0,
            "" + board[row][col]);
    }

    /**
    * allows the user to access the current board
    * @return char array representing the current game board
    */
    public char[][] getBoard() {
        return this.board;
    }

    /**
    * allows the user to set the current board (updates the board)
    * @param board is a char[][] (array)
    */
    public void setBoard(char[][] board) {
        this.board = board;
    }
}