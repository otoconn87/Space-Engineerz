package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	
	public BufferedImage bg;
	public BufferedImage mSprites;
	
	public Menu(String background, String menuSprites){
		try {
			bg = ImageIO.read(getClass().getResourceAsStream(background));
			mSprites = ImageIO.read(getClass().getResourceAsStream(menuSprites));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getMenuBG(){
		return bg;
	}
	
	public BufferedImage getNGSprite(){
		return mSprites.getSubimage(24, 12, 242, 59);
	}
	public BufferedImage getQuitSprite(){
		return mSprites.getSubimage(24, 254, 243, 57);
	}
	
}
