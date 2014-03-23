package Map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public abstract class Map {

	
	private int mapWidth;
	private int mapHeight;
	private int pixelWidth ;
	private int pixelHeight;
	private int rows;
	private int cols;
	private BufferedReader br; 
	private BufferedImage tileSet;
	private BufferedImage tileMap[];
	//private BufferedImage actualMap[][];
	private int levelMap[][];
	
	public Map (String tiles, InputStream map) {
		tileMap = new BufferedImage[cols + (rows*cols)];
		try {
			tileSet = ImageIO.read(getClass().getResourceAsStream(tiles));
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < cols; j++){
					BufferedImage mapImage = tileSet.getSubimage(j*pixelWidth, i*pixelWidth, pixelWidth, pixelHeight);
					tileMap[j + (i*cols)] = mapImage;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try{
		br = new BufferedReader(new InputStreamReader(map));
		mapWidth = Integer.parseInt(br.readLine());
		setMapWidth(mapWidth);
		//System.out.println(mapWidth);
	    mapHeight = Integer.parseInt(br.readLine());
	    setMapHeight(mapHeight);
	    //System.out.println(mapHeight);
	    levelMap = new int[mapHeight][mapWidth];
	   // actualMap = new BufferedImage[mapHeight][mapWidth];

	        for(int i = 0; i < mapHeight; i++) {
	            String line = br.readLine();
	          //  System.out.println(line);
	            String[] tileValues = line.split("\\s+");
	            for(int j = 0; j < mapWidth; j++){
	                levelMap[i][j] = Integer.parseInt(tileValues[j]); 
	               //actualMap[i][j] = tileMap[levelMap[i][j]];
	               //System.out.println(levelMap[i][j]);
	               
	            }
	        }
	        map.close();
	        
	       
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	

	
	
	public int setMapWidth(int x){
		return mapWidth = x;
	}
	
	public int setMapHeight(int y){
		return mapHeight = y;
	}
	
	public int getMapWidth(){return this.mapWidth;}
	public int getMapHeight(){return this.mapHeight;}
	
	public void paint(Graphics g){
		for(int i = 0; i < mapHeight; i++){
			for(int j = 0; j < mapWidth; j++){
				g.drawImage(tileMap[levelMap[i][j]], j*pixelWidth, i*pixelHeight, null);
			}
		}
		
	}
	
	
}
