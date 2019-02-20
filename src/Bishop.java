import java.util.*;

public class Bishop extends ChessPiece {

    /**
     * Constructor for the Bishop
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public Bishop(String id, int row, int col, Board board, User u) {
        this.type = "Bishop";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.board = board;
        this.captured = false;
        this.setChessImage();
    }

    /**
     * Get any possible captures for one bishop.
     * @return any possible captures for one bishop
     */
    public Vector<Grid> possibleCapture() {
        return possibleHelper(false);
    }

    /**
     * Get any possible moves for one bishop.
     * @return any possible moves for one bishop
     */
    public Vector<Grid> possibleMove() {
        return possibleHelper(true);
    }

    /**
     * Helper function to get any possible moves/captures for one bishop.
     * @return any possible moves/captures helper function for one bishop
     */
    public Vector<Grid> possibleHelper(boolean move) {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = leftDiagonal(curr_x, curr_y, move, true);
        res.addAll(leftDiagonal(curr_x, curr_y, move, false));
        res.addAll(rightDiagonal(curr_x, curr_y, move, true));
        res.addAll(rightDiagonal(curr_x, curr_y, move, false));
        return res;
    }
}
