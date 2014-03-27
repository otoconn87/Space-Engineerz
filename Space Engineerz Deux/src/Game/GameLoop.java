package Game;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Map.LevelOne;
import Sprites.Laser;
import Sprites.Lobster;
import Sprites.Player;

@SuppressWarnings("serial")
public class GameLoop extends Applet implements Runnable, KeyListener {	

	public int x, y, laserX;
	public int walkTimer = 0;

	public Image offscreen;
	public Graphics d;
	
	
	public boolean select, down, up, cursor;
	public boolean left, right, jump, shoot; // directional buttons

	public boolean walking, idling, shooting,dead; // character states
	
	public boolean lobsterPlayerCollision, lobsterLaserCollision;

	public BufferedImage background,  playerAnimations, lobsterAnimations, laserAnimations;
	public Menu menu;
	public LevelOne levelOne;
	public BufferedImage gameMapBlocked[][];
	public BufferedImage gameMapPassed[][];
	public Rectangle levelOneBlockedRectangles[][];
	public int[][] levelOneMap;
	public Player player;
	public ArrayList<Lobster> lobsters;
	public ArrayList<Laser> lazer;
	
	public int gameTimer = 0;
	public Laser laser;
	

			
	public void run() {
		
		cursor = true;
		menu = new Menu("megaman_menu.jpg", "menuSprites.png", "MegaMan7.gif");
		
		player = new Player("space_player.png");
		player.setFacingRight(true);
		player.setPosition(100, 100);
			
		lobsters = new ArrayList<Lobster>();
		lazer = new ArrayList<Laser>();

		Lobster l;		
		Point[] points = new Point[]{
				new Point(400,275)
//				new Point(860,200),
//				new Point(1525,200),
//				new Point(1680,200),
//				new Point(1800,200)
		};
		for(int i = 0; i < points.length; i++){
			l = new Lobster("space_lobster.png");
			l.setFacingRight(false);
			l.setPosition(points[i].x, points[i].y);
			lobsters.add(l);
		}		
		
		
		
		
		lobsterPlayerCollision = false;
		lobsterLaserCollision = false;
		
		levelOne = new LevelOne("level1_space.png", getClass()
				.getResourceAsStream("space_map.map"));
		// levelOne.loadMap();
		gameMapBlocked = levelOne.getLevelOneBlockedTiles();
		gameMapPassed = levelOne.getLevelOnePassTiles();
		levelOneMap = levelOne.getLevelOneMap();
		

		try {

			background = ImageIO.read(getClass().getResourceAsStream(
					"citybg.jpg"));
			

		} catch (IOException e1) {

			e1.printStackTrace();
		}

		while (true) {
			

			
			if (!player.left && !player.right && !player.falling && !player.jumping && !player.shooting){
				player.setIdling(true);
			}
			else{
				player.setIdling(false);
			}
			

			if(player.dead){
				System.out.println("Game Over");
			}

			player.update();
			createLazer();
			updateLaser();
			lobsterPlayerCollision();
			lobsterMovement();
			checkMapCollision();
			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void checkMapCollision() {
		for (int i = 0; i < levelOne.getMapHeight(); i++) {
			for (int j = 0; j < levelOne.getMapWidth(); j++) {
				if (levelOneMap[i][j] > 19) {
					Rectangle rect = new Rectangle(j * levelOne.pixelWidth, i
							* levelOne.pixelHeight, levelOne.pixelWidth,
							levelOne.pixelWidth);

					if (player.mapCollision(player.getTBRect(), rect)) {

						if (player.inAir) {
							player.setTopMapCollision(true);
						} else if (player.falling) {
							player.setBottomMapCollision(true);
						}
					}
					if (player.mapCollision(player.getLRRect(), rect)) {
						if (player.left) {
							player.setLeftMapCollision(true);
							player.setRightMapCollision(false);
							System.out.println(player.leftMapCollision);
						}
						if (player.right) {
							player.setRightMapCollision(true);
							player.setLeftMapCollision(false);
						}
					}
				}
			}
		}
	}

	private void updateLaser() {
		for(int i=0; i < lazer.size(); i++){
			lazer.get(i).update();
		}
		checkLaserCollision();
		checkLaserLobsterCollision();
	}

	private void createLazer(){
		if(player.shootLaser){
			player.shootLaser = false;		// only one shot per button pressed
			shoot = false;
			
			Laser lz;
			
			lz = new Laser("space_player.png");
			if (player.facingRight) {
				lz.setPosition(player.getX()+20, player.getY());
				lz.setFacingRight(true);
				lz.setRight();
			} else {
				lz.setPosition(player.getX()-20, player.getY());
				lz.setFacingRight(false);
				lz.setLeft();
			}	
			lazer.add(lz);
		}
	}

	private void checkLaserLobsterCollision() {
		for(int i=0; i < lazer.size(); i++){
			for(int j = 0; j < lobsters.size(); j++){
				if ((lobsters.get(j).getRect().intersects(lazer.get(i).getRect())) && !lobsterLaserCollision) {
					lobsterLaserCollision = true;
					lobsters.get(j).health -= 1;
					player.shootLaser = false;
					lazer.remove(i);
					lobsterLaserCollision = false;
					System.out.println("Lobster Health:\t"+lobsters.get(j).health);				
				}
				if(lobsters.get(j).health == 0){
					lobsters.get(j).dead = true;
					lobsters.remove(j);
				}
			}
		}		
	}

	private void checkLaserCollision() {
		
		for(int k=0; k < lazer.size(); k++){
			for (int i = 0; i < levelOne.getMapHeight(); i++) {
				for (int j = 0; j < levelOne.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(j * levelOne.pixelWidth, i
								* levelOne.pixelHeight, levelOne.pixelWidth,
								levelOne.pixelWidth);

						if (lazer.get(k).mapCollision(lazer.get(k).getRect(), rect)) {
							if (!lazer.get(k).facingRight) {
								lazer.get(k).setLeftMapCollision(true);
								lazer.get(k).setRightMapCollision(false);
							}
							if (lazer.get(k).facingRight) {
								lazer.get(k).setRightMapCollision(true);
								lazer.get(k).setLeftMapCollision(false);
							}
							player.shootLaser = false;
							lazer.remove(k);
							return;
						}
					}
				}
			}
		}	
	}

	private void lobsterMovement() {

		for(int i = 0; i < lobsters.size(); i++){
			if (!lobsters.get(i).facingRight) {
				lobsters.get(i).setLeft();
			} else {
				lobsters.get(i).setRight();
			}
			if (lobsters.get(i).getX() == 200) {
				lobsters.get(i).setRight();
			}
			if (lobsters.get(i).getX() == 400) {
				lobsters.get(i).setLeft();
			}
		}
	}


	private void lobsterPlayerCollision() {
	
		for(int i = 0; i < lobsters.size(); i++){
			if ((lobsters.get(i).getRect().intersects(player.getLRRect())) && !lobsterPlayerCollision) {
				lobsterPlayerCollision = true;
				player.health -= 1;
				System.out.println("Player Health:\t"+player.health);	
				if(player.health == 0){
					player.dead = true;
				}
			}
			if(!(lobsters.get(i).getRect().intersects(player.getLRRect()))){
				lobsterPlayerCollision = false;
			}
		}				
	}


	public void keyPressed(KeyEvent key) {

		if (key.getKeyCode() == 37) {
			player.setLeft(true);
			
		}
		if (key.getKeyCode() == 39) {
			player.setRight(true);
			
		}
		if (key.getKeyCode() == 38) {
			up = true;
			cursor = true;
			
		}
	
		if (key.getKeyCode() == 40) {
			down = true;
			cursor = false;
			
		}
		if (key.getKeyCode() == 87) {
			player.setJump(true);
		}
		
		if (key.getKeyCode() == 70){
			player.setShoot(true);
		}
		if(key.getKeyCode() == 10){
			select = true;
			
		}
		
	}

	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == 37) {
			player.setLeft(false);			
		}
		if (key.getKeyCode() == 39) {
	
			player.setRight(false);
		}
		if (key.getKeyCode() == 87) {
//			player.setJump(false);
		}
	
		if (key.getKeyCode() == 70){
			player.setShoot(false);
			player.shotOnce = false;
		}
		if(key.getKeyCode() == 10){
			select = false;		
		}
		if (key.getKeyCode() == 38) {
			up = false	;		
		}
	
		if (key.getKeyCode() == 40) {
			down = false;
		}
			
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// unused

	}
}
