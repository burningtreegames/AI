package game;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Game implements ApplicationListener
{
	private static int windowWidth = 1024;
	private static int windowHeight = 576;
	private static int spriteSize = 32;
	private static float scale;
	
	private static SpriteBatch spriteBatch;
	
	private static OrthographicCamera camera;
	
	private static Entity entity;
	
	public void create()
	{
		System.out.println("Currently working controls.");

		camera = new OrthographicCamera(Game.getWindowWidth(), Game.getWindowHeight());
		camera.setToOrtho(true, Game.getWindowWidth(), Game.getWindowHeight());
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.enableBlending();
		
		scale = (float)Gdx.graphics.getWidth() / windowWidth;
		
		entity = new Entity(windowWidth / 2, windowHeight / 2);
	}
	
	public void render()
	{
		Gdx.gl.glEnable(GL11.GL_BLEND);
		Gdx.gl.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		entity.render();
		spriteBatch.end();
		
		ShapeRenderer shape = new ShapeRenderer();
		shape.setProjectionMatrix(Game.getCamera().combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(1,0,0,1);
		shape.circle((float) (((Game.getSpriteSize() * Game.getScale()) * Math.cos(entity.sprite.getRotation() / (Game.getSpriteSize() * 1.8)))) + entity.x, (float) ((Game.getSpriteSize()* Game.getScale()) * Math.sin(entity.sprite.getRotation() / (Game.getSpriteSize() * 1.8))) + entity.y, (4f * Game.getScale()));
		//System.out.println(entity.sprite.getRotation());
		shape.end();
		shape.dispose();
		
		//spriteBatch.dispose();
		
		update();
	}
	
	public void update()
	{
		inputListener();
		entity.update();

		Gdx.graphics.setTitle("MapEditor, FPS = " + Gdx.graphics.getFramesPerSecond() + " DT = " + Gdx.graphics.getDeltaTime());
	}
	
	public void inputListener()
	{
		if (Gdx.input.isKeyPressed(Keys.F11)){
		    Boolean fullScreen = Gdx.graphics.isFullscreen();
		        Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
		        if (fullScreen == true)
		        {
		            Gdx.graphics.setWindowedMode(Game.getWindowWidth(), Game.getWindowHeight());
		            scale = (float)Gdx.graphics.getWidth() / windowWidth;
		            entity.resize();
		        }
		        else 
		        {
		            Gdx.graphics.setFullscreenMode(currentMode);
		            scale = (float)Gdx.graphics.getWidth() / windowWidth;
		            entity.resize();
		        }
		        
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
	}
	
	public void pause(){}
	
	public void resize(int arg0, int arg1)
	{
		scale = (float)Gdx.graphics.getWidth() / windowWidth;
		entity.resize();
	}
	
	public void resume(){}
	public void dispose(){}
	
	public static OrthographicCamera getCamera(){return camera;}

	public static float getScale(){return scale;}
	public static float getSpriteSize(){return spriteSize;}
	public static SpriteBatch getSpriteBatch(){return spriteBatch;}
	public static int getWindowWidth(){return windowWidth;}
	public static int getWindowHeight(){return windowHeight;}

}
