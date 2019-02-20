import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;
import javax.swing.*;

public class GUITest {
    @Test
    public void otherTest() {
        GUI gui = new GUI("5", "6");

        Game game = gui.getGame();
        boolean g = (null != game);
        Assert.assertEquals(true, g);

        JButton exitButton = gui.exitButton();
        boolean equal = (null != exitButton);
        Assert.assertEquals(true, equal);

        JComponent component = gui.getGui();
        equal = (null == component);
        Assert.assertEquals(false, equal);

        boolean addSuccess = gui.addIndexLabel();
        Assert.assertEquals(true, addSuccess);

        gui.initializeGUI();

        gui = new GUI("5", "6");
        boolean start = gui.getStart();
        Assert.assertEquals(false, start);
        gui.setStart();
        gui.resetHelper();
        gui.setGUIOriginalColor();
        gui.clearBoard();
        start = gui.getStart();
        gui.exitHelper();
        Assert.assertEquals(true, start);
        Assert.assertEquals(2, gui.playerStringHelper().size());
        Assert.assertEquals(false, gui.exitAutomatically());
        Vector<Grid> moves = new Vector<>();
        moves.add(new Grid(0, 0));
        gui.changePossibleColor(moves, moves);
        gui.selectAction(gui.getChessBoard()[0][1]);
        gui.setStart();
        gui.selectAction(gui.getChessBoard()[0][6]);
        gui.selectAction(gui.getChessBoard()[0][4]);
        gui.selectAction(gui.getChessBoard()[3][3]);
        gui.selectAction(gui.getChessBoard()[0][6]);
        gui.selectAction(gui.getChessBoard()[0][7]);
    }

    @Test
    public void winTest() {
        GUI gui = new GUI("Bot1", "Bot2");
        Game newGame = gui.getGame();
        gui.setStart();
        King blackKing = (King)newGame.board.gameBoard[0][4];
        King whiteKing = (King)newGame.board.gameBoard[7][4];
        Queen whiteQueen = (Queen)newGame.board.gameBoard[7][3];
        for(int i=0; i<newGame.board.getNumberOfRow(); i++) {
            for(int j=0; j<newGame.board.getNumberOfCol(); j++) {
                newGame.board.gameBoard[i][j] = null;
            }
        }
        newGame.board.gameBoard[0][7] = blackKing;
        newGame.board.gameBoard[1][5] = whiteKing;
        newGame.board.gameBoard[1][6] = whiteQueen;
        newGame.board.log.add(null);
        blackKing.changePosition(new Grid(0, 7));
        whiteKing.changePosition(new Grid(1, 5));
        whiteQueen.changePosition(new Grid(1, 6));
        Assert.assertEquals(true, gui.exitAutomatically());
    }
}