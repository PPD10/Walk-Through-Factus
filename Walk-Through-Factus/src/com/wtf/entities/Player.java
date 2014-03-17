package com.wtf.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {

	private static final String PATH_WALKING = "entities/characters/giraffe/walking.png";
	private static final String PATH_JUMPING = "entities/characters/giraffe/jumping.png";
	private static final String PATH_DIVING = "entities/characters/giraffe/diving.png";

	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 1;
	private static final float FRAME_DURATION = 0.1f;

	private static final int SPEED = 4;
	
	private static final int JUMPING_PIXELS = 100;
	private static final int DIVING_PIXELS = 100;

	private Animation walking;
	private Animation jumping;
	private Animation diving;

	private Animation currentAnimation;
	private float stateTime;
	private TextureRegion currentFrame;

	public Player() {
		super();

		currentFrame = new TextureRegion();

		walking = new Animation(FRAME_DURATION, getFrames(PATH_WALKING));
		jumping = new Animation(FRAME_DURATION, getFrames(PATH_JUMPING));
		diving = new Animation(FRAME_DURATION, getFrames(PATH_DIVING));

		setCurrentAnimation(walking);

		setX(100);
		setY(124);
	}

	@Override
	public void render(float delta, SpriteBatch batch) {
		stateTime += delta;
		
		// Si le personnage a fini son saut ou son plongeon, il remarche
		if(currentAnimation.isAnimationFinished(stateTime) && (!isWalking())){
			setCurrentAnimation(walking);
		}
		
		currentFrame = currentAnimation.getKeyFrame(stateTime, true);
				
		int x = getX();
		int y = getY();
		
		// Déplacements en ordonnée
		// Si le personnage saute		
		if (isJumping()) {
			float frame = (stateTime % currentAnimation.animationDuration) * (1 / FRAME_DURATION);
			
			if (frame < 1)
				y += JUMPING_PIXELS / 2;
			else if (frame < 3)
				y += JUMPING_PIXELS;
		}
		
		if (isDiving()) {
			x -= DIVING_PIXELS;
		}
		
		// Déplacements en abscisse
		setX(getX() + SPEED);
		
		batch.draw(currentFrame, x, y);
	}

	private TextureRegion[] getFrames(String path) {
		Texture texture = new Texture(Gdx.files.internal(path));
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()
				/ FRAME_COLS, texture.getHeight() / FRAME_ROWS);
		TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		return frames;
	}

	private void setCurrentAnimation(Animation animation) {
		this.currentAnimation = animation;
		stateTime = 0f;
	}

	public boolean isWalking() {
		return currentAnimation == walking;
	}
	
	public boolean isJumping() {
		return currentAnimation == jumping;
	}
	
	public boolean isDiving() {
		return currentAnimation == diving;
	}

	public void jump() {
		if (isWalking())
			setCurrentAnimation(jumping);
	}

	public void dive() {
		if (isWalking())
			setCurrentAnimation(diving);
	}

}
