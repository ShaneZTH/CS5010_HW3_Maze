package maze;

public abstract class NonPerfectMaze extends AbstractMaze {

    public NonPerfectMaze(MazeType type, int row, int col, int[] startPoint, int[] endPoint) {
        super(type, row, col, startPoint, endPoint);
    }

    /**
     * @param row Number of rows
     * @param col Number of columns
     */
    void removeWalls(int row, int col) {
        int n = totalWalls - row * col + 1;

        boolean containsEdges = this.getMazeType() == MazeType.WrappedRoomMaze;
        int r, c;

        while (n >= remainWalls) {
            r = rand.nextInt(row);
            c = rand.nextInt(col);

            // No more wall could be removed
            if (!mMaze[r][c].hasAWall()) continue;

            // Invalid row/col
            if (!containsEdges && (r == 0 || r == row - 1 || c == 0 || c == col - 1))
                continue;

            // Randomly remove a wall
            if (randomlyRemoveWall(mMaze[r][c], r, c)) n--;
        }
    }

    /**
     * Randomly remove a remaining wall of this cell
     *
     * @param cell to have a wall removed
     * @param r    the row number of this cell
     * @param c    the column number of this cell
     * @return true
     */
    boolean randomlyRemoveWall(Cell cell, int r, int c) throws IllegalArgumentException {
        if (r < 0 || c < 0 || r >= row || c >= col)
            throw new IllegalArgumentException("Invalid row/column");

        int n = rand.nextInt(4) + 1;
        boolean hasRemoved = false;
        while (!hasRemoved) {
//            System.out.printf("(%d,%d): n=%d\n", r, c, n);
            switch (n) {
                case 1: // North
                    if (cell.isWallNorth()) {
                        if (cell.getNeiNorth() != null) { // Not on the edge
                            cell.knockDownWall(cell.getNeiNorth());
                        } else {
                            cell.setWallNorth(false);
                        }
                        hasRemoved = true;
                    }
                    break;
                case 2: // South
                    if (cell.isWallSouth()) {
                        if (cell.getNeiSouth() != null) { // Not on the edge
                            cell.knockDownWall(cell.getNeiSouth());
                        } else {
                            cell.setWallSouth(false);
                        }
                        hasRemoved = true;
                    }
                    break;
                case 3: // West
                    if (cell.isWallWest()) {
                        if (cell.getNeiWest() != null) { // Not on the edge
                            cell.knockDownWall(cell.getNeiWest());
                        } else {
                            cell.setWallWest(false);
                        }
                        hasRemoved = true;
                    }
                    break;
                case 4: // East
                    if (cell.isWallEast()) {
                        if (cell.getNeiEast() != null) { // Not on the edge
                            cell.knockDownWall(cell.getNeiEast());
                        } else {
                            cell.setWallEast(false);
                        }
                        hasRemoved = true;
                    }
                    break;
            }
            n = rand.nextInt(4) + 1;
        }
        return hasRemoved;
    }
}
