package Game;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Map.LevelOne;
import Sprites.Lobster;
import Sprites.Player;

@SuppressWarnings("serial")
public class GameLoop extends Applet implements Runnable, KeyListener {

	public int x, y;
	public int walkTimer = 0;

	public Image offscreen;
	public Graphics d;

	public boolean left, right, jump, down, shoot; // directional buttons

	public boolean walking, idling, shooting,dead; // character states
	
	public boolean lobsterCollision;

	public BufferedImage background, playerAnimations, lobsterAnimations;
	public LevelOne levelOne;
	public BufferedImage gameMapBlocked[][];
	public BufferedImage gameMapPassed[][];
	public int[][] levelOneMap;
	public Player player;
	public Lobster lobster;
			
	public void run() {

		player = new Player("space_player.png");
		player.setFacingRight(true);
		player.setPosition(100, 100);
		
		lobster = new Lobster("space_lobster.png");
		lobster.setFacingRight(false);
		lobster.setPosition(400, 100);
		
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

			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
