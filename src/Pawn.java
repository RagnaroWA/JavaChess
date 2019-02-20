import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;


public class Pawn extends ChessPiece{
    private boolean firstMove;
    private int direction;

    final int FORWARD1 = 1;
    final int FORWARD2 = 2;
    final int LEFTDIAG = 3;
    final int RIGHTDIAG = 4;

    /**
     * @param id
     * @param row
     * @param col
     * @param board
     * @param u
     */
    public Pawn(String id, int row, int col, Board board, User u) {
        this.type = "Pawn";
        this.id = id;
        this.position = new Grid(row, col);
        this.owner = u;
        this.firstMove = true;
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
     * @return possible captures for pawn
     */
    public Vector<Grid> possibleCapture() {
        Vector<Integer> capture = new Vector<>();
        capture.add(LEFTDIAG);
        capture.add(RIGHTDIAG);
        Vector<Grid> res = checkCapture(capture);
        return res;
    }

    /**
     * @return possible moves for pawn
     */
    public Vector<Grid> possibleMove() {
        Vector<Integer> moves = new Vector<>();
        moves.add(FORWARD1);
        if(this.checkFirstMove() == true) {
            moves.add(FORWARD2);
        }
        Vector<Grid> res = checkMove(moves);
        return res;
    }

    /**
     * @param moves
     * @return which grids can be moved to
     */
    public Vector<Grid> checkMove(Vector<Integer> moves) {
        Vector<Grid> res = new Vector<>();
        for(int i=0; i<moves.size(); i++) {
            int move = moves.get(i);
            Grid newGrid = moveForward(move);
            if (newGrid != null) {
                res.add(newGrid);
            }
        }
        return res;
    }

    /**
     * @param moves
     * @return which grids can be captured to
     */
    public Vector<Grid> checkCapture(Vector<Integer> moves) {
        Vector<Grid> res = new Vector<>();
        for(int i=0; i<moves.size(); i++) {
            int move = moves.get(i);
            Grid newGrid = captureDiagonal(move);
            if(newGrid != null) {
                res.add(newGrid);
            }
        }
        return res;
    }

    /**
     * @param move
     * @return helper function for checkMove
     */
    public Grid moveForward(int move) {
        int newX = this.direction * move + this.position.getX();
        int y = this.position.getY();
        if(inBoundary(newX, y) == false) {
            return null;
        }
        if(this.board.gameBoard[newX][y] == null) {
            Grid newGrid = new Grid(newX, y);
            return newGrid;
        }
        return null;
    }

    /**
     * @param move
     * @return helper function for checkCapture
     */
    public Grid captureDiagonal(int move) {
        int newX = this.direction * 1 + this.position.getX();
        int y = this.position.getY();
        if(move == LEFTDIAG) {
            y -= 1;
        }
        else {
            y += 1;
        }
        if(inBoundary(newX, y) == false) {
            return null;
        }
        if(this.board.gameBoard[newX][y] != null && this.board.gameBoard[newX][y].owner != this.owner ) {
            Grid newGrid = new Grid(newX, y);
            return newGrid;
        }
        return null;
    }

    /**
     * @param b
     * Change whether the pawn is in first move
     */
    public void changeFirstMove(boolean b) {
        this.firstMove = b;
    }

    /**
     * @return if the pawn is first moved
     */
    public boolean checkFirstMove() {
        return this.firstMove;
    }

    /**
     * @param ori
     * @param des
     * Move the pawn and change the first move
     */
    public void moveOnBoard(Grid ori, Grid des) {
        moveOnBoardHelper(ori, des);
        if(this.checkFirstMove() == true) {
            this.changeFirstMove(false);
        }
    }
}