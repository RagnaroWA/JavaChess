import javax.imageio.ImageIO;
import java.io.File;
import java.util.Vector;
import java.awt.Image;

public class ChessPiece {
    public String type;
    public Grid position;
    public boolean captured;
    public Board board;
    public User owner;
    public String id;
    public Image image;

    /**
     * Function for inheritance
     */
    public ChessPiece() {

    }

    /**
     * @param id
     * @param row
     * @param col
     * @param u
     * Constructor for chess piece
     */
    public ChessPiece(String id, int row, int col, User u) {
        this.type = "Chess";
        this.position = new Grid(row, col);
        this.owner = u;
        this.id = id;
    }

    /**
     * @return get the position for the chess
     */
    public Grid getPosition() {
        return this.position;
    }

    /**
     * @return function for overloaded possible move
     */
    public Vector<Grid> possibleMove() {
        Vector<Grid> moves = new Vector<>();
        return moves;
    }

    /**
     * @return function for overloaded possible capture
     */
    public Vector<Grid> possibleCapture() {
        Vector<Grid> moves = new Vector<>();
        return moves;
    }

    /**
     * @return ge the owner of the chess
     */
    public User getOwner() {
        return this.owner;
    }

    /**
     * @return get the type of the chess
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return get the id of the chess
     */
    public String getID() {
        return this.id;
    }

    /**
     * @param g
     * Change the position of the chess
     */
    public void changePosition(Grid g) {
        this.position.setX(g.getX());
        this.position.setY(g.getY());
    }

    /**
     * @param ori
     * @param des
     * Move the chess on the board, from original position to destination position
     */
    public void moveOnBoard(Grid ori, Grid des) {
        moveOnBoardHelper(ori, des);
    }

    /**
     * @param ori
     * @param des
     * Helper function for moving the chess
     */
    public void moveOnBoardHelper(Grid ori, Grid des) {
        int x_ori = ori.getX();
        int y_ori = ori.getY();
        int x_des = des.getX();
        int y_des = des.getY();
        this.changePosition(des);
        this.board.gameBoard[x_des][y_des] = this;
        this.board.gameBoard[x_ori][y_ori] = null;
    }


    /**
     * @param x
     * @param y
     * @return whether the index is in the board
     */
    public boolean inBoundary(int x, int y) {
        if(x < 0 || x >= this.board.getNumberOfRow() || y < 0 || y >= this.board.getNumberOfCol()) {
            return false;
        }
        return true;
    }

