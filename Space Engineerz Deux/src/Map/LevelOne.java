package Map;

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
	
	
	
	
	public LevelOne(String tiles, InputStream is){
		
		super(tiles);
		
		pixelWidth = 32;
		pixelHeight = 32;
		rows = 2;
		cols = 20;
		
		this.readMap(is);
		this.makeTileMap();
		
	}
	
	// matt is a neat guy
	
	
	
	
	
	
	
	
}
