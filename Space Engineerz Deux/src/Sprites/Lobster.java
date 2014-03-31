package Sprites;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Lobster extends Sprites {

	public int x, y;
	public int health;

	public boolean walking, shooting, flinching, dead;

	public boolean facingRight, left;

	public int walkTimer, lobsterTimer;
	
	public BufferedImage walk; // walking subImages
	public BufferedImage shoot; // TODO
	public BufferedImage flinch; // TODO
	
	public Lobster(String s) {
		super(s);

		walkTimer = 0;
		walking = true;
		health = 3;
		
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.x,this.y,47,49);
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

		walking = true;
		setFacingRight(false);
		x -= 2;

	}

	public void setRight() {

		walking = true;
		setFacingRight(true);
		x += 2;
	}

	public void setFlinching() {
		flinching = true;
	}

	public void setIdling() {
		walking = false;
	}
	
	public void update(){
		lobsterTimer++;
		
		if(lobsterTimer >=0 && lobsterTimer < 50){
			x+=2;
		}
		else if(lobsterTimer >= 50 && lobsterTimer < 100){
			x-=2;
		}
		else{
			lobsterTimer = 0;
		}
		
		
		
	}

	public BufferedImage walking(BufferedImage b) {
		walkTimer++;

		try {
			if (walking) {

				if (walkTimer >= 1 && walkTimer < 15) {
					walk = image.getSubimage(3, 0, 47, 49);

				} else if (walkTimer >= 15 && walkTimer < 30) {
					walk = image.getSubimage(58, 0, 47, 49);

				} else if (walkTimer >= 30 && walkTimer < 45) {
					walk = image.getSubimage(111, 0, 47, 49);

				} else if (walkTimer >= 45 && walkTimer < 60) {
					walk = image.getSubimage(159, 0, 50, 49);

				} else if (walkTimer >= 60 && walkTimer < 75) {
					walk = image.getSubimage(220, 0, 47, 49);

				} else if (walkTimer >= 75 && walkTimer < 90) {
					walk = image.getSubimage(276, 0, 47, 49);

				} else if (walkTimer >= 90 && walkTimer < 105) {
					walk = image.getSubimage(324, 0, 40, 49);

				} else if (walkTimer >= 105 && walkTimer < 120) {
					walk = image.getSubimage(365, 0, 47, 49);

				} else if (walkTimer >= 120 && walkTimer < 135) {
					walk = image.getSubimage(416, 0, 47, 49);

				} else {
					walkTimer = 0;
				}

				// Have to Implements walking left
			

				if (!facingRight) {
					walk = flip(walk);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return walk;
	}

	// TODO shoot
	public BufferedImage shoot(BufferedImage b) {
		if (facingRight && shooting) {
			// shoot = image.getSubimage(50, 86, 32, 32);
		} else if (!facingRight && shooting) {
			// shoot = image.getSubimage(291, 86, 35, 33);
		}
		return shoot;
	}

	public BufferedImage flinch(BufferedImage b) {
		if (facingRight && flinching) {
			// flinch = image.getSubimage(50, 86, 32, 32);
		} else if (!facingRight && flinching) {
			// flinch = image.getSubimage(291, 86, 35, 33);
		}
		return flinch;
	}

}
