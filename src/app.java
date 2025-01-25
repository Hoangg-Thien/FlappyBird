import  javax.swing.*;

public class app {
    public static void main(String[] args) throws Exception {
        int windowWidth = 1080; // chieu rong man hinh
        int windowHeight = 720; // chieu dai man hinh

        JFrame frame = new JFrame("Flappy Bird: ");
        frame.setVisible(true);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dong cua so khi bam nut tat

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        frame.setVisible(true);
    }
}