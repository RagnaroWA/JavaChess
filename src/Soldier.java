import java.util.*;

public class Soldier extends ChessPiece {

    private int direction;

    /**
     * Constructor for the soldier
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public Soldier(String id, int row, int col, Board board, User u) {
        this.type = "Soldier";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.board = board;
        this.captured = false;
        this.setChessImage();
        if(this.owner.color == "white") {
            this.direction = -1;
        }
        else {
            this.direction = 1;
        }
    }

    /**
     * Soldier possible captures
     * @return possible captures for Soldier
     */
    public Vector<Grid> possibleCapture() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        int des_x = newX(curr_x);
        Vector<Grid> res = checkSingleGrid(des_x, curr_y, false);
        return res;
    }

    /**
     * Soldier possible moves
     * @return possible moves for Soldier
     */
    public Vector<Grid> possibleMove() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        int des_x = newX(curr_x);
        Vector<Grid> res = checkSingleGrid(des_x, curr_y, true);
        return res;
    }

    /**
     * @param curr_x
     * @return the destination x position for the soldier
     */
    public int newX(int curr_x) {
        return curr_x + 1 * this.direction;
    }
}
