import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class KingTest {
    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals("King", newGame.board.gameBoard[0][4].type);
        Assert.assertEquals("King", newGame.board.gameBoard[7][4].type);
    }

    @Test
    public void ownerTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals(newGame.getPlayer2(), newGame.board.gameBoard[0][4].owner);
        Assert.assertEquals(newGame.getPlayer1(), newGame.board.gameBoard[7][4].owner);
    }

    @Test
    public void staticPositionTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid g = new Grid(0, 4);
        boolean res = g.compareGrid(newGame.board.gameBoard[0][4].position);
        Assert.assertEquals(true, res);
    }

    @Test
    public void moveBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(1, 4);
        Grid des = new Grid(3, 4);
        Pawn chess = (Pawn) newGame.board.gameBoard[3][4];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(1, 3);
        des = new Grid(3, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(1, 5);
        des = new Grid(3, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(0, 4);
        des = new Grid(1, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }

    @Test
    public void moveWhiteTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(6, 4);
        Grid des = new Grid(5, 4);
        Pawn chess = (Pawn) newGame.board.gameBoard[3][4];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(6, 3);
        des = new Grid(5, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(6, 5);
        des = new Grid(5, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(7, 4);
        des = new Grid(5, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);
//        newGame.board.displayBoard();

        ori = new Grid(7, 4);
        des = new Grid(6, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }

    @Test
    public void captureBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(6, 5);
        Grid des = new Grid(4, 5);
        Pawn chess = (Pawn) newGame.board.gameBoard[6][5];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(4, 5);
        des = new Grid(3, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(3, 5);
        des = new Grid(2, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(2, 5);
        des = new Grid(1, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(0, 4);
        des = new Grid(1, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }

    @Test
    public void captureWhiteTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Pawn chess = (Pawn) newGame.board.gameBoard[1][3];

        Grid ori = new Grid(1, 2);
        Grid des = new Grid(3, 2);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(3, 2);
        des = new Grid(4, 2);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(4, 2);
        des = new Grid(5, 2);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(5, 2);
        des = new Grid(6, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(7, 4);
        des = new Grid(6, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }
}
