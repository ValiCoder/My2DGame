package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public final int originalTileSize = 16;
    public final int scale = 4;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public CollisionDetection cCheck = new CollisionDetection(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject object[] = new SuperObject[10];


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame() {

        aSetter.setObject();

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - lastTime;
            lastTime = currentTime;

            timer += elapsedTime;

            update();
            repaint();
            drawCount++;

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount / 2);
                drawCount = 0;
                timer = 0;
            }

            // Calculate remaining time for the next frame
            double remainingTime = drawInterval - elapsedTime;
            if (remainingTime > 0) {
                try {
                    Thread.sleep((long) (remainingTime / 1000000)); // Convert to milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update() {

        player.update();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //TILE
        tileM.draw(g2);

        //OBJECT
        for (int i = 0; i < object.length; i++) {
            if (object[i] != null) {
                object[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();
    }
}