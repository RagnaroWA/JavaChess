import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.util.*;

// reference https://stackoverflow.com/questions/21142686/making-a-robust-resizable-swing-chess-gui
// reference https://stackoverflow.com/questions/13662618/how-to-add-text-to-jframe


public class GUI {
    private JPanel GUIPanel;
    private Game game;
    private JButton[][] chessBoard;
    private JPanel gameBoard;
    private Grid guiOri;
    private Grid guiDes;
    private int score1 = 0;
    private int score2 = 0;
    private String player1;
    private String player2;
    private JLabel scoreLabel1;
    private JLabel scoreLabel2;
    private JLabel turnLabel;
    private boolean start = false;

    /**
     * Constructor for the GUI
     * @param player1
     * @param player2
     */
    public GUI(String player1, String player2) {
        player1 = JOptionPane.showInputDialog(null, "Player 1 (white) name?");
        player2 = JOptionPane.showInputDialog(null, "Player 2 (black) name?");
        this.game = new Game(player1, player2);
        this.player1 = player1;
        this.player2 = player2;
        scoreLabel1 = new JLabel(this.player1+":"+"0");
        scoreLabel2 = new JLabel(this.player2+":"+"0");
        initializeHelper();
        Color backgroundGrey = new Color(58, 54, 49, 254);
        Insets buttonMargin = new Insets(10, 10, 10, 10);
        for (int i = 0; i < this.game.board.getNumberOfRow(); i++) {
            for (int j = 0; j < this.game.board.getNumberOfCol(); j++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB));
                button.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    button.setOpaque(true);
                    button.setBackground(Color.WHITE);
                    button.putClientProperty("color", Color.WHITE);
                    button.setBorderPainted(false);
                } else {
                    button.setOpaque(true);
                    button.setBackground(Color.DARK_GRAY);
                    button.putClientProperty("color", backgroundGrey);
                    button.setBorderPainted(false);
                }
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectAction(button);
                    }
                });
                chessBoard[j][i] = button;
            }
        }
        addIndexLabel();
    }

    /**
     * helper function to constructor
     */
    public void initializeHelper() {
        turnLabel = new JLabel("White turn");
        initializeGUI();
        this.gameBoard = new JPanel(new GridLayout(10, 9));
        Color backgroundColor = new Color(153, 68, 3);
        this.gameBoard.setBackground(backgroundColor);
        GUIPanel.add(this.gameBoard);
        initializeToolBar();
    }

    /**
     * Change the color of the squares to the possible moves or captures color yellow
     * @param moves
     * @param captures
     */
    public void changePossibleColor(Vector<Grid> moves, Vector<Grid>captures) {
        changePossibleColorHelper(moves);
        changePossibleColorHelper(captures);
    }

    /**
     * changePossibleColor helper function
     * @param tempVector
     */
    public void changePossibleColorHelper(Vector<Grid> tempVector) {
        for(int i=0; i<tempVector.size(); i++) {
            int guiTempX = tempVector.get(i).getX();
            int guiTempY = tempVector.get(i).getY();
            this.chessBoard[guiTempY][guiTempX].setBackground(Color.YELLOW);
        }
    }

    /**
     * Perform actions for board for the button
     * @param button
     */
    public void selectAction(JButton button) {
        if(start == false) {
            return;
        }
        if(this.guiOri == null) {
            boolean whetherRestart = exitAutomatically();
            if(whetherRestart == true) {
                exitHelper();
                return;
            }
            this.guiOri = (Grid) button.getClientProperty("position");
            int gui_x = this.guiOri.getX();
            int gui_y = this.guiOri.getY();
            ChessPiece chess = this.game.board.gameBoard[gui_x][gui_y];
            if(chess != null) {
                int turnValue = 0;
                if(chess.getOwner().getColor().equals("black")) {
                    turnValue = 1;
                }
                if(this.game.board.log.size() % 2 == turnValue) {
                    button.setBackground(Color.GREEN);
                    Vector<Grid> guiMoves = this.game.board.gameBoard[gui_x][gui_y].possibleMove();
                    Vector<Grid> guiCaptures = this.game.board.gameBoard[gui_x][gui_y].possibleCapture();
                    changePossibleColor(guiMoves, guiCaptures);
                }
                else {
                    this.guiOri = null;
                    JOptionPane.showMessageDialog(null, "You can't move others' chess!","Message", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                this.guiOri = null;
                JOptionPane.showMessageDialog(null, "You can't move empty!","Message", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            this.guiDes = (Grid) button.getClientProperty("position");
            boolean moved = this.game.board.moveChessPieceByCheck(this.guiOri, this.guiDes);
            if(moved == true) {
                clearHelper();
                this.guiOri = null;
                this.guiDes = null;
                clearHelper();
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid move!","Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void clearHelper() {
        clearBoard();
        setGUIOriginalColor();
        disPlayBoardToGUI();
    }

    /**
     * exit helper function
     */
    public void exitHelper() {
        this.game = new Game(this.player1, this.player2);
        clearBoard();
        setGUIOriginalColor();
        disPlayBoardToGUI();
    }

    /**
     * Whether to end the current game
     * @return true if need to end the current game
     */
    public boolean exitAutomatically() {
        int res1 = 0;
        int res2 = 0;
        res1 = this.game.board.checkBoardStatus(this.game.player1, this.game.player2);
        res2 = this.game.board.checkBoardStatus(this.game.player2, this.game.player1);
        if(res1 == 2 || res2 == 2) {
            JOptionPane.showMessageDialog(null, "Stalemate detected!","Message", JOptionPane.PLAIN_MESSAGE);
            resetHelper();
            return true;
        }
        if(res1 == 1) {
            JOptionPane.showMessageDialog(null, this.player1+" wins!","Message", JOptionPane.PLAIN_MESSAGE);
            this.score1 += 1;
            this.scoreLabel1.setText(player1+":"+score1);
            resetHelper();
            disPlayBoardToGUI();
            return true;
        }
        if(res2 == 1) {
            JOptionPane.showMessageDialog(null, this.player2+" wins!","Message", JOptionPane.PLAIN_MESSAGE);
            this.score2 += 1;
            this.scoreLabel2.setText(player2+":"+score2);
            resetHelper();
            disPlayBoardToGUI();
            return true;
        }
        return false;
    }

    /**
     * Clear board, set to original color, then display the board
     */
    public void resetHelper() {
        clearBoard();
        setGUIOriginalColor();
        clearBoard();
        setGUIOriginalColor();
        disPlayBoardToGUI();
    }

    /**
     * Set the board to original color
     */
    public void setGUIOriginalColor() {
        for(int i=0; i<this.game.board.getNumberOfRow(); i++) {
            for(int j=0; j<this.game.board.getNumberOfCol(); j++) {
                Color c = (Color) chessBoard[j][i].getClientProperty("color");
                chessBoard[j][i].setBackground(c);
            }
        }
    }

    /**
     * Clear the icons on the board
     */
    public void clearBoard(){
        for (int i = 0; i < this.game.board.getNumberOfRow(); i++){
            for (int j = 0; j < this.game.board.getNumberOfCol(); j++){
                chessBoard[i][j].setIcon(null);
            }
        }
    }

    /**
     * Initialize the tool bar
     */
    public void initializeToolBar() {
        JToolBar bar = new JToolBar();
        bar.setFloatable(false);
        this.GUIPanel.add(bar, BorderLayout.PAGE_START);
        bar.add(startButton());
        bar.add(undoButton());
        bar.add(restartButton());
        bar.add(forfeitButton());
        bar.add(exitButton());
    }

    /**
     * Initialize the startButton with its ActionListener
     * @return the startButton with its ActionListener
     */
    public JButton startButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start = true;
                game = new Game(player1, player2);
                disPlayBoardToGUI();
                disPlayBoardToGUI();
            }
        });
        return startButton;
    }

    /**
     * Initialize the forfeitButton with its ActionListener
     * @return the forfeitButton with its ActionListener
     */
    public JButton forfeitButton() {
        JButton forfeitButton = new JButton("Forfeit");
        forfeitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.board.log.size() % 2 == 0) {
                    score2 += 1;
                }
                else {
                    score1 += 1;
                }
                scoreLabel1.setText(player1+":"+score1);
                scoreLabel2.setText(player2+":"+score2);
                clearBoard();
                game = new Game(player1, player2);
                disPlayBoardToGUI();
                disPlayBoardToGUI();
            }
        });
        return forfeitButton;
    }

    /**
     * Initialize the restartButton with its ActionListener
     * @return the restartButton with its ActionListener
     */
    public JButton restartButton() {
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> players = playerStringHelper();
                String requestPlayer = players.get(0);
                String acceptPlayer = players.get(1);
                Object[] options = {"Reject", "Restart"};
                JPanel restartPanel = new JPanel();
                restartPanel.add(new JLabel("Hi "+acceptPlayer+", "+requestPlayer+" wants to restart the game."));
                int result = JOptionPane.showOptionDialog(null, restartPanel, "Restart Request",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                if(result == 1) {
                    clearBoard();
                    game = new Game(player1, player2);
                    clearBoard();
                    clearBoard();
                    disPlayBoardToGUI();
                    guiOri = null;
                    guiDes = null;
                    disPlayBoardToGUI();
                }
                else {
                    String errorDisplay = acceptPlayer + " doesn't agree with the restart!";
                    JOptionPane.showMessageDialog(null, errorDisplay,"Message", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        return restartButton;
    }

    public Vector<String> playerStringHelper() {
        String requestPlayer = player1;
        String acceptPlayer = player2;
        if(game.board.log.size() % 2 == 1) {
            requestPlayer = player2;
            acceptPlayer = player1;
        }
        Vector<String> res = new Vector<>();
        res.add(requestPlayer);
        res.add(acceptPlayer);
        return res;
    }

    /**
     * Initialize the exitButton with its ActionListener
     * @return the exitButton with its ActionListener
     */
    public JButton exitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> players = playerStringHelper();
                String requestPlayer = players.get(0);
                String acceptPlayer = players.get(1);
                Object[] options = {"Reject", "Accept"};
                JPanel restartPanel = new JPanel();
                restartPanel.add(new JLabel("Hi "+acceptPlayer+", "+requestPlayer+" wants to exit the game."));
                int result = JOptionPane.showOptionDialog(null, restartPanel, "Exit Request",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                if(result == 1) {
                    System.exit(0);
                }
                else {
                    String errorDisplay = acceptPlayer + " doesn't agree with the exit!";
                    JOptionPane.showMessageDialog(null, errorDisplay,"Message", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        return exitButton;
    }

    /**
     * Initialize the undoButton with its ActionListener
     * @return the undoButton with its ActionListener
     */
    public JButton undoButton() {
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.board.log.size() > 0) {
                    game.board.undoOneAction();
                    if (game.board.log.size() % 2 == 0) {
                        turnLabel.setText("White turn");
                    } else {
                        turnLabel.setText("Black turn");
                    }
                    clearBoard();
                    clearBoard();
                    disPlayBoardToGUI();
                }
            }
        });
        return undoButton;
    }

    /**
     * Initialize the GUI
     * @return true if initialize sucessfully
     */
    public void initializeGUI() {
        this.GUIPanel = new JPanel(new BorderLayout(0, 0));
        this.chessBoard = new JButton[8][8];
        GUIPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        GUIPanel.add(tools, BorderLayout.PAGE_START);
        GUIPanel.add(new JLabel(""), BorderLayout.LINE_START);
    }

    /**
     * Function to return the gui component
     * @return the gui component
     */
    public final JComponent getGui() {
        return this.GUIPanel;
    }

    /**
     * Add the index label
     * @return true if the index label is added successfully
     */
    public boolean addIndexLabel() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0){
                    this.gameBoard.add(new JLabel((9-(i + 1))+"", SwingConstants.CENTER));
                }
                this.gameBoard.add(chessBoard[j][i]);
            }
        }

        this.gameBoard.add(new JLabel(""));
        this.gameBoard.add(new JLabel("A", SwingConstants.CENTER));
        this.gameBoard.add(new JLabel("B", SwingConstants.CENTER));
        this.gameBoard.add(new JLabel("C", SwingConstants.CENTER));
        this.gameBoard.add(new JLabel("D", SwingConstants.CENTER));
        this.gameBoard.add(new JLabel("E", SwingConstants.CENTER));
        this.gameBoard.add(new JLabel("F", SwingConstants.CENTER));
        this.gameBoard.add(new JLabel("G", SwingConstants.CENTER));
        this.gameBoard.add(new JLabel("H", SwingConstants.CENTER));
        this.gameBoard.add(scoreLabel1);
        this.gameBoard.add(scoreLabel2);
        this.gameBoard.add(turnLabel);
        return true;
    }

    /**
     * Display the board to the GUI
     */
    private void disPlayBoardToGUI(){
        if(this.game.board.log.size() % 2 == 0) {
            turnLabel.setText("White turn");
        }
        else {
            turnLabel.setText("Black turn");
        }
        for (int i = 0; i < this.game.board.getNumberOfRow(); i++){
            for (int j = 0; j < this.game.board.getNumberOfCol(); j++){
                if(this.game.board.gameBoard[i][j] != null) {
                    ChessPiece targetPiece = this.game.board.gameBoard[i][j];
                    Image img = targetPiece.getChessImage();
                    Image newImg = img.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
                    chessBoard[j][i].setIcon(new ImageIcon(newImg));
                    chessBoard[j][i].setBorder(BorderFactory.createEmptyBorder());
                    chessBoard[j][i].putClientProperty("ChessPiece", targetPiece);
                }
                chessBoard[j][i].putClientProperty("position", new Grid(i, j));
            }
        }
    }

    /**
     * Get the game object of the GUI
     * @return the game object
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Set the start in the GUI to true
     */
    public void setStart() {
        this.start = true;
    }

    /**
     * Get start of GUI
     * @return true if has been started
     */
    public boolean getStart() {
        return this.start;
    }

    public JButton[][] getChessBoard() {
        return this.chessBoard;
    }

    public static void main(String[] args) {
        GUI gui = new GUI("Bot1", "Bot2");

//        GUI gui = new GUI("Bot1", "Bot2");
//        Game newGame = gui.getGame();
//        gui.setStart();
//        King blackKing = (King)newGame.board.gameBoard[0][4];
//        King whiteKing = (King)newGame.board.gameBoard[7][4];
//        Queen whiteQueen = (Queen)newGame.board.gameBoard[7][3];
//        for(int i=0; i<newGame.board.getNumberOfRow(); i++) {
//            for(int j=0; j<newGame.board.getNumberOfCol(); j++) {
//                newGame.board.gameBoard[i][j] = null;
//            }
//        }
//        newGame.board.gameBoard[0][7] = blackKing;
//        newGame.board.gameBoard[1][5] = whiteKing;
//        newGame.board.gameBoard[2][6] = whiteQueen;
//        newGame.board.log.add(null);
//        blackKing.changePosition(new Grid(0, 7));
//        whiteKing.changePosition(new Grid(1, 5));
//        whiteQueen.changePosition(new Grid(2, 6));
////        newGame.board.displayBoard();
//        gui.disPlayBoardToGUI();

//        GUI gui = new GUI("Bot1", "Bot2");
//        Game newGame = gui.getGame();
//        gui.setStart();
//        King blackKing = (King)newGame.board.gameBoard[0][4];
//        King whiteKing = (King)newGame.board.gameBoard[7][4];
//        Queen whiteQueen = (Queen)newGame.board.gameBoard[7][3];
//        for(int i=0; i<newGame.board.getNumberOfRow(); i++) {
//            for(int j=0; j<newGame.board.getNumberOfCol(); j++) {
//                newGame.board.gameBoard[i][j] = null;
//            }
//        }
//        newGame.board.gameBoard[0][7] = blackKing;
//        newGame.board.gameBoard[1][5] = whiteKing;
//        newGame.board.gameBoard[1][6] = whiteQueen;
//        newGame.board.log.add(null);
//        blackKing.changePosition(new Grid(0, 7));
//        whiteKing.changePosition(new Grid(1, 5));
//        whiteQueen.changePosition(new Grid(1, 6));
////        newGame.board.displayBoard();
//        gui.disPlayBoardToGUI();

        JFrame frame = new JFrame("Chess Game");
        frame.add(gui.getGui());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setPreferredSize(new Dimension(700,700));
        frame.pack();
        frame.setVisible(true);
    }
}
