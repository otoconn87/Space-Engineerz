package Sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Audio.JukeBox;



public class KillBot extends Sprites {
	
	public int x, y;
	public int health;
	
	public JukeBox audio;
	public boolean audioPlayed;

	public boolean walking, shooting, flinching, dead, idle, falling;
	public boolean bottomMapCollision;
	//public LevelOne level;

	public boolean facingRight, left, right, shootLaser;

	public int animationTimer, botTimer, flinchTimer;
	
	//Animation images
	public BufferedImage walk; 
	public BufferedImage shoot; 
	public BufferedImage flinch; 
	public BufferedImage idling;
	
	public Player player;
	
	public KillBot(String s, Player p) {
		super(s);

		animationTimer = 0;
		idle = true;
		health = 3;
		player = p;
		shootLaser = false;
		
		
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
		
		if(shooting){
			if(!audioPlayed){
				audio = new JukeBox("scifi003.mp3");
				audio.play();
				audioPlayed= true;
			}
		}
		if(!shooting){
			audioPlayed = false;
		}
		
		if(falling){
			y+=2;
		}
		if(bottomMapCollision){
			y+=0;
		}
		
		if(idle){
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
		
		if(((Math.abs(x - player.x) <= 200) && (Math.abs(y - player.y) <=20)) && !shooting){
			idle = false;
			walking = true;
			
			if(facingRight){
				if(Math.abs(x - player.x) <=50){
					x+=0;
				}
				else{
					x+=1;
				}
			}
			else if(!facingRight){
				if(Math.abs(x - player.x) <=50){
					x-=0;
				}
				else{
				x-=1;
				}
			}
		
		}
		else{
			idle = true;
			walking = false;
		}
		if(Math.abs(x - player.x) <=100 && (Math.abs(y - player.y) <=20)){
			x+=0;
			x-=0;
			shooting = true;
			idle = false;
			walking = false;
			
		}
		else{
			idle = true;
			shooting = false;
		}
		if(flinching){
			walking = false;
			idle = false;
			
		}
		
		if(walking){
			idle = false;
		}
		
		
		
	}
	
	public BufferedImage idling(){
			animationTimer++;
		

		try {
			
			if (idle) {
				
				if (animationTimer >= 1 && animationTimer < 15) {
					idling = image.getSubimage(1, 2, 36, 39);

				} else if (animationTimer >= 15 && animationTimer < 30) {
					idling = image.getSubimage(38, 2, 40, 38);

				} else if (animationTimer >= 30 && animationTimer < 45) {
					idling = image.getSubimage(78, 2, 37, 38);

				}
				else if (animationTimer >= 30 && animationTimer < 45) {
					idling = image.getSubimage(115, 1, 39, 39);

				} 
				else if (animationTimer >= 30 && animationTimer < 45) {
					idling = image.getSubimage(155, 1, 37, 38);

				}
				else if (animationTimer >= 30 && animationTimer < 45) {
					idling = image.getSubimage(193, 2, 38, 38);

				}
				else if (animationTimer >= 30 && animationTimer < 45) {
					idling = image.getSubimage(230, 2, 37, 38);

				}else {
					animationTimer = 0;
				}

				// Have to Implements walking left
			

				if (facingRight) {
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
					walk = image.getSubimage(4, 45, 30, 38);

				} else if (animationTimer >= 15 && animationTimer < 30) {
					walk = image.getSubimage(37, 45, 31, 38);

				} else if (animationTimer >= 30 && animationTimer < 45) {
					walk = image.getSubimage(67, 44, 40, 38);

				} else if (animationTimer >= 45 && animationTimer < 60) {
					walk = image.getSubimage(112, 46, 44, 38);

				} else if (animationTimer >= 60 && animationTimer < 75) {
					walk = image.getSubimage(162, 46, 27, 38);

				} else if (animationTimer >= 75 && animationTimer < 90) {
					walk = image.getSubimage(202, 46, 27, 38);

				} else if (animationTimer >= 90 && animationTimer < 105) {
					walk = image.getSubimage(239, 46, 33, 37);

				} else if (animationTimer >= 105 && animationTimer < 120) {
					walk = image.getSubimage(278, 44, 42, 38);

				} else if (animationTimer >= 120 && animationTimer < 135) {
					walk = image.getSubimage(321, 44, 39, 38);

				} else if (animationTimer >= 135 && animationTimer < 150) {
					walk = image.getSubimage(363, 45, 27, 38);

				}  else if (animationTimer >= 150 && animationTimer < 175) {
					walk = image.getSubimage(404, 45, 27, 38);

				}else {
					animationTimer = 0;
				}

				// Have to Implements walking left
			

				if (facingRight) {
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
				
				if (animationTimer >= 1 && animationTimer < 15) {
					shoot = image.getSubimage(2, 182, 43, 40);

				} else if (animationTimer >= 15 && animationTimer < 30) {
					shoot = image.getSubimage(44, 183, 44, 40);
					if(animationTimer == 25){
						laserFire();
					}
					

				} else if (animationTimer >= 30 && animationTimer < 45) {
					shoot = image.getSubimage(94, 182, 47, 40);

				} else {
					animationTimer = 0;
				}

				// Have to Implements walking left
			

				if (facingRight) {
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
			

				if (facingRight) {
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
