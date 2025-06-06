package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_chest extends SuperObject{
    public OBJ_chest(){
        name = "Chest";
        try{
            image = ImageIO.read(new File("res/objects/chest.png")); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        solidArea.height=38;
        solidArea.y = 10;
        solidAreaDefaultY = 10;
        collision = true;
    }
}
