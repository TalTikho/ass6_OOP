package TicTacToeP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
//These one are for Auto-Player tests, didn't find any other way to initialize the tests
/*
 * These one are for Main tests, saw that in stack Overflow and some more sites - due to the fact that we cant change
 * the code, meaning we cant add classes, im not allowed to refactor main class, so that is the only way to test the
 * changes I made in Main.
 */

/**
 * Tests class.
 */
public class TicTacToeTest {
    private Board board;
    //Ouput
    //dont know if need to use before each but will try
    /**
     * Tests for checkWin method
     */
    @Test
    public void checkWinner() {
        /*
         * Testing the row case.
         */
        board = new Board(3);//Create a 3x3 board for testing
        board.placeTheMove('X', 1);// Place 'X' at position 1 (row 1, column 1)
        board.placeTheMove('X', 2);// Place 'X' at position 2 (row 1, column 2)
        board.placeTheMove('X', 3);// Place 'X' at position 3 (row 1, column 3)
        assertTrue(board.checkWin('X'));// 'X' should win in the first row
        /*
         * Testing the column case.
         */
        board = new Board(3);//Create a 3x3 board for testing
        board.placeTheMove('O', 1); // Place 'O' at position 1 (row 1, column 1)
        board.placeTheMove('O', 4); // Place 'O' at position 4 (row 2, column 1)
        board.placeTheMove('O', 7); // Place 'O' at position 7 (row 3, column 1)
        assertTrue(board.checkWin('O')); // 'O' should win in the first column
        /*
         * Testing the main diagonal case.
         */
        board = new Board(3);//Create a 3x3 board for testing
        board.placeTheMove('X', 1); // Place 'X' at position 1 (top-left)
        board.placeTheMove('X', 5); // Place 'X' at position 5 (center)
        board.placeTheMove('X', 9); // Place 'X' at position 9 (bottom-right)
        assertTrue(board.checkWin('X')); // 'X' should win in the main diagonal
        /*
         * Testing the secondary diagonal case.
         */
        board = new Board(3);//Create a 3x3 board for testing
        board.placeTheMove('O', 3); // Place 'O' at position 3 (top-right)
        board.placeTheMove('O', 5); // Place 'O' at position 5 (center)
        board.placeTheMove('O', 7); // Place 'O' at position 7 (bottom-left)
        assertTrue(board.checkWin('O')); // 'O' should win in the secondary diagonal
        /*
         * Testing the case where there is no win.
         */
        board = new Board(3);//Create a 3x3 board for testing
        board.placeTheMove('X', 1); // Place 'X' at position 1
        board.placeTheMove('O', 2); // Place 'O' at position 2
        board.placeTheMove('X', 3); // Place 'X' at position 3
        board.placeTheMove('X', 4); // Place 'O' at position 4
        board.placeTheMove('O', 5); // Place 'X' at position 5
        board.placeTheMove('O', 6); // Place 'O' at position 6
        board.placeTheMove('O', 7); // Place 'X' at position 7
        board.placeTheMove('X', 8); // Place 'O' at position 8
        board.placeTheMove('X', 9); // Place 'X' at position 9
        assertFalse(board.checkWin('X')); // No winner
        assertFalse(board.checkWin('O')); // No winner
    }
    //-----------------------------------------------------------------------------------------------------------------
    /**
     * Tests for handleWinner method
     */
    //Setting variables
    private TicTacToe game;
    private Player player1;
    private Player player2;
    @Test
    public void handleWinnerTest () {
        //Output
        //dont know if need to use before each but will try
        //Initialize players and game
        player1 = new Player("Alice", 1, 'X');
        player2 = new Player("Bob", 2, 'O');
        game = new TicTacToe(player1, player2);
        /*
         * Checking increment case
         */
        //Player 1 wins
        game.handleWinner(player1);
        assertEquals(1, player1.getNumberOfWins());
        assertEquals(0, player2.getNumberOfWins());
        //Player 2 wins
        game.handleWinner(player2);
        assertEquals(1, player1.getNumberOfWins());
        assertEquals(1, player2.getNumberOfWins());
        //Player 1 won again
        game.handleWinner(player1);
        assertEquals(2, player1.getNumberOfWins());
        assertEquals(1, player2.getNumberOfWins());
        /*
         * Checking winner output
         */
        //Simulate capturing console output for validation (checking for me idk if necessary)
        game.handleWinner(player1); // Should output Alice's win
        game.handleWinner(player2); // Should output Bob's win
    }
    //-----------------------------------------------------------------------------------------------------------------
    /**
     * Tests for isValidPosition method
     */
    @Test
    public void isValidPositionTest () {
        /*
         * Checking valid positions case
         */
        board = new Board(3);//Create a 3x3 board for testing
        assertTrue(board.isValidPosition("5"));//Middle position
        assertTrue(board.isValidPosition("1"));//First position
        assertTrue(board.isValidPosition("9"));//Last position
        /*
         * Checking invalid position case
         */
        board = new Board(3);//Create a 3x3 board for testing
        assertFalse(board.isValidPosition("0"));//Too low
        assertFalse(board.isValidPosition("10"));//Too high
        assertFalse(board.isValidPosition("-1"));//Negative
        /*
         * Checking non-numeric invalid position case
         */
        board = new Board(3);//Create a 3x3 board for testing
        assertFalse(board.isValidPosition("abc"));//Letters
        assertFalse(board.isValidPosition("5a"));//Mixed
        assertFalse(board.isValidPosition(" "));//Space
        assertFalse(board.isValidPosition(""));//Empty
        /*
         * Checking already occupied positions case
         */
        board = new Board(3);//Create a 3x3 board for testing
        board.placeTheMove('X', 5);//Place a move in the middle
        assertFalse(board.isValidPosition("5"));//Should be invalid as position is occupied
        assertTrue(board.isValidPosition("1"));//Should still be valid as position is free
        /*
         * Checking different board sizes case
         */
        Board largerBoard = new Board(4);//4x4 board
        assertTrue(largerBoard.isValidPosition("16"));//Valid for 4x4
        assertFalse(largerBoard.isValidPosition("17"));//Invalid for 4x4
        Board smallerBoard = new Board(2);//2x2 board
        assertTrue(smallerBoard.isValidPosition("4"));//Valid for 2x2
        assertFalse(smallerBoard.isValidPosition("5"));//Invalid for 2x2
    }
//    //-----------------------------------------------------------------------------------------------------------------
    /**
     * Tests for isFull method
     */
    @Test
    public void isFullTest () {
        /*
         * Checking empty board case
         */
        board = new Board(3); //Create a 3x3 board for testing
        assertFalse(board.isFull());
        /*
         * Checking part board full case
         */
        board = new Board(3); //Create a 3x3 board for testing
        board.placeTheMove('X', 1);
        board.placeTheMove('O', 5);
        board.placeTheMove('X', 9);
        assertFalse(board.isFull());
        /*
         * Checking full board case
         */
        board = new Board(3); //Create a 3x3 board for testing
        //Fill all positions
        for (int i = 1; i <= 9; i++) {
            board.placeTheMove((i % 2 == 0) ? 'O' : 'X', i);
        }
        //
        assertTrue(board.isFull());
        /*
         * Checking different size board case
         */
        Board smallBoard = new Board(2);
        assertFalse(smallBoard.isFull());//Empty 2x2 board
        // Fill the 2x2 board
        for (int i = 1; i <= 4; i++) {
            smallBoard.placeTheMove('X', i);
        }
        //
        assertTrue(smallBoard.isFull());
        /*
         * Checking board after some moves case
         */
        board = new Board(3); //Create a 3x3 board for testing
        assertFalse(board.isFull());//Initially empty
        // Fill partially
        board.placeTheMove('X', 1);
        board.placeTheMove('O', 2);
        board.placeTheMove('X', 3);
        assertFalse(board.isFull());
        // Fill rest of the board
        board.placeTheMove('O', 4);
        board.placeTheMove('X', 5);
        board.placeTheMove('O', 6);
        board.placeTheMove('X', 7);
        board.placeTheMove('O', 8);
        board.placeTheMove('X', 9);
        //asserting
        assertTrue(board.isFull());
    }
    //-----------------------------------------------------------------------------------------------------------------
    /**
     * Tests for verifyBoardSize method
     */
    @Test
    public void verifyBoardSizeTest () {
        /*
         * Checking upper&lower bounds and middle cases
         */
        game = new TicTacToe();
        assertTrue(game.verifyBoardSize("3"));//Minimum size
        assertTrue(game.verifyBoardSize("7"));//Middle size
        assertTrue(game.verifyBoardSize("10"));//Maximum size
        /*
         * Checking the invalid size case (low)
         */
        game = new TicTacToe();
        assertFalse(game.verifyBoardSize("0"));
        assertFalse(game.verifyBoardSize("1"));
        assertFalse(game.verifyBoardSize("2"));
        assertFalse(game.verifyBoardSize("-5"));
        /*
         * Checking the invalid size case (large)
         */
        game = new TicTacToe();
        assertFalse(game.verifyBoardSize("11"));
        assertFalse(game.verifyBoardSize("15"));
        assertFalse(game.verifyBoardSize("100"));
        /*
         * Checking non-numeric size case
         */
        game = new TicTacToe();
        assertFalse(game.verifyBoardSize("abc"));//Letters
        assertFalse(game.verifyBoardSize("5.5"));//Decimal
        assertFalse(game.verifyBoardSize(""));//Empty string
        assertFalse(game.verifyBoardSize(" "));//Space
        assertFalse(game.verifyBoardSize("3a"));//Mixed
        /*
         * Checking Sup&Infy type size case
         */
        game = new TicTacToe();
        assertFalse(game.verifyBoardSize("2.99"));//Just below minimum
        assertTrue(game.verifyBoardSize("3"));//Minimum
        assertTrue(game.verifyBoardSize("10"));//Maximum
        assertFalse(game.verifyBoardSize("10.1"));//Just above maximum
        /*
         * Checking null size case
         */
        game = new TicTacToe();
        assertFalse(game.verifyBoardSize(null));//Null input
    }
    //-----------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------
}
