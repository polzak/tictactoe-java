import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String FLOOR = "---------";
        final String WALL = "|";
        final String SPACE = " ";

        boolean player = false;
        boolean xWins = false;
        boolean oWins = false;
        boolean gameNotFinished = true;
        boolean impossible = false;
        int xNum = 0;
        int oNum = 0;
        int emptyNum = 0;
        String result = "";

        Scanner scanner = new Scanner(System.in);
        int row_pos = 0;
        int col_pos = 0;
        int board_pos = 0;


        // Create an empty grid.
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        // Game loop
        while (gameNotFinished) {

            // Reset the numbers for validation.
            xNum = 0;
            oNum = 0;
            emptyNum = 0;

            // Check the difference of occurrence between X and O.
            for (char b: board) {
                if (b == 'X') {
                    xNum++;
                } else if (b == 'O') {
                    oNum++;
                } else {
                    emptyNum++;
                }
            }

            // if there is any empty cell, the game is not finished.
            if (emptyNum < 1) {
                gameNotFinished = false;
            }

            // if the difference is 2 or more, the game is impossible.
            if (Math.abs(xNum - oNum) > 1) {
                impossible = true;
            }

            // Display the board
            String l1 = WALL + SPACE + board[0] + SPACE + board[1] + SPACE + board[2] + SPACE + WALL;
            String l2 = WALL + SPACE + board[3] + SPACE + board[4] + SPACE + board[5] + SPACE + WALL;
            String l3 = WALL + SPACE + board[6] + SPACE + board[7] + SPACE + board[8] + SPACE + WALL;

            System.out.println(FLOOR);
            System.out.println(l1);
            System.out.println(l2);
            System.out.println(l3);
            System.out.println(FLOOR);

            // Examine 3 horizontal lines, 3 vertical lines, and 2 diagonal lines
            // and store them as a String in an array.
            String[] rows = new String[8];

            rows[0] = "" + board[0] + board[1] + board[2];
            rows[1] = "" + board[3] + board[4] + board[5];
            rows[2] = "" + board[6] + board[7] + board[8];

            rows[3] = "" + board[0] + board[3] + board[6];
            rows[4] = "" + board[1] + board[4] + board[7];
            rows[5] = "" + board[2] + board[5] + board[8];

            rows[6] = "" + board[0] + board[4] + board[8];
            rows[7] = "" + board[2] + board[4] + board[6];

            // Check if there is a win for either side.
            for (String row: rows) {
                if (row.equals("XXX")) {
                    xWins = true;
                } else if (row.equals("OOO")) {
                    oWins = true;
                }
            }

            // Evaluate the game
            if (impossible || xWins && oWins) {
                result = "Impossible";
                break;
            } else if (xWins) {
                result = "X wins";
                break;
            } else if (oWins) {
                result = "O wins";
                break;
            } else if (!gameNotFinished) {
                result = "Draw";
                break;
            }

            // Get input for a user's move
            while (true) {
                System.out.print("Enter the coordinates: ");
                String coordinates = scanner.nextLine();

                // if the input is "quit" then
                if (coordinates.equals("quit")) {
                    gameNotFinished = false;
                    result = "The game was interrupted.";
                    break;
                }

                String[] pos = coordinates.split(" ");

                try {
                    row_pos = Integer.parseInt(pos[0]);
                    col_pos = Integer.parseInt(pos[1]);
                } catch (NumberFormatException ne) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                // Check if the move is valid for a grid range
                if (row_pos > 3 || col_pos > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                // The board is from 0 to 8
                // Change the positions into the real position in the board.
                board_pos = (row_pos - 1) * 3 + (col_pos - 1);

                // Check if the board position is already occupied.
                if (board[board_pos] == 'X' || board[board_pos] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    break; // The move is valid.
                }
            } // Get Coordinate input Loop ends

            // Change a player's turn.
            if (player) {
                board[board_pos] = 'O';
            } else {
                board[board_pos] = 'X';
            }
            player = !player;

        } // while (gameNotFinished) Loop ends

        System.out.println(result);
    }
}
