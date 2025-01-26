package TicTacToeP;
import java.util.Scanner;
//TalTikhonov
//215275512
//ass6
/**
 * TicTacToeP.TicTacToe.
 */
public final class TicTacToe {
    private static Scanner sc;
    private Player player1 = new Player("PLAYER-X", 1, 'X');
    private Player player2 = new Player("PLAYER-O", 2, 'O');
    private Board board;
    /**
     * Constructor.
     */
    public TicTacToe() {
        sc = new Scanner(System.in);
    }
    /**
     * Constructor.
     * @param player1 represents the first player
     * @param player2 represents the second player
     */
    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        sc = new Scanner(System.in);
    }
    /**
     * Play.
     */
    public void play() {
        Player currentPlayer = this.player1;
        if (!this.playAgain()) {
            this.gameOver();
            return;
        }
        //
        while (true) {
            currentPlayer.move(this.board);
            if (this.board.checkWin(currentPlayer.getMarker())) {
                this.handleWinner(currentPlayer);
                if (!this.playAgain()) {
                    this.gameOver();
                    return;
                }
            } else if (this.board.isFull()) {
                System.out.println("The board is full. It's a tie!");
                if (!this.playAgain()) {
                    this.gameOver();
                    return;
                } else {
                    continue;
                }
            }
            //
            currentPlayer = currentPlayer == this.player1 ? this.player2 : this.player1;
        }
    }
    /**
     * Game over.
     */
    private void gameOver() {
        this.printResults();
        sc.close();
        this.player1.closeSC();
        this.player2.closeSC();
    }
    /**
     * Handling the winner.
     * @param winner represents the winner
     */
    void handleWinner(Player winner) {
        winner.incrementNumberOfWins(); //Increment the winner's score
        //Checking to see the tests: will be in a comment later
        System.out.println("Congratulations, " + winner.getName() + "! You won this game.");
        System.out.println("Current Scores:");
        System.out.println(this.player1.getName() + ": " + this.player1.getNumberOfWins() + " win(s)");
        System.out.println(this.player2.getName() + ": " + this.player2.getNumberOfWins() + " win(s)");
    }
    /**
     * Printing the welcome text.
     */
    private void welcome() {
        System.out.println("Hit \"y/Y\" to start a new game. Or hit any other key to exit.");
    }
    /**
     * Getting the board size.
     * @return the board size
     */
    private int getBoardSize() {
        while (true) {
            System.out.print("Please enter your preferred SIZE of the board");
            System.out.println(" (from 3 to 10. 3 -> 3x3; 4 -> 4x4; 10 -> 10x10, etc): ");
            //
            if (sc.hasNextLine()) {
                String userInput = sc.nextLine();
                if (this.verifyBoardSize(userInput)) {
                    return Integer.parseInt(userInput);
                }
            }
        }
    }
    /**
     * Verifying the board size.
     * @param boardSize represents the board size.
     * @return T/F idf can build board using the entered size
     */
    boolean verifyBoardSize(String boardSize) {
        try {
            int size = Integer.parseInt(boardSize);
            return size >= 3 && size <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Playing again.
     * @return T/F for the user decision
     */
    private boolean playAgain() {
        this.welcome();
        sc = new Scanner((System.in));
        String userDecision = sc.nextLine();
        //
        if (userDecision.equalsIgnoreCase("Y")) {
            int boardSize = this.getBoardSize();
            this.board = new Board(boardSize);
            return true;
        }
        //
        return false;
    }
    /**
     * Printing the result.
     */
    public void printResults() {
        System.out.println("TicTacToeP.Player " + this.player1.getName() + " has won: "
                + this.player1.getNumberOfWins() + " time(s).");
        System.out.println("TicTacToeP.Player " + this.player2.getName() + " has won: "
                + this.player2.getNumberOfWins() + " time(s).");
        //
        if (this.player1.getNumberOfWins() == this.player2.getNumberOfWins()) {
            System.out.println("Its a tie!");
        } else {
            String winner = this.player1.getNumberOfWins() > this.player2.getNumberOfWins()
                    ? this.player1.getName() : this.player2.getName();
            System.out.println("The final winner is: " + winner + "!!!");
        }
        //
        System.out.println();
    }
}
