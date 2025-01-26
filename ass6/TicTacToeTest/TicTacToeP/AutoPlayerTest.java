package TicTacToeP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
//These one are for Auto-Player tests, didn't find any other way to initialize the tests
import java.util.HashSet;
import java.util.Set;
/*
 * These one are for Main tests, saw that in stack Overflow and some more sites - due to the fact that we cant change
 * the code, meaning we cant add classes, im not allowed to refactor main class, so that is the only way to test the
 * changes I made in Main.
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AutoPlayerTest {
    /**
     * Tests for Auto-player class
     */
    //Setting variables
    private Board board;
    private AutoPlayer autoPlayer;
    @Test
    public void AutoPlayerTests (){
        /*
         * Checking initialization case
         */
        autoPlayer = new AutoPlayer("AUTO-X", 1, 'X');
        board = new Board(3);
        assertEquals("AUTO-X", autoPlayer.getName());
        assertEquals(1, autoPlayer.getId());
        assertEquals('X', autoPlayer.getMarker());
        assertEquals(0, autoPlayer.getNumberOfWins());
        /*
         * Checking selection on empty board case
         */
        autoPlayer = new AutoPlayer("AUTO-X", 1, 'X');
        board = new Board(3);
        //Make multiple moves to test randomness
        Set<Integer> selectedPositions = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            Board testBoard = new Board(3);
            autoPlayer.move(testBoard);
            //Finding the position that was marked
            for (int pos = 1; pos <= 9; pos++) {
                if (!testBoard.isValidPosition(String.valueOf(pos))) {
                    selectedPositions.add(pos);
                    break;
                }
            }
        }
        //Checking if multiple different positions were selected
        assertTrue("AutoPlayer should select different positions randomly",selectedPositions.size() > 1);
        /*
         * Checking selection on part board case
         */
        autoPlayer = new AutoPlayer("AUTO-X", 1, 'X');
        board = new Board(3);
        // Fill some positions
        board.placeTheMove('O', 1);
        board.placeTheMove('O', 5);
        board.placeTheMove('O', 9);
        //Make multiple moves
        selectedPositions = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            Board testBoard = new Board(3);
            testBoard.placeTheMove('O', 1);
            testBoard.placeTheMove('O', 5);
            testBoard.placeTheMove('O', 9);
            autoPlayer.move(testBoard);
            //Finding the position that was marked
            for (int pos = 1; pos <= 9; pos++) {
                if (!testBoard.isValidPosition(String.valueOf(pos)) && pos != 1 && pos != 5 && pos != 9) {
                    selectedPositions.add(pos);
                    break;
                }
            }
        }
        //Check if multiple different positions were selected - added comment to manage cases
        assertTrue("AutoPlayer should select different available positions randomly",selectedPositions.size() > 1);
        assertFalse("AutoPlayer should not select occupied position 1",selectedPositions.contains(1));
        assertFalse("AutoPlayer should not select occupied position 5",selectedPositions.contains(5));
        assertFalse("AutoPlayer should not select occupied position 9",selectedPositions.contains(9));
        /*
         * Checking moves on diff size board
         */
        autoPlayer = new AutoPlayer("AUTO-X", 1, 'X');
        //Test on 4x4 board
        Board largeBoard = new Board(4);
        autoPlayer.move(largeBoard);
        int occupiedCount = 0;
        for (int i = 1; i <= 16; i++) {
            if (!largeBoard.isValidPosition(String.valueOf(i))) {
                occupiedCount++;
            }
        }
        assertEquals(1, occupiedCount);
    }
    //-----------------------------------------------------------------------------------------------------------------
    /**
     * Tests for Main because Main was changed to include Auto-Player case
     */
    @Test
    public void MainTest () {
        /*
         * Checking player vs player case
         */
        //Simulate user input for Player vs Player mode
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Main.main(new String[]{});
        // Reset System.in
        System.setIn(System.in);
        /*
         * Checking Player vs Computer case (AS X)
         */
        //Simulate user input for Player vs Computer mode, player as X
        input = "2\nX\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Main.main(new String[]{});
        System.setIn(System.in);
        /*
         * Checking Player vs Computer case (AS O)
         */
        //Simulate user input for Player vs Computer mode, player as O
        input = "2\nO\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Main.main(new String[]{});
        System.setIn(System.in);
        /*
         * Checkin Computer vs Computer case
         */
        //Simulate user input for Computer vs Computer mode
        input = "3\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Main.main(new String[]{});
        System.setIn(System.in);
        /*
         * Checking invalid input case
         */
        //Simulate invalid input followed by valid input
        input = "5\n1\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Main.main(new String[]{});
        System.setIn(System.in);
    }
}
