package TicTacToeP;
import java.util.Scanner;

/**
 * TicTacToeP.Player class.
 */
public class Player {
    private final int id;
    private final String name;
    private final char marker;
    private final Scanner sc;
    private int numberOfWins;
    /**
     * Constructor.
     * @param name represents the name
     * @param id represents the id
     * @param marker represents the symbol
     */
    public Player(String name, int id, char marker) {
        this.id = id;
        this.name = name;
        this.numberOfWins = 0;
        this.marker = marker;
        sc = new Scanner(System.in);
    }
    /**
     * Getting the id.
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * Getting the name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Gettnig the marker.
     * @return the marker
     */
    public char getMarker() {
        return this.marker;
    }
    /**
     * Getting the scanner.
     * @return the scanner
     */
    public Scanner getSC() {
        return this.sc;
    }
    /**
     * Closing the scanner.
     */
    public void closeSC() {
        this.sc.close();
    }
    /**
     * Getting the number of wins.
     * @return the number of wins
     */
    public int getNumberOfWins() {
        return this.numberOfWins;
    }
    /**
     * Incrementing the number of wins.
     */
    public void incrementNumberOfWins() {
        this.numberOfWins++;
    }
    /**
     * Resetting the number of wins.
     */
    public void resetNumberOfWins() {
        this.numberOfWins = 0;
    }

    /**
     * Making move.
     * @param board represents the board
     */
    public void move(Board board) {
        String movePos;
        //
        while (true) {
            System.out.println("TicTacToeP.Player " + this.name + ", please enter your move. (enter a value from 1 - "
                    + board.getBoardSize() * board.getBoardSize() + ")");
            board.print();
            //
            if (sc.hasNextLine()) {
                movePos = sc.nextLine();
                //
                if (!board.isValidPosition(movePos)) {
                    System.out.println("Invalid move. Please try again.");
                } else {
                    break;
                }
            }
        }
        //
        board.placeTheMove(this.marker, Integer.parseInt(movePos));
    }
}
