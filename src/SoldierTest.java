import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class SoldierTest {
    @Test
    public void typeTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Soldier s = new Soldier("S", 5, 1, newGame.board, newGame.player1);
        Assert.assertEquals("Soldier", s.getType());
        Soldier s1 = new Soldier("S", 5, 1, newGame.board, newGame.player2);
        Assert.assertEquals("Soldier", s1.getType());
    }

    @Test
    public void xTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Soldier s1 = new Soldier("S", 5, 1, newGame.board, newGame.player1);
        Assert.assertEquals(0, s1.newX(1));
        Soldier s2 = new Soldier("S", 5, 1, newGame.board, newGame.player2);
        Assert.assertEquals(2, s2.newX(1));
    }

    @Test
    public void movesTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Soldier s1 = new Soldier("S", 5, 1, newGame.board, newGame.player1);
        newGame.board.gameBoard[5][1] = s1;
        Assert.assertEquals(1, s1.possibleMove().size());
    }

    @Test
    public void capturesTest() {
        Game newGame = new Game("Bot1", "Bot2");
        Soldier s1 = new Soldier("S", 5, 1, newGame.board, newGame.player1);
        newGame.board.gameBoard[5][1] = s1;
        Assert.assertEquals(0, s1.possibleCapture().size());
    }
}
