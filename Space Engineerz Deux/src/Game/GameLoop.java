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
import Sprites.Blastik;
import Sprites.BlastikLaser;
import Sprites.KillBot;
import Sprites.KillBotLaser;
import Sprites.Laser;
import Sprites.Lobster;
import Sprites.Player;

@SuppressWarnings("serial")
public class GameLoop extends Applet implements Runnable, KeyListener {

	public int x, y;
	public int walkTimer = 0;

	public Image offscreen;
	public Graphics d;

	public boolean gameStarted, gameOver, gameCompleted, levelOneState, levelOneSet, levelOneBState,
			levelOneBSet, levelOneCState, levelOneCSet, levelOneDState,
			levelOneDSet;
	public boolean select, down, up, cursor;
	public boolean left, right, jump, shoot; // directional buttons

	public boolean walking, idling, shooting, dead; // character states

	public boolean lobsterPlayerCollision, lobsterLaserCollision;

	public BufferedImage background, playerAnimations, lobsterAnimations, laserAnimations, gameOverScreen, completionScreen,	 blastikLaserAnimation;

	public Menu menu;
	public LevelOne levelOne, levelOneB, levelOneC, levelOneD;
	public BufferedImage gameMapBlocked[][];
	public BufferedImage gameMapPassed[][];
	public Rectangle levelOneBlockedRectangles[][];
	public int[][] levelOneMap;
	public Player player;
	public ArrayList<KillBot> killBots;
	public ArrayList<Lobster> lobsters;
	public Blastik blastik;
	public ArrayList<Laser> lazer;
	public ArrayList<KillBotLaser> kbLaser;
	public ArrayList<BlastikLaser> blastikLaser;
	public int bottomCollisionCounter;

	public int gameTimer = 0;

	public Laser laser;
	private int topCollisionCounter;
	private int leftCollisionCounter;
	private int rightCollisionCounter;
	private boolean killBotLaserCollision;
	private boolean killBotLaserPlayerCollision;
	private boolean blastikLaserCollision;
	private boolean blastikLaserPlayerCollision;
	private boolean blastikLASERcollision;
	private boolean BlastikLaserPlayerCollision;
	private boolean LaserBlastikCollision;
	private boolean blastikPlayerCollision;

	public void levelOneSetUp() {
		if (!levelOneSet) {

			lobsterPlayerCollision = false;
			lobsterLaserCollision  = killBotLaserCollision = false;

			levelOne = new LevelOne("level1_space.png", getClass()
					.getResourceAsStream("level1a.map"));
			// levelOne.loadMap();
			gameMapBlocked = levelOne.getLevelOneBlockedTiles();
			gameMapPassed = levelOne.getLevelOnePassTiles();
			levelOneMap = levelOne.getLevelOneMap();

			Lobster l;
			Point[] points = new Point[] { new Point(400, 275)
			// new Point(860,200),
			// new Point(1525,200),
			// new Point(1680,200),
			// new Point(1800,200)
			};
			for (int i = 0; i < points.length; i++) {
				l = new Lobster("space_lobster.png");
				l.setFacingRight(false);
				l.setPosition(points[i].x, points[i].y);
				lobsters.add(l);
			}

			KillBot k;

			Point[] p = new Point[]{
					new Point(209, 186),
					new Point(493, 192),
					new Point(411, 296)
					
			};
			for(int i=0; i<p.length; i++){
				k = new KillBot("killBotBoss.png", player);
				k.setFacingRight(false);
				k.setPosition(p[i].x, p[i].y);
				killBots.add(k);
			}
			
			System.out.println(killBots.size());
			
			
			
	


			try {

				background = ImageIO.read(getClass().getResourceAsStream(
						"futuropolis.jpg"));

			} catch (IOException e1) {

				e1.printStackTrace();
			}
			levelOneSet = true;
		}
	}

