import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class ChessPieceTest {
    @Test
    public void getChessPosTest() {
        Game newGame = new Game("Bot1", "Bot2");
        ChessPiece chess = newGame.board.gameBoard[7][1];
        Assert.assertEquals(true, chess.getPosition().compareGrid(new Grid(7, 1)));
    }

    @Test
    public void getOwnerTest() {
        Game newGame = new Game("Bot1", "Bot2");
        ChessPiece chess = newGame.board.gameBoard[1][1];
        Assert.assertEquals(newGame.getPlayer2(), chess.getOwner());
    }

    @Test
    public void getTypeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        ChessPiece chess = newGame.board.gameBoard[1][1];
        Assert.assertEquals("Pawn", chess.getType());
    }

    @Test
    public void getIDTest() {
        Game newGame = new Game("Bot1", "Bot2");
        ChessPiece chess = newGame.board.gameBoard[1][1];
        Assert.assertEquals("BP1", chess.getID());
    }

    @Test
    public void changePositionTest() {
        Game newGame = new Game("Bot1", "Bot2");
        ChessPiece chess = newGame.board.gameBoard[1][1];
        chess.changePosition(new Grid(3,3));
        Assert.assertEquals(true, chess.getPosition().compareGrid(new Grid(3,3)));
    }

    @Test
    public void inBoundaryTest() {
        Game newGame = new Game("Bot1", "Bot2");
        ChessPiece chess = newGame.board.gameBoard[1][1];
        Assert.assertEquals(false, chess.inBoundary(-1,0));
        Assert.assertEquals(false, chess.inBoundary(8,0));
        Assert.assertEquals(false, chess.inBoundary(0,-1));
        Assert.assertEquals(false, chess.inBoundary(3,8));
    }
}