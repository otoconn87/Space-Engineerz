package Sprites;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Laser extends Player {

	public int x, y;

	public boolean laserFire, facingRight, left;
	public Player player;

	public BufferedImage laserIm;

	public Laser(String s) {
		super(s);
		laserFire = true;
		
	}

	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, 29, 32);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setFacingRight(boolean b) {
		facingRight = b;
	}

	public void setLeft() {
		setFacingRight(false);
		x -= 3;

	}

	public void setRight() {
		setFacingRight(true);
		x += 3;
	}

	public BufferedImage laserIm(BufferedImage b) {

		try {
			if (laserFire) {
				laserIm = image.getSubimage(133, 8, 29, 32);

				if (!facingRight) {
					AffineTransform imageFlip = AffineTransform
							.getScaleInstance(-1, 1);
					imageFlip.translate(-laserIm.getWidth(null), 0);
					AffineTransformOp op = new AffineTransformOp(imageFlip,
							AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
					laserIm = op.filter(laserIm, null);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return laserIm;
	}
}