	public void levelOneBSetUp() {
		if (!levelOneBSet) {
			Lobster l;
			Point[] points = new Point[] { new Point(400, 275)
			// new Point(860,200),
			// new Point(1525,200),
			// new Point(1680,200),
			// new Point(1800,200)
			};
			for (int i = 0; i < points.length; i++) {
				l = new Lobster("space_lobster.png");
				l.setFacingRight(false);
				l.setPosition(points[i].x, points[i].y);
				lobsters.add(l);
			}

			lobsterPlayerCollision = false;
			lobsterLaserCollision = false;

			levelOneB = new LevelOne("tileset2.png", getClass()
					.getResourceAsStream("level1b.map"));
			// levelOne.loadMap();
			gameMapBlocked = levelOneB.getLevelOneBlockedTiles();
			gameMapPassed = levelOneB.getLevelOnePassTiles();
			levelOneMap = levelOneB.getLevelOneMap();

			try {

				background = ImageIO.read(getClass().getResourceAsStream(
						"futuropolis.jpg"));

			} catch (IOException e1) {

				e1.printStackTrace();
			}
			levelOneBSet = true;
		}

	}

	public void levelOneCSetUp() {
		if (!levelOneCSet) {
			Lobster l;
			Point[] points = new Point[] { new Point(400, 275)
			// new Point(860,200),
			// new Point(1525,200),
			// new Point(1680,200),
			// new Point(1800,200)
			};
			for (int i = 0; i < points.length; i++) {
				l = new Lobster("space_lobster.png");
				l.setFacingRight(false);
				l.setPosition(points[i].x, points[i].y);
				lobsters.add(l);
			}

			lobsterPlayerCollision = false;
			lobsterLaserCollision = false;

			levelOneC = new LevelOne("tileset2.png", getClass()
					.getResourceAsStream("level1c.map"));
			// levelOne.loadMap();
			gameMapBlocked = levelOneC.getLevelOneBlockedTiles();
			gameMapPassed = levelOneC.getLevelOnePassTiles();
			levelOneMap = levelOneC.getLevelOneMap();

			try {

				background = ImageIO.read(getClass().getResourceAsStream(
						"futuropolis.jpg"));

			} catch (IOException e1) {

				e1.printStackTrace();
			}
			levelOneCSet = true;
		}
	}

	public void levelOneDSetUp() {
		if (!levelOneDSet) {
			blastik = new Blastik("GundamDude.png", player);
			blastik.setPosition(380, 340);
			

			levelOneD = new LevelOne("tileset3.png", getClass()
					.getResourceAsStream("level1d_allt.map"));
			// levelOne.loadMap();
			gameMapBlocked = levelOneD.getLevelOneBlockedTiles();
			gameMapPassed = levelOneD.getLevelOnePassTiles();
			levelOneMap = levelOneD.getLevelOneMap();

			try {

				background = ImageIO.read(getClass().getResourceAsStream(
						"futuropolis.jpg"));

			} catch (IOException e1) {

				e1.printStackTrace();
			}
			levelOneDSet = true;
		}
	}

