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
	
	
	//check rectangle collisions 
	
	public boolean mapCollision(Rectangle r1, Rectangle r2){
		if (r1.intersects(r2)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Flip images when appropriate
	
	public BufferedImage flip(BufferedImage b) {
		AffineTransform imageFlip = AffineTransform.getScaleInstance(-1, 1);
		imageFlip.translate(-b.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(imageFlip,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		b = op.filter(b, null);
		
		return b;
	}
	
	public BufferedImage rotate(BufferedImage b, int px, int py, int lx, int ly, int w, int h) {
		
		double theta;
		AffineTransform imageRotate = null;
		
		if(px < lx){
			theta = (Math.atan((py-ly)/(px-lx)));
			
			imageRotate = AffineTransform.getScaleInstance(1, 1);
			imageRotate.translate(w/2, h/2);
			
			imageRotate.rotate(theta);
			
			imageRotate.translate(-b.getWidth()/2, -b.getHeight()/2);
		}
		if (px > lx) {
			theta = (Math.atan((ly - py) / (px - lx)));

			imageRotate = AffineTransform.getScaleInstance(-1, 1);
			imageRotate.translate(-b.getWidth(null), 0);
			imageRotate.translate(w / 2, h / 2);

			imageRotate.rotate(theta);

			imageRotate.translate(-b.getWidth() / 2, -b.getHeight() / 2);
		}
		if(px == lx){
			if(py < ly){
				theta = Math.PI/2;
				
				imageRotate = AffineTransform.getScaleInstance(1, 1);
				imageRotate.translate(w/2, h/2);
				
				imageRotate.rotate(theta);
				
				imageRotate.translate(-b.getWidth()/2, -b.getHeight()/2);
			}
			if(py > ly){
				theta = (3/2)*Math.PI;
				
				imageRotate = AffineTransform.getScaleInstance(1, 1);
				imageRotate.translate(w/2, h/2);
				
				imageRotate.rotate(theta);
				
				imageRotate.translate(-b.getWidth()/2, -b.getHeight()/2);
			}
		}
	

		AffineTransformOp op = new AffineTransformOp(imageRotate,
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
