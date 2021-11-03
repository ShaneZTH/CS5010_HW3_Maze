package maze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WrappedRoomMazeTest {
    WrappedRoomMaze maze;

    @Before
    public void setUp() throws Exception {
        maze = new WrappedRoomMaze(10, 10, new int[]{0,0}, new int[]{8,8});
        maze.printMaze();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void printMaze() {
    }
}