	public void run() {

		try {
			completionScreen = ImageIO.read(getClass().getResourceAsStream(
					"endgame.jpg"));
			gameOverScreen = ImageIO.read(getClass().getResourceAsStream(
					"gameover.jpeg"));

		} catch (IOException e1) {

			e1.printStackTrace();
		}
		cursor = true;
		menu = new Menu("megaman_menu.jpg", "menuSprites.png", "MegaMan7.gif");

		player = new Player("space_player.png");
		player.setFacingRight(true);

		lobsters = new ArrayList<Lobster>();
		
		killBots = new ArrayList<KillBot>();
		
		lazer = new ArrayList<Laser>();
		
		kbLaser = new ArrayList<KillBotLaser>();
		
		blastikLaser = new ArrayList<BlastikLaser>();
		

		while (true) {

			if (player.dead) {
				System.out.println("Game Over");
			}
			if (levelOneState && !levelOneSet) {
				levelOneSetUp();

			}
			if (levelOneBState && !levelOneBSet) {
				levelOneBSetUp();
			}
			if (levelOneCState && !levelOneCSet) {
				levelOneCSetUp();
			}
			if (levelOneDState && !levelOneDSet) {
				levelOneDSetUp();
			}
			
			player.update();
			createLazer();
			updateLaser();
			lobsterPlayerCollision();
			lobsterMovement();
			
			//killbot laser
			createKillBotLaser();
			updateKillBotLaser();
			
			//TODO
			//blastik laser
			if(levelOneDState){
				blastikPlayerCollision();		
				createBlastikLaser();
				updateBlastikLaser();
				blastik.update();
			}
			
			
			

			for (int i = 0; i < killBots.size(); i++){
				killBots.get(i).update();
			}
			if(levelOneDState){
			blastik.update();
			}
			

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
		if (gameStarted && levelOneState) {

			for (int i = 0; i < levelOne.getMapHeight(); i++) {
				for (int j = 0; j < levelOne.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(j * levelOne.pixelWidth,
								i * levelOne.pixelHeight, levelOne.pixelWidth,
								levelOne.pixelWidth);

						// Left Map Collision
						if (player.mapCollision(player.getLeftRect(), rect)) {
							leftCollisionCounter++;
							if (player.left && (leftCollisionCounter >= 1)) {
								player.setLeftMapCollision(true);
								player.setRightMapCollision(false);

							}
						}

						// Right Map Collision
						if (player.mapCollision(player.getRightRect(), rect)) {
							rightCollisionCounter++;
							if (player.right && (rightCollisionCounter >= 1)) {
								player.setRightMapCollision(true);
								player.setLeftMapCollision(false);
							}
						}

						// Top Map Collision
						if (player.mapCollision(player.getTopRect(), rect)) {
							topCollisionCounter++;
							if ((player.jumping || player.jetpack)
									&& (topCollisionCounter >= 1)) {
								player.setTopMapCollision(true);
							}
						}

						// Bottom Map Collision
						if (player.mapCollision(player.getBottomRect(), rect)) {
							bottomCollisionCounter++;
							if (!(player.jumping || player.jetpack)
									&& (bottomCollisionCounter >= 1)) {
								player.setBottomMapCollision(true);
							}
						}

						if (player.grounded == true) {
							if ((bottomCollisionCounter == 0)) {
								player.grounded = false;
								player.setBottomMapCollision(false);
								// player.setFalling(true);
								player.setJump(false);
							}
						}
					}
				}
			}
			bottomCollisionCounter = 0;
		}
		if (gameStarted && levelOneBState) {

			for (int i = 0; i < levelOneB.getMapHeight(); i++) {
				for (int j = 0; j < levelOneB.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(
								j * levelOneB.pixelWidth, i
										* levelOneB.pixelHeight,
								levelOneB.pixelWidth, levelOneB.pixelWidth);

						// Left Map Collision
						if (player.mapCollision(player.getLeftRect(), rect)) {
							leftCollisionCounter++;
							if (player.left && (leftCollisionCounter >= 1)) {
								player.setLeftMapCollision(true);
								player.setRightMapCollision(false);

							}
						}

						// Right Map Collision
						if (player.mapCollision(player.getRightRect(), rect)) {
							rightCollisionCounter++;
							if (player.right && (rightCollisionCounter >= 1)) {
								player.setRightMapCollision(true);
								player.setLeftMapCollision(false);
							}
						}

						// Top Map Collision
						if (player.mapCollision(player.getTopRect(), rect)) {
							topCollisionCounter++;
							if ((player.jumping || player.jetpack)
									&& (topCollisionCounter >= 1)) {
								player.setTopMapCollision(true);
							}
						}

						// Bottom Map Collision
						if (player.mapCollision(player.getBottomRect(), rect)) {
							bottomCollisionCounter++;
							if (!(player.jumping || player.jetpack)
									&& (bottomCollisionCounter >= 1)) {
								player.setBottomMapCollision(true);
							}
						}

						if (player.grounded == true) {
							if ((bottomCollisionCounter == 0)) {
								player.grounded = false;
								player.setBottomMapCollision(false);
								// player.setFalling(true);
								player.setJump(false);
							}
						}
					}
				}
			}
			bottomCollisionCounter = 0;
		}

