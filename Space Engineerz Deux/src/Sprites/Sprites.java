package Sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;


public abstract class Sprites {
	
	protected int x, y;
	protected BufferedImage image;
	
	public Sprites(String s){
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean mapCollision(Rectangle r1, Rectangle r2){
		if (r1.intersects(r2)){
			return true;
		}
		else{
			return false;
		}
	}
	
//	public Rectangle getTBRect() {
//		return new Rectangle(this.x+10, this.y, 10, 30);
//	}
//	
//	public Rectangle getLRRect(){
//		return new Rectangle(this.x, this.y+10, 30, 10);
//	}
	
	
		
}
