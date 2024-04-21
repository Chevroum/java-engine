package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//abstract class for all entities
public class Entity {
    
    public int worldX, worldY; //position in world
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteNum = 1;     // used to know which sprite to use when painting 
    public int spriteCounter = 0; // used to count the number of frames before a change in sprite
    public Rectangle solidArea; //create abstract rectangle
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
}
