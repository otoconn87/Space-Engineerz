package Sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Blastik extends Sprites{
	
	public int x, y;
	public int health;

	public boolean walking, shooting, flinching, dead, idle, falling;
	public boolean bottomMapCollision;
	//public LevelOne level;

	public boolean facingRight, left, right, shootLaser;

	public int animationTimer, botTimer, flinchTimer, laserTimer;
	
	//Animation images
	public BufferedImage walk; 
	public BufferedImage shoot; 
	public BufferedImage flinch; 
	public BufferedImage idling;
	
	public Player player;
	
	public Blastik(String s, Player p) {
		super(s);

		animationTimer = 0;
		idle = true;
		health = 500;
		player = p;
		shootLaser = false;
		laserTimer = 0;
		
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.x+40,this.y,25,105);
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

	public void setLeft(boolean b) {
		
		left = b;

	}

	public void setRight(boolean b) {

		right = b;
	}

	public void setFlinching(boolean b) {
		flinching = b;
	}
	
	public void setIdling(boolean b){
		idle = b;
	}
	
	public void laserFire(){		
		shootLaser = true;
	}

	
	
	public void update(){
		
		laserTimer++;
		if(shooting){
			if(laserTimer == 100){
				laserFire();
			}
		}
		if(laserTimer == 400){
			laserTimer = 0;
		}
		
		if(falling){
			y+=2;
		}
		if(bottomMapCollision){
			y+=0;
		}
		
		if(idle){
			shooting = false;
			walking = false;
			x+=0;
			x-=0;
			y+=0;
		}
		
		if((x - player.x) > 0){
			setFacingRight(false);
			
		}
		if((x - player.x) < 0){
			setFacingRight(true);
		}
		

		
		if(((Math.abs((x+40) - player.x) <= 250) && (Math.abs(y - player.y) <=90)) && !shooting){
			idle = false;
			walking = true;
			
			if(facingRight){
				if(Math.abs(x - player.x) <=150){
					x+=0;
					walking = false;
				}
				else if(x <= 470){
					x+=2;
				}
			}
			else if(!facingRight){
				if(Math.abs(x - player.x) <=150){
					x-=0;
					walking = false;
				}
				else if(x >= 300){
				x-=2;
				}
			}
		
		}
		else if((Math.abs(x - player.x) >250)){
			idle = true;
			walking = false;
		}

		
		if(Math.abs((x+40) - player.x) <=250 && (Math.abs(y - player.y) <=90)){
			x+=0;
			x-=0;
			shooting = true;
			idle = false;
			walking = false;
			
		}
		else{
			
			shooting = false;
		}
		
		
		
		if(flinching){
			walking = false;
			idle = false;
			
		}
		
		if(!walking && !shooting){
			idle = true;
		}
		
		
		
	}
	
	public BufferedImage idling(){
			animationTimer++;
		

		try {
			
			if (idle) {
				
				if (animationTimer >= 1 && animationTimer < 15) {
					idling = image.getSubimage(679, 123, 108, 113);

				} 
				

				else {
					animationTimer = 0;
				}

				
			

				if (!facingRight) {
					idling = flip(idling);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return idling;
	}

	public BufferedImage walking() {
		animationTimer++;

		try {
			if (walking) {

				if (animationTimer >= 1 && animationTimer < 15) {
					walk = image.getSubimage(579, 131, 95, 106);

				} 

				else {
					animationTimer = 0;
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

	// TODO shoot
	public BufferedImage shoot() {
		animationTimer++;

		try {
			
			if (shooting) {
				
				 if (animationTimer >= 0 && animationTimer < 500) {
					shoot = image.getSubimage(885, 251, 106, 105);
					

				}
				else {
					animationTimer = 0;
				}

				// Have to Implements walking left
			

				if (!facingRight) {
					shoot = flip(shoot);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shoot;
	}

	public BufferedImage flinch() {
		animationTimer++;
		

		try {
			
			if (flinching) {
				
				if (animationTimer >= 1 && animationTimer < 15) {
					flinch = image.getSubimage(3, 136, 41, 42);

				} else if (animationTimer >= 15 && animationTimer < 30) {
					flinch = image.getSubimage(44, 136, 41, 42);

				} else if (animationTimer >= 30 && animationTimer < 45) {
					flinch = image.getSubimage(84, 135, 42, 43);

				}
				else if (animationTimer >= 30 && animationTimer < 45) {
					flinch = image.getSubimage(126, 132, 38, 46);

				} 
				else if (animationTimer >= 30 && animationTimer < 45) {
					flinch = image.getSubimage(166, 133, 40, 46);

				}else {
					animationTimer = 0;
				}

				// Have to Implements walking left
			

				if (!facingRight) {
					flinch = flip(flinch);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		flinching = false;
		setIdling(true);
		return flinch;
	}

}
