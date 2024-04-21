package main;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fruit Eater");

        File icon = new File("res/icon/icon.png");

        window.setIconImage(ImageIO.read(icon));

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameThread();
    }
}
