package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	
	public BufferedImage bg;
	public BufferedImage mSprites;
	public BufferedImage selSprites;
	public BufferedImage sprite;
	public int x = 250;
	public int y = 240;
	public int timer = 0;
	
	public Menu(String background, String menuSprites, String selectSprites){
		try {
			bg = ImageIO.read(getClass().getResourceAsStream(background));
			mSprites = ImageIO.read(getClass().getResourceAsStream(menuSprites));
			selSprites = ImageIO.read(getClass().getResourceAsStream(selectSprites));
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
	public BufferedImage getSelectSprite(){
		return selSprites.getSubimage(44, 53, 39, 41);
	}
	public BufferedImage getStartSprites(){
		timer++;
		
		if(timer < 10 && timer >=1){
			 sprite = selSprites.getSubimage(112, 30, 34, 20);
		}
		if(timer <20 && timer >= 10){
			 sprite = selSprites.getSubimage(77, 21, 34, 29);
		}
		if(timer <30 && timer >=20){
			 sprite = selSprites.getSubimage(49, 15, 28, 36);
		}
		if(timer < 40 && timer >=30){
			 sprite = selSprites.getSubimage(28, 7, 22, 44);
		}
		if(timer < 100 && timer >=40){
			y-= 8;
			 sprite = selSprites.getSubimage(15, 1, 13, 50);
		}
		
		return sprite;
	}
	
}
