import java.util.*;

public class Rook extends ChessPiece {
    final int FORWARD = 0;
    final int BACKWARD = 1;
    final int LEFT = 2;
    final int RIGHT = 3;

    /**
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public Rook(String id, int row, int col, Board board, User u) {
        this.type = "Rook";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.board = board;
        this.captured = false;
        this.setChessImage();
    }

    /**
     * @return
     * Possible captures for rook
     */
    public Vector<Grid> possibleCapture() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = downCheck(curr_x, curr_y, false);
        res.addAll(upCheck(curr_x, curr_y, false));
        res.addAll(rightCheck(curr_x, curr_y, false));
        res.addAll(leftCheck(curr_x, curr_y, false));
//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "white") {
//            System.out.println(curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }

    /**
     * @return
     * Possible moves for rook
     */
    public Vector<Grid> possibleMove() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = downCheck(curr_x, curr_y, true);
        res.addAll(upCheck(curr_x, curr_y, true));
        res.addAll(rightCheck(curr_x, curr_y, true));
        res.addAll(leftCheck(curr_x, curr_y, true));
//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "white") {
//            System.out.println(curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }
}