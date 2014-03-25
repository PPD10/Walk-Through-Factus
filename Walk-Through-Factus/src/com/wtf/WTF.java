package com.wtf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wtf.entities.graphical.characters.CharacterEnum;
import com.wtf.levels.LevelEnum;
import com.wtf.screens.GameScreen;

public class WTF extends Game {

	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		this.setScreen(new GameScreen(this, CharacterEnum.FLUFFY_BALL,
				LevelEnum.LEVEL1));
	}

	@Override
	public void dispose() {
		super.dispose();
		
		batch.dispose();
		font.dispose();
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

	public BitmapFont getFont() {
		return font;
	}

}
