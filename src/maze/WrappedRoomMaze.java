package maze;

public class WrappedRoomMaze extends NonPerfectMaze {
    private final static MazeType mType = MazeType.WrappedRoomMaze;

    public WrappedRoomMaze(int row, int col, int[] startPoint, int[] endPoint) {
        super(mType, row, col, startPoint, endPoint);
        initMaze();
        remainWalls = rand.nextInt((totalWalls - (row * col)) + 1);
        formPerfectMaze(row, col);
        removeWalls(row, col);
    }
}
