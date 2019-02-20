import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class QueenTest {
    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals("Queen", newGame.board.gameBoard[0][3].type);
    }

    @Test
    public void ownerTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals(newGame.getPlayer2(), newGame.board.gameBoard[0][3].owner);
        Assert.assertEquals(newGame.getPlayer1(), newGame.board.gameBoard[7][3].owner);
    }

    @Test
    public void staticPositionTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid g = new Grid(0, 3);
        boolean res = g.compareGrid(newGame.board.gameBoard[0][3].position);
        Assert.assertEquals(true, res);
    }

    @Test
    public void moveBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Pawn p = (Pawn) newGame.board.gameBoard[1][3];
        Grid ori = new Grid(1, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        Queen queen = (Queen) newGame.board.gameBoard[0][3];
        ori = new Grid(0, 3);
        des = new Grid(2, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(2, 3);
        des = new Grid(4, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(4, 1);
        des = new Grid(5, 2);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }

    @Test
    public void captureBlackTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(1, 3);
        Grid des = new Grid(3, 3);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Pawn p = (Pawn) newGame.board.gameBoard[1][3];

        Queen queen = (Queen) newGame.board.gameBoard[0][3];
        ori = new Grid(0, 3);
        des = new Grid(2, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        ori = new Grid(2, 3);
        des = new Grid(4, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);
//        newGame.board.displayBoard();

        ori = new Grid(4, 1);
        des = new Grid(6, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();

        ori = new Grid(7, 3);
        des = new Grid(6, 3);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
    }
}
