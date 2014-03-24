package Map;



import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public abstract class Map {

	//private boolean blocked;
	private int mapWidth;
	private int mapHeight;
	
	private BufferedReader br; 
	private BufferedImage tileSet;
	private BufferedImage blockedTiles[];
	private BufferedImage tileMap[];
	
	
	private int levelMap[][];
	
	public Map (String tiles) {
		
		try {
			tileSet = ImageIO.read(getClass().getResourceAsStream(tiles));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void makeTileMap(int rows, int cols, int pixelWidth){
		tileMap = new BufferedImage[cols + ((rows-1)*cols)];
		blockedTiles = new BufferedImage[cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				BufferedImage mapImage = tileSet.getSubimage(j*pixelWidth, i*pixelWidth, pixelWidth, pixelWidth);
				tileMap[j + (i*cols)] = mapImage;
				if(i==0){
					blockedTiles[j + (i*cols)] = mapImage;
				}
			}
		}
	}
	
	
	
	public void readMap(InputStream is){
		try{
			br = new BufferedReader(new InputStreamReader(is));
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
		       
		        is.close();
		        
		       
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	

	public BufferedImage[][] getMap(){
		BufferedImage[][] actualMap = new BufferedImage[getMapHeight()][getMapWidth()];
		for(int i =0; i < mapHeight; i++){
			for(int j = 0; j<mapWidth; j++){
				actualMap[i][j] = tileMap[levelMap[i][j]];
			}
		}
		return actualMap;
	}
	
//	public boolean setBlocked(boolean b){
//		return this.blocked = b;
//	}
	
	public int setMapWidth(int x){
		return mapWidth = x;
	}
	
	public int setMapHeight(int y){
		return mapHeight = y;
	}
	
	public int getMapWidth(){return this.mapWidth;}
	public int getMapHeight(){return this.mapHeight;}
	

	
	
}
