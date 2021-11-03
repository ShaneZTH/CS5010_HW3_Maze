package maze;

import java.util.Random;

public class Cell {
    private boolean gold, thief;
    private boolean visited;
    private boolean wallNorth, wallSouth, wallEast, wallWest;
    private Cell neiNorth, neiSouth, neiEast, neiWest;
    private Random rand;

    public Cell() {
        this.wallNorth = true;
        this.wallSouth = true;
        this.wallWest = true;
        this.wallEast = true;

        rand = new Random();
    }

    public Cell(boolean hasGold, boolean hasThief, boolean wallNorth, boolean wallSouth,
                boolean wallEast, boolean wallWest) {
        this.gold = hasGold;
        this.thief = hasThief;
        this.visited = false;

        this.wallNorth = wallNorth;
        this.wallSouth = wallSouth;
        this.wallEast = wallEast;
        this.wallWest = wallWest;

        rand = new Random();
    }

    /**
     * Knock down the wall between current cell and a neighbor cell
     *
     * @param nei the neighboring cell of this cell
     */
    public void knockDownWall(Cell nei) {
        if (nei == null) return;

        if (nei == neiNorth) {
            setWallNorth(false);
            neiNorth.setWallSouth(false);
            return;
        }

        if (nei == neiSouth) {
            setWallSouth(false);
            neiSouth.setWallNorth(false);
            return;
        }

        if (nei == neiWest) {
            setWallWest(false);
            neiWest.setWallEast(false);
            return;
        }

        if (nei == neiEast) {
            setWallEast(false);
            neiEast.setWallWest(false);
            return;
        }

        // Error occurs
        System.err.println("Neighbor does not exist");
    }

    /**
     * @return Neighbor with all walls intact
     */
    public Cell getNeighborWithAllWalls() {
        Cell[] pool = new Cell[4];
        int count = 0;

        if (neiNorth != null && neiNorth.hasAllWalls()) {
            pool[count++] = neiNorth;
        }

        if (neiSouth != null && neiSouth.hasAllWalls()) {
            pool[count++] = neiSouth;
        }

        if (neiWest != null && neiWest.hasAllWalls()) {
            pool[count++] = neiWest;
        }

        if (neiEast != null && neiEast.hasAllWalls()) {
            pool[count++] = neiEast;
        }

        // No available neighbor
        if (count == 0) return null;

        // Random choose a neighbor from the pool
        return pool[rand.nextInt(count)];
    }

    /**
     * Set all 4 neighbors of this cell
     */
    public void setNeighbors(Cell n, Cell s, Cell w, Cell e) {
        setNeiNorth(n);
        setNeiSouth(s);
        setNeiWest(w);
        setNeiEast(e);
    }

    /**
     * @return true if this cell contains neither Gold nor Thief
     */
    public boolean isSpecial() {
        return hasGold() || hasThief();
    }

    /**
     * @return true if this cell has ALL 4 walls intact
     */
    public boolean hasAllWalls() {
        return (wallNorth && wallSouth && wallWest && wallEast);
    }

    /**
     * @return true if this cell has at lease 1 wall intact
     */
    public boolean hasAWall() {
        return wallNorth || wallSouth || wallWest || wallEast;
    }

    /**
     * @return whether this cell has Wall on its [N, S, W, E] direction
     */
    public boolean[] hasNoWall() {
        return new boolean[] {!wallNorth, !wallSouth, !wallWest, !wallEast};
    }

    public boolean hasGold() {
        return gold;
    }

    public boolean hasThief() {
        return thief;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public void setThief(boolean thief) {
        this.thief = thief;
    }

    public boolean isWallNorth() {
        return wallNorth;
    }

    public void setWallNorth(boolean wallNorth) {
        this.wallNorth = wallNorth;
    }

    public boolean isWallSouth() {
        return wallSouth;
    }

    public void setWallSouth(boolean wallSouth) {
        this.wallSouth = wallSouth;
    }

    public boolean isWallEast() {
        return wallEast;
    }

    public void setWallEast(boolean wallEast) {
        this.wallEast = wallEast;
    }

    public boolean isWallWest() {
        return wallWest;
    }

    public void setWallWest(boolean wallWest) {
        this.wallWest = wallWest;
    }

    public Cell getNeiNorth() {
        return neiNorth;
    }

    public Cell getNeiSouth() {
        return neiSouth;
    }

    public Cell getNeiEast() {
        return neiEast;
    }

    public Cell getNeiWest() {
        return neiWest;
    }

    public void setNeiNorth(Cell neiNorth) {
        this.neiNorth = neiNorth;
    }

    public void setNeiSouth(Cell neiSouth) {
        this.neiSouth = neiSouth;
    }

    public void setNeiEast(Cell neiEast) {
        this.neiEast = neiEast;
    }

    public void setNeiWest(Cell neiWest) {
        this.neiWest = neiWest;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
