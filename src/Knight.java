import java.util.*;

public class Knight extends ChessPiece {

    /**
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public Knight(String id, int row, int col, Board board, User u) {
        this.type = "Knight";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.board = board;
        this.captured = false;
        this.setChessImage();
    }

    /**
     * @return possible captures for knight
     */
    public Vector<Grid> possibleCapture() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = checkSingleGrid(curr_x - 1, curr_y + 2, false);
        res.addAll(checkSingleGrid(curr_x - 2, curr_y + 1, false));
        res.addAll(checkSingleGrid(curr_x - 1, curr_y - 2, false));
        res.addAll(checkSingleGrid(curr_x - 2, curr_y - 1, false));

        res.addAll(checkSingleGrid(curr_x + 1, curr_y + 2, false));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y + 1, false));
        res.addAll(checkSingleGrid(curr_x + 1, curr_y - 2, false));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y - 1, false));
//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "white") {
//            System.out.println(curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }

    /**
     * @return possible moves for knight
     */
    public Vector<Grid> possibleMove() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = checkSingleGrid(curr_x - 1, curr_y + 2, true);
        res.addAll(checkSingleGrid(curr_x - 2, curr_y + 1, true));
        res.addAll(checkSingleGrid(curr_x - 1, curr_y - 2, true));
        res.addAll(checkSingleGrid(curr_x - 2, curr_y - 1, true));

        res.addAll(checkSingleGrid(curr_x + 1, curr_y + 2, true));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y + 1, true));
        res.addAll(checkSingleGrid(curr_x + 1, curr_y - 2, true));
        res.addAll(checkSingleGrid(curr_x + 2, curr_y - 1, true));
//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "white") {
//            System.out.println(curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }
}
