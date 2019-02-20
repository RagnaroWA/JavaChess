import java.util.*;

public class Board {
    private int nrow;
    private int ncol;
    private User user1;
    private User user2;
    public ChessPiece[][] gameBoard;
    public Vector<ChessPiece> capturedChess;
    public Vector<Memory> log;

    /**
     * @param row
     * @param col
     * @param user1
     * @param user2
     */
    public Board(int row, int col, User user1, User user2) {
        this.nrow = row;
        this.ncol = col;
        this.gameBoard = new ChessPiece[col][row];
        this.user1 = user1;
        this.user2 = user2;
        this.capturedChess = new Vector<>();
        this.log = new Vector<>();
        addNullToBoard();
        addChessToBoard("white");
        addChessToBoard("black");
    }

    /**
     * Initialize the board for adding all null
     */
    public void addNullToBoard() {
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                this.gameBoard[i][j] = null;
            }
        }
    }

    /**
     * @param color
     * Call function to add specific color chess
     */
    public void addChessToBoard(String color) {
        if(color == "white") {
            addChessToWhite();
        }
        else {
            addChessToBlack();
        }
    }

    /**
     * Add all white chess
     */
    public void addChessToWhite() {
        for(int i=0; i<8; i++) {
            Pawn chess = new Pawn("WP"+i, 6, i, this, this.user1);
            this.gameBoard[6][i] = chess;
        }
        Rook rook1 = new Rook("WR0", 7, 0, this, this.user1);
        this.gameBoard[7][0] = rook1;
        Rook rook2 = new Rook("WR1", 7, 7, this, this.user1);
        this.gameBoard[7][7] = rook2;

        Knight knight1 = new Knight("WK0", 7, 1, this, this.user1);
        this.gameBoard[7][1] = knight1;
        Knight knight2 = new Knight("WK1", 7, 6, this, this.user1);
        this.gameBoard[7][6] = knight2;

        Bishop bishop1 = new Bishop("WB0", 7, 2, this, this.user1);
        this.gameBoard[7][2] = bishop1;
        Bishop bishop2 = new Bishop("WB1", 7, 5, this, this.user1);
        this.gameBoard[7][5] = bishop2;

        Queen queen = new Queen("WQu", 7, 3, this, this.user1);
        this.gameBoard[7][3] = queen;

        King king = new King("WKg", 7, 4, this, this.user1);
        this.gameBoard[7][4] = king;
        this.user1.setKing(king);

//        Elephant elephant = new Elephant("WEp", 5, 0, this, this.user1);
//        this.gameBoard[5][7] = elephant;

//        Soldier soldier = new Soldier("WSo", 5, 1, this, this.user1);
//        this.gameBoard[5][6] = soldier;
    }

    /**
     * Add all black chess
     */
    public void addChessToBlack() {
        for(int i=0; i<8; i++) {
            Pawn chess = new Pawn("BP"+i, 1, i, this, this.user2);
            this.gameBoard[1][i] = chess;
        }
        Rook rook1 = new Rook("BR0", 0, 0, this, this.user2);
        this.gameBoard[0][0] = rook1;
        Rook rook2 = new Rook("BR1", 0, 7, this, this.user2);
        this.gameBoard[0][7] = rook2;

        Knight knight1 = new Knight("BK0", 0, 1, this, this.user2);
        this.gameBoard[0][1] = knight1;
        Knight knight2 = new Knight("BK1", 0, 6, this, this.user2);
        this.gameBoard[0][6] = knight2;

        Bishop bishop1 = new Bishop("BB0", 0, 2, this, this.user2);
        this.gameBoard[0][2] = bishop1;
        Bishop bishop2 = new Bishop("BB1", 0, 5, this, this.user2);
        this.gameBoard[0][5] = bishop2;

        Queen queen = new Queen("BQu", 0, 3, this, this.user2);
        this.gameBoard[0][3] = queen;

        King king = new King("BKg", 0, 4, this, this.user2);
        this.gameBoard[0][4] = king;
        this.user2.setKing(king);

//        Elephant elephant = new Elephant("BEp", 2, 0, this, this.user2);
//        this.gameBoard[2][7] = elephant;
//
//        Soldier soldier = new Soldier("BSo", 2, 1, this, this.user2);
//        this.gameBoard[2][6] = soldier;
    }

    /**
     * @return get the first user (white)
     */
    public User getUser1() {
        return this.user1;
    }

    /**
     * @return get the second user (black)
     */
    public User getUser2() {
        return this.user2;
    }

    /**
     * @return the number of rows
     */
    public int getNumberOfRow() {
        return this.nrow;
    }

    /**
     * @return the number columns
     */
    public int getNumberOfCol() {
        return this.ncol;
    }

    /**
     * @param x_ori
     * @param y_ori
     * @param x_des
     * @param y_des
     * @return whether the chess is in the board
     */
    public boolean checkInsideBoard(int x_ori, int y_ori, int x_des, int y_des) {
        if(x_ori < 0 || y_ori < 0 || x_des < 0 || y_des < 0) {
            return false;
        }
        if(x_ori > 7 || y_ori > 7 || x_des > 7 || y_des > 7) {
            return false;
        }
        return true;
    }

    /**
     * @param ori
     * @param des
     * @return whether the chess can be moved from original grid to destination, if true the chess will be moved
     */
    public boolean moveChessPieceByCheck(Grid ori, Grid des) {
//        System.out.println("in move" + ori + " " + des);
        if(ori.compareGrid(des)) {
            return false;
        }
        int x_ori = ori.getX();
        int y_ori = ori.getY();
        int x_des = des.getX();
        int y_des = des.getY();
        if(nullAndInsideCheck(x_ori, y_ori, x_des, y_des) == false) {
            return false;
        }
        ChessPiece chess = this.gameBoard[x_ori][y_ori];
        Vector<Grid> moves = chess.possibleMove();
        Vector<Grid> captures = chess.possibleCapture();
        Grid move = checkGrids(moves, des);
        Grid capture = checkGrids(captures, des);
//        System.out.println("in move 2" + ori + " " + des);
        Grid newOri = new Grid(x_ori, y_ori);
        Grid newDes = new Grid(x_des, y_des);
        if(move != null) {
            boolean pawnFirst = instancePawnFirst(chess);
            chess.moveOnBoard(ori, des);
//            System.out.println("in set" + newOri + " " + newDes);
            Memory m = new Memory(newOri, newDes, false, pawnFirst);
            log.add(m);
            return true;
        }
        if(capture != null) {
            ChessPiece captured = this.gameBoard[x_des][y_des];
            this.capturedChess.add(captured);
            boolean pawnFirst = instancePawnFirst(chess);
            chess.moveOnBoard(ori, des);
            User owner = chess.getOwner();
            owner.increaseScore();
            Memory m = new Memory(newOri, newDes, true, pawnFirst);
            log.add(m);
            return true;
        }
        return false;
    }

    /**
     * Whether the four grids are in board or the there are no chess in those grid
     * @param x_ori
     * @param y_ori
     * @param x_des
     * @param y_des
     * @return false if not inside or null
     */
    public boolean nullAndInsideCheck(int x_ori, int y_ori, int x_des, int y_des) {
        boolean inside = checkInsideBoard(x_ori, y_ori, x_des, y_des);
        if(inside == false) {
            return false;
        }
        ChessPiece chess = this.gameBoard[x_ori][y_ori];
        if(chess == null) {
            return false;
        }
        return true;
    }

    /**
     * Check whether the chess is instance of pawn
     * @param chess
     * @return true if is the pawn
     */
    public boolean instancePawnFirst(ChessPiece chess) {
        boolean pawnFirst = false;
        if(chess instanceof Pawn) {
            Pawn p = (Pawn) chess;
            if(p.checkFirstMove() == true) {
                pawnFirst = true;
            }
        }
        return pawnFirst;
    }


    /**
     * Check whether grid des in gs vector
     * @param gs
     * @param des
     * @return null if not, else return the grid
     */
    public Grid checkGrids(Vector<Grid> gs, Grid des) {
        for(int i=0; i<gs.size(); i++) {
            if(gs.get(i).compareGrid(des) == true) {
                return gs.get(i);
            }
        }
        return null;
    }

