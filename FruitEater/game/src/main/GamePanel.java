package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;


public class GamePanel extends JPanel implements Runnable,MouseListener{
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

    // SYSTEM
    Player player = new Player(this);
    public AssetSetter aSetter = new AssetSetter(this);
    UI ui = new UI(this);
    Thread gameThread;
    JLabel label;

    // FRUITS
    public SuperObject fruits[] = new SuperObject[10];
    public int eaten = 0;

    // COLLISION
    public CollisionChecker cChecker = new CollisionChecker(this);

    //FPS
    final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        // JLabel STRUCTURE AND SETUP

        label = new JLabel();
		label.setBounds(0, 0, screenWidth, screenHeight); // on all of the screen
		
		this.add(label);
        
        this.addMouseListener(this); // adds mouselistener to GamePanel
        aSetter.setObject();
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

    
    // MOUSE LISTENER
    @Override
    public void mouseClicked(MouseEvent e){
    
    }

    @Override
    public void mousePressed(MouseEvent e){
        
        player.screenX = e.getX();
        player.screenY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e){
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    // PAINTING

    public void update(){
        player.update();
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //PLAYER
        player.draw(g2);
        
        //OBJECT
        for(int i=0; i < fruits.length;i++){
            if(fruits[i]!=null){
                fruits[i].draw(g2, this);
            }
        }

        

        

        ui.draw(g2);
        
        g2.dispose(); //save memory like clearing in C
    }
}
