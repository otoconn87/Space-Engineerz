package Game;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	
	public boolean lobsterCollision;

	public BufferedImage background, playerAnimations, lobsterAnimations, laserAnimations;
	public LevelOne levelOne;
	public BufferedImage gameMapBlocked[][];
	public BufferedImage gameMapPassed[][];
	public Rectangle levelOneBlockedRectangles[][];
	public int[][] levelOneMap;
	public Player player;
	public Lobster lobster;
	public Laser laser;
	
			
	public void run() {

		player = new Player("space_player.png");
		player.setFacingRight(true);
		player.setPosition(100, 100);
		
		lobster = new Lobster("space_lobster.png");
		lobster.setFacingRight(false);
		lobster.setPosition(400, 100);
		
		laserX = 0;
		
		lobsterCollision = false;
		
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
			if(player.dead){
				System.out.println("Game Over");
			}

			playerMovement();
			lobsterMovement();
			checkIntersection();
			createLaser();
			
			
//			for(int i = 0; i < levelOne.getMapHeight(); i++){
//				for (int j = 0; j < levelOne.getMapWidth(); j++){
//					if(player.mapCollision(player.getRect(), levelOne.getRect(player.getX() - 32,  player.getY()) ) == true){
//						
//					
//					System.out.println("collision");
//					}
//					
//				else if (player.mapCollision(player.getRect(), levelOne.getRect(player.getX(),  player.getY()) ) == false){
//					System.out.println("no collision");
//				}
//			}
//			}
			
			//player.mapCollision(player.getRect(), );

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
			
//			System.out.println("Laser Game Loop");
			
			laserX+=10;
			
			laser = new Laser("space_player.png");
			laser.setPosition(player.getX()+laserX, player.getY());
			if (player.facingRight) {
				laser.setFacingRight(true);
				laser.setRight();
			} else {
				laser.setFacingRight(false);
				laser.setLeft();
			}


//			player.shootLaser = false;
		}		
	}

	private void playerMovement() {
		if (left == true) {
			player.setLeft();
		} else if (right == true) {
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

		if (!lobster.facingRight) {
			lobster.setLeft();
		} else {
			lobster.setRight();
		}
		if (lobster.getX() == 200) {
			lobster.setRight();
		}
		if (lobster.getX() == 400) {
			lobster.setLeft();
		}

	}


	private void checkIntersection() {
		
		if ((lobster.getRect().intersects(player.getRect())) && !lobsterCollision) {
			lobsterCollision = true;
			player.health -= 1;
			System.out.println("Player Health:\t"+player.health);	
			if(player.health == 0){
				player.dead = true;
			}
		}
		if(!(lobster.getRect().intersects(player.getRect()))){
			lobsterCollision = false;
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
