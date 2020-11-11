package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Entity
{
	static public float x;
	static public float y;
	static private float rot;
	
	static private float xVel;
	static private float yVel;
	static public float rotVel;
	static private float friction;
	
	static private float maxSpeed;
	static private float maxRotSpeed;
	
	Sprite sprite;
	
	public Entity(float x, float y)
	{
		friction = 0.005f;
		maxSpeed = 1f;
		maxRotSpeed = 1f;
		
		this.x = x;
		this.y = y;
		
		sprite = new Sprite(new Texture(new FileHandle(Gdx.files.getLocalStoragePath() + "/bin/resources/Entities/Ship.png")));
		sprite.setSize(Game.getSpriteSize() * Game.getScale(), Game.getSpriteSize() * Game.getScale());
		sprite.setFlip(false, true);
		sprite.setOriginCenter();
		sprite.setCenterX(x);
		sprite.setCenterY(y);
	}
	
	
	public void update()
	{
		move();
	}
	
	public void move()
	{
		
		if(Gdx.input.isKeyPressed(Keys.A) && rotVel > -maxRotSpeed)
			rotVel = rotVel - Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.D) && rotVel < maxRotSpeed)
			rotVel = rotVel + Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			if(xVel < maxSpeed && xVel > -maxSpeed)
				xVel = (float) (xVel + ((Game.getSpriteSize() * Game.getScale()) * Math.cos(sprite.getRotation() / (Game.getSpriteSize() * 1.8))) * (Gdx.graphics.getDeltaTime() * 0.1));
			if(yVel < maxSpeed && yVel > -maxSpeed)
				yVel = (float) (yVel + ((Game.getSpriteSize() * Game.getScale()) * Math.sin(sprite.getRotation() / (Game.getSpriteSize() * 1.8))) * (Gdx.graphics.getDeltaTime() * 0.1));
			System.out.println(xVel);
		}

		                      
		
		/*if(Gdx.input.isKeyPressed(Keys.S) && yVel < maxSpeed)
			yVel = yVel + Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.W) && yVel > -maxSpeed)
			yVel = yVel - Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.D) && xVel < maxSpeed)
			xVel = xVel + Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.A) && xVel > -maxSpeed)
			xVel = xVel - Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.Q) && rotVel > -maxRotSpeed)
			rotVel = rotVel - Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.E) && rotVel < maxRotSpeed)
			rotVel = rotVel + Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.X))
		{
			xVel = 0f;
			yVel = 0f;
			rotVel = 0f;
		}*/

		x = x + xVel;
		y = y + yVel;
		sprite.setCenterX(x);
		sprite.setCenterY(y);
		sprite.rotate(rotVel);
		
		
		if(sprite.getRotation() > 360f)
			sprite.setRotation(0f);
		if(sprite.getRotation() < 0f)
			sprite.setRotation(360f);
		
		friction();
		//System.out.println(sprite.getRotation());
	}
	
	public void friction()
	{
		if(xVel > 0)
			xVel = xVel - friction;
		if(xVel < 0)
			xVel = xVel + friction;
		if(yVel > 0)
			yVel = yVel - friction;
		if(yVel < 0)
			yVel = yVel + friction;
		if(rotVel > 0)
			rotVel = rotVel - friction;
		if(rotVel < 0)
			rotVel = rotVel + friction;
	}
	
	public void render()
	{
		sprite.draw(Game.getSpriteBatch());
	}
	
	public void resize()
	{
		sprite.setSize(Game.getSpriteSize() * Game.getScale(), Game.getSpriteSize() * Game.getScale());
		sprite.setOriginCenter();
	}
	

}
