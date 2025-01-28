package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        
        this.gp = gp;

        tile = new Tile[10]; //จำนวนรูปบล็อกที่อยู่ใน ArrayList
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("D:/Feather RPG/Main/res/maps/world01.txt");

    }

    public void loadMap(String filePath){
        
        try {

            String map01 = (filePath);
            FileReader is = new FileReader(map01);
            BufferedReader br = new BufferedReader(is);
        
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }

            }

            br.close();
        
        } catch (Exception e) {
            
        
        }
    }

    public void getTileImage(){

        try {
            
            tile[0] = new Tile();
            File grass = new File("D:/Feather RPG/Main/res/tiles/grass.png");
            tile[0].image = ImageIO.read(grass);

            tile[1] = new Tile();
            File wall = new File("D:/Feather RPG/Main/res/tiles/wall.png");
            tile[1].image = ImageIO.read(wall);

            tile[2] = new Tile();
            File water = new File("D:/Feather RPG/Main/res/tiles/water.png");
            tile[2].image = ImageIO.read(water);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow= 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
