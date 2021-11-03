package maze;

public class PerfectMaze extends AbstractMaze{
    private final static MazeType mType = MazeType.PerfectMaze;

    public PerfectMaze(int row, int col, int[] startPoint, int[] endPoint) {
        super(mType, row, col, startPoint, endPoint);
        initMaze();
        remainWalls = (totalWalls - (row * col)) + 1;
        formPerfectMaze(row, col);
        printMaze();
    }

//    @Override
//    public void formMaze(int row, int col) {
//        int totalCells = row * col;
//        int visited = 1;
//
//        int r = rand.nextInt(row);
//        int c = rand.nextInt(col);
//
//        Stack<Cell> stack = new Stack<>();
//        Cell curr = mMaze[r][c];
//
//        while (visited < totalCells) {
//            Cell next = curr.getNeighborWithAllWalls();
//
//            if (next == null) {
//                curr = stack.pop();
//            } else {
//                curr.knockDownWall(next);
//                stack.push(curr);
//                curr = next;
//                visited++;
//            }
//        }
//        System.out.println("A Perfect Maze is formed");
//    }

//    /**
//     * Print out the maze to stdout
//     */
//    @Override
//    public void printMaze() {
//        int R = mMaze.length;
//        int C = mMaze[0].length;
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n\nDEBUG: Printing the maze...\n");
//        appendln(sb, ("Maze of size " + R + " x " + C + "\n"));
//        // FIXME: Gold should be having specific value
//        appendln(sb, ("'S'= Start | 'E'= End | 'G'= Gold | 'T'= Thief\n"));
//        for (int i = 0; i < C; i++) {
//            sb.append("+---");
//        }
//        appendln(sb,"+");
//
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                Cell cell = getCell(i, j);
//
//                // Wall on the left end
//                if (cell.isWallWest()) {
//                    sb.append("| ");
//                } else {
//                    sb.append("  ");
//                }
//
//                // TODO:
//                if (i == getStartPoint()[0] && j == getStartPoint()[1]) {
//                    sb.append("S ");
//                } else if (i == getEndPoint()[0] && j == getEndPoint()[1]) {
//                    sb.append("E ");
//                } else if (cell.hasGold()) {
//                    sb.append("G ");
//                } else if (cell.hasThief()) {
//                    sb.append("T ");
//                } else {
//                    sb.append("  ");
//                }
//
//                // Wall on the right end
//                if (j == C-1 && cell.isWallEast()) {
//                    sb.append("|");;
//                }
//            }
//            sb.append("\n");
//            for (int j = 0; j < C; j++) {
//                Cell cell = getCell(i, j);
//                if (cell.isWallSouth()) {
//                    sb.append("+---");
//                } else {
//                    sb.append("+   ");
//                }
//            }
//            sb.append("+\n");
//        }
//
//        // Print out the final maze to stdout
//        System.out.println(sb);
//    }
}
