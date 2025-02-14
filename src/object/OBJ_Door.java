package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {

        boolean isOpen = false;
        name = "door";
        try {
            if (isOpen = false) {

                image = ImageIO.read(getClass().getResourceAsStream("/objects/doorClosed.png"));

            }

            else {

                image = ImageIO.read(getClass().getResourceAsStream("/objects/doorOpen.png"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
