package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_boots extends SuperObject{
    public OBJ_boots(){
        name = "Boots";
        try{
            image = ImageIO.read(new File("res/objects/boots.png")); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
