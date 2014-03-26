package Game;


import java.awt.Graphics;



@SuppressWarnings("serial")
public class Game extends GameLoop {
	
		
	public void init(){
		setSize(900, 500);
		Thread thread = new Thread(this);
		thread.start();
		offscreen = createImage(900, 500);
		d = offscreen.getGraphics();
		addKeyListener(this);
	}
	
	public void paint(Graphics g){
		d.clearRect(0, 0, 900, 500);
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
		d.fillRect(player.x, player.y+10, 30, 10);
		d.fillRect(player.x+10, player.y, 10, 30);
		
		 
		
		if(player.idling == true){
			d.drawImage(player.idle(playerAnimations),  player.x,  player.y, this);
		}
		if(player.walking==true){
			d.drawImage(player.walking(playerAnimations),  player.x, player.y, this);
		}
		if(player.jumping == true){
			d.drawImage(player.jumping(playerAnimations), player.x, player.y, this);
		}
		if(player.shooting == true){
			d.drawImage(player.shoot(playerAnimations), player.x, player.y, this);
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
			if(player.shootLaser){
				if(laser.laserFire){
					d.drawImage(laser.laserIm(laserAnimations), laser.x, laser.y, this);
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