    /**
     * @param x
     * @param y
     * @param move
     * @return Possible moves or captures for downward direction
     */
    public Vector<Grid> downCheck(int x, int y, boolean move) {
        Vector<Grid> res = new Vector<>();
        for(int i=x+1; i<this.board.getNumberOfRow(); i++) {
            if(move == true) {
                if (inBoundary(i, y) && this.board.gameBoard[i][y] == null) {
                    res.add(new Grid(i, y));
                } else {
                    break;
                }
            }
            else {
                if (inBoundary(i, y) && this.board.gameBoard[i][y] != null) {
                    if(this.board.gameBoard[i][y].owner != this.owner) {
                        res.add(new Grid(i, y));
                    }
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @param x
     * @param y
     * @param move
     * @return Possible moves or captures for upward direction
     */
    public Vector<Grid> upCheck(int x, int y, boolean move) {
        Vector<Grid> res = new Vector<>();
        for(int i=x-1; i>=0; i--) {
            if(move == true) {
                if (inBoundary(i, y) && this.board.gameBoard[i][y] == null) {
                    res.add(new Grid(i, y));
                } else {
                    break;
                }
            }
            else {
                if (inBoundary(i, y) && this.board.gameBoard[i][y] != null) {
                    if(this.board.gameBoard[i][y].owner != this.owner) {
                        res.add(new Grid(i, y));
                    }
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @param x
     * @param y
     * @param move
     * @return Possible moves or captures for rightward direction
     */
    public Vector<Grid> rightCheck(int x, int y, boolean move) {
        Vector<Grid> res = new Vector<>();
        for(int i=y+1; i<this.board.getNumberOfCol(); i++) {
            if(move == true) {
                if (inBoundary(x, i) && this.board.gameBoard[x][i] == null) {
                    res.add(new Grid(x, i));
                } else {
                    break;
                }
            }
            else {
                if (inBoundary(x, i) && this.board.gameBoard[x][i] != null) {
                    if(this.board.gameBoard[x][i].owner != this.owner) {
                        res.add(new Grid(x, i));
                    }
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @param x
     * @param y
     * @param move
     * @return Possible moves or captures for leftward direction
     */
    public Vector<Grid> leftCheck(int x, int y, boolean move) {
        Vector<Grid> res = new Vector<>();
        for(int i=y-1; i>=0; i--) {
            if(move == true) {
                if (inBoundary(x, i) && this.board.gameBoard[x][i] == null) {
                    res.add(new Grid(x, i));
                } else {
                    break;
                }
            }
            else {
                if (inBoundary(x, i) && this.board.gameBoard[x][i] != null) {
                    if(this.board.gameBoard[x][i].owner != this.owner) {
                        res.add(new Grid(x, i));
                    }
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @param x
     * @param y
     * @param move
     * @param down
     * @return Possible moves or captures for left diagonal direction
     */
    public Vector<Grid> leftDiagonal(int x, int y, boolean move, boolean down) {
        int factor = 1;
        if(down == false) {
            factor = -1;
        }
        Vector<Grid> res = new Vector<>();
        for(int i=1; i<this.board.getNumberOfRow(); i++) {
            int tempX = x + factor * i;
            int tempY = y + factor * i;
            if(move == true) {
                if (inBoundary(tempX, tempY) && this.board.gameBoard[tempX][tempY] == null) {
                    res.add(new Grid(tempX, tempY));
                } else {
                    break;
                }
            }
            else {
                if (inBoundary(tempX, tempY) && this.board.gameBoard[tempX][tempY] != null) {
                    if(this.board.gameBoard[tempX][tempY].owner != this.owner) {
                        res.add(new Grid(tempX, tempY));
                    }
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @param x
     * @param y
     * @param move
     * @param down
     * @return Possible moves or captures for right diagonal direction
     */
    public Vector<Grid> rightDiagonal(int x, int y, boolean move, boolean down) {
        int factor = 1;
        if(down == false) {
            factor = -1;
        }
        Vector<Grid> res = new Vector<>();
        for(int i=1; i<this.board.getNumberOfRow(); i++) {
            int tempX = x + factor * i;
            int tempY = y - factor * i;
            if(move == true) {
                if (inBoundary(tempX, tempY) && this.board.gameBoard[tempX][tempY] == null) {
                    res.add(new Grid(tempX, tempY));
                } else {
                    break;
                }
            }
            else {
                if (inBoundary(tempX, tempY) && this.board.gameBoard[tempX][tempY] != null) {
                    if(this.board.gameBoard[tempX][tempY].owner != this.owner) {
                        res.add(new Grid(tempX, tempY));
                    }
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @param x
     * @param y
     * @param move
     * @return check single grid whether can be moved or captured to
     */
    public Vector<Grid> checkSingleGrid(int x, int y, boolean move) {
        Vector<Grid> res = new Vector<>();
        if(move == true) {
            if (inBoundary(x, y) && this.board.gameBoard[x][y] == null) {
                res.add(new Grid(x, y));
            }
        }
        else {
            if(inBoundary(x, y) && this.board.gameBoard[x][y] != null && this.board.gameBoard[x][y].getOwner() != this.owner) {
                res.add(new Grid(x, y));
            }
        }
        return res;
    }

    /**
     * Set the image for each chess
     */
    public void setChessImage() {
        String color = this.getOwner().getColor();
        String type = this.getType();
        String filename = type + "-" + color + ".png";
        try {
            Image image = ImageIO.read(new File("/Users/zechengzhang/IdeaProjects/sp-19-cs242-assignment1/chessIcon/" + filename));
            this.image = image;
//            System.out.println("/Users/zechengzhang/IdeaProjects/sp-19-cs242-assignment1/chessIcon/" + filename);
        }
        catch (Exception e) {
//            System.out.println("load image error!");
            System.exit(1);
        }
    }

    public Image getChessImage() {
        return this.image;
    }
}