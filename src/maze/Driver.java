package maze;

import java.util.Scanner;

/**
 * Illegal arguments should be handled at frontend (CLI)
 */

// TODO: Implement Quit for all 'ASK'

// TODO: Implement Option for user to see current gold status
public class Driver {
    private static final int GOLD_COIN_VALUE = 10;
    private static AbstractMaze maze;
    private static Player player;
    private static Scanner scan;
    private static StringBuilder sb;
    private int row, col;
    private int start[], end[];

    public Driver() {
        scan = new Scanner(System.in);
        greeting();
        MazeType type = askMazeType();
        row = askRow();
        col = askCol();
        start = askStartPoint();
        end = askEndPoint();

        createMaze(type, row, col, start, end);
        player = new Player(start[0], start[1]);

        // TODO: Remove after all (debugging)
        if (maze != null) {
            maze.printMaze();
        }

        startGame();
    }

    /**
     * The core of the Maze Game
     * Player chooses their next move from given possible directions/moves
     *  and moves towards that direction
     */
    void startGame() {
        boolean isEnd = false;

        while (!isEnd) {
            boolean[] moves = maze.mMaze[player.row][player.col].hasNoWall();
            int[] res = getNextPosition(moves);
            player.updatePosition(res);
            System.out.println("Current Position: " + player.row + " " + player.col);
            if (player.row == end[0] && player.col == end[1]) isEnd = true;
            checkSpecial();
            System.out.println(player.status());
        }

        ending();
    }

    /**
     * Get the next position of the User after interacting
     *
     * @param moves possible moves of current position [North, South, West, East]
     * @return Player's next position[row,col]
     */
    private int[] getNextPosition(boolean[] moves) {
        int r = player.row;
        int c = player.col;

        boolean isWrapped = maze.getMazeType() == MazeType.WrappedRoomMaze;

        // Check for edges
        if (!isWrapped) {
            moves[0] = moves[0] && r > 0;
            moves[1] = moves[1] && r < row - 1;
            moves[2] = moves[2] && c > 0;
            moves[3] = moves[3] & c < col - 1;
        }

        int res = askNextMove(createNextMovesOptions(moves), moves);

        // Add special handlers for Wrapped Maze
        switch (res) {
            case 1: // North
                if (isWrapped && r == 0) return new int[]{row-1, c};
                return new int[]{r-1, c};
            case 2: // South
                if (isWrapped && r == row-1) return new int[]{0, c};
                return new int[]{r+1, c};
            case 3: // West
                if (isWrapped && c == 0) return new int[]{r, col-1};
                return new int[]{r, c-1};
            case 4: // East
                if (isWrapped && c == col-1) return new int[]{r, 0};
                return new int[]{r, c+1};
        }
        return null;
    }

    /**
     * Based on the current available direction/moves,
     *  create an Option String
     *
     * @param moves possible moves of current position
     * @return String to print out for Player
     */
    private String createNextMovesOptions(boolean[] moves) {
        String NA = "Not Available";
        StringBuilder sb = new StringBuilder();
        sb.append("You have the following possible move(s)\n");
        sb.append("[1] (North)\t" + (moves[0] ? "" : NA) + "\n");
        sb.append("[2] (South)\t" + (moves[1] ? "" : NA) + "\n");
        sb.append("[3] (West)\t" + (moves[2] ? "" : NA) + "\n");
        sb.append("[4] (East)\t" + (moves[3] ? "" : NA) + "\n");
        sb.append("[0] Quit\n");
        return sb.toString();
    }

    // NOTE: Debug
    private void printB(boolean[] bool) {
        for (boolean b: bool) {
            System.out.print( (b?"True":"False") + " ");
        }
        System.out.println();
    }

