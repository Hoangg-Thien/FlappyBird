import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int windowWidth = 360;
    int windowHeight = 640;

    // anh
    Image backgroundImg;
    Image topPipeImg;
    Image BottomPipeImg;
    Image birdImg;

    // chim
    int birdX = windowWidth/8; // chim nam ben trai
    int birdY = windowHeight/2; //chim nam giua man hinh
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird{
        int x = birdX;
        int y = birdY;
        int height = birdHeight;
        int width = birdWidth;
        Image img;

            Bird(Image img){
                this.img = img;
            }
    }

    //ong
    int pipeX = windowWidth;
    int pipeY = 0;
    int pipeWidth = 64; //350/6
    int pipeHeight = 512;

    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img){
            this.img = img;
        }
    }

    //game logic
    Bird bird;
    int velocityX = -4; // di chuyen ong sang trai (bi chim di chuyen qua phai)
    int velocityY = 0; // van toc
    int gravity = 1; // trong luc

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    double score = 0;

    FlappyBird(){
        // hinh nen
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setFocusable(true); // nhan cac phim
        addKeyListener(this);

        // tai anh
        backgroundImg = new ImageIcon(getClass().getResource("/img/background.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/img/bird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/img/topPipe.png")).getImage();
        BottomPipeImg = new ImageIcon(getClass().getResource("/img/bottomPipe.png")).getImage();

        //chim
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        //place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer
        gameLoop = new Timer(1000/60, this); //this la actionPerformed cua phim
        gameLoop.start();

    }

    public void placePipes(){
        //random (0-1) * pipeHeight(512)/2 -> (0-256)
        //pipeHeight/4 = 128
        //0-128-(0-256) --> pipeHeight/4 --> 3/4 pipeHeight

        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = windowHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(BottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g){
        // nen
        g.drawImage(backgroundImg, 0, 0, windowWidth, windowHeight, null);

        //chim
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        //ong
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if(gameOver){
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        }else{
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move(){
        //chim
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        //ong
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if(!pipe.passed && bird.x > pipe.x + pipe.width){
                pipe.passed = true;
                score += 0.5; // vi co 2 ong nen 0.5 * 2 = 1 cho moi lan di qua 2 ong
            }

            if(collision(bird,pipe)){
                gameOver = true;
            }
        }

        if(bird.y > windowHeight){
            gameOver = true;
        }
    }

    //va cham voi cot
    public boolean collision(Bird a, Pipe b){
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.width > b.y;
    }

    // phim
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
            if(gameOver){
                placePipesTimer.stop();
                gameLoop.stop();
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY = -9;
            if(gameOver){
                //bat dau lai
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
