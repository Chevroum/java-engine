package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class OBJ_fruit extends SuperObject{

    public OBJ_fruit(){
        try {
            image = ImageIO.read(new File("res/fruit/cherry.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

   
}
