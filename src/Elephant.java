import java.util.*;

public class Elephant extends ChessPiece {

    /**
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public Elephant(String id, int row, int col, Board board, User u) {
        this.type = "Elephant";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.board = board;
        this.captured = false;
        this.setChessImage();
    }

    /**
     * Elephant possible captures
     * @return possible captures for Elephant
     */
    public Vector<Grid> possibleCapture() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = checkSingleGrid(curr_x - 2, curr_y + 2, false);
        res.addAll(checkSingleGrid(curr_x - 2, curr_y - 2, false));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y - 2, false));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y + 2, false));
        return res;
    }

    /**
     * Elephant possible moves
     * @return possible moves for Elephant
     */
    public Vector<Grid> possibleMove() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = checkSingleGrid(curr_x - 2, curr_y + 2, true);
        res.addAll(checkSingleGrid(curr_x - 2, curr_y - 2, true));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y - 2, true));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y + 2, true));
        return res;
    }
}
