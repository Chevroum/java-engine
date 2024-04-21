package entity;

import main.KeyHandler;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2); //the minus is here because screenX and Y are the top left corner of the image, we need adjustments
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32); // colision rectangle smaller than player 

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefautValues();
        getPlayerImage();
    }

    public void setDefautValues(){

        //starting pos on world01
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down"; //default or spawn position of sprite
    }

    public void getPlayerImage(){
        
        //don't know why but only way I found to get a file in VsCode
        try{
            up1 = ImageIO.read(new File("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("res/player/boy_right_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    // called 60 times a second
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

            collisionOn = false; // reset collision
            gp.cChecker.checkTile(this); // check collision tile
            int objIndex = gp.cChecker.checkObj(this, true); // check collision obj
            pickUpObject(objIndex);
            if(objIndex != 999 && gp.obj[objIndex] != null && gp.obj[objIndex].collision == true ){ // obj in collision and has physics
                collisionOn = true;
            }

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "right": worldX += speed; break;
                    case "left": worldX -= speed; break;
                }
            }



            spriteCounter++;
            if (spriteCounter>10){ // only called 6 times a second to not get epileptic change of sprite
                if(spriteNum==1){
                    spriteNum = 2;
                }
                else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index){
        if(index != 999 ){
            String objName = gp.obj[index].name;

            switch(objName){
                case "Key" :
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door" :
                    if(hasKey >= 1){
                        gp.ui.showMessage("Door is open!");
                        gp.playSE(3); // unlocking sound
                        hasKey--;
                        gp.obj[index] = null; // unlocks door
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    speed += 1; // makes player faster
                    gp.obj[index] = null;
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4); // FANFARE
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

    // used to paint a white rectangle on screen in the player's location
    //    g2.setColor(Color.white);
    //    g2.fillRect(x, y, gp.tileSize, gp.tileSize);

    BufferedImage image = null;

    switch(direction) {
    case "up":
        if(spriteNum == 1){image = up1;}
        else{image = up2;}
        break;
    case "down":
        if(spriteNum == 1){image = down1;}
        else{image = down2;}
        break;
    case "left":
        if(spriteNum == 1){image = left1;}
        else{image = left2;}
        break;
    case "right":
        if(spriteNum == 1){image = right1;}
        else{image = right2;}
        break;
    }
    g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null); // draw at x y with size tileSize
    }
}   