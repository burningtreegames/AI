package engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.Game;

public class Desktop {
	
	private static LwjglApplicationConfiguration config;
	
	public static void main(String[] args) 
	{
		config = new LwjglApplicationConfiguration();
		config.width = Game.getWindowWidth();
		config.height = Game.getWindowHeight();
		config.title = "Map Editor";
		config.vSyncEnabled = true;
		config.forceExit = true;
		new LwjglApplication(new Game(), config);
	}
}