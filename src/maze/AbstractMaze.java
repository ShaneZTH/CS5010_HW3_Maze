package maze;

import java.util.Random;
import java.util.Stack;

public abstract class AbstractMaze implements MazeInterface {
    final private MazeType mazeType;
    final private int row;
    final private int col;
    final int totalWalls;
    int remainWalls;
    final private int goldNumber;
    final private int thiefNumber;
    final private int[] startPoint;
    final private int[] endPoint;
    protected Cell[][] mMaze;
    Random rand;

    // TODO: add check for illegal parameters
    public AbstractMaze(MazeType type, int row, int col, int[] startPoint, int[] endPoint) {
        this.mazeType = type;
        this.row = row;
        this.col = col;
        this.totalWalls = row * (col-1) + (row-1) * col;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.mMaze = new Cell[row][col];
        this.goldNumber = (int) (row * col * 0.2);
        this.thiefNumber = (int) (row * col * 0.1);
        this.rand = new Random();
    }

    @Override
    public void generateGoldCoins() {
        int count = 0;
        while (count < goldNumber) {
            int r = rand.nextInt(row);
            int c = rand.nextInt(col);

            if (!mMaze[r][c].isSpecial() && !isStart(r, c) && !isEnd(r, c)) {
                mMaze[r][c].setGold(true);
                count++;
            }
        }
    }

    @Override
    public void generateThief() {
        int count = 0;
        while (count < thiefNumber) {
            int r = rand.nextInt(row);
            int c = rand.nextInt(col);

            if (!mMaze[r][c].isSpecial() && !isStart(r, c) && !isEnd(r, c)) {
                mMaze[r][c].setThief(true);
                count++;
            }
        }
    }

    @Override
    public void initMaze() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mMaze[i][j] = new Cell();
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Cell n, s, w, e;
                n = (i == 0) ? null : mMaze[i - 1][j];
                s = (i == row - 1) ? null : mMaze[i + 1][j];
                w = (j == 0) ? null : mMaze[i][j - 1];
                e = (j == col - 1) ? null : mMaze[i][j + 1];

                mMaze[i][j].setNeighbors(n, s, w, e);
            }
        }

        // Insert Gold
        generateGoldCoins();

        // Insert Thief
        generateThief();
    }

    @Override
    public void formPerfectMaze(int row, int col) {
            int totalCells = row * col;
            int visited = 1;

            int r = rand.nextInt(row);
            int c = rand.nextInt(col);

            Stack<Cell> stack = new Stack<>();
            Cell curr = mMaze[r][c];

            while (visited < totalCells) {
                Cell next = curr.getNeighborWithAllWalls();

                if (next == null) {
                    curr = stack.pop();
                } else {
                    curr.knockDownWall(next);
                    stack.push(curr);
                    curr = next;
                    visited++;
                }
            }
            System.out.println("A Perfect Maze is formed");

    }

    boolean isStart(int r, int c) {
        return (r == startPoint[0]) && (c == startPoint[1]);
    }

    boolean isEnd(int r, int c) {
        return (r == endPoint[0]) && (c == endPoint[1]);
    }

    // TODO: Add exception handlers
    public Cell getCell(int r, int c) {
        return mMaze[r][c];
    }

    public int getRemainWalls() {
        return this.remainWalls;
    }

    public int[] getStartPoint() {
        return startPoint;
    }

    public int[] getEndPoint() {
        return endPoint;
    }

    public MazeType getMazeType() {
        return this.mazeType;
    }

    /**
     * NOTE: for debugging only
     * Print out the maze with Gold & Thief status and locations
     */
    @Override
    public void printMaze() {
        int R = mMaze.length;
        int C = mMaze[0].length;
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nDEBUG: Printing the maze...\n");
        appendln(sb, (String.format("Maze of size %d x %d\n", R, C)));
        // FIXME: Gold should be having specific value
        appendln(sb, ("'S'= Start | 'E'= End | 'G'= Gold | 'T'= Thief\n"));
        for (int i = 0; i < C; i++) {
            sb.append("+---");
        }
        appendln(sb, "+");

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Cell cell = getCell(i, j);

                // Wall on the left end
                if (cell.isWallWest()) {
                    sb.append("| ");
                } else {
                    sb.append("  ");
                }

                // TODO:
                if (i == getStartPoint()[0] && j == getStartPoint()[1]) {
                    sb.append("S ");
                } else if (i == getEndPoint()[0] && j == getEndPoint()[1]) {
                    sb.append("E ");
                } else if (cell.hasGold()) {
                    sb.append("G ");
                } else if (cell.hasThief()) {
                    sb.append("T ");
                } else {
                    sb.append("  ");
                }

                // Wall on the right end
                if (j == C - 1 && cell.isWallEast()) {
                    sb.append("|");
                    ;
                }
            }
            sb.append("\n");
            for (int j = 0; j < C; j++) {
                Cell cell = getCell(i, j);
                if (cell.isWallSouth()) {
                    sb.append("+---");
                } else {
                    sb.append("+   ");
                }
            }
            sb.append("+\n");
        }

        // Print to stdout
        System.out.println(sb);
    }

    /**
     * @param sb StringBuilder to be used
     * @param s  String that needs to add a new line to
     */
    void appendln(StringBuilder sb, String s) {
        sb.append(String.format("%s\n", s));
    }
}
