package main;

import entity.Entity;

public class CollisionDetection {

    GamePanel gp;

    public CollisionDetection(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int TileNum1, TileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                entityTopRow = Math.max(0, Math.min(entityTopRow, gp.maxWorldRow - 1)); // Fix
                TileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                TileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                TileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                TileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                TileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                TileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                TileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                TileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
