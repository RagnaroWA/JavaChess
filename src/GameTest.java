import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

public class GameTest {
    @Test
    public void parseInputTest() {
        Game newGame = new Game("Bot1", "Bot2");
        boolean compare = newGame.parseInput("1,2").compareGrid(new Grid(1, 2));
        Assert.assertEquals(true, compare);
    }

    @Test
    public void userNameTest() {
        Game newGame = new Game("Bot1", "Bot2");
        boolean compare = newGame.getPlayer1().getName().equals("Bot1");
        Assert.assertEquals(true, compare);
    }

    @Test
    public void exitGameNullCheck() {
        Game newGame = new Game("Bot1", "Bot2");
        boolean exit = newGame.exitGameInsideNullCheck(newGame, new Grid(3,0), new Grid(9,0));
        Assert.assertEquals(true, exit);

        exit = newGame.exitGameInsideNullCheck(newGame, new Grid(1,1), new Grid(3,1));
        Assert.assertEquals(false, exit);
    }
}
