package Game;


import java.awt.Graphics;


@SuppressWarnings("serial")
public class Game extends GameLoop {
	
		
	public void init(){
		setSize(854, 480);
		Thread thread = new Thread(this);
		thread.start();
		offscreen = createImage(854, 480);
		d = offscreen.getGraphics();
		addKeyListener(this);
	}
	//hi guys
	public void paint(Graphics g){
		d.clearRect(0, 0, 854, 480);
		d.drawImage(background, 0, 0, this);
		//d.drawImage(levelOne.tileMap[30],100, 100, this);
	
		
		for (int i = 0; i < levelOne.getMapHeight(); i++){
			for(int j = 0; j < levelOne.getMapWidth(); j++){
				d.drawImage(gameMap[i][j], j*levelOne.pixelHeight, i*levelOne.pixelWidth, this);
			}
		}
		
		if(player.idling == true){
			d.drawImage(player.idle(playerAnimations),  player.x,  player.y, this);
		}
		if(player.walking==true){
			d.drawImage(player.walking(playerAnimations),  player.x, player.y, this);
		}
		if(player.jumping == true){
			d.drawImage(player.jumping(playerAnimations), player.x, player.y, this);
		}
		
		if(lobster.flinching == true){
			d.drawImage(lobster.flinch(lobsterAnimations),  lobster.x,  lobster.y, this);
		}
		if(lobster.walking==true){
			d.drawImage(lobster.walking(lobsterAnimations),  lobster.x, lobster.y, this);
		}
		
		g.drawImage(offscreen,0,0, this);
		
	}
	
	public void update(Graphics g){
		paint(g);
		//levelOne.paint(g);
	}
	
}
