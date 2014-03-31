package Sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Audio.JukeBox;

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
		height = 80;
		laserFire = true;
		player = p;
		updateCounter = 0;

	}
	
	public void update(){
		updateCounter++;
		playerX = player.x;
		playerY = player.y;
		

		
		if (updateCounter == 1) {
			if (y < playerY) {
				// laser is above player
				dy = 1;
			}
			if (y > playerY) {
				// laser is below player
				dy = -1;
			}
			if (x < playerX) {
				dx = 2;
			}
			if (x > playerX) {
				dx = -2;
			}
		}
		if ((Math.abs(y - playerY) < 10) && (Math.abs(x - playerX) < 10)) {
			if (y < playerY) {
				// laser is above player
				dy = 1;
			}
			if (y > playerY) {
				// laser is below player
				dy = -1;
			}
			if (x < playerX) {
				dx = 2;
			}
			if (x > playerX) {
				dx = -2;
			}
		}

		if (updateCounter == 50) {
			updateCounter = 0;
		}
		setPosition(x,y);
	}

	public Rectangle getRect() {
		return new Rectangle(this.x+20, this.y+23, 23, 23);
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
					laserIm = image.getSubimage(10, 390, width, height);
				}else if(laserTimer > 5 && laserTimer <= 10){
					laserIm = image.getSubimage(73, 390, width, height);
				}else if(laserTimer > 10 && laserTimer <= 15){
					laserIm = image.getSubimage(139, 390, width, height);
				}else{
					laserTimer = 0;
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		laserIm = rotate(laserIm, player.x, player.y, this.x, this.y, width, height);
		
		
		return laserIm;
	}
}
