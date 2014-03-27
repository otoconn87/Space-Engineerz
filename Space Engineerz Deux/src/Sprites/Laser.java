package Sprites;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Laser extends Player {

	public int x, y;

	public boolean laserFire, facingRight, left;
	public Player player;

	public BufferedImage laserIm;

	public Laser(String s) {
		super(s);
		laserFire = true;
	}
	
	public void update(){
		if(facingRight){
			x+=5;	
		}else{
			x-=5;
		}
		setPosition(x,y);
	}

	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, 29, 15);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setFacingRight(boolean b) {
		facingRight = b;
	}

	public void setLeft() {
		setFacingRight(false);
		x -= 3;

	}

	public void setRight() {
		setFacingRight(true);
		x += 3;
	}
	
	public void setLeftMapCollision(boolean b){
		leftMapCollision = b;
	}
	public void setRightMapCollision(boolean b){
		rightMapCollision = b;
	}

	public BufferedImage laserIm(BufferedImage b) {

		try {
			if (laserFire) {
				laserIm = image.getSubimage(133, 8, 29, 32);

				if (!facingRight) {
					laserIm = flip(laserIm);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return laserIm;
	}
}
