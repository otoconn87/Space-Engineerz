

package Sprites;

import java.awt.image.BufferedImage;


public class Player extends Sprites {
	
	public int jumpTimer;
	public int x, y;
	
	public boolean walking, idling, jumping, shooting, jetpack, falling;
	
	public boolean facingRight, left;
	
	public int walkTimer;
	
	public BufferedImage walk; //walking subImages
	public BufferedImage idle;
	public BufferedImage jump;
		
	public Player(String s){
		super(s);
		
		walkTimer = 0;
		jumpTimer = 0;
	}
	
	public int getX(){
		return this.x;
	}
	
	
	
	public int getY(){
		return this.y;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setFacingRight(boolean b){
		facingRight = b;
	}
	
	public void setLeft(){
		
		walking = true;
		setFacingRight(false);
		idling = false;
		jumping = false;
		x-=2;
		
	}
	public void setRight(){
		
		walking = true;
		setFacingRight(true);
		idling = false;
		jumping = false;
		x+=2;
	}
	
	public void setJump(){
		jumpTimer++;
		walking = false;
		idling = false;
		jumping = true;
		y-= 5;
		if (jumpTimer == 5){
			falling = true;
		}		
		
	}
	
	public void setFalling(){
		falling = true;
	}
	
	public void setIdling(){
		walking = false;
		jumping = false;
		idling = true;
	}
	
	public BufferedImage jumping(BufferedImage b){
		if(facingRight){
			jump = image.getSubimage(72, 122, 40, 41);
		}
		else if (!facingRight){
			jump = image.getSubimage(260, 123, 44, 41);
		}
		
		return jump;
	}	
	
	
	public BufferedImage walking(BufferedImage b){
		walkTimer++;
		
		try{
			
		
		if(facingRight && walking){
			
		
		if(walkTimer >= 1 && walkTimer < 30){
			walk = image.getSubimage(8,43,32,33);
			
		}
		else if(walkTimer >= 30 && walkTimer < 60){
			walk = image.getSubimage(46,43,30,32);
			
		}
		else if (walkTimer >=60 && walkTimer < 90 ){
			walk = image.getSubimage(80,43,36,32);
			
		}
		else{
			walkTimer = 0;
		}
	}
		else if (!facingRight && walking){
			if(walkTimer >= 1 && walkTimer < 30){
			walk = image.getSubimage(333,43,35,34);
			
		}
		else if(walkTimer >= 30 && walkTimer < 60){
			walk = image.getSubimage(299,43,30,35);
			
		}
		else if (walkTimer >=60 && walkTimer < 90 ){
			walk = image.getSubimage(258,43,38,33);
			
		}
		else{
			walkTimer = 0;
		}
		
			
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return walk;		
	}
	
	public BufferedImage idle(BufferedImage b){
		if (facingRight && idling) 
			 idle = image.getSubimage(50, 86, 32, 32);
		else if(!facingRight && idling)
			 idle = image.getSubimage(291,86,35,33);
		
		return idle;
	}
	
	
}
