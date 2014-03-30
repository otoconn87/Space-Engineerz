package Game;


import java.awt.Graphics;
import java.awt.Rectangle;

import Sprites.Lobster;
import Audio.JukeBox;



@SuppressWarnings("serial")
public class Game extends GameLoop {
	
	public JukeBox audio;
	
	public boolean menuState;
	//public boolean levelOneState;
	public boolean newGame;
	public boolean quit;
	public boolean gameStart;
	public boolean choose;
	public int menuTimer;
	public int waitTimer;
	
	

		
	public void init(){
		setSize(864, 500);
		Thread thread = new Thread(this);
		thread.start();
		offscreen = createImage(854, 500);
		d = offscreen.getGraphics();
		addKeyListener(this);
		menuState = true;
		choose = true;
		gameTimer = 1;
		waitTimer = 0;
		
	}
	
	public void paint(Graphics g){
		d.clearRect(0, 0, 864, 500);
		
		if(menuState){
			
			if(gameTimer == 1){
				gameTimer++;
//				audio = new JukeBox("Flash man.mp3");
//				audio.loop();
			}
			d.clearRect(0, 0, 900, 500);
			d.drawImage(menu.getMenuBG(), 0, 0, this);
			d.drawImage(menu.getNGSprite(), 300, 230, this);
			d.drawImage(menu.getQuitSprite(), 300, 300, this);
			
			if(gameStart){
//				audio.stop();
//				audio.close();
				gameTimer = 3;
				choose  = false;
				menuTimer++;
				d.drawImage(menu.getStartSprites(), menu.x, menu.y, this );
				if(menuTimer == 101){
					menuState = false;
					levelOneState = true;
					
					gameStarted = true;
					
				}
			}
			
			if(choose && (up || cursor)){
				
				d.drawImage(menu.getSelectSprite(),  250, 240, this);
			}
			if(choose && (down || !cursor)){				
				
				d.drawImage(menu.getSelectSprite(),  250, 310, this);
			}
			if(!cursor && select){
				System.exit(0);
			}
			if(cursor && select){
				gameStart = true;
				
				
			}
			
		}
		
		
		if(levelOneState){
			waitTimer++;
			
			if (waitTimer == 100){
				player.setPosition(109, 100);
				System.out.println("done");
			}
			
			if(player.x <= 33 && player.y <=200){
				player.x +=0;
				player.y+=0;
				player.x-=0;
				player.y-=0;
				player.walking = true;
				levelOneMap = null;
				gameMapBlocked = null;
				gameMapPassed = null;
				levelOneMap = null;
				if(lobsters.size() > 0){
					for(int i = 0; i < lobsters.size(); i++){
						lobsters.remove(i);
						
					}	
					}
				if(killBots.size() > 0){
					for(int i = 0; i < killBots.size(); i++){
						killBots.remove(i);
						
					}	
					}
				levelOneState = false;
				levelOneSet = false;
				levelOneBState = true;
				waitTimer = 0;
				
			}
		
		if(gameTimer == 3){
//			audio = new JukeBox("Epic Level Music.mp3");
//			audio.loop();
			gameTimer++;
		}
		d.drawImage(background, 0, 0, this);
		
		
		for (int i = 0; i < levelOne.getMapHeight(); i++){
			for(int j = 0; j < levelOne.getMapWidth(); j++){
				
				d.drawImage(gameMapPassed[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);
			
			}
		}
		
		for (int i = 0; i < levelOne.getMapHeight(); i++){
			for(int j = 0; j < levelOne.getMapWidth(); j++){
				if(levelOneMap[i][j] > 19){
					//Rectangle rect = new Rectangle(j*levelOne.pixelWidth, i*levelOne.pixelHeight, levelOne.pixelWidth, levelOne.pixelWidth);
					
					d.drawImage(gameMapBlocked[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);

				}
				
			}
		
		}
		}


		if(levelOneBState){
			waitTimer++;
			
			if (waitTimer == 5){
				player.setPosition(790, 132);
				System.out.println("done");
			}
			
			if(player.x <= 19 && player.y <=128){
				player.x +=0;
				player.y+=0;
				player.x-=0;
				player.y-=0;
				player.walking = true;
				if(lobsters.size() > 0){
					for(int i = 0; i < lobsters.size(); i++){
						lobsters.remove(i);
						
					}	
					}
				if(killBots.size() > 0){
					for(int i = 0; i < killBots.size(); i++){
						killBots.remove(i);
						
					}	
					}
				levelOneMap = null;
				gameMapBlocked = null;
				gameMapPassed = null;
				
				levelOneBState = false;
				levelOneBSet = false;
				levelOneCState = true;
				waitTimer = 0;
				
			}
		
		
		d.drawImage(background, 0, 0, this);
		
		
		for (int i = 0; i < levelOneB.getMapHeight(); i++){
			for(int j = 0; j < levelOneB.getMapWidth(); j++){
				
				d.drawImage(gameMapPassed[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);
			
			}
		}
		
		for (int i = 0; i < levelOneB.getMapHeight(); i++){
			for(int j = 0; j < levelOneB.getMapWidth(); j++){
				if(levelOneMap[i][j] > 19){
					//Rectangle rect = new Rectangle(j*levelOne.pixelWidth, i*levelOne.pixelHeight, levelOne.pixelWidth, levelOne.pixelWidth);
					
					d.drawImage(gameMapBlocked[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);

				}
				
			}
		
		}
		}
		
		if(levelOneCState){
			waitTimer++;
			
			if (waitTimer == 5){
				player.setPosition(800, 170);
				System.out.println("done");
			}
			
			if((player.x <= 263 && player.x >= 179) && player.y >=515){
				player.x +=0;
				player.y+=0;
				player.x-=0;
				player.y-=0;
				player.falling = true;
				if(lobsters.size() > 0){
				for(int i = 0; i < lobsters.size(); i++){
					lobsters.remove(i);
					
				}	
				}
				if(killBots.size() > 0){
					for(int i = 0; i < killBots.size(); i++){
						killBots.remove(i);
						
					}	
					}
				levelOneMap = null;
				gameMapBlocked = null;
				gameMapPassed = null;
				
				levelOneCState = false;
				levelOneCSet = false;
				levelOneDState = true;
				waitTimer = 0;
				
			}
		
		
		d.drawImage(background, 0, 0, this);
		
		
		for (int i = 0; i < levelOneC.getMapHeight(); i++){
			for(int j = 0; j < levelOneC.getMapWidth(); j++){
				
				d.drawImage(gameMapPassed[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);
			
			}
		}
		
		for (int i = 0; i < levelOneC.getMapHeight(); i++){
			for(int j = 0; j < levelOneC.getMapWidth(); j++){
				if(levelOneMap[i][j] > 19){
					//Rectangle rect = new Rectangle(j*levelOne.pixelWidth, i*levelOne.pixelHeight, levelOne.pixelWidth, levelOne.pixelWidth);
					
					d.drawImage(gameMapBlocked[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);

				}
				
			}
		
		}
		}
		
		if(levelOneDState){
			waitTimer++;
			
			if (waitTimer == 5){
				player.setPosition(414, 171);
				System.out.println("done");
			}
			
//			if((player.x <= 263 && player.x >= 179) && player.y >=515){
//				player.x +=0;
//				player.y+=0;
//				player.x-=0;
//				player.y-=0;
//				player.falling = true;
//				levelOneMap = null;
//				gameMapBlocked = null;
//				gameMapPassed = null;
//				
//				levelOneCState = false;
//				levelOneCSet = false;
//				levelOneDState = true;
//				waitTimer = 0;
//				
//			}
		
		
		d.drawImage(background, 0, 0, this);
		
		
		for (int i = 0; i < levelOneD.getMapHeight(); i++){
			for(int j = 0; j < levelOneD.getMapWidth(); j++){
				
				d.drawImage(gameMapPassed[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);
			
			}
		}
		
		for (int i = 0; i < levelOneD.getMapHeight(); i++){
			for(int j = 0; j < levelOneD.getMapWidth(); j++){
				if(levelOneMap[i][j] > 19){
					//Rectangle rect = new Rectangle(j*levelOne.pixelWidth, i*levelOne.pixelHeight, levelOne.pixelWidth, levelOne.pixelWidth);
					
					d.drawImage(gameMapBlocked[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);

				}
				
			}
		
		}
		}
		
//		d.drawRect(player.x + 10, player.y, 10, 15);
//		d.drawRect(player.x + 10, player.y + 15, 10, 14);
//		d.drawRect(player.x + 5, player.y + 10, 15, 10);
//		d.drawRect(player.x + 12, player.y + 10, 15, 10);
//		d.drawRect(player.x + 5, player.y+2, 20, 25 );
		 
		 
		 
		
		if(player.idling){
			d.drawImage(player.idle(playerAnimations),  player.x,  (int)player.y, this);
		}
		if((player.jumping || player.falling) && !(player.shooting || player.jumpShooting)){
			d.drawImage(player.jumping(playerAnimations), player.x, (int)player.y, this);
		}
		if(player.walking){
			d.drawImage(player.walking(playerAnimations),  player.x, (int)player.y, this);
		}
		
		if(player.shooting && !player.jumpShooting){
			d.drawImage(player.shoot(playerAnimations), player.x, (int)player.y, this);
		}
		if(player.jumpShooting){
			d.drawImage(player.jumpShoot(playerAnimations), player.x, (int)player.y, this);
		}
		if(player.jetpack){
			d.drawImage(player.jetpackIm(playerAnimations), player.x, (int)player.y, this);
		}
		for(int i = 0; i < lobsters.size(); i++){
			if(lobsters.get(i).flinching == true){
				d.drawImage(lobsters.get(i).flinch(lobsterAnimations),  lobsters.get(i).x,  lobsters.get(i).y, this);
			}
			if(lobsters.get(i).walking==true){
				d.drawImage(lobsters.get(i).walking(lobsterAnimations),  lobsters.get(i).x, lobsters.get(i).y, this);
			}
		}
		for(int i = 0; i <killBots.size(); i++){
			if(killBots.get(i).flinching == true){
				d.drawImage(killBots.get(i).flinch(),  killBots.get(i).x,  killBots.get(i).y, this);
			}
			if(killBots.get(i).walking==true){
				d.drawImage(killBots.get(i).walking(),  killBots.get(i).x, killBots.get(i).y, this);
			}
			if(killBots.get(i).idle==true){
				d.drawImage(killBots.get(i).idling(),  killBots.get(i).x, killBots.get(i).y, this);
			}
			if(killBots.get(i).shooting==true){
				d.drawImage(killBots.get(i).shoot(),  killBots.get(i).x, killBots.get(i).y, this);
			}
			
		}
		
		
		
		try{
				for(int i=0; i < lazer.size(); i++){
					if(lazer.get(i).laserFire){
						d.drawImage(lazer.get(i).laserIm(laserAnimations), lazer.get(i).x, lazer.get(i).y, this);
					}
				}			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		g.drawImage(offscreen,0,0, this);
		
	}
	
	public void update(Graphics g){
		paint(g);
		
			
		
		//levelOne.paint(g);
	}
	
}
