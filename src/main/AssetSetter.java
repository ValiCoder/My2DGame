package main;

import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.object[0] = new OBJ_Key();
        gp.object[0].worldX = 30 * gp.tileSize;
        gp.object[0].worldY = 20 * gp.tileSize;

        gp.object[1] = new OBJ_Key();
        gp.object[1].worldX = 45 * gp.tileSize;
        gp.object[1].worldY = 37 * gp.tileSize;

        gp.object[2] = new OBJ_Door();
        gp.object[2].worldX = 30 * gp.tileSize;
        gp.object[2].worldY = 22 * gp.tileSize;

        gp.object[3] = new OBJ_Door();
        gp.object[3].worldX = 7 * gp.tileSize;
        gp.object[3].worldY = 5 * gp.tileSize;

    }
}
