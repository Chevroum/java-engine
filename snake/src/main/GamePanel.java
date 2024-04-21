package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import snake.Snake;

public class GamePanel extends JPanel implements Runnable{

    final int originalTitleSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale; // 48*48 tile

    //number of tiles on screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels 

    // SYSTEM
    Thread gameThread = new Thread();
    public KeyHandler keyH = new KeyHandler();
    Snake snake = new Snake(this);

    //FPS
    final int FPS = 60;

    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // pass GamePanel to class constructor
        gameThread.start(); // calls run() method
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 1s in nano divided by 60
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        @SuppressWarnings("unused")
        long timer = 0; // is used in while loop

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // add past time to timer 
            lastTime = currentTime;


            if(delta >= 1){
                //1) UPDATE character position 
                update();
                //2) DRAW the screen with updated information
                repaint();           //call paintComponent (JPanel implementation)
                delta--;
            }
        }
    }

    public void update(){
        snake.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        snake.draw(g2);
    }
}
