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

	public boolean left, right, jump, down; // directional buttons

	public boolean walking, idling; // character states

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

		} else {
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

		if (lobster.getRect().intersects(player.getRect())) {
			System.out.println("Intersection");
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

	}

	@Override
	public void keyTyped(KeyEvent key) {
		// unused

	}
}
