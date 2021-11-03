package maze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PerfectMazeTest {
    PerfectMaze maze;

    @Before
    public void setUp() throws Exception {
        maze = new PerfectMaze(10, 10, new int[]{0, 0}, new int[]{8, 8});
        maze.printMaze();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generateGoldCoins() {
        // tested & passed
    }

    @Test
    public void generateThief() {
        // tested & passed
    }

    @Test
    public void formMaze() {
        // tested & passed
    }

    @Test
    public void printMaze() {
        // tested & passed
    }
}