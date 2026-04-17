package TicTacToeP;
import java.util.Scanner;

/**
 * TicTacToeP.Main class.
 */
public class Main {
    /**
     * TicTacToeP.Main.
     * @param args represents the arguments.
     */
    public static void main(String[] args) {
        //before initializing Auto-Player
//        TicTacToe game = new TicTacToe();
//        game.play();
        //
        //Setting variables
        Scanner mainScanner = new Scanner(System.in);
        Player player1;
        Player player2;
        //Output
        //wrote in part because of checkstyle
        System.out.println("Welcome to TicTacToe!");
        System.out.println("Choose game mode:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Computer");
        System.out.println("3. Computer vs Computer");
        //space
        String choice = mainScanner.nextLine();
        switch (choice) {
            case "1":
                //Human vs Human
                player1 = new Player("PLAYER-X", 1, 'X');
                player2 = new Player("PLAYER-O", 2, 'O');
                break;
            case "2":
                //Human vs Computer
                System.out.println("Do you want to play as X or O? (X plays first)");
                String playerSymbol = mainScanner.nextLine().toUpperCase();
                //checking players decision
                if (playerSymbol.equals("X")) {
                    player1 = new Player("PLAYER-X", 1, 'X');
                    player2 = new AutoPlayer("COMPUTER-O", 2, 'O');
                } else {
                    player1 = new AutoPlayer("COMPUTER-X", 1, 'X');
                    player2 = new Player("PLAYER-O", 2, 'O');
                }
                break;
            case "3":
                //Computer vs Computer
                player1 = new AutoPlayer("COMPUTER-X", 1, 'X');
                player2 = new AutoPlayer("COMPUTER-O", 2, 'O');
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Player vs Player");
                player1 = new Player("PLAYER-X", 1, 'X');
                player2 = new Player("PLAYER-O", 2, 'O');
        }
        // Create and start the game
        TicTacToe game = new TicTacToe(player1, player2);
        game.play();
        //I saw we need to close the scanner,or we will get exception
        mainScanner.close();
    }
}
