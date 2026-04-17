package TicTacToeP;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * represents Auto player class.
 */
public class AutoPlayer extends Player {
    private final Random random;

    /**
     * Constructor.
     * @param name represents the name
     * @param id represents the id
     * @param marker represents the symbol
     */
    public AutoPlayer(String name, int id, char marker) {
        super(name, id, marker);
        this.random = new Random();
    }

    @Override
    public void move(Board board) {
        List<Integer> availablePositions = getAvailablePositions(board);
        int movePos = availablePositions.get(random.nextInt(availablePositions.size()));
        // Print the move to maintain identical output to human players
        System.out.println("Player " + getName() + ", please enter your move. (enter a value from 1 - "
                + board.getBoardSize() * board.getBoardSize() + ")");
        board.print();
        System.out.println(movePos); // Print the selected move
        board.placeTheMove(getMarker(), movePos);
    }
    /**
     * Getting available positions.
     * @param board represents the board
     * @return a list of all available positions
     */
    private List<Integer> getAvailablePositions(Board board) {
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= board.getBoardSize() * board.getBoardSize(); i++) {
            if (board.isValidPosition(String.valueOf(i))) {
                available.add(i);
            }
        }
        return available;
    }
    /**
     * Example usage in Main.java.
     */
    public static class Main {
        /**
         * Main for auto player.
         * @param args represents hte argument
         */
        public static void main(String[] args) {
//                // Auto vs Auto game
//                Player auto1 = new AutoPlayer("AUTO-X", 1, 'X');
//                Player auto2 = new AutoPlayer("AUTO-O", 2, 'O');
//                TicTacToe autoGame = new TicTacToe(auto1, auto2);
//                autoGame.play();
//                //Human vs Auto game
//                Player human = new Player("PLAYER-X", 1, 'X');
//                Player auto = new AutoPlayer("AUTO-O", 2, 'O');
//                TicTacToe mixedGame = new TicTacToe(human, auto);
//                mixedGame.play();
        }
    }
}
