import java.util.*;

public class Queen extends ChessPiece {

    /**
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public Queen(String id, int row, int col, Board board, User u) {
        this.type = "Queen";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.board = board;
        this.captured = false;
        this.setChessImage();
    }

    /**
     * @return
     * Possible captures for queen
     */
    public Vector<Grid> possibleCapture() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();

        Vector<Grid> res = leftDiagonal(curr_x, curr_y, false, true);
        res.addAll(leftDiagonal(curr_x, curr_y, false, false));
        res.addAll(rightDiagonal(curr_x, curr_y, false, true));
        res.addAll(rightDiagonal(curr_x, curr_y, false, false));
        res.addAll(downCheck(curr_x, curr_y, false));
        res.addAll(upCheck(curr_x, curr_y, false));
        res.addAll(rightCheck(curr_x, curr_y, false));
        res.addAll(leftCheck(curr_x, curr_y, false));

//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "black") {
//            System.out.println(curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }

    /**
     * @return
     * Possible moves for queen
     */
    public Vector<Grid> possibleMove() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();

        Vector<Grid> res = leftDiagonal(curr_x, curr_y, true, true);
        res.addAll(leftDiagonal(curr_x, curr_y, true, false));
        res.addAll(rightDiagonal(curr_x, curr_y, true, true));
        res.addAll(rightDiagonal(curr_x, curr_y, true, false));
        res.addAll(downCheck(curr_x, curr_y, true));
        res.addAll(upCheck(curr_x, curr_y, true));
        res.addAll(rightCheck(curr_x, curr_y, true));
        res.addAll(leftCheck(curr_x, curr_y, true));

//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "black") {
//            System.out.println(curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }
}
