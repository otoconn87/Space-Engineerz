package Sprites;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player extends Sprites {

	public int jumpTimer;
	public int x, y;
	public int health;

	public boolean walking, idling, jumping, shooting, jetpack, falling, dead;

	public boolean facingRight, left, shotOnce;

	public int walkTimer, shootTimer;

	public BufferedImage walk; // walking subImages
	public BufferedImage idle;
	public BufferedImage jump;
	public BufferedImage shoot;

	public Player(String s) {
		super(s);

		walkTimer = shootTimer = 0;
		jumpTimer = 0;
		health = 5;
		dead = false;
		shotOnce = false;

	}

	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, 32, 32);
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
		idling = false;
		jumping = false;
		x -= 2;

	}

	public void setRight() {

		walking = true;
		setFacingRight(true);
		idling = false;
		jumping = false;
		x += 2;
	}

	public void setJump() {
		jumpTimer++;
		walking = false;
		idling = false;
		jumping = true;
		y -= 5;
		if (jumpTimer == 5) {
			falling = true;
		}

	}
	
	public void setShoot(){
		shooting = true;
		idling = false;
		walking = false;
		jumping = false;
		
	}
	
	public void laserFire(){
		System.out.println("LASER!!!");
	}

	public void setFalling() {
		falling = true;
	}

	public void setIdling() {
		walking = false;
		jumping = false;
		idling = true;
	}

	public BufferedImage jumping(BufferedImage b) {
		if (facingRight) {
			jump = image.getSubimage(72, 122, 40, 41);
		} else if (!facingRight) {
			jump = image.getSubimage(260, 123, 44, 41);
		}

		return jump;
	}

	public BufferedImage walking(BufferedImage b) {
		walkTimer++;

		try {
			if (walking) {
				if (walkTimer >= 1 && walkTimer < 5) {
					walk = image.getSubimage(8, 43, 32, 33);
				} else if (walkTimer >= 5 && walkTimer < 10) {
					walk = image.getSubimage(46, 43, 30, 32);
				} else if (walkTimer >= 10 && walkTimer < 15) {
					walk = image.getSubimage(80, 43, 36, 32);
				} else {
					walkTimer = 0;
				}
				if (!facingRight) {
					AffineTransform imageFlip = AffineTransform
							.getScaleInstance(-1, 1);
					imageFlip.translate(-walk.getWidth(null), 0);
					AffineTransformOp op = new AffineTransformOp(imageFlip,
							AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
					walk = op.filter(walk, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return walk;
	}

	public BufferedImage idle(BufferedImage b) {
		if (idling){
			idle = image.getSubimage(50, 86, 32, 32);
			if (!facingRight) {
				AffineTransform imageFlip = AffineTransform.getScaleInstance(
						-1, 1);
				imageFlip.translate(-idle.getWidth(null), 0);
				AffineTransformOp op = new AffineTransformOp(imageFlip,
						AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
				idle = op.filter(idle, null);
			}
		}

		return idle;
	}

	public BufferedImage shoot(BufferedImage b) {

		shootTimer++;

		try {
			if (shooting) {
				if(shootTimer == 10){
					laserFire();
				}
				
				if (shootTimer >= 1 && shootTimer < 5) {
					shoot = image.getSubimage(13, 8, 32, 32);
				} else if (shootTimer >= 5 && shootTimer < 10) {
					shoot = image.getSubimage(50, 8, 32, 32);
				} else if (shootTimer >= 10 && shootTimer < 15) {
					shoot = image.getSubimage(84, 8, 45, 32);
				} else {
					shootTimer = 0;
					shooting = false;
					shotOnce = true;
				}
				if (!facingRight) {
					AffineTransform imageFlip = AffineTransform
							.getScaleInstance(-1, 1);
					imageFlip.translate(-shoot.getWidth(null), 0);
					AffineTransformOp op = new AffineTransformOp(imageFlip,
							AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
					shoot = op.filter(shoot, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shoot;
	}

}
