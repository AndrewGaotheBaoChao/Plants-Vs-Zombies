package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.SSound;

public class SplashScreen extends BasicGameState {
	
	// Declare variable
	private Image background;
	public Image playButton;
	
	/**
	 * Create splash screen
	 * @param state	State index
	 */
	public SplashScreen(int state){
		SSound.play("res/Sound/theme/music.ogg", true, 1f, 1f);
	}

	/**
	 * Initialize SplashScreen state
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.getGraphics().setAntiAlias(PZGUI.isAA());

		background = new Image("res/wallpaper.png");
		playButton = new Image("res/Button/playButton.png");
	}

	// Start Button
	public void startButton(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		float rate = 0.5f;
		float width = playButton.getWidth() * PZGUI.getResolutionRateWidth() * rate;
		float height = playButton.getHeight() * PZGUI.getResolutionRateHeight() * rate;
		float posX = PZGUI.getWidth()  /2 - width/2;
		float posY = PZGUI.getHeight() * (float)(0.7) - height/2;
		float edgeX = posX + width;
		float edgeY = posY + height;

		playButton.draw(posX, posY, width, height);
		if (Mouse.getX() >= posX && Mouse.getX() <= edgeX && PZGUI.getHeight() - Mouse.getY() >= posY && PZGUI.getHeight() - Mouse.getY() <= edgeY){
			playButton.draw(posX, posY, width, height, new Color(100, 100, 100, 60));
			
			if (Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
	}

	// background
	public void showBackGround() throws SlickException {
		background.draw(0, 0, PZGUI.getWidth(), PZGUI.getHeight());
	}
	/**
	 * SplashScreen render
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setAntiAlias(PZGUI.isAA());
		showBackGround();
		startButton(gc, sbg, g);
		//DebugTool.showMousePosition(g);
	}	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	}

	public int getID() {
		return 0;
	}
}
