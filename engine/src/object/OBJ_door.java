package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_door extends SuperObject{
    public OBJ_door(){
        name = "Door";
        try{
            image = ImageIO.read(new File("res/objects/door.png")); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
