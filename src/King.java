import java.util.*;

public class King extends ChessPiece {

    /**
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public King(String id, int row, int col, Board board, User u) {
        this.type = "King";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.board = board;
        this.captured = false;
        this.setChessImage();
    }

    /**
     * @return possible captures for king
     */
    public Vector<Grid> possibleCapture() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = checkSingleGrid(curr_x - 1, curr_y, false);
        res.addAll(checkSingleGrid(curr_x - 1, curr_y + 1, false));
        res.addAll(checkSingleGrid(curr_x, curr_y + 1, false));
        res.addAll(checkSingleGrid(curr_x + 1, curr_y + 1, false));

        res.addAll(checkSingleGrid(curr_x + 1, curr_y, false));
        res.addAll(checkSingleGrid(curr_x + 1, curr_y - 1, false));
        res.addAll(checkSingleGrid(curr_x, curr_y - 1, false));
        res.addAll(checkSingleGrid(curr_x - 1, curr_y - 1, false));
//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "white") {
//            System.out.println("Capture " + curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }

    /**
     * @return possible moves for king
     */
    public Vector<Grid> possibleMove() {
        Grid curr_position = this.position;
        int curr_x = curr_position.getX();
        int curr_y = curr_position.getY();
        Vector<Grid> res = checkSingleGrid(curr_x - 1, curr_y, true);
        res.addAll(checkSingleGrid(curr_x - 1, curr_y + 1, true));
        res.addAll(checkSingleGrid(curr_x, curr_y + 1, true));
        res.addAll(checkSingleGrid(curr_x + 1, curr_y + 1, true));

        res.addAll(checkSingleGrid(curr_x + 1, curr_y, true));
        res.addAll(checkSingleGrid(curr_x + 1, curr_y - 1, true));
        res.addAll(checkSingleGrid(curr_x, curr_y - 1, true));
        res.addAll(checkSingleGrid(curr_x - 1, curr_y - 1, true));
//        if(this.board.gameBoard[curr_x][curr_y].getOwner().getColor() == "white") {
//            System.out.println("Move " + curr_x + " " + curr_y + " " + res);
//        }
        return res;
    }
}
