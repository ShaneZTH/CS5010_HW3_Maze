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

}
