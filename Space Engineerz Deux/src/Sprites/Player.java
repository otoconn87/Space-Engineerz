package Sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Sprites {

	public int jumpTimer;
	public int x, y;
	public int dx, dy;
	
	public boolean walking, idling, jumping, shooting, jetpack, falling, inAir, jumpShooting;
	public boolean leftMapCollision, rightMapCollision, topMapCollision, bottomMapCollision;
	
	public boolean facingRight, left, right, shootLaser;	
	
	public int walkTimer;
	
	public int health;

	public boolean dead, shotOnce;
	public int shootTimer;

	public BufferedImage walk; //walking subImages
	public BufferedImage idle;
	public BufferedImage jump;
	public BufferedImage shoot;
	public BufferedImage jumpShoot;
	
	public Laser laser;

	public Player(String s) {
		super(s);

		walkTimer = shootTimer = 0;
		jumpTimer = 0;
		health = 5;
		dead = false;
		shotOnce = false;
		shootLaser = false;

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public void setLeftMapCollision(boolean b){
		leftMapCollision = b;
	}
	public void setRightMapCollision(boolean b){
		rightMapCollision = b;
	}
	public void setTopMapCollision(boolean b){
		topMapCollision = b;
	}
	public void setBottomMapCollision(boolean b){
		bottomMapCollision = b;
	}
	
	public void setPosition(int x, int y){

		this.x = x;
		this.y = y;
	}

	public void setFacingRight(boolean b) {
		facingRight = b;
	}

	
	public void update(){
		
		if (!left && !right && !falling && !jumping && !shooting && !jumpShooting){
			setIdling(true);
		}
		else{
			setIdling(false);
		}
		
		if(jumping){
			walking = false;
			idling = false;
			y-=3;
			jumpTimer++;
			if(right && !rightMapCollision){
				x+=2;
			}
			if(left && !leftMapCollision){
				x-=2;
			}
			if(jumpTimer == 20){
				falling = true;
				jumping = false;
				bottomMapCollision = false;
				jumpTimer = 0;
			}
			if(!shooting){
				jumpShooting = false;
			}
						
		}
		if(falling){
			walking = false;
			topMapCollision = false;
			if(!bottomMapCollision){
				y+=3;				
				jumping = false;
				if(right && !rightMapCollision){
					x+=2;
				}
				if(left && !leftMapCollision){
					x-=2;
				}
			}
			if(bottomMapCollision){
				y+=0;
				falling = false;
			}
			if(!shooting){
				jumpShooting = false;
			}
		}
		if(left && (!falling && !jumping)){
			inAir = false;
			right = false;
			facingRight = false;
			walking = true;
			idling = false;
			jumpShooting = false;
			if(leftMapCollision){
				x+=3;
				leftMapCollision = rightMapCollision = false;
			}
			else if(!leftMapCollision){
				x-=2;
			}
		}
		
		if(right && (!falling && !jumping)){
			inAir = false;
			left = false;
			facingRight = true;
			walking = true;
			idling = false;
			jumpShooting = false;
			if(rightMapCollision){
				x-=3;
				leftMapCollision = rightMapCollision = false;
			}
			else if(!rightMapCollision){
				x+=2;
			}
		}
		if(!right && !left){
			walking = false;
		}
		
		if (!walking && !falling && !jumping &&!shooting && !jumpShooting){
			setIdling(true);
			
		}
		
		if(!leftMapCollision && !rightMapCollision && !topMapCollision && !bottomMapCollision){
			falling = true;
		}			
		
		if(idling){	
			x+=0;
			x-=0;
			y+=0;
			y-=0;
		}
		
		if((jumping || falling) && shooting){
			jumpShooting = true;
		}
		if(!(jumping || falling) && shooting){
			jumpShooting = false;
		}

	}
	
	public void setLeft(boolean b){
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void setJump(boolean b) {
		jumping = b;
	}
	
	public void setShoot(boolean b){
		shooting = b;	
	}
	
	public void laserFire(){		
		shootLaser = true;
	}
	
	public Rectangle getTBRect() {
		return new Rectangle(this.x+10, this.y, 10, 30);
	}
	
	public Rectangle getLRRect(){
		return new Rectangle(this.x, this.y+10, 30, 10);
	}

	public void setFalling(boolean b) {
		falling = b;	
	}

	public void setIdling(boolean b) {	
		idling = b;
	}
	
	public void setJumpShooting(boolean b){
		jumpShooting = b;
	}

	public BufferedImage jumping(BufferedImage b) {
		
		if(jumping && !shooting && !jumpShooting){
			jump = image.getSubimage(72, 122, 40, 41);
		}
		
		
		if (!facingRight) {
			jump = flip(jump);
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
					walk = flip(walk);
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
				idle = flip(idle);
			}
		}

		return idle;
	}

	public BufferedImage shoot(BufferedImage b) {

		shootTimer++;

		try {
			if (shooting && !jumpShooting) {
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
				}
				if (!facingRight) {
					shoot = flip(shoot);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shoot;
	}
	
	public BufferedImage jumpShoot(BufferedImage b){
		
		try{
			if(jumpShooting){
				jumpShoot = image.getSubimage(49, 167, 42, 40);
				if(!shotOnce){
					laserFire();
					shotOnce = true;
				}
				
				if(!facingRight){
					jumpShoot = flip(jumpShoot);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jumpShoot;
	}

}
