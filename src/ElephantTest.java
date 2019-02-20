import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class ElephantTest {
    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Elephant e = new Elephant("E", 5, 1, newGame.board, newGame.player1);
        Assert.assertEquals("Elephant", e.getType());
        Elephant e1 = new Elephant("E", 5, 1, newGame.board, newGame.player2);
        Assert.assertEquals("Elephant", e1.getType());
    }

    @Test
    public void movesTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Elephant e1 = new Elephant("E", 5, 1, newGame.board, newGame.player2);
        newGame.board.gameBoard[5][1] = e1;
        Assert.assertEquals(1, e1.possibleMove().size());
    }

    @Test
    public void capturesTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Elephant e1 = new Elephant("E", 5, 1, newGame.board, newGame.player2);
        newGame.board.gameBoard[5][1] = e1;
        Assert.assertEquals(1, e1.possibleCapture().size());
    }
}
