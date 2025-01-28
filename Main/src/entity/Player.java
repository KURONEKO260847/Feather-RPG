package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        
        setDefaultValues();
        getPlayerImage();
        
    }
    
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "right";

    }
    
    public void getPlayerImage(){

        try {
            
            File f1 = new File("D:/Feather RPG/Main/res/player/peacock_left_1.png");
            File f2 = new File("D:/Feather RPG/Main/res/player/peacock_left_2.png");
            File f3 = new File("D:/Feather RPG/Main/res/player/peacock_left_3.png");
            File f4 = new File("D:/Feather RPG/Main/res/player/peacock_left_4.png");
            File f5 = new File("D:/Feather RPG/Main/res/player/peacock_right_1.png");
            File f6 = new File("D:/Feather RPG/Main/res/player/peacock_right_2.png");
            File f7 = new File("D:/Feather RPG/Main/res/player/peacock_right_3.png");
            File f8 = new File("D:/Feather RPG/Main/res/player/peacock_right_4.png");

            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f5);
            up3 = ImageIO.read(f3);
            up4 = ImageIO.read(f4);
            down1 = ImageIO.read(f1);
            down2 = ImageIO.read(f5);
            down3 = ImageIO.read(f3);
            down3 = ImageIO.read(f4);
            left1 = ImageIO.read(f1);
            left2 = ImageIO.read(f2);
            left3 = ImageIO.read(f3);
            left4 = ImageIO.read(f4);
            right1 = ImageIO.read(f5);
            right2 = ImageIO.read(f6);
            right3 = ImageIO.read(f7);
            right4 = ImageIO.read(f8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void update(){
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed == true){
                direction = "up";
                worldY -= speed;
            }
            
            else if(keyH.downPressed == true){
                direction = "down";
                worldY += speed;
            }
            
            else if(keyH.leftPressed == true){
                direction = "left";
                worldX -= speed;
            }
            
            else if(keyH.rightPressed == true){
                direction = "right";
                worldX += speed;
            }

            spriteCount++;
            if(spriteCount > 12){ // FPS SETTING
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 3;
                }else if(spriteNum == 3){
                    spriteNum = 4;
                }else if(spriteNum == 4){
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
    
        }
        
    }
    
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white); // Set a color to use for drawing objects
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize); //(int x, int y, int width, int height) Draw a rectangle
        
        BufferedImage image = null;

        if(direction == "left" || (direction == "up" ||direction == "down")){
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            if(spriteNum == 3){
                image = left3;
            }
            if(spriteNum == 4){
                image = left4;
            }
        }
            
        if(direction == "right" || (direction == "up" || direction == "down")){
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            if(spriteNum == 3){
                image = right3;
            }
            if(spriteNum == 4){
                image = right4;
            }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
    
}
