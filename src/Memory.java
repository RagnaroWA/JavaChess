public class Memory {
    private Grid from;
    private Grid to;
    private boolean capture;
    private boolean pawnFirst;

    /**
     * @param from
     * @param to
     * @param capture
     * @param pawnFirst
     */
    public Memory(Grid from, Grid to, boolean capture, boolean pawnFirst) {
        this.from = from;
        this.to = to;
        this.capture = capture;
        this.pawnFirst = pawnFirst;
    }

    /**
     * @return memory from
     */
    public Grid getFrom() {
        return this.from;
    }

    /**
     * @return memory to
     */
    public Grid getTo() {
        return this.to;
    }


    /**
     * @return whether the chess is captured
     */
    public boolean getCaptured() {
        return this.capture;
    }

    public boolean getPawnFist() {
        return this.pawnFirst;
    }

    /**
     * @return toString
     */
    public String toString() {
        return "(" + this.from.getX() + "," + this.from.getY() + ")" + "," + "(" + this.to.getX() + "," + this.to.getY() + ")" + "," + this.capture + ";";
    }
}
