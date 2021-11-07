package maze;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
//    static AbstractMaze maze = new PerfectMaze(10, 10, new int[]{0, 0}, new int[]{8, 8});
    Cell c, n, s, w, e;
    @Before
    public void setUp() throws Exception {
        c = new Cell();
        n = new Cell();
        s = new Cell();
        w = new Cell();
        e = new Cell();
    }

    @Test
    public void knockDownWall() {
        c.setNeiNorth(n);
        assertTrue(c.isWallNorth());
        c.knockDownWall(n);
        assertFalse(c.isWallNorth());
    }

    @Test
    public void getNeighborWithAllWalls() {
        assertTrue(c.getNeighborWithAllWalls() == null);
        c.setNeighbors(n, s, w, e);
        assertTrue(c.getNeighborWithAllWalls() != null);
    }

    @Test
    public void isSpecial() {
        assertFalse(c.isSpecial());
        c.setGold(true);
        assertTrue(c.isSpecial());
        c.setGold(false);
        c.setThief(true);
        assertTrue(c.isSpecial());
    }

    @Test
    public void hasAllWalls() {
        c.setNeighbors(n, s, w, e);
        assertTrue(c.hasAllWalls());
        c.setWallNorth(false);
        assertFalse(c.hasAllWalls());
    }

    @Test
    public void hasAWall() {
        assertTrue(c.hasAWall());
        c.setWallNorth(false);
        c.setWallSouth(false);
        c.setWallWest(false);
        assertTrue(c.hasAWall());
        c.setWallEast(false);
        assertFalse(c.hasAWall());
    }

}