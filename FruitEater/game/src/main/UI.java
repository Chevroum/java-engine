package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;




public class UI {

    GamePanel gp;
    Font arial_10;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter =0;
    public boolean gameFinished = false;

    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_10 = new Font("Arial", Font.PLAIN, 10);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        g2.setFont(arial_10);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("fruits : " + gp.eaten, 2, 16);
    }

}