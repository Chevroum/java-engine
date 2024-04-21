package main;


public class CollisionChecker {
    
    GamePanel gp;
    

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public int CheckObject(){
        int index = 999;
        for(int i = 0; i < gp.fruits.length; i++){
            if(gp.fruits[i] != null){
                //Get the player's solid area position (hitbox)
                gp.player.solidArea.x = gp.player.screenX;
                gp.player.solidArea.y = gp.player.screenY;
                //System.out.println("PLAYER : x = "+ gp.player.solidArea.x + " y = " + gp.player.solidArea.y);
                //Get the object's solid area position (hitbox)
                gp.fruits[i].solidArea.x = gp.fruits[i].worldX;
                gp.fruits[i].solidArea.y = gp.fruits[i].worldY;
                
                //System.out.println("FRUIT ["+i+"]: x = "+ gp.fruits[i].solidArea.x + " y = " + gp.fruits[i].solidArea.y);
                
                if(gp.player.solidArea.intersects(gp.fruits[i].solidArea)){
                    
                    gp.eaten ++;
                    index = i; 
                    return index;
                }
        }
        }
        return index;
    }
}
