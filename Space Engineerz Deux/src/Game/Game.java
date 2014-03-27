package Game;


import java.awt.Graphics;

import Audio.JukeBox;



@SuppressWarnings("serial")
public class Game extends GameLoop {
	
	public JukeBox audio;
	
	public boolean menuState;
	public boolean levelOneState;
	public boolean newGame;
	public boolean quit;
	public boolean gameStart;
	public boolean choose;
	public int menuTimer;
	
	

		
	public void init(){
		setSize(900, 500);
		Thread thread = new Thread(this);
		thread.start();
		offscreen = createImage(900, 500);
		d = offscreen.getGraphics();
		addKeyListener(this);
		menuState = true;
		choose = true;
		gameTimer = 1;
		
	}
	
	public void paint(Graphics g){
		d.clearRect(0, 0, 900, 500);
		
		if(menuState){
			
			if(gameTimer == 1){
				gameTimer++;
				audio = new JukeBox("Flash man.mp3");
				audio.loop();
			}
			d.clearRect(0, 0, 900, 500);
			d.drawImage(menu.getMenuBG(), 0, 0, this);
			d.drawImage(menu.getNGSprite(), 300, 230, this);
			d.drawImage(menu.getQuitSprite(), 300, 300, this);
			
			if(gameStart){
				audio.stop();
				audio.close();
				gameTimer = 3;
				choose  = false;
				menuTimer++;
				d.drawImage(menu.getStartSprites(), menu.x, menu.y, this );
				if(menuTimer == 101){
					menuState = false;
					levelOneState = true;
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
		
		if(gameTimer == 3){
			audio = new JukeBox("Epic Level Music.mp3");
			audio.loop();
			gameTimer++;
		}
		d.drawImage(background, 0, 0, this);
		
		//d.drawImage(levelOne.tileMap[30],100, 100, this);
		
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
		//d.fillRect(player.x, player.y+10, 30, 10);
	//	d.fillRect(player.x+10, player.y, 10, 30);
		
		 
		
		if(player.idling){
			d.drawImage(player.idle(playerAnimations),  player.x,  player.y, this);
		}
		if((player.jumping || player.falling) && !(player.shooting || player.jumpShooting)){
			d.drawImage(player.jumping(playerAnimations), player.x, player.y, this);
		}
		if(player.walking){
			d.drawImage(player.walking(playerAnimations),  player.x, player.y, this);
		}
		
		if(player.shooting && !player.jumpShooting){
			d.drawImage(player.shoot(playerAnimations), player.x, player.y, this);
		}
		if(player.jumpShooting){
			d.drawImage(player.jumpShoot(playerAnimations), player.x, player.y, this);
		}
		for(int i = 0; i < lobsters.size(); i++){
			if(lobsters.get(0).flinching == true){
				d.drawImage(lobsters.get(0).flinch(lobsterAnimations),  lobsters.get(0).x,  lobsters.get(0).y, this);
			}
			if(lobsters.get(0).walking==true){
				d.drawImage(lobsters.get(0).walking(lobsterAnimations),  lobsters.get(0).x, lobsters.get(0).y, this);
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
		}
		
		
		g.drawImage(offscreen,0,0, this);
		
	}
	
	public void update(Graphics g){
		paint(g);
		
			
		
		//levelOne.paint(g);
	}
	
}
