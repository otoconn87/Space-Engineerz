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

	public boolean left, right, jump, down, shoot; // directional buttons

	public boolean walking, idling, shooting,dead; // character states
	
	public boolean lobsterPlayerCollision, lobsterLaserCollision;

	public BufferedImage background, playerAnimations, lobsterAnimations, laserAnimations;
	public LevelOne levelOne;
	public BufferedImage gameMapBlocked[][];
	public BufferedImage gameMapPassed[][];
	public Rectangle levelOneBlockedRectangles[][];
	public int[][] levelOneMap;
	public Player player;
//	public Lobster lobster;
	public ArrayList<Lobster> lobsters;
	
	public int gameTimer = 0;
	public Laser laser;
	

			
	public void run() {
		
		gameTimer++;
		

		player = new Player("space_player.png");
		player.setFacingRight(true);
		player.setPosition(100, 100);
			
		lobsters = new ArrayList<Lobster>();

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
		
		
		
		
		laserX = 0;
		
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
			
			if(gameTimer == 1){
				player.setFalling();
			}
			if(player.dead){
				System.out.println("Game Over");
			}

			playerMovement();
			createLaser();
			checkIntersection();
			lobsterMovement();

//			if(lobsters.get(0).dead){
//				lobsters.remove(0);
////				i--;
//			}
			
			
			
			

			for (int i = 0; i < levelOne.getMapHeight(); i++){
				for(int j = 0; j < levelOne.getMapWidth(); j++){
					if(levelOneMap[i][j] > 19){
						Rectangle rect = new Rectangle(j*levelOne.pixelWidth, i*levelOne.pixelHeight, levelOne.pixelWidth, levelOne.pixelWidth);
						
						if(player.mapCollision(player.getRect(), rect)){
							if(!player.facingRight){
								player.setLeftMapCollision(true);
								player.setRightMapCollision(false);
							}
							if(player.facingRight){
								player.setRightMapCollision(true);
								player.setLeftMapCollision(false);
							}
							if(player.falling){
								player.setBottomMapCollision(true);
								player.setTopMapCollision(false);
							}
						}
					
						
					}
					
				}
			}


			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void createLaser() {
		if(player.shootLaser){
	
			laser = new Laser("space_player.png");
			laser.setPosition(player.getX()+laserX, player.getY());
			if (player.facingRight) {
				laser.setFacingRight(true);
				laser.setRight();
				laserX+=10;
			} else {
				laser.setFacingRight(false);
				laser.setLeft();
				laserX-=10;
			}
			
			checkLaserCollision();
			checkLaserLobsterCollision();
		}else{
			laserX = 0;
		}
	}

	private void checkLaserLobsterCollision() {
		for(int i = 0; i < lobsters.size(); i++){
			if ((lobsters.get(i).getRect().intersects(laser.getRect())) && !lobsterLaserCollision) {
				lobsterLaserCollision = true;
				lobsters.get(i).health -= 1;
				player.shootLaser = false;
				System.out.println("Lobster Health:\t"+lobsters.get(i).health);				
			}
			if(!(lobsters.get(i).getRect().intersects(laser.getRect()))){
				lobsterLaserCollision = false;
			}
			if(lobsters.get(i).health == 0){
				lobsters.get(i).dead = true;
				lobsters.remove(i);
//				i--;
			}
		}
		
		
	}

	private void checkLaserCollision() {
		
		for (int i = 0; i < levelOne.getMapHeight(); i++) {
			for (int j = 0; j < levelOne.getMapWidth(); j++) {
				if (levelOneMap[i][j] > 19) {
					Rectangle rect = new Rectangle(j * levelOne.pixelWidth, i
							* levelOne.pixelHeight, levelOne.pixelWidth,
							levelOne.pixelWidth);

					if (laser.mapCollision(laser.getRect(), rect)) {
						if (!laser.facingRight) {
							laser.setLeftMapCollision(true);
							laser.setRightMapCollision(false);
							player.shootLaser = false;
						}
						if (laser.facingRight) {
							laser.setRightMapCollision(true);
							laser.setLeftMapCollision(false);
							player.shootLaser = false;
						}
					}
				}
			}
		}
	}

	private void playerMovement() {
		if (left == true && player.bottomMapCollision) {
			player.setLeft();
		} else if (right == true && player.bottomMapCollision) {
			player.setRight();
		} else if (jump == true) {
			player.setJump();
		} else if (down == true) {
			if (left == true)
				player.setFacingRight(false);
		} else if (shoot == true){
			player.setShoot();
		} else{
			player.setIdling();
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


	private void checkIntersection() {
		for(int i = 0; i < lobsters.size(); i++){
			if ((lobsters.get(i).getRect().intersects(player.getRect())) && !lobsterPlayerCollision) {
				lobsterPlayerCollision = true;
				player.health -= 1;
				System.out.println("Player Health:\t"+player.health);	
				if(player.health == 0){
					player.dead = true;
				}
			}
			if(!(lobsters.get(i).getRect().intersects(player.getRect()))){
				lobsterPlayerCollision = false;
			}
		}
		
		
	}


	public void keyPressed(KeyEvent key) {

		if (key.getKeyCode() == 37) {
			left = true;
		}
		if (key.getKeyCode() == 39) {
			right = true;
		}
		if (key.getKeyCode() == 87) {
			jump = true;
		}
		if (key.getKeyCode() == 40) {
			down = true;
		}
		if (key.getKeyCode() == 70){
			shoot = true;
		}
	}

	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == 37) {
			left = false;
		}
		if (key.getKeyCode() == 39) {
			right = false;
		}
		if (key.getKeyCode() == 87) {
			jump = false;
		}
		if (key.getKeyCode() == 40) {
			down = false;
		}
		if (key.getKeyCode() == 70){
			shoot = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// unused

	}
}
