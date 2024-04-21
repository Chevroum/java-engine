package snake;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Snake {
    GamePanel gp;
    KeyHandler keyH;
    String direction;

    public final int screenX;
    public final int screenY;
    public final int speed = 3;

    public Snake(GamePanel gp){
        this.gp = gp;
        this.keyH = gp.keyH;
        direction = "down";

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2); 
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed ==true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.fillRect(screenX,screenY,gp.tileSize/2,gp.tileSize/2);
    }
}
