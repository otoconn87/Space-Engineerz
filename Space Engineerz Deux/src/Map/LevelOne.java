package Map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Game.Menu;
import Sprites.Laser;
import Sprites.Lobster;
import Sprites.Player;



public class LevelOne extends Map {
	

	

		
	public boolean lobsterPlayerCollision, lobsterLaserCollision;

	public BufferedImage background,  playerAnimations, lobsterAnimations, laserAnimations;

	public Player player;
	public ArrayList<Lobster> lobsters;
	public ArrayList<Laser> lazer;
	
	BufferedImage[][] gameMapBlocked;
	BufferedImage[][] gameMapPassed;
	int[][] levelOneMap;
	

	 
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
