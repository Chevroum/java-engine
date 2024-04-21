package main;

import object.OBJ_fruit;

public class AssetSetter {
    GamePanel gp;
    public final int rangeX;
    public final int  rangeY;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
        rangeY = (gp.maxScreenRow - 1);
        rangeX = (gp.maxScreenCol - 1);
    }
    
    public void setObject(){
        gp.fruits[0] = new OBJ_fruit();
        gp.fruits[0].worldX = 2 * gp.tileSize;
        gp.fruits[0].worldY = 7 * gp.tileSize;
        gp.fruits[0].setArea(gp.fruits[0].worldX, gp.fruits[0].worldY);

        gp.fruits[1] = new OBJ_fruit();
        gp.fruits[1].worldX = 2 * gp.tileSize;
        gp.fruits[1].worldY = 10 * gp.tileSize;
        gp.fruits[1].setArea(gp.fruits[1].worldX, gp.fruits[1].worldY);

        gp.fruits[2] = new OBJ_fruit();
        gp.fruits[2].worldX = 3 * gp.tileSize;
        gp.fruits[2].worldY = 9 * gp.tileSize;
        gp.fruits[2].setArea(gp.fruits[2].worldX, gp.fruits[2].worldY);
        
        gp.fruits[3] = new OBJ_fruit();
        gp.fruits[3].worldX = 9 * gp.tileSize;
        gp.fruits[3].worldY = 9 * gp.tileSize;
        gp.fruits[3].setArea(gp.fruits[3].worldX, gp.fruits[3].worldY);

        gp.fruits[4] = new OBJ_fruit();
        gp.fruits[4].worldX = 8 * gp.tileSize;
        gp.fruits[4].worldY = 11 * gp.tileSize;
        gp.fruits[4].setArea(gp.fruits[4].worldX, gp.fruits[4].worldY);

        gp.fruits[5] = new OBJ_fruit();
        gp.fruits[5].worldX = 11 * gp.tileSize;
        gp.fruits[5].worldY = 8 * gp.tileSize;
        gp.fruits[5].setArea(gp.fruits[5].worldX, gp.fruits[5].worldY);

        gp.fruits[6] = new OBJ_fruit();
        gp.fruits[6].worldX = 10 * gp.tileSize;
        gp.fruits[6].worldY = 7 * gp.tileSize;
        gp.fruits[6].setArea(gp.fruits[6].worldX, gp.fruits[6].worldY);

        gp.fruits[7] = new OBJ_fruit();
        gp.fruits[7].worldX = 7 * gp.tileSize;
        gp.fruits[7].worldY = 2 * gp.tileSize;
        gp.fruits[7].setArea(gp.fruits[7].worldX, gp.fruits[7].worldY);
    }

    public void createFruit(int i){
        int randX = (int) Math.floor(Math.random() * rangeX);
        int randY = (int) Math.floor(Math.random() * rangeY);

        gp.fruits[i] = new OBJ_fruit();

        gp.fruits[i].worldX = randX * gp.tileSize;
        gp.fruits[i].worldY = randY * gp.tileSize;
        gp.fruits[i].setArea(gp.fruits[i].worldX, gp.fruits[i].worldY);
        

        while((i != 0  &&  gp.fruits[0].solidArea.intersects(gp.fruits[i].solidArea)) ||
              (i != 1  &&  gp.fruits[1].solidArea.intersects(gp.fruits[i].solidArea)) ||
              (i != 2  &&  gp.fruits[2].solidArea.intersects(gp.fruits[i].solidArea)) ||
              (i != 3  &&  gp.fruits[3].solidArea.intersects(gp.fruits[i].solidArea)) ||
              (i != 4  &&  gp.fruits[4].solidArea.intersects(gp.fruits[i].solidArea)) ||
              (i != 5  &&  gp.fruits[5].solidArea.intersects(gp.fruits[i].solidArea)) ||
              (i != 6  &&  gp.fruits[6].solidArea.intersects(gp.fruits[i].solidArea)) ||
              (i != 7  &&  gp.fruits[7].solidArea.intersects(gp.fruits[i].solidArea)) ||
              gp.player.solidArea.intersects(gp.fruits[i].solidArea)){
             
            randX = (int) Math.floor(Math.random() * rangeX);
            randY = (int) Math.floor(Math.random() * rangeY);

            gp.fruits[i].worldX = randX * gp.tileSize;
            gp.fruits[i].worldY = randY * gp.tileSize;
            gp.fruits[i].setArea(gp.fruits[i].worldX, gp.fruits[i].worldY);
        }

    }
}
