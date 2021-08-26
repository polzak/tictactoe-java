import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String FLOOR = "---------";
        final String WALL = "|";
        final String SPACE = " ";

        boolean xWins = false;
        boolean oWins = false;
        boolean gameNotFinished = false;
        boolean impossible = false;
        int xNum = 0;
        int oNum = 0;
        String result = "";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String input = scanner.next();

        // if there is any empty cell, the game is not finished.
        if (input.contains("_") || input.contains(" ")) {
            gameNotFinished = true;
        }

        // Convert the input String into a char array to take it easier for me.
        char[] board = input.toCharArray();

        // Check the difference of occurrence between X and O.
        for (char b: board) {
            if (b == 'X') {
                xNum++;
            } else if (b == 'O') {
                oNum++;
            }
        }

        // if the difference is 2 or more, the game is impossible.
        if (Math.abs(xNum - oNum) > 1) {
            impossible = true;
        }

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
        } else if (xWins) {
            result = "X wins";
        } else if (oWins) {
            result = "O wins";
        } else if (gameNotFinished) {
            result = "Game not finished";
        } else {
            result = "Draw";
        }

        // Display the board
        char[] r1 = input.substring(0, 3).toCharArray();
        char[] r2 = input.substring(3, 6).toCharArray();
        char[] r3 = input.substring(6).toCharArray();

        String l1 = WALL + SPACE + r1[0] + SPACE + r1[1] + SPACE + r1[2] + SPACE + WALL;
        String l2 = WALL + SPACE + r2[0] + SPACE + r2[1] + SPACE + r2[2] + SPACE + WALL;
        String l3 = WALL + SPACE + r3[0] + SPACE + r3[1] + SPACE + r3[2] + SPACE + WALL;

        System.out.println(FLOOR);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(FLOOR);
        System.out.println(result);
    }
}
