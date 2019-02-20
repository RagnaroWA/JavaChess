import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class KnightTest {
    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals("Knight", newGame.board.gameBoard[0][1].type);
        Assert.assertEquals("Knight", newGame.board.gameBoard[7][6].type);
    }

    @Test
    public void ownerTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals(newGame.getPlayer2(), newGame.board.gameBoard[0][1].owner);
        Assert.assertEquals(newGame.getPlayer1(), newGame.board.gameBoard[7][6].owner);
    }

    @Test
    public void staticPositionTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid g = new Grid(7, 6);
        boolean res = g.compareGrid(newGame.board.gameBoard[7][6].position);
        Assert.assertEquals(true, res);
    }

    @Test
    public void moveBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(0, 1);
        Grid des = new Grid(2, 0);
        Knight chess = (Knight) newGame.board.gameBoard[0][1];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(2, 0);
        des = new Grid(4, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(0, 6);
        des = new Grid(2, 6);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);

        ori = new Grid(0, 6);
        des = new Grid(2, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(2, 5);
        des = new Grid(3, 7);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
    }

    @Test
    public void moveWhiteTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(7, 1);
        Grid des = new Grid(5, 1);
        Knight chess = (Knight) newGame.board.gameBoard[0][1];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);

        ori = new Grid(7, 1);
        des = new Grid(5, 0);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(5, 0);
        des = new Grid(4, 2);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(7, 6);
        des = new Grid(5, 6);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);

        ori = new Grid(7, 6);
        des = new Grid(5, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(5, 5);
        des = new Grid(4, 7);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }
}
