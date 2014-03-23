package com.wtf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.wtf.WTF;
import com.wtf.games.Game;

public class GameOverScreen implements Screen {

	private final WTF wtf;
	private Game game;

	private OrthographicCamera camera;

	public GameOverScreen(WTF wtf, Game game) {
		this.wtf = wtf;
		this.game = game;

		// Caméra dont l'affichage est égal à la taille de l'écran
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		wtf.getBatch().setProjectionMatrix(camera.combined);
		wtf.getBatch().begin();
		wtf.getFont().draw(wtf.getBatch(),
				game.getCharacter().hasWon() ? "You won !" : "You lose !", 300,
				300);
		wtf.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
