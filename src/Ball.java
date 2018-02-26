import java.awt.*;


public class Ball {

    private final int diameter = 20;
    private final int SPEED = 4;
    int x, y;
    double dx = SPEED, dy = SPEED;

    double MAXANGLE = 3*Math.PI/12;

    Board board;

    public Ball(Board board) {
        this.board = board;
        x = 0;
        y = 0;
    }

    public void move() {

        if (y < 0) {
            dy *= -4;
        }
        if (x + diameter > board.getWidth() || x < 0) {
            dx *= -4;
        }
        if (y < board.getHOLE()) {
            board.GameReset();
            board.GameRestart();
        }

        x += dx;
        y += dy;

    }

    public void checkCollisions(Paddle other, Brick brick){

        if(getBounds().intersects(other.getBounds())){
            double paddleX = other.getBounds().getX();
            double paddleZ = other.getBounds().getWidth()/2;
            double ballx = x+(diameter/2);

            double relativeIntersect = (paddleX + paddleZ) - ballx;
            double normalIntersect = relativeIntersect/paddleZ;
            double bounceAngle = MAXANGLE * normalIntersect;


            dx = (int)(SPEED*-Math.sin(bounceAngle));
            dy = (int)(SPEED*-Math.cos(bounceAngle));

        }
        if(getBounds().intersects(brick.getBounds())){
            double brickX = brick.getBounds().getX();
            double brickZ = brick.getBounds().getWidth()/2;
            double ballx = x+(diameter/2);

            double relativeIntersect = (brickX + brickZ) - ballx;
            double normalIntersect = relativeIntersect/brickZ;
            double bounceAngle = MAXANGLE * normalIntersect;


            dx = (int)(SPEED*-Math.sin(bounceAngle));
            dy = (int)(SPEED*-Math.cos(bounceAngle));

        }
    }





    public Rectangle getBounds() {

        return new Rectangle(x, y, diameter, diameter);
    }

    public void setPosition(int x, int y) {
        this.x = x - diameter / 2;
        this.y = y - diameter / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiam() {
        return diameter;
    }

    public void paint(Graphics g) {
        g.fillOval(x, y, diameter, diameter);
    }


}