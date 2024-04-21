package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
    //Screen settings 
    //top left is 0,0
    final int originalTitleSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale; // 48*48 tile

    //number of tiles on screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels 

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    //FPS
    final int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public Sound music = new Sound();
    public Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10]; // only display 10 at a time but can change when obj not needed on screen

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();

        playMusic(0); // i = 0 for main theme, check Sound class
    }

    public void startGameThread(){
        gameThread = new Thread(this); // pass GamePanel to class constructor
        gameThread.start(); // calls run() method
    }

    @Override
    public void run(){

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
        player.update();
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);     //first tile to not hide player
        //OBJECT
        for(int i=0; i < obj.length;i++){
            if(obj[i]!=null){
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);
        
        g2.dispose(); //save memory like clearing in C
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}