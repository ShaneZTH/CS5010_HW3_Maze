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

    @Test
    public void generateGoldCoins() {
    }

    @Test
    public void generateThief() {
    }

    @Test
    public void initMaze() {
    }

    @Test
    public void formPerfectMaze() {
    }

    @Test
    public void printMaze() {
    }
}