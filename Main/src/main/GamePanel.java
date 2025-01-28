package main;

import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // Scale
    
    public final int tileSize = originalTileSize * scale; // 48x48 title
    public final int maxScreenCol = 16; // Width
    public final int maxScreenRow = 12; // Height
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);
    
    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Screen size
        this.setBackground(Color.black); // Background Color
        this.setDoubleBuffered(true); // Double FPS
        this.addKeyListener(keyH);
        this.setFocusable(true); // Recieve key input
    }
    
    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    @Override
    public void run(){
        
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
        
    }
    
    public void update(){
        
        player.update();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        // System.out.println("testPaint");
        
        Graphics2D g2 = (Graphics2D) g;
        
        tileM.draw(g2);
        player.draw(g2);
        
        //g2.dispose(); // Dispose of this graphics context and release any system resources that it is using
    }
    
}