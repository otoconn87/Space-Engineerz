package Sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Audio.JukeBox;

public class Player extends Sprites {
	
	
	public JukeBox audio;
	
	
	public int jumpTimer;
	public int x, y;
	public int dx, dy;
	
	public boolean walking, idling, jumping, shooting, jetpack, falling, inAir, jumpShooting, grounded;
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
	public BufferedImage jetpackIm;
	
	public Laser laser;


	public int jetTimer;

	public Player(String s) {
		super(s);

		walkTimer = shootTimer = jetTimer = jumpTimer = 0;
		health = 5;
		dead = false;
		shotOnce = false;
		shootLaser = false;
		dx = dy = 0;

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return (int)this.y;
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
		
		if(!bottomMapCollision &&!jumping && !jetpack){
			falling = true;
		}
		
		if (!left && !right && !falling && !jumping && !shooting && !jumpShooting && !jetpack){
			setIdling(true);
		}
		else{
			setIdling(false);
		}
		
		if(jumping && !jetpack){
			jumpTimer++;
			
			if(jumpTimer == 1){
				audio = new JukeBox("jump_09.wav");
				audio.play();
			}
			walking = false;
			idling = false;
			dy = -3;
		
			if(right && facingRight && !rightMapCollision){
				dx = 2;
			}
			if(left && !facingRight && !leftMapCollision){
				dx = -2;
			}
			if(jumpTimer == 20){
				falling = true;
				jumping = false;
				grounded = false;
				bottomMapCollision = false;
				jumpTimer = 0;
			}
			if(!shooting){
				jumpShooting = false;
			}
						
		}
		if(falling && !jetpack){
			walking = false;
			jumping = false;
			topMapCollision = false;
			if(bottomMapCollision){
				dy = 0;
				falling = false;
				grounded = true;
			}
			if(!grounded) {
				
				dy = 3;
				jumping = false;
				if(right && facingRight && !rightMapCollision){
					dx = 2;
				}
				if(left && !facingRight && !leftMapCollision){
					dx = -2;
				}
			}
			
			if(!shooting){
				jumpShooting = false;
			}
		}
		
		if(left && (!falling && !jumping && !jetpack)){
			grounded = true;
			inAir = false;
			right = false;
			facingRight = false;
			walking = true;
			idling = false;
			jumpShooting = false;
			if(leftMapCollision){
				dx = 0;
				leftMapCollision = rightMapCollision = false;
			}
			else if(!leftMapCollision){
				dx = -2;
				
			}
		}
		
		if(right && (!falling && !jumping && !jetpack)){
			grounded = true;
			inAir = false;
			left = false;
			facingRight = true;
			walking = true;
			idling = false;
			jumpShooting = false;
			if(rightMapCollision){
				dx = 0;
				leftMapCollision = rightMapCollision = false;
			}
			else if(!rightMapCollision){
				dx = 2;
			}
		}
		
		
		
		if(!right && !left){
			walking = false;
		}
		
		if (!walking && !falling && !jumping &&!shooting && !jumpShooting && !jetpack){
			setIdling(true);
			
		}
		
		if(!leftMapCollision && !rightMapCollision && !topMapCollision && !bottomMapCollision && !jetpack){
			falling = true;
		}			
		
		if(idling){	
			dx = dy = 0;
		}
		
		if((jumping || falling) && shooting){
			jumpShooting = true;
		}
		if(!(jumping || falling) && shooting){
			jumpShooting = false;
		}
		
		// TODO still a bunch of issues
		if (jetpack) {
			walking = idling = jumping = falling = shooting = jumpShooting = grounded = false;
			jetTimer++;
			if (jetTimer <= 20) {
				dy = -3 * jetTimer * jetTimer / (20 * 20); // exponential
															// jetpack
			} else {
				dy = -3;
			}

			if (right) {
				facingRight = true;
			}
			if (left) {
				facingRight = false;
			}

			if (!(rightMapCollision || leftMapCollision)) {
				if (right) {
					dx = 2;
				}
				if (left) {
					dx = -2;
				} else {
					dx = 0;
				}
			}
		}


		if(dy == 0){
			grounded = true;
		}
		if(topMapCollision && !grounded){
			dy = 0;
		}

		if (facingRight && (dx < 0)) {
			dx = 0;
		}
		if (!facingRight && (dx > 0)) {
			dx = 0;
		}
		
		if(leftMapCollision && (!facingRight || dy < 0)){
			dx = 0;
		}
		
		if(rightMapCollision && (facingRight || dy > 0)){
			dx = 0;
		}
		
		if(bottomMapCollision && !(jumping || jetpack)){
			dy = 0;
		}
		

		// keep bottom of update function
		movementUpdate();

	}
	
	public void movementUpdate(){
		x+=dx;
		y+=dy;
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
		return new Rectangle(this.x+10, (int)this.y, 10, 30);
	}
	
	public Rectangle getLRRect(){
		return new Rectangle(this.x, (int)this.y+10, 30, 10);
	}
	
	// new rectangles

	public Rectangle getTopRect() {
		return new Rectangle(this.x + 10, this.y, 10, 15);
	}

	public Rectangle getBottomRect() {
		return new Rectangle(this.x + 10, this.y + 15, 10, 14);
	}

	public Rectangle getLeftRect() {
		return new Rectangle(this.x + 5, this.y + 10, 15, 10);
	}

	public Rectangle getRightRect() {
		return new Rectangle(this.x + 12, this.y + 10, 15, 10);
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
	
	public void setJetpack(boolean b) {
		jetpack = b;
		}

	public BufferedImage jumping(BufferedImage b) {
		
		if((jumping || falling) && !shooting && !jumpShooting){
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

	public BufferedImage jetpackIm(BufferedImage b){
		
		try{
			if(jetpack){
				jetpackIm = image.getSubimage(11, 210, 40, 41);
				if(!facingRight){
					jetpackIm = flip(jetpackIm);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jetpackIm;
	}


}
