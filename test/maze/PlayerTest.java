package maze;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player p;
    @Before
    public void setUp() throws Exception {
        p = new Player(0,0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void updatePosition1() {
        p.updatePosition(new int[]{1,2,3});
    }

    @Test (expected = IllegalArgumentException.class)
    public void updatePosition2() {
        p.updatePosition(new int[]{-2,3});
    }

    @Test
    public void meetGold() {
        assertEquals(0, p.getGoldCount());
        p.meetGold(10);
        assertEquals(10, p.getGoldCount());
    }

    @Test
    public void meetThief() {
        assertEquals(0, p.getGoldCount());
        p.meetGold(10);
        p.meetThief();
        assertEquals(9, p.getGoldCount());
    }

    @Test
    public void meetThief2() {
        assertEquals(0, p.getGoldCount());
        p.meetThief();
        assertEquals(0, p.getGoldCount());
    }

}