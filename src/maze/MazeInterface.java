package maze;

public interface MazeInterface {

    /**
     * Initialize the maze by connecting cells to their neighbors
     * and randomly add Gold Coins and Thieves into the maze
     */
    void initMaze();

    /**
     * Form a Perfect Maze
     */
    void formPerfectMaze(int row, int col);

    /**
     * 20% of locations (at random) have gold coins in them that the player can pick up
     */
    void generateGoldCoins();

    /**
     * 10% of locations (at random) have a thief that takes some of the player's gold coins
     */
    void generateThief();


    /**
     * Print the Maze to stdout
     */
    void printMaze();
}
