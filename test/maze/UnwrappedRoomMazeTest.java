package maze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnwrappedRoomMazeTest {
    NonPerfectMaze maze;

    @Before
    public void setUp() throws Exception {
        maze = new UnwrappedRoomMaze(10, 10, new int[]{0,0}, new int[]{8,8});
        maze.printMaze();
    }


    @Test
    public void removeWalls() {
        assertTrue(maze.getRemainWalls() < maze.totalWalls);
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j != 9 && maze.mMaze[i][j].isWallEast()) count++;
                if (i != 9 && maze.mMaze[i][j].isWallSouth()) count++;
            }
        }
        assertEquals(maze.getRemainWalls()-1, count);
    }

    @Test (expected = IllegalArgumentException.class)
    public void randomlyRemoveWall() {
        maze.randomlyRemoveWall(new Cell(), -1, 2);
    }
}