import java.util.Map;
import java.util.Vector;
import java.util.Scanner;

public class Game {
     public Board board;
     public User player1;
     public User player2;

    /**
     * @param user1
     * @param user2
     */
    public Game(String user1, String user2) {
        this.player1 = new User(user1, "white");
        this.player2 = new User(user2, "black");
        this.board = new Board(8, 8, this.player1, this.player2);
        this.player1.referenceBoardToUser(this.board);
        this.player2.referenceBoardToUser(this.board);
    }

    /**
     * @return the first player (white)
     */
    public User getPlayer1() {
        return this.player1;
    }

    /**
     * @return the second player (black)
     */
    public User getPlayer2() {
        return this.player2;
    }

    /**
     * @param s
     * @return Grid from the user input
     */
    public Grid parseInput(String s) {
        int x = Character.getNumericValue(s.charAt(0));
        int y = Character.getNumericValue(s.charAt(2));
        Grid g = new Grid(x, y);
        return g;
    }

//    /**
//     * @param newGame
//     * @param p
//     * @param o
//     * @return boolean whether user p will win or in stalemate
//     */
//    public boolean exitGame(Game newGame, User p, User o) {
//        boolean exit = false;
//        boolean moved = false;
//        while(moved == false) {
//            User player = p;
//            System.out.println("It's " + p.getName() + " turn:");
//            User opponent = o;
//            Scanner reader = new Scanner(System.in);
//            String input1 = reader.next();
//            Grid g1 = newGame.parseInput(input1);
//            String input2 = reader.next();
//            Grid g2 = newGame.parseInput(input2);
//            boolean none = exitGameInsideNullCheck(newGame, g1, g2);
//            if(none == false) {
//                if (newGame.board.gameBoard[g1.getX()][g1.getY()].getOwner() == p) {
//                    boolean move = newGame.board.moveChessPieceByCheck(g1, g2);
//                    if (move == true) {
//                        Vector<Grid> captureActions = newGame.board.getPlayerPossibleCapture(opponent);
//                        Grid playKingPos = player.getKingPosition();
//                        boolean checked = newGame.board.checkKingCaptured(captureActions, playKingPos);
//                        if (checked == false) {
//                            moved = true;
//                        }
//                    } else {
//                        moved = false;
//                        System.out.println("Invalid move.");
//                    }
//                    exit = exitStatus(player, opponent, newGame);
//                    if(exit == true) {
//                        break;
//                    }
//                }
//                else {
//                    System.out.println("Not polite to move others' chess!");
//                }
//            }
//            else {
//                System.out.println("Please try again!");
//            }
//        }
//        return exit;
//    }

    /**
     * Check exit for the ori and des grids
     * @param newGame
     * @param g1
     * @param g2
     * @return true if need to exit
     */
    public boolean exitGameInsideNullCheck(Game newGame, Grid g1, Grid g2) {
        if(newGame.board.checkInsideBoard(g1.getX(), g1.getY(), g2.getX(), g2.getY()) == false || newGame.board.gameBoard[g1.getX()][g1.getY()] == null) {
            return true;
        }
        return false;
    }

    /**
     * Helper function to check whether exit the game
     * @param player
     * @param opponent
     * @param newGame
     * @return true if need to exit
     */
    public boolean exitStatus(User player, User opponent, Game newGame) {
        boolean exit = false;
        int status = newGame.board.checkBoardStatus(player, opponent);
        if (status == 1) {
            exit = true;
            System.out.println(player.getColor() + " wins!");
        } else if (status == 2) {
            System.out.println("Stalemate!");
            exit = true;
        }
        return exit;
    }

//    /**
//     * @param args
//     * Main function to run the game
//     */
//    public static void main(String[] args) {
//        Game newGame = new Game("Zecheng", "Bot1");
//        newGame.board.displayBoard();
//        int i = 0;
//        while(i < 3) {
//            boolean e1 = newGame.exitGame(newGame, newGame.player1, newGame.player2);
//            if(e1 == true) {
//                break;
//            }
//            else {
//                newGame.board.displayBoard();
//            }
//            boolean e2 = newGame.exitGame(newGame, newGame.player2, newGame.player1);
//            if(e2 == true) {
//                break;
//            }
//            else {
//                newGame.board.displayBoard();
//            }
//            i++;
//        }
//    }
}