		if (gameStarted && levelOneCState) {

			for (int i = 0; i < levelOneC.getMapHeight(); i++) {
				for (int j = 0; j < levelOneC.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(
								j * levelOneC.pixelWidth, i
										* levelOneC.pixelHeight,
								levelOneC.pixelWidth, levelOneC.pixelWidth);

						// Left Map Collision
						if (player.mapCollision(player.getLeftRect(), rect)) {
							leftCollisionCounter++;
							if (player.left && (leftCollisionCounter >= 1)) {
								player.setLeftMapCollision(true);
								player.setRightMapCollision(false);

							}
						}

						// Right Map Collision
						if (player.mapCollision(player.getRightRect(), rect)) {
							rightCollisionCounter++;
							if (player.right && (rightCollisionCounter >= 1)) {
								player.setRightMapCollision(true);
								player.setLeftMapCollision(false);
							}
						}

						// Top Map Collision
						if (player.mapCollision(player.getTopRect(), rect)) {
							topCollisionCounter++;
							if ((player.jumping || player.jetpack)
									&& (topCollisionCounter >= 1)) {
								player.setTopMapCollision(true);
							}
						}

						// Bottom Map Collision
						if (player.mapCollision(player.getBottomRect(), rect)) {
							bottomCollisionCounter++;
							if (!(player.jumping || player.jetpack)
									&& (bottomCollisionCounter >= 1)) {
								player.setBottomMapCollision(true);
							}
						}

						if (player.grounded == true) {
							if ((bottomCollisionCounter == 0)) {
								player.grounded = false;
								player.setBottomMapCollision(false);
								// player.setFalling(true);
								player.setJump(false);
							}
						}
					}
				}
			}
			bottomCollisionCounter = 0;
		}

		if (gameStarted && levelOneDState) {

			for (int i = 0; i < levelOneD.getMapHeight(); i++) {
				for (int j = 0; j < levelOneD.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(
								j * levelOneD.pixelWidth, i
										* levelOneD.pixelHeight,
								levelOneD.pixelWidth, levelOneD.pixelWidth);

						// Left Map Collision
						if (player.mapCollision(player.getLeftRect(), rect)) {
							leftCollisionCounter++;
							if (player.left && (leftCollisionCounter >= 1)) {
								player.setLeftMapCollision(true);
								player.setRightMapCollision(false);

							}
						}

						// Right Map Collision
						if (player.mapCollision(player.getRightRect(), rect)) {
							rightCollisionCounter++;
							if (player.right && (rightCollisionCounter >= 1)) {
								player.setRightMapCollision(true);
								player.setLeftMapCollision(false);
							}
						}

						// Top Map Collision
						if (player.mapCollision(player.getTopRect(), rect)) {
							topCollisionCounter++;
							if ((player.jumping || player.jetpack)
									&& (topCollisionCounter >= 1)) {
								player.setTopMapCollision(true);
							}
						}

						// Bottom Map Collision
						if (player.mapCollision(player.getBottomRect(), rect)) {
							bottomCollisionCounter++;
							if (!(player.jumping || player.jetpack)
									&& (bottomCollisionCounter >= 1)) {
								player.setBottomMapCollision(true);
							}
						}

						if (player.grounded == true) {
							if ((bottomCollisionCounter == 0)) {
								player.grounded = false;
								player.setBottomMapCollision(false);
								// player.setFalling(true);
								player.setJump(false);
							}
						}
					}
				}
			}
			bottomCollisionCounter = 0;
		}

	}

	private void updateLaser() {
		for (int i = 0; i < lazer.size(); i++) {
			lazer.get(i).update();
		}
		
		//which map to pass in
		if(levelOneState){
			checkLaserCollision(levelOne);
		}
		if(levelOneBState){
			checkLaserCollision(levelOneB);
		}
		if(levelOneCState){
			checkLaserCollision(levelOneC);
		}
		if(levelOneDState){
			checkLaserCollision(levelOneD);
			checkLaserBlastikCollision();
		}
		checkLaserLobsterCollision();
		checkLaserKillBotCollision();
	}



	private void createLazer() {
		if (player.shootLaser) {
			player.shootLaser = false; // only one shot per button pressed
			shoot = false;

			Laser lz;

			lz = new Laser("space_player.png");
			if (player.facingRight) {
				lz.setPosition(player.getX() + 20, player.getY());
				lz.setFacingRight(true);
				lz.setRight();
			} else {
				lz.setPosition(player.getX() - 20, player.getY());
				lz.setFacingRight(false);
				lz.setLeft();
			}
			lazer.add(lz);
		}
	}
	
	
	private void updateKillBotLaser() {
		for (int i = 0; i < kbLaser.size(); i++) {
			kbLaser.get(i).update();
		}
		checkKBLaserCollision();
		checkLaserPlayerCollision();
	}
	
	//TODO updateBlastikLaser
	private void updateBlastikLaser(){
		for (int i = 0; i < blastikLaser.size(); i++) {
			blastikLaser.get(i).update();
		}
		checkBlastikLaserCollision();
		checkBlastikLaserPlayerCollision();
		checkBlasticHit();
	}

	//TODO checkBlastikLaserCollision
	private void checkBlastikLaserCollision() {
		for (int k = 0; k < blastikLaser.size(); k++) {
			for (int i = 0; i < levelOneD.getMapHeight(); i++) {
				for (int j = 0; j < levelOneD.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(j * levelOneD.pixelWidth,
								i * levelOneD.pixelHeight, levelOneD.pixelWidth,
								levelOneD.pixelWidth);

						if (blastikLaser.get(k).mapCollision(blastikLaser.get(k).getRect(),
								rect)) {
							if (!blastikLaser.get(k).facingRight) {
								blastikLaser.get(k).setLeftMapCollision(true);
								blastikLaser.get(k).setRightMapCollision(false);
							}
							if (blastikLaser.get(k).facingRight) {
								blastikLaser.get(k).setRightMapCollision(true);
								blastikLaser.get(k).setLeftMapCollision(false);
							}
//							killbots.shootLaser = false;
							blastikLaser.remove(k);
							return;
						}
					}
				}
			}
		}
	}

	// TODO checkBlastikLaserPlayerCollision
	private void checkBlastikLaserPlayerCollision() {
		for (int i = 0; i < blastikLaser.size(); i++) {
			if ((player.getPlayerRect().intersects(blastikLaser.get(i)
					.getRect())) && !BlastikLaserPlayerCollision) {
				BlastikLaserPlayerCollision = true;
				player.health -= 5;
				// player.shootLaser = false;
				blastikLaser.remove(i);
				BlastikLaserPlayerCollision = false;
				System.out.println("Player Health:\t" + player.health);
			}
			if (player.health <= 0) {
				player.dead = true;
				// killBots.remove(j);
			}
		}
	}

	private void checkBlasticHit() {
		// TODO Auto-generated method stub
		for (int i = 0; i < blastikLaser.size(); i++) {
			if ((blastik.getRect().intersects(blastikLaser.get(i)
					.getRect())) && !blastikLASERcollision) {
				blastikLASERcollision = true;
				blastik.health -= 100;
//				player.shootLaser = false;
				blastikLaser.remove(i);
				blastikLASERcollision = false;
				System.out.println("Blastic Health:\t"
						+ blastik.health);
			}
			if (blastik.health <= 0) {
				blastik.dead = true;
			}
		
	}
		
	}

	private void checkLaserPlayerCollision() {
		for (int i = 0; i < kbLaser.size(); i++) {
				if ((player.getPlayerRect().intersects(kbLaser.get(i)
						.getRect())) && !killBotLaserPlayerCollision) {
					killBotLaserPlayerCollision = true;
					player.health -= 1;
//					player.shootLaser = false;
					kbLaser.remove(i);
					killBotLaserPlayerCollision = false;
					System.out.println("Player Health:\t"
							+ player.health);
				}
				if (player.health <= 0) {
					player.dead = true;
//					killBots.remove(j);
				}
			
		}
	}

	//TODO createBlastikLaser
	private void createBlastikLaser() {
		
		if (blastik.shootLaser) {
			blastik.shootLaser = false; // only one shot per button pressed
			shoot = false;

			BlastikLaser lz;

			lz = new BlastikLaser("GundamDude.png", player);
			
			if (blastik.facingRight) {
				lz.setPosition(blastik.getX() + 70, blastik.getY());
				lz.setFacingRight(true);
				lz.setRight();
			} else {
				lz.setPosition(blastik.getX() - 70, blastik.getY());
				lz.setFacingRight(false);
				lz.setLeft();
			}
			blastikLaser.add(lz);
		}
		
	}
	
	
	private void createKillBotLaser() {
		for(int i = 0; i < killBots.size(); i++){
			if (killBots.get(i).shootLaser) {
				killBots.get(i).shootLaser = false; // only one shot per button pressed
				shoot = false;

				KillBotLaser lz;

				lz = new KillBotLaser("space_player.png");
				if (killBots.get(i).facingRight) {
					lz.setPosition(killBots.get(i).getX() + 20, killBots.get(i).getY());
					lz.setFacingRight(true);
					lz.setRight();
				} else {
					lz.setPosition(killBots.get(i).getX() - 20, killBots.get(i).getY());
					lz.setFacingRight(false);
					lz.setLeft();
				}
				kbLaser.add(lz);
			}
		}
		
	}

	
	private void checkLaserKillBotCollision() {
		for (int i = 0; i < lazer.size(); i++) {
			for (int j = 0; j < killBots.size(); j++) {
				if ((killBots.get(j).getRect().intersects(lazer.get(i)
						.getRect())) && !killBotLaserCollision) {
					killBotLaserCollision = true;
					killBots.get(j).health -= 1;
					player.shootLaser = false;
					lazer.remove(i);
					killBotLaserCollision = false;
					System.out.println("Kill Bot Health:\t"
							+ killBots.get(j).health);
				}
				if (killBots.get(j).health == 0) {
					killBots.get(j).dead = true;
					killBots.remove(j);
				}
			}
		}
	}
	
	private void blastikPlayerCollision() {
			if ((blastik.getRect().intersects(player.getPlayerRect()))
					&& !blastikPlayerCollision) {
				blastikPlayerCollision = true;
				player.health -= 1;
				System.out.println("Player Health:\t" + player.health);
				if (player.health <= 0) {
					player.dead = true;
				}
			}
			if (!(blastik.getRect().intersects(player.getPlayerRect()))) {
				blastikPlayerCollision = false;
			}
		
	}
	
	private void checkLaserBlastikCollision() {
		for (int i = 0; i < lazer.size(); i++) {
			if(blastik.getRect().intersects(lazer.get(i).getRect()) && !LaserBlastikCollision){
				LaserBlastikCollision = true;
				blastik.health -= 1; // lol
				player.shootLaser = false;
				lazer.remove(i);
				LaserBlastikCollision = false;
				System.out.println("Blastik Health:\t" + blastik.health);
			}
			if(blastik.health <= 0){	// lol
				blastik.dead = true;
			}
		}
	}
	
	private void checkKBLaserCollision() {

		for (int k = 0; k < kbLaser.size(); k++) {
			for (int i = 0; i < levelOne.getMapHeight(); i++) {
				for (int j = 0; j < levelOne.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(j * levelOne.pixelWidth,
								i * levelOne.pixelHeight, levelOne.pixelWidth,
								levelOne.pixelWidth);

						if (kbLaser.get(k).mapCollision(kbLaser.get(k).getRect(),
								rect)) {
							if (!kbLaser.get(k).facingRight) {
								kbLaser.get(k).setLeftMapCollision(true);
								kbLaser.get(k).setRightMapCollision(false);
							}
							if (kbLaser.get(k).facingRight) {
								kbLaser.get(k).setRightMapCollision(true);
								kbLaser.get(k).setLeftMapCollision(false);
							}
//							killbots.shootLaser = false;
							kbLaser.remove(k);
							return;
						}
					}
				}
			}
		}
	}
	
	private void checkLaserLobsterCollision() {
		for (int i = 0; i < lazer.size(); i++) {			
			
			for (int j = 0; j < lobsters.size(); j++) {
				if ((lobsters.get(j).getRect().intersects(lazer.get(i)
						.getRect())) && !lobsterLaserCollision) {
					lobsterLaserCollision = true;
					lobsters.get(j).health -= 1;
					player.shootLaser = false;
					lazer.remove(i);
					lobsterLaserCollision = false;
					System.out.println("Lobster Health:\t"
							+ lobsters.get(j).health);
				}
				if (lobsters.get(j).health == 0) {
					lobsters.get(j).dead = true;
					lobsters.remove(j);
				}
			}
		}
	}

	private void checkLaserCollision(LevelOne l) {
		
		LevelOne level = l;

		for (int k = 0; k < lazer.size(); k++) {
			for (int i = 0; i < level.getMapHeight(); i++) {
				for (int j = 0; j < level.getMapWidth(); j++) {
					if (levelOneMap[i][j] > 19) {
						Rectangle rect = new Rectangle(j * level.pixelWidth,
								i * level.pixelHeight, level.pixelWidth,
								level.pixelWidth);

						if (lazer.get(k).mapCollision(lazer.get(k).getRect(),
								rect)) {
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

		for (int i = 0; i < lobsters.size(); i++) {
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

		for (int i = 0; i < lobsters.size(); i++) {
			if ((lobsters.get(i).getRect().intersects(player.getPlayerRect()))
					&& !lobsterPlayerCollision) {
				lobsterPlayerCollision = true;
				player.health -= 1;
				System.out.println("Player Health:\t" + player.health);
				if (player.health <= 0) {
					player.dead = true;
				}
			}
			if (!(lobsters.get(i).getRect().intersects(player.getPlayerRect()))) {
				lobsterPlayerCollision = false;
			}
		}
	}

	public void keyPressed(KeyEvent key) {

		if (key.getKeyCode() == 37) {
			player.setLeft(true);
			player.setRight(false);

		}
		if (key.getKeyCode() == 39) {
			player.setRight(true);
			player.setLeft(false);

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

		if (key.getKeyCode() == 70) {
			player.setShoot(true);
		}
		if (key.getKeyCode() == 10) {
			select = true;

		}
		if (key.getKeyCode() == KeyEvent.VK_E) {
			player.setJetpack(true);
		}

		if (key.getKeyCode() == KeyEvent.VK_P) {
			player.setPosition(109, 300);
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
			// player.setJump(false);
		}

		if (key.getKeyCode() == 70) {
			player.setShoot(false);
			player.shotOnce = false;
		}
		if (key.getKeyCode() == 10) {
			select = false;
		}
		if (key.getKeyCode() == 38) {
			up = false;
		}

		if (key.getKeyCode() == 40) {
			down = false;
		}
		if (key.getKeyCode() == KeyEvent.VK_E) {
			player.setJetpack(false);
			player.bottomMapCollision = false;
			player.setFalling(true);
			player.jetTimer = 0;
		}

	}

	@Override
	public void keyTyped(KeyEvent key) {
		// unused

	}
}