//    /**
//     * @param size
//     * @param row
//     * @param matrix
//     * helper function to display the board
//     * https://stackoverflow.com/questions/12845208/how-to-print-two-dimensional-array-like-table
//     */
//    public void displayBoardHelper(int size, int row, ChessPiece[][] matrix) {
//        for(int i = 0;i <  7 * size ;i++){
//            System.out.print("-");
//        }
//        System.out.println("-");
//
//        for(int i = 1;i <= matrix[row].length;i++){
//            if(matrix[row][i - 1] != null) {
//                System.out.printf("| %4s ", matrix[row][i - 1].id);
//            }
//            else {
//                System.out.printf("| %4s ", " ");
//            }
//        }
//        System.out.println("|");
//
//        if(row == size -  1){
//            for(int i = 0;i <  7 * size ;i++){
//                System.out.print("-");
//            }
//            System.out.println("-");
//        }
//    }
//
//    /**
//     * Display the board
//     */
//    public void displayBoard() {
//        int row = this.gameBoard.length;
//        for(int i=0; i<row; i++) {
//            displayBoardHelper(row, i, this.gameBoard);
//        }
//    }

    /**
     * @param player
     * @return possible captures for player
     */
    public Vector<Grid> getPlayerPossibleCapture(User player) {
        Vector<Grid> actions = new Vector<>();
        for(int i=0; i<this.getNumberOfRow(); i++) {
            for(int j=0; j<this.getNumberOfCol(); j++) {
                ChessPiece c = this.gameBoard[i][j];
                if(c != null && c.getOwner() == player) {
                    actions.addAll(c.possibleCapture());
                }
            }
        }
        return actions;
    }

    /**
     * @param player
     * @return get player possible actions
     */
    public Map<Grid, Vector<Grid>> getPlayerPossibleAction(User player) {
        Map<Grid, Vector<Grid>> hmap = new HashMap<>();
        for(int i=0; i<this.getNumberOfRow(); i++) {
            for(int j=0; j<this.getNumberOfCol(); j++) {
                if(this.gameBoard[i][j] != null && this.gameBoard[i][j].getOwner() == player) {
                    ChessPiece c = this.gameBoard[i][j];
                    Vector<Grid> actions = new Vector<>();
                    actions.addAll(c.possibleCapture());
                    actions.addAll(c.possibleMove());
                    Grid key = new Grid(i, j);
                    hmap.put(key, actions);
                }
            }
        }
        return hmap;
    }

    /**
     * Undo one action
     */
    public void undoOneAction() {
        Memory m = this.log.get(this.log.size()-1);
        boolean pawnFirst = m.getPawnFist();
        Grid ori = m.getFrom();
        Grid curr = m.getTo();
        int x_ori = ori.getX();
        int y_ori = ori.getY();
        int x_curr = curr.getX();
        int y_curr = curr.getY();
        ChessPiece c = this.gameBoard[x_curr][y_curr];
        this.gameBoard[x_curr][y_curr] = null;
        this.gameBoard[x_ori][y_ori] = c;
        c.changePosition(ori);
        if(pawnFirst == true) {
            Pawn p = (Pawn) c;
            p.changeFirstMove(true);
        }

        if(m.getCaptured() == true) {
            ChessPiece c2 = this.capturedChess.get(this.capturedChess.size()-1);
            this.gameBoard[x_curr][y_curr] = c2;
            c2.changePosition(curr);
            this.capturedChess.remove(this.capturedChess.size()-1);
        }
        this.log.remove(this.log.size()-1);
    }

    /**
     * @param possibleCaptures
     * @param kingPos
     * @return check whether king will be captured in others possible captures
     */
    public boolean checkKingCaptured(Vector<Grid> possibleCaptures, Grid kingPos) {
        boolean currentInCheck = false;
        for(int i=0; i<possibleCaptures.size(); i++) {
            if(possibleCaptures.get(i).compareGrid(kingPos)) {
                currentInCheck = true;
            }
        }
        return currentInCheck;
    }

    /**
     * @param user
     * @param opponent
     * @return check whether user will win the opponent after the user move/capture
     */
    public int checkBoardStatus(User user, User opponent) {
        Vector<Grid> possibleCaptures = getPlayerPossibleCapture(user);
        Grid kingPos = opponent.getKingPosition();
        boolean currentInCheck = checkKingCaptured(possibleCaptures, kingPos);
        boolean futureInCheck = true;
        Map<Grid, Vector<Grid>> opponentPossibleActions = getPlayerPossibleAction(opponent);
        for (Grid chessPosition : opponentPossibleActions.keySet()) {
            Grid from = chessPosition;
            Vector<Grid> toGrids  = opponentPossibleActions.get(from);
            for (int i = 0; i < toGrids.size(); i++) {
                Grid to = toGrids.get(i);
                boolean move = this.moveChessPieceByCheck(from, to);
                if(move == true) {
                    Vector<Grid> newPossibleCaptures = getPlayerPossibleCapture(user);
                    Grid newKingPos = opponent.getKingPosition();
                    boolean kingStillCaptured = checkKingCaptured(newPossibleCaptures, newKingPos);
                    if(kingStillCaptured == false) {
                        undoOneAction();
                        futureInCheck = false;
                        break;
                    }
                    else {
                        undoOneAction();
                    }
                }
            }
        }
        if(futureInCheck == true && currentInCheck == true) {
            return 1;
        }
        else if(futureInCheck == true && currentInCheck == false) {
            return 2;
        }
        return 0;
    }
}
