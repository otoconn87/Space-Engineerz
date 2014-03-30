package Sprites;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


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
	
	public BufferedImage flip(BufferedImage b) {
		AffineTransform imageFlip = AffineTransform.getScaleInstance(-1, 1);
		imageFlip.translate(-b.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(imageFlip,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		b = op.filter(b, null);
		
		return b;
	}
	
	public BufferedImage rotate(BufferedImage b) {
		AffineTransform imageFlip = AffineTransform.getScaleInstance(-1, 1);
		imageFlip.translate(-b.getWidth(null), 0);
		imageFlip.rotate(Math.PI/4);
		AffineTransformOp op = new AffineTransformOp(imageFlip,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		b = op.filter(b, null);
		
		return b;
	}
	
//	public Rectangle getTBRect() {
//		return new Rectangle(this.x+10, this.y, 10, 30);
//	}
//	
//	public Rectangle getLRRect(){
//		return new Rectangle(this.x, this.y+10, 30, 10);
//	}
	
	
		
}
