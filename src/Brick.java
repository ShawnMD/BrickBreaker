import java.awt.*;

public class Brick {

    private int x, y;
    private final int WIDTH = 50, HEIGHT = 20;
    Board board;
    Game game;
    Ball ball;

    public Brick(Board board) {

        x = 0;
        y = 0;
        this.board = board;
        this.game = game;
        this.ball = ball;
    }

    public Rectangle getBounds(){

        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void setPosition(int x, int y){
        this.x = x - WIDTH/2;
        this.y = y - HEIGHT/2;
    }

    public void paint(Graphics g){
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

}
