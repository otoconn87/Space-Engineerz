package Sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BlastikLaser extends Player {

	public int x, y, dx, dy,  width, height;

	public boolean laserFire, facingRight, left;
	public Player player;
	
	int playerX, playerY, updateCounter;

	public BufferedImage laserIm;

	private int laserTimer;

	public BlastikLaser(String s, Player p) {
		super(s);
		width = 62;
		height = 32;
		laserFire = true;
		player = p;

	}
	
	public void update(){
		updateCounter++;
		playerX = player.x;
		playerY = player.y;
		
//		if(facingRight){
//			x+=5;	
//		}else{
//			x-=5;
//		}
		
		if(y < playerY){
			//laser is above player
			dy = 1;
		}
		if(y > playerY){
			//laser is below player
			dy = -1;
		}
		if(x < playerX){
			dx = 2;
		}
		if(x > playerX){
			dx = -2;
		}
		
		setPosition(x,y);
	}

	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, width, height);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setPosition(int x, int y) {
		this.x = x + dx;
		this.y = y + dy;
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
				laserTimer++;
				if(laserTimer > 0 && laserTimer <= 5){
					laserIm = image.getSubimage(10, 414, width, height);
				}else if(laserTimer > 5 && laserTimer <= 10){
					laserIm = image.getSubimage(73, 414, width, height);
				}else if(laserTimer > 10 && laserTimer <= 15){
					laserIm = image.getSubimage(139, 414, width, height);
				}else{
					laserTimer = 0;
				}
				

				if (facingRight) {
					laserIm = flip(laserIm);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return laserIm;
	}
}
