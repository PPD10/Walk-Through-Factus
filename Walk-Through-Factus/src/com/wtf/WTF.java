package com.wtf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wtf.screens.GameScreen;

public class WTF extends Game {

	private SpriteBatch batch;
	
	@Override
	public void create() {
        batch = new SpriteBatch();

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}

}
