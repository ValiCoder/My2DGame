package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(24, 32, 16, 24);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 26;
        worldY = gp.tileSize * 24;
        speed = 2;
        direction = "down";

    }

    public void getPlayerImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/player/playerup.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/playerup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/playerup2.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/playerdown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/playerdown2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/playerleft.png"));
            leftw = ImageIO.read(getClass().getResourceAsStream("/player/playerleftwalk.png"));
            leftw2 = ImageIO.read(getClass().getResourceAsStream("/player/playerleftwalk2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/playerright.png"));
            rightw = ImageIO.read(getClass().getResourceAsStream("/player/playerrightwalk.png"));
            rightw2 = ImageIO.read(getClass().getResourceAsStream("/player/playerrightwalk2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void update() {

        boolean isMoving = false;

        if (keyH.upPressed) {
            direction = "up";
            isMoving = true;
        }
        if (keyH.downPressed) {
            direction = "down";
            isMoving = true;
        }
        if (keyH.leftPressed) {
            direction = "left";
            isMoving = true;
        }
        if (keyH.rightPressed) {
            direction = "right";
            isMoving = true;
        }

        if (!isMoving) {
            spriteNum = 1;
            return;
        }

        collisionOn = false;
        gp.cCheck.checkTile(this);

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    if (worldY - speed >= 0) worldY -= speed;
                    break;
                case "down":
                    if (worldY + speed < gp.maxWorldRow * gp.tileSize) worldY += speed;
                    break;
                case "left":
                    if (worldX - speed >= 0) worldX -= speed;
                    break;
                case "right":
                    if (worldX + speed < gp.maxWorldCol * gp.tileSize) worldX += speed;
                    break;
            }
        }


        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 20) {
                spriteCounter = 0;
                spriteNum++;
                if (spriteNum > 3) {
                    spriteNum = 1;
                }
            }
        } else {
            spriteNum = 1;
            spriteCounter = 0;
        }
    }


    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);


        BufferedImage image = null;

        switch (direction) {
            case "down":
                if (spriteNum == 1) {
                    image = down;
                }
                if (spriteNum == 2) {
                    image = down1;
                }
                if (spriteNum == 3) {
                    image = down2;
                }
                break;
            case "up":
                if (spriteNum == 1) {
                    image = up;
                }
                if (spriteNum == 2) {
                    image = up1;
                }
                if (spriteNum == 3) {
                    image = up2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left;
                }
                if (spriteNum == 2) {
                    image = leftw;
                }
                if (spriteNum == 3) {
                    image = leftw2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right;
                }
                if (spriteNum == 2) {
                    image = rightw;
                }
                if (spriteNum == 3) {
                    image = rightw2;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}