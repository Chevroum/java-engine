package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import main.GamePanel;


public class Player extends Entity{
    
    GamePanel gp;

    public int screenX;
    public int screenY;

    JLabel label;

    

    int randX;
    int randY;


    public Player(GamePanel gp){
        this.gp = gp;

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2); //the minus is here because screenX and Y are the top left corner of the image, we need adjustments
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32); // colision rectangle smaller than player 

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
       
    }


    // called 60 times a second
    public void update(){
        //is there an object in same pos
        int i = gp.cChecker.CheckObject();
        if (i != 999){
            
            gp.aSetter.createFruit(i); // cast to fruit
            
        }
    }
    

    

    public void draw(Graphics2D g2){
    
    BufferedImage image = null;
    try {
        image = ImageIO.read(new File("res/player/emoji.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    g2.drawImage(image, screenX - (gp.tileSize/2), screenY - (gp.tileSize/2), gp.tileSize,gp.tileSize,null); // adjust for cursor position
    }
}   