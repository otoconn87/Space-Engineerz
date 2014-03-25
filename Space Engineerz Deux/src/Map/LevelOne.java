package Map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;



public class LevelOne extends Map {

	 
	public int mapWidth;
	public int mapHeight;
	public int pixelWidth;
	public int pixelHeight;
	public int rows;
	public int cols;
	public BufferedImage tileMap[];
	public BufferedImage actualLevel[][];
	public int levelMap[][];
	public Rectangle[][] blockedRect;
	
	
	
	
	
	public LevelOne(String tiles, InputStream is){
		
		super(tiles);
		
		pixelWidth = 32;
		pixelHeight = 32;
		rows = 2;
		cols = 20;
		
		this.readMap(is);
		this.makeTileMap(rows, cols, pixelWidth);
		//this.blockedRectangles();
		this.makeRectangles();
		
		blockedRect = blockedRectangles();
		levelMap = getLevelOneMap();
		
		
	}
	
	public Rectangle getRect(int x, int y){
		Rectangle tempRect = new Rectangle(x, y, pixelWidth, pixelWidth);
		Rectangle rect = new Rectangle();
		for (int i = 0; i<this.getMapHeight(); i++){
			for(int j = 0; j<this.getMapWidth();j++){
				if (blockedRect[i][j] == tempRect){
					rect = blockedRect[i][j];
					break;
				}
			}
		}
		return rect;
	}
		
	
	public BufferedImage[][] getLevelOneBlockedTiles(){
		return this.getBlockedTiles();
	}
	public BufferedImage[][] getLevelOnePassTiles(){
		return this.getPassTiles();
	}
	
	public int[][] getLevelOneMap(){
		return this.setLevelMap();
	}
	
	public Rectangle[][] getLevelOneRect(){
		return this.blockedRectangles();
	}
	
	
}
