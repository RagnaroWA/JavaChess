import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class BoardTest {
    @Test
    public void undoTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Pawn chess = (Pawn) newGame.board.gameBoard[6][1];
        Grid ori = new Grid(1, 0);
        Grid des = new Grid(3, 0);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(3, 0);
        des = new Grid(4, 0);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(4, 0);
        des = new Grid(5, 0);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(5, 0);
        des = new Grid(6, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, newGame.board.log.get(3).getCaptured());
//        newGame.board.displayBoard();
//        System.out.println(newGame.board.log);
//        System.out.println(newGame.board.capturedChess);
        newGame.board.undoOneAction();
        Assert.assertEquals(chess, newGame.board.gameBoard[6][1]);
//        newGame.board.displayBoard();
//        System.out.println(newGame.board.log);
//        System.out.println(newGame.board.capturedChess);
    }

    @Test
    public void boardSizeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals(8, newGame.board.getNumberOfRow());
        Assert.assertEquals(8, newGame.board.getNumberOfCol());
    }

    @Test
    public void checkKingCapturedTest() {
        Game newGame = new Game("Bot1", "Bot2");

        Grid ori = new Grid(1, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(6, 4);
        des = new Grid(4, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(0, 3);
        des = new Grid(2, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(4, 4);
        des = new Grid(3, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        ori = new Grid(2, 3);
        des = new Grid(3, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        Grid kingPos = newGame.getPlayer1().getKingPosition();
        Vector<Grid> possibleCaptures = newGame.board.getPlayerPossibleCapture(newGame.getPlayer2());
        boolean captured = newGame.board.checkKingCaptured(possibleCaptures, kingPos);
        Assert.assertEquals(true, captured);
    }

    @Test
    public void inBoardTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(-1, 0);
        Grid des = new Grid(2, 0);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);

        ori = new Grid(1, 0);
        des = new Grid(2, 8);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);
    }

    @Test
    public void checkBoardWinFake() {
        Game newGame = new Game("Bot1", "Bot2");

        Grid ori = new Grid(1, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(6, 4);
        des = new Grid(4, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(0, 3);
        des = new Grid(2, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(4, 4);
        des = new Grid(3, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        int res = newGame.board.checkBoardStatus(newGame.player1, newGame.player2);
//        newGame.board.displayBoard();
        Assert.assertEquals(0, res);


        ori = new Grid(2, 3);
        des = new Grid(3, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();
        res = newGame.board.checkBoardStatus(newGame.player2, newGame.player1);
//        newGame.board.displayBoard();
        Assert.assertEquals(0, res);
//        System.out.println(res);
    }

    @Test
    public void checkBoardStalemate() {
        Game newGame = new Game("Bot1", "Bot2");

        King blackKing = (King)newGame.board.gameBoard[0][4];
        King whiteKing = (King)newGame.board.gameBoard[7][4];
        Queen whiteQueen = (Queen)newGame.board.gameBoard[7][3];
        for(int i=0; i<newGame.board.getNumberOfRow(); i++) {
            for(int j=0; j<newGame.board.getNumberOfCol(); j++) {
                newGame.board.gameBoard[i][j] = null;
            }
        }
        newGame.board.gameBoard[0][7] = blackKing;
        newGame.board.gameBoard[1][5] = whiteKing;
        newGame.board.gameBoard[2][6] = whiteQueen;

        blackKing.changePosition(new Grid(0, 7));
        whiteKing.changePosition(new Grid(1, 5));
        whiteQueen.changePosition(new Grid(2, 6));

//        newGame.board.displayBoard();
        int res = newGame.board.checkBoardStatus(newGame.player1, newGame.player2);
        Assert.assertEquals(2, res);
        boolean exit = newGame.exitStatus(newGame.getPlayer1(), newGame.getPlayer2(), newGame);
        Assert.assertEquals(true, exit);
    }

    @Test
    public void checkBoardWin() {
        Game newGame = new Game("Bot1", "Bot2");

        King blackKing = (King)newGame.board.gameBoard[0][4];
        King whiteKing = (King)newGame.board.gameBoard[7][4];
        Queen whiteQueen = (Queen)newGame.board.gameBoard[7][3];
        for(int i=0; i<newGame.board.getNumberOfRow(); i++) {
            for(int j=0; j<newGame.board.getNumberOfCol(); j++) {
                newGame.board.gameBoard[i][j] = null;
            }
        }
        newGame.board.gameBoard[0][7] = blackKing;
        newGame.board.gameBoard[1][5] = whiteKing;
        newGame.board.gameBoard[1][6] = whiteQueen;

        blackKing.changePosition(new Grid(0, 7));
        whiteKing.changePosition(new Grid(1, 5));
        whiteQueen.changePosition(new Grid(1, 6));

//        newGame.board.displayBoard();
        int res = newGame.board.checkBoardStatus(newGame.player1, newGame.player2);
        Assert.assertEquals(1, res);
        boolean exit = newGame.exitStatus(newGame.getPlayer1(), newGame.getPlayer2(), newGame);
        Assert.assertEquals(true, exit);
    }

    @Test
    public void checkMoveEqual() {
        Game newGame = new Game("Bot1", "Bot2");

        Grid ori = new Grid(3, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);
    }

    @Test
    public void checkMoveNull() {
        Game newGame = new Game("Bot1", "Bot2");

        Grid ori = new Grid(3, 3);
        Grid des = new Grid(6, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);
    }

    @Test
    public void gridTest() {
        String test = "(1,2)";
        Grid x = new Grid(1, 2);
        Assert.assertEquals(test, x.toString());
    }

    @Test
    public void memoryTest() {
        String test = "(1,2),(3,4),true;";
        Grid x = new Grid(1, 2);
        Grid y = new Grid(3, 4);
        Memory m = new Memory(x, y, true, true);
        Assert.assertEquals(test, m.toString());
        System.out.println(m.toString());
    }
}
