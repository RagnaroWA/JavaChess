import org.junit.jupiter.api.Test;
import org.junit.Assert;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PawnTest {

    @Test
    public void chessPieceTypeTest() {
        ChessPiece c = new ChessPiece("test", 1, 1, null);
        Assert.assertEquals("Chess", c.getType());
        Assert.assertEquals(null, c.getOwner());
        Assert.assertEquals("test", c.getID());
        Assert.assertEquals(0, c.possibleMove().size());
        Assert.assertEquals(0, c.possibleCapture().size());
    }

    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals("Pawn", newGame.board.gameBoard[1][1].type);
    }

    @Test
    public void ownerTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Assert.assertEquals(newGame.getPlayer2(), newGame.board.gameBoard[1][1].owner);
    }

    @Test
    public void staticPositionTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid g = new Grid(6, 6);
        boolean res = g.compareGrid(newGame.board.gameBoard[6][6].position);
        Assert.assertEquals(true, res);
    }

    @Test
    public void possibleFirstMoveTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Vector<Grid> moves = newGame.board.gameBoard[6][3].possibleMove();
        int count = 0;
        Vector<Grid> possibleMoves = new Vector<>();
        possibleMoves.add(new Grid(4, 3));
        possibleMoves.add(new Grid(5, 3));
        for (int i = 0; i < possibleMoves.size(); i++) {
            for (int j = 0; j < moves.size(); j++) {
                if (possibleMoves.get(i).compareGrid(moves.get(j))) {
                    count++;
                }
            }
        }
        Assert.assertEquals(2, count);
        Assert.assertEquals(2, possibleMoves.size());
    }

    @Test
    public void moveTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Grid ori = new Grid(1, 1);
        Grid des = new Grid(2, 1);
        Pawn chess = (Pawn) newGame.board.gameBoard[1][1];
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
        Assert.assertEquals(null, newGame.board.gameBoard[1][1]);
        Assert.assertEquals(chess, newGame.board.gameBoard[2][1]);
        Assert.assertEquals(false, chess.checkFirstMove());

        ori = new Grid(6, 1);
        des = new Grid(4, 1);
        Pawn chess1 = (Pawn) newGame.board.gameBoard[6][1];
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
        Assert.assertEquals(null, newGame.board.gameBoard[6][1]);
        Assert.assertEquals(chess1, newGame.board.gameBoard[4][1]);

        ori = new Grid(2, 1);
        des = new Grid(3, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
        Assert.assertEquals(null, newGame.board.gameBoard[2][1]);
        Assert.assertEquals(chess, newGame.board.gameBoard[3][1]);
    }

    @Test
    public void captureTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Pawn chess = (Pawn) newGame.board.gameBoard[1][1];
        Pawn chess1 = (Pawn) newGame.board.gameBoard[6][2];


        Grid ori = new Grid(1, 1);
        Grid des = new Grid(2, 1);
        boolean move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(6, 1);
        des = new Grid(4, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(2, 1);
        des = new Grid(3, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);

        ori = new Grid(6, 2);
        des = new Grid(4, 2);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);

        ori = new Grid(4, 2);
        des = new Grid(3, 1);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(true, move);
//        newGame.board.displayBoard();
//        Assert.assertEquals(true, chess.checkChessCaptured());
        Assert.assertEquals(chess1, newGame.board.gameBoard[3][1]);
        Assert.assertEquals(newGame.board.getUser1().getColor(), newGame.board.gameBoard[3][1].getOwner().getColor());
        Assert.assertEquals(newGame.board.getUser2().getColor(), newGame.board.gameBoard[1][0].getOwner().getColor());

        ori = new Grid(4, 1);
        des = new Grid(3, 0);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);

        ori = new Grid(4, 1);
        des = new Grid(3, 2);
        move = newGame.board.moveChessPieceByCheck(ori, des);
        Assert.assertEquals(false, move);
        Assert.assertEquals(chess, newGame.board.capturedChess.get(0));
    }
}
