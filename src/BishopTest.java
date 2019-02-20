import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class BishopTest {
    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals("Bishop", newGame.board.gameBoard[0][2].type);
    }

    @Test
    public void ownerTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals(newGame.getPlayer2(), newGame.board.gameBoard[0][2].owner);
        Assert.assertEquals(newGame.getPlayer1(), newGame.board.gameBoard[7][5].owner);
    }

    @Test
    public void staticPositionTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid g = new Grid(7, 5);
        boolean res = g.compareGrid(newGame.board.gameBoard[7][5].position);
        Assert.assertEquals(true, res);
    }

    @Test
    public void moveBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(1, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(0, 2);
        des = new Grid(3, 5);
        Bishop chess = (Bishop) newGame.board.gameBoard[0][2];
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(3, 5);
        des = new Grid(4, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(4, 4);
        des = new Grid(3, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);

        ori = new Grid(4, 4);
        des = new Grid(5, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }

    @Test
    public void captureBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Pawn p = (Pawn) newGame.board.gameBoard[6][6];
        Grid ori = new Grid(1, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Bishop b = (Bishop) newGame.board.gameBoard[0][2];

        ori = new Grid(0, 2);
        des = new Grid(3, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(3, 5);
        des = new Grid(4, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        ori = new Grid(4, 4);
        des = new Grid(6, 6);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
        Assert.assertEquals(b, newGame.board.gameBoard[6][6]);
        Assert.assertEquals("black", newGame.board.gameBoard[6][6].getOwner().getColor());
        Assert.assertEquals("Bishop", newGame.board.gameBoard[6][6].getType());
        Assert.assertEquals("BB0", newGame.board.gameBoard[6][6].getID());
        Assert.assertEquals(p, newGame.board.capturedChess.get(0));
//        newGame.board.displayBoard();

        Bishop b2 = (Bishop) newGame.board.gameBoard[7][5];
        ori = new Grid(6, 6);
        des = new Grid(7, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
        Assert.assertEquals(b, newGame.board.gameBoard[7][5]);
        Assert.assertEquals("black", newGame.board.gameBoard[7][5].getOwner().getColor());
        Assert.assertEquals("Bishop", newGame.board.gameBoard[7][5].getType());
        Assert.assertEquals("BB0", newGame.board.gameBoard[7][5].getID());
        Assert.assertEquals(b2, newGame.board.capturedChess.get(1));
//        newGame.board.displayBoard();
    }

    @Test
    public void moveWhiteTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(6, 4);
        Grid des = new Grid(4, 4);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(7, 5);
        des = new Grid(4, 2);
        Bishop chess = (Bishop) newGame.board.gameBoard[7][5];
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(4, 2);
        des = new Grid(3, 7);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);

        ori = new Grid(4, 2);
        des = new Grid(3, 1);
        boolean move1 = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move1);
//        newGame.board.displayBoard();
    }

    @Test
    public void captureWhiteTest() {
        Game newGame = new Game("Bot1", "Bot2");

        Bishop b = (Bishop) newGame.board.gameBoard[7][5];
        Pawn p = (Pawn) newGame.board.gameBoard[1][3];
        Pawn p2 = (Pawn) newGame.board.gameBoard[1][5];
        Grid ori = new Grid(1, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        ori = new Grid(6, 4);
        des = new Grid(4, 4);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        ori = new Grid(7, 5);
        des = new Grid(4, 2);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        ori = new Grid(4, 2);
        des = new Grid(3, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
        Assert.assertEquals(b, newGame.board.gameBoard[3][3]);
        Assert.assertEquals("white", newGame.board.gameBoard[3][3].getOwner().getColor());
        Assert.assertEquals("Bishop", newGame.board.gameBoard[3][3].getType());
        Assert.assertEquals("WB1", newGame.board.gameBoard[3][3].getID());
        Assert.assertEquals(p, newGame.board.capturedChess.get(0));
//        newGame.board.displayBoard();

        ori = new Grid(3, 3);
        des = new Grid(1, 5);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
        Assert.assertEquals(b, newGame.board.gameBoard[1][5]);
        Assert.assertEquals(null, newGame.board.gameBoard[3][3]);
        Assert.assertEquals("white", newGame.board.gameBoard[1][5].getOwner().getColor());
        Assert.assertEquals("Bishop", newGame.board.gameBoard[1][5].getType());
        Assert.assertEquals("WB1", newGame.board.gameBoard[1][5].getID());
        Assert.assertEquals(p2, newGame.board.capturedChess.get(1));
//        newGame.board.displayBoard();
    }
}
