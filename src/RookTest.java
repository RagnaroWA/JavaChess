import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class RookTest {
    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals("Rook", newGame.board.gameBoard[0][0].type);
    }

    @Test
    public void ownerTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals(newGame.getPlayer2(), newGame.board.gameBoard[0][0].owner);
        Assert.assertEquals(newGame.getPlayer1(), newGame.board.gameBoard[7][7].owner);
    }

    @Test
    public void staticPositionTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid g = new Grid(7, 7);
        boolean res = g.compareGrid(newGame.board.gameBoard[7][7].position);
        Assert.assertEquals(true, res);
    }

    @Test
    public void moveBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(1, 0);
        Grid des = new Grid(3, 0);
        Pawn chess = (Pawn) newGame.board.gameBoard[1][0];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(0, 0);
        des = new Grid(2, 0);
        Rook chess1 = (Rook) newGame.board.gameBoard[0][0];
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(2, 0);
        des = new Grid(2, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(2, 4);
        des = new Grid(3, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(3, 4);
        des = new Grid(4, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
    }

    @Test
    public void moveWhiteTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(6, 0);
        Grid des = new Grid(4, 0);
        Pawn chess = (Pawn) newGame.board.gameBoard[6][0];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(7, 0);
        des = new Grid(5, 0);
        Rook chess1 = (Rook) newGame.board.gameBoard[7][0];
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(5, 0);
        des = new Grid(5, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(5, 4);
        des = new Grid(3, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(3, 4);
        des = new Grid(2, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }

    @Test
    public void captureBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(1, 0);
        Grid des = new Grid(3, 0);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Pawn chess = (Pawn) newGame.board.gameBoard[6][4];
        Rook chess1 = (Rook) newGame.board.gameBoard[0][0];

        ori = new Grid(0, 0);
        des = new Grid(2, 0);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(2, 0);
        des = new Grid(2, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(2, 4);
        des = new Grid(3, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(3, 4);
        des = new Grid(6, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(chess, newGame.board.capturedChess.get(0));

        Vector<Grid> compareVec = new Vector<>();
        compareVec.add(new Grid(6, 5));
        compareVec.add(new Grid(6, 3));
        Assert.assertEquals(true, compareVec.get(0).compareGrid(chess1.rightCheck(6, 4, false).get(0)));
        Assert.assertEquals(true, compareVec.get(1).compareGrid(chess1.leftCheck(6, 4, false).get(0)));
        Assert.assertEquals(1, newGame.getPlayer2().getScore());

        ori = new Grid(6, 4);
        des = new Grid(7, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();
    }

    @Test
    public void captureWhiteTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(6, 0);
        Grid des = new Grid(4, 0);
        Pawn chess = (Pawn) newGame.board.gameBoard[1][4];
        Rook chess1 = (Rook) newGame.board.gameBoard[7][0];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(7, 0);
        des = new Grid(5, 0);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(5, 0);
        des = new Grid(5, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(5, 4);
        des = new Grid(2, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(2, 4);
        des = new Grid(1, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(1, newGame.getPlayer1().getScore());
        Assert.assertEquals(chess, newGame.board.capturedChess.get(0));
        Vector<Grid> compareVec = new Vector<>();
        compareVec.add(new Grid(1, 5));
        compareVec.add(new Grid(1, 3));
        Assert.assertEquals(true, compareVec.get(0).compareGrid(chess1.rightCheck(1, 4, false).get(0)));
        Assert.assertEquals(true, compareVec.get(1).compareGrid(chess1.leftCheck(1, 4, false).get(0)));
//        newGame.board.displayBoard();
    }
}
