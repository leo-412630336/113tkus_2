import java.util.Scanner;

public class TicTacToeBoard {

    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean checkWin() {
        return checkRows() || checkCols() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++)
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2])
                return true;
        return false;
    }

    private boolean checkCols() {
        for (int i = 0; i < 3; i++)
            if (board[0][i] != ' ' &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i])
                return true;
        return false;
    }

    private boolean checkDiagonals() {
        return (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2])
            || (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]);
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    public boolean isGameOver() {
        return checkWin() || isBoardFull();
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeBoard game = new TicTacToeBoard();

        System.out.println("井字遊戲開始！");
        game.printBoard();

        while (!game.isGameOver()) {
            System.out.println("玩家 " + game.getCurrentPlayer() + " 的回合。請輸入列和行 (0-2):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.placeMark(row, col)) {
                game.printBoard();

                if (game.checkWin()) {
                    System.out.println("玩家 " + game.getCurrentPlayer() + " 獲勝！");
                    break;
                } else if (game.isBoardFull()) {
                    System.out.println("平手！");
                    break;
                }

                game.switchPlayer();
            } else {
                System.out.println("位置無效，請重新輸入。");
            }
        }

    }
}