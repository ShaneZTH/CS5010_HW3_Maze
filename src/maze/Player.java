package maze;

public class Player {
    private int goldCount;
    int row;
    int col;

    public Player(int startX, int startY) throws IllegalArgumentException {
        if (startX < 0 || startY < 0)
            throw new IllegalArgumentException("Starting position must be positive");

        this.row = startY;
        this.col = startX;
        this.goldCount = 0;
    }

    /**
     * Set the current position of the player
     *
     * @param n contains row and column
     */
    public void updatePosition(int[] n) throws IllegalArgumentException {
        if (n == null || n.length != 2)
            throw new IllegalArgumentException("Invalid position");
        if (n[0] < 0 || n[1] < 0)
            throw new IllegalArgumentException("Invalid position");

        this.row = n[0];
        this.col = n[1];
    }

    /**
     * a player "collects" gold by entering a room that has gold which is then removed from the room
     *
     * @param gold the gold just collected
     */
    public void meetGold(int gold) {
        this.goldCount += gold;
        System.out.printf("You just picked up %d Gold! You currently have %d Gold.\n",
                gold, getGoldCount());
    }

    /**
     * a player "loses" 10% of their gold by entering a room with a thief
     */
    public void meetThief() throws IllegalStateException {
        if (getGoldCount() < 0)
            throw new IllegalStateException("Player's gold count cannot be negative");
        if (getGoldCount() == 0) return;

        this.goldCount = (int) (this.getGoldCount() * 0.9);
        System.out.printf("Oops, you just robbed by a thief! You currently have %d Gold.\n",
                 getGoldCount());
    }

    /**
     * Parse the Player's status into a string
     * @return String that contains the Player's status
     */
    public String status() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Player currently at (%d,%d)\n", row, col));
        sb.append(String.format("\t with %d Gold!\n", getGoldCount()));
        return sb.toString();
    }

    public int getGoldCount() {
        return this.goldCount;
    }
}
