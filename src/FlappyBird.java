import java.awt.*;
import javax.swing.*;

public class FlappyBird extends JPanel {
    int windowWidth = 1080;
    int windowHeight = 720;

    // anh
    Image background;
    Image topPipe;
    Image BottomPipe;
    Image bird;

    // chim
    int birdX = windowWidth/8; // chim nam ben trai
    int birdY = windowHeight/2; //chim nam giua man hinh
    int birdWidth = 135;
    int birdHeight = 360;

    FlappyBird(){
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        // setBackground(Color.blue);

        // tai anh
        background = new ImageIcon(getClass().getResource("/img/background.png")).getImage();
        bird = new ImageIcon(getClass().getResource("/img/bird")).getImage();
        topPipe = new ImageIcon(getClass().getResource("/img/topPipe")).getImage();
        BottomPipe = new ImageIcon(getClass().getResource("/img/bottomPipe")).getImage();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g){
        // nen
        g.drawImage(background, 0, 0, windowWidth, windowHeight, null);
    }

}
