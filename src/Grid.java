public class Grid {
    private int x;
    private int y;

    /**
     * @param row
     * @param col
     */
    public Grid(int row, int col) {
        this.x = row;
        this.y = col;
    }

    /**
     * @return the x (row) for a grid object
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y (column) for a grid object
     */
    public int getY() {
        return this.y;
    }


    /**
     * @param x
     * Change the grid object x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y
     * Change the grid object y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param g
     * @return whether this grid equals grid g
     */
    public boolean compareGrid(Grid g) {
        return this.x == g.getX() && this.y == g.getY();
    }

    /**
     * @return toString method for grid
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
