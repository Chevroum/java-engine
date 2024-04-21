package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject{

    public BufferedImage image;
    public String name;
    public int worldX,worldY; //where is the obj
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    

    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize,null);
    }

    public void setArea(int x, int y){
        this.solidArea.x = x;
        this.solidArea.y = y;
    }
}