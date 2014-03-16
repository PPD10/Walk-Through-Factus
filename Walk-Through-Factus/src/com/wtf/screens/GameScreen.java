package com.wtf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.wtf.WTF;
import com.wtf.entities.Player;

public class GameScreen implements Screen {

	private static final String PATH_MAP = "maps/map.tmx";

	private final WTF game;

	private TiledMap map;
	private int mapPixelWidth;
	private int mapPixelHeight;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;

	public GameScreen(WTF game) {
		this.game = game;

		map = new TmxMapLoader().load(PATH_MAP);

		MapProperties properties = map.getProperties();

		mapPixelWidth = properties.get("width", Integer.class)
				* properties.get("tilewidth", Integer.class);
		mapPixelHeight = properties.get("height", Integer.class)
				* properties.get("tileheight", Integer.class);

		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		player = new Player();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		moveCamera();

		camera.update();

		renderer.setView(camera);
		renderer.render();

		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		player.render(delta, game.getBatch());
		game.getBatch().end();
	}

	// La caméra est centrée sur le personnage en abscisse 
	// dans la limite de la map
	private void moveCamera() {
		float x = player.getX() + (player.getWidth() / 2);
		// la caméra doit-elle suvre le perso en y ?
		float y = camera.viewportHeight / 2;

		if (x < camera.viewportWidth / 2)
			x = camera.viewportWidth / 2;
		else if (x > mapPixelWidth - (camera.viewportWidth / 2))
			x = mapPixelWidth - (camera.viewportWidth / 2);

		camera.position.set(x, y, 0);
		camera.update();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
	}

}
