package com.wtf;

import com.badlogic.gdx.Game;
import com.wtf.screens.PlayScreen;

public class WTF extends Game {

	@Override
	public void create() {
		this.setScreen(new PlayScreen());
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

}
