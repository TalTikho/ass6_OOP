package TicTacToeP;

/**
 * Represents the board.
 */
public class Board {
    private int boardSize;
    private char[][] board;
    //
    private Board() {
    }
    /**
     * Constructor.
     * @param boardSize represents the board size.
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.setBoard();
    }
    /**
     * Setting the board size.
     * @param boardSize represents the board size
     */
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    /**
     * Getting the board size.
     * @return the board size
     */
    public int getBoardSize() {
        return this.boardSize;
    }
    /**
     * Setting the board.
     */
    private void setBoard() {
        this.board = new char[this.boardSize][this.boardSize];

        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] = ' ';
            }
        }
    }
    /**
     * Printing the board.
     */
    public void print() {
        StringBuilder topBottomBoundary = new StringBuilder();

        topBottomBoundary.append("+---".repeat(Math.max(0, this.boardSize)));
        topBottomBoundary.append("+");

        for (char[] row : this.board) {
            System.out.println(topBottomBoundary);

            for (char cell : row) {
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }
        System.out.println(topBottomBoundary);
        System.out.println();
    }
    /**
     * Placing the move wanted.
     * @param checkMark the symbol we place (X/O)
     * @param movePosition the location of the placement
     */
    public void placeTheMove(char checkMark, int movePosition) {
        int i = (movePosition - 1) / this.board.length;
        int j = (movePosition - 1) % this.board.length;
        this.board[i][j] = checkMark;
    }
    /**
     * Checking for a win.
     * @param player represents the player.
     * @return T/F for a win
     */
    public boolean checkWin(char player) {
        //Checking rows
        for (int i = 0; i < boardSize; i++) {
            boolean rowWin = true;
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != player) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) {
                return true;
            }
        }
        //Checking columns
        for (int j = 0; j < boardSize; j++) {
            boolean colWin = true;
            for (int i = 0; i < boardSize; i++) {
                if (board[i][j] != player) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) {
                return true;
            }
        }
        //Checking main diagonal
        boolean mainDiagonalWin = true;
        for (int i = 0; i < boardSize; i++) {
            if (board[i][i] != player) {
                mainDiagonalWin = false;
                break;
            }
        }
        if (mainDiagonalWin) {
            return true;
        }
        // Checking secondary diagonal
        boolean secondaryDiagonalWin = true;
        for (int i = 0; i < boardSize; i++) {
            if (board[i][boardSize - 1 - i] != player) {
                secondaryDiagonalWin = false;
                break;
            }
        }
        if (secondaryDiagonalWin) {
            return true;
        }
        //No winning condition met
        return false;
    }
    /**
     * Checking if the positions is available.
     * @param position represents the position
     * @return T/F is possible
     */
    public boolean isValidPosition(String position) {
        //Check if the string contains only digits
        if (!position.matches("\\d+")) {
            return false;
        }
        try {
            int pos = Integer.parseInt(position);
            //Check if position is within valid range
            if (pos < 1 || pos > (boardSize * boardSize)) {
                return false;
            }
            //Convert position to array indices
            int row = (pos - 1) / boardSize;
            int col = (pos - 1) % boardSize;
            //Check if position is already occupied
            return board[row][col] == ' ';
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Checking if the board is full.
     * @return T/F if full
     */
    public boolean isFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
