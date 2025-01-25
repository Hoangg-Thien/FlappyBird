import  javax.swing.*;

public class app {
    public static void main(String[] args) throws Exception {
        int boardWidth = 1080;
        int boardHeight = 720;

        JFrame frame = new JFrame("Flappy Bidr: ");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}