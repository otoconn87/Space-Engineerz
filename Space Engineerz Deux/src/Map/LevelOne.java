package Map;

import java.awt.image.BufferedImage;

import java.io.InputStream;



public class LevelOne extends Map {

	
	public int mapWidth;
	public int mapHeight;
	public int pixelWidth = 32;
	public int pixelHeight = 32;
	public int rows = 2;
	public int cols = 20;
	public BufferedImage tileMap[];
	public BufferedImage actualLevel[][];
	public int levelMap[][];
	
	
	public LevelOne(String tiles, InputStream map){
		
		super(tiles, map);
		pixelWidth = 32;
		pixelHeight = 32;
		rows = 2;
		cols = 20;
		
	}
	
	
}
