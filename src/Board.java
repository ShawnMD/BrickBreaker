import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    Paddle paddle;
    Ball ball;
    Brick brick;
    Timer timer;
    private final int HOLE = 50;
    private final int DECORSIZE = 10;

    public Board(Game game) {
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.BLACK);
        ball = new Ball(this);
        paddle = new Paddle(this, game, ball);
        ball = new Ball(this);
        brick = new Brick(this);

    }


    public void GameStart() {

        ball.setPosition(getWidth()/2, getHeight()/4);
        paddle.setPosition(getWidth() / 2, (getHeight()/10)*9);
        brick.setPosition(getWidth() /2, (getHeight()/35)*5);
        timer = new Timer(1000 / 60, this);
        timer.start();

    }

    public void GameReset() {

        ball.setPosition(getWidth()/2, getHeight()/4);
        paddle.setPosition(getWidth() / 2, (getHeight()/10)*9);
        brick.setPosition(getWidth() /2, (getHeight()/35)*5);
    }

    public void GameRestart(){
        GameReset();
        Menus.setScore(0);
    }






    @Override
    public void actionPerformed(ActionEvent e) {

        if(Menus.isPlay()){
            paddle.move();
            ball.move();
        }

        ball.checkCollisions(paddle, brick);


        if(Menus.getScore() > 30){
            GameRestart();
            Menus.endGame();
            Menus.stopPlay();
            Menus.stopPause();
        }
        repaint();
    }


    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);


        if(Menus.isPlay()){
            ball.paint(g);
            paddle.paint(g);
            brick.paint(g);
        }
        else if(Menus.isMenu()){
            g.setFont(new Font("Serif", Font.BOLD, 36));
            printSimpleString("Brick Breaker", getWidth(), 0, (int)getHeight()/3, g);
            printSimpleString("Press SPACE to begin  .", getWidth(), 0, (int)(getHeight()*(2.0/3)), g);
        }
        else if(Menus.isPause()){
            g.setFont(new Font("Serif", Font.BOLD, 36));
            printSimpleString("PAUSED", getWidth(), 0, (int)getHeight()/3, g);
            printSimpleString("Press P to resume.", getWidth(), 0, (int)(getHeight()*(2.0/3)), g);
        }
        else if(Menus.isEnd()){
            g.setFont(new Font("Serif", Font.BOLD, 36));
            if(Menus.getScore() > 30) {
                printSimpleString("YOU WON!!!", getWidth(), 0, (int) getHeight() / 3, g);
            }
        }

    }


    public int getHOLE() {
        return HOLE;
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){

        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s,g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos);
    }
}
