package com.wtf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.wtf.WTF;
import com.wtf.entities.Player;
import com.wtf.inputs.MyGestureListener;

public class GameScreen implements Screen {

	private static final String PATH_MAP = "maps/map.tmx";
	
	private final WTF game;

	private TiledMap map;
	private int mapPixelWidth;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;

	public GameScreen(WTF game) {
		this.game = game;

		map = new TmxMapLoader().load(PATH_MAP);

		MapProperties properties = map.getProperties();

		mapPixelWidth = properties.get("width", Integer.class)
				* properties.get("tilewidth", Integer.class);
		
		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		player = new Player();
		
		Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(player)));
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
	
	// La caméra suit le personnage en abscisse dans la limite de la map
	// Le personnage est placé en abscisse à un quart du viewport,
	// sachant que les coordonnées x, y et z correspondent au point de la map 
	// qui sera affiché au milieu de l'écran
	private void moveCamera() {
		float x = player.getX();
		float y = camera.viewportHeight / 2;

		// Si le personnage n'est pas encore arrivé au quart du viewport
		// on limite l'affichage de la map à gauche
		if (x < camera.viewportWidth / 4)
			x = camera.viewportWidth / 2;
		// Si le personnage est arrivé un quart avant le bout de la map
		// on limite l'affichage de la map à droite
		else if (x + (camera.viewportWidth / 4) > mapPixelWidth - (camera.viewportWidth / 4))
			x = mapPixelWidth - (camera.viewportWidth / 2);
		// Sinon on affiche le personnage à un quart du viewport
		else
			x += camera.viewportWidth / 4;
			
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
