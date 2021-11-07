package maze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PerfectMazeTest {
    PerfectMaze maze;
    int R = 10;
    int C = 10;

    @Before
    public void setUp() throws Exception {
        maze = new PerfectMaze(10, 10, new int[]{0, 0}, new int[]{8, 8});
        maze.printMaze();
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorTest1() {
        new PerfectMaze(-10,2, new int[]{1,1}, new int[]{2,2});
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorTest2() {
        new PerfectMaze(2,2, new int[]{0,0}, new int[]{2,2});
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorTest3() {
        new PerfectMaze(3,3, new int[]{-2,3}, new int[]{0,0});
    }

    @Test
    public void generateGoldCoins() {
        int count = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maze.mMaze[i][j].hasGold()) count++;
            }
        }
        assertEquals((int)(R * C * 0.2), count);
    }

    @Test
    public void generateThief() {
        int count = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maze.mMaze[i][j].hasThief()) count++;
            }
        }
        assertEquals((int)(R * C * 0.1), count);
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

    @Test
    public void isStart() {
        maze.isStart(0,0);
    }

    @Test
    public void isEnd() {
        maze.isEnd(8,8);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isStart2() {
        maze.isStart(-2,20);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isEnd2() {
        maze.isEnd(-1,11);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getCell() {
        maze.isEnd(-1,11);
    }
}