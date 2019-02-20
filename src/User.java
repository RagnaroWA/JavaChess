import java.util.*;

public class User {

    public String name;
    public int score;
    public String color;
    public Board board;
    public King king;

    /**
     * constructor
     * @param username
     * @param color
     */
    public User(String username, String color) {
        this.name = username;
        this.color = color;
        this.score = 0;
    }

    /**
     * @param b
     * Reference a board to this user
     */
    public void referenceBoardToUser(Board b) {
        this.board = b;
    }

    /**
     * Increase the user's score
     */
    public void increaseScore() {
        this.score += 1;
    }

    /**
     * @return get the score for one user
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @return get the color of the user
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @param k
     * Reference the king object to this user
     */
    public void setKing(King k) {
        this.king = k;
    }

    /**
     * @return get the position of user's king
     */
    public Grid getKingPosition() {
        return this.king.getPosition();
    }

    /**
     * @return get name of this user
     */
    public String getName() {
        return this.name;
    }
}