    private void createMaze(MazeType type, int row, int col, int[] startPoint, int[] endPoint) {
        switch (type) {
            case PerfectMaze:
                maze = new PerfectMaze(row, col, startPoint, endPoint);
                break;
            case WrappedRoomMaze:
                maze = new WrappedRoomMaze(row, col, startPoint, endPoint);
                break;
            case UnwrappedRoomMaze:
                maze = new UnwrappedRoomMaze(row, col, startPoint, endPoint);
                break;
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // Ask action methods
    /////////////////////////////////////////////////////////////////////////////
    /**
     * Ask Player where to move next based on available direction(s)
     *
     * @param q Option String for Player
     * @param moves possible moves of current position
     * @return Player's decision of where to move
     */
    private int askNextMove(String q, boolean[] moves) {
        System.out.println(q);
        String resp = scan.nextLine();
        if (resp.length() == 0) {
            return getNumber(q);
        }

        int res = -1;
        do {
            try {
                res = Integer.parseInt(resp);

                // Quit if user type 0
                checkQuit(res);

                if (isValidMove(moves, res)) break;
            } catch (NumberFormatException nfe) {
                System.err.println("Illegal input received.");
            }
            System.err.println("Invalid move received.");
            res = askNextMove(q, moves);
        } while (res == -1);
        return res;
    }

    private int[] askStartPoint() {
        int[] res = new int[2];
        String q1 = "Which row would you like to set the Start Point at?";
        String q2 = "Which column would you like to set the Start Point at?";
        res[0] = getNumber(q1, 0, row - 1);
        res[1] = getNumber(q2, 0, col - 1);
        return res;
    }

    private int[] askEndPoint() {
        int[] res = new int[2];
        String q1 = "Which row would you like to set the End Point at?";
        String q2 = "Which column would you like to set the End Point at?";
        res[0] = getNumber(q1, 0, row - 1);
        res[1] = getNumber(q2, 0, col - 1);
        return res;
    }

    private int askRow() {
        String q = "How many rows would you like to have?";
        return getNumber(q);
    }

    private int askCol() {
        String q = "How many columns would you like to have?";
        return getNumber(q);
    }

    private MazeType askMazeType() {
        sb = new StringBuilder();
        sb.append("Please choose the type of maze you want to play\n");
        sb.append("[0] Quit\n");
        sb.append("[1] Perfect Maze\n");
        sb.append("[2] Wrapped Room Maze\n");
        sb.append("[3] Unwrapped Room Maze\n");
        int res = getOption(sb.toString(), 3);
        switch (res) {
            case 1:
                return MazeType.PerfectMaze;
            case 2:
                return MazeType.WrappedRoomMaze;
            case 3:
                return MazeType.UnwrappedRoomMaze;
            default:
                return null;
        }
    }

    private void greeting() {
        System.out.println("Welcome to the maze!");
    }

    private void ending() {
        System.out.println("Congratulation! You have reached the END.");
    }

    /////////////////////////////////////////////////////////////////////////////
    // Checker methods
    /////////////////////////////////////////////////////////////////////////////
    /**
     * Check if the Player's next move is valid
     * @param moves     Player's possible moves
     * @param res       Player's choice of direction
     * @return
     */
    private boolean isValidMove(boolean[] moves, int res) {
        return res <= 4 && res >= 0 && moves[res - 1];
    }

    /**
     * Check if Player encounters Gold OR Thief
     */
    private void checkSpecial() {
        checkGold();
        checkThief();
    }

    /**
     * If Player meet a Gold,
     *  update Player's status
     *  update Cell's status
     */
    private void checkGold() {
        if (maze.mMaze[player.row][player.col].hasGold()) {
            player.meetGold(GOLD_COIN_VALUE);
            maze.mMaze[player.row][player.col].setGold(false); // Update cell status
        }
    }

    /**
     * If Player meet a Thief,
     *  update Player's status
     *  update Cell's status
     */
    private void checkThief() {
        if (maze.mMaze[player.row][player.col].hasThief()) {
            player.meetThief();
            maze.mMaze[player.row][player.col].setThief(false); // Update cell status
        }
    }

    /**
     * Quit the game if Player type 0
     *
     * @param n User's response
     */
    private void checkQuit(int n) {
        if (n == 0) System.exit(0);
    }

    /////////////////////////////////////////////////////////////////////////////
    // Helper methods for interacting, retrieving, and parsing user's response
    /////////////////////////////////////////////////////////////////////////////
    /**
     * Get a numeric input from Player within a given range (inclusive min & max)
     *
     * @param q             formatted String contains questions and options
     * @param min           number acceptable (inclusive)
     * @param max           number acceptable (inclusive)
     * @return
     */
    int getNumber(String q, int min, int max) {
        System.out.println(q);
        String resp = scan.nextLine();
        if (resp.length() == 0) {
            return getNumber(q);
        }

        int n = -1;
        do {
            try {
                n = Integer.parseInt(resp);
                if (n >= min && n <= max) break;
            } catch (NumberFormatException nfe) {
                System.err.println("Illegal input received.");
            }
            System.err.println("Illegal number received.");
            n = getNumber(q, min, max);
        } while (n == -1);

        return n;
    }

    /**
     * Get a numeric input from Player
     *
     * @param q             formatted String contains questions and options
     * @return              Player's numeric input
     */
    int getNumber(String q) {
        System.out.println(q);
        String resp = scan.nextLine();
        if (resp.length() == 0) {
            return getNumber(q);
        }

        int n = -1;
        do {
            try {
                n = Integer.parseInt(resp);
                if (n > 0) break;
            } catch (NumberFormatException nfe) {
                System.err.println("Illegal input received.");
            }
            System.err.println("Illegal number received.");
            n = getNumber(q);
        } while (n == -1);

        return n;
    }

    /**
     * Get a numeric input of Choice(s) from Player
     *
     * @param q             formatted String contains questions and options
     * @param numOptions    the total number of options available for this question
     * @return              Player's choice
     */
    int getOption(String q, int numOptions) {
        System.out.println(q);
        String resp = scan.nextLine();
        if (resp.length() == 0) {
            return getOption(q, numOptions);
        }

        int n = -1;
        do {
            try {
                n = Integer.parseInt(resp);
                if (n >= 0 && n <= numOptions) break;
            } catch (NumberFormatException nfe) {
                System.err.println("Illegal option received.");
            }
            System.err.println("Illegal option received.");
            n = getOption(q, numOptions);
        } while (n == -1);

        return n;
    }

    public static void main(String[] args) {
        new Driver();
    }
}
