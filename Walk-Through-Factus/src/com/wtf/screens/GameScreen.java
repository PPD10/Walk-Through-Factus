package com.wtf.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.wtf.WTF;
import com.wtf.entities.EntityRenderer;
import com.wtf.entities.graphical.characters.CharacterEnum;
import com.wtf.entities.graphical.foods.Food;
import com.wtf.games.Game;
import com.wtf.inputs.PlayerGestureListener;
import com.wtf.levels.LevelEnum;

public class GameScreen implements Screen {

	private final WTF wtf;

	private Game game;

	private ArrayList<Food> foods;

	private OrthographicCamera camera;

	private OrthogonalTiledMapRenderer mapRenderer;
	private EntityRenderer entityRenderer;

	public GameScreen(WTF wtf, CharacterEnum characterName, LevelEnum levelName) {
		this.wtf = wtf;

		game = new Game(characterName, levelName);

		game.getCharacter().getBackgroundMusic().setLooping(true);
		game.getCharacter().getBackgroundMusic().play();

		foods = new ArrayList<Food>();
		// setFoods();

		// Caméra dont l'affichage est égal à la taille de l'écran
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		// Renderers qui vont s'occuper de l'affichage
		mapRenderer = new OrthogonalTiledMapRenderer(game.getLevel().getMap());
		entityRenderer = new EntityRenderer();

		// Détection des mouvements du joueur sur l'écran
		Gdx.input.setInputProcessor(new GestureDetector(
				new PlayerGestureListener(game.getCharacter())));
	}

	/*
	 * // Initialisation de la nourriture à récolter private void setFoods() {
	 * foods.add(new Food(50, 250, game.getCharacter().getPathFood())); }
	 */

	@Override
	public void render(float delta) {
		// Fond noir
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Si partie non terminée
		if (!game.gameOver()) {
			game.getCharacter().move(delta);
			game.checkCollisions();

			// Mise à jour de la position de la caméra
			updateCameraPosition();

			// Mise à jour de la position des informations
			updateHealthPointsPosition();
		} else {
			// Mise en pause de l'exécution pendant deux secondes
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Affichage de l'écran de fin de partie
			wtf.setScreen(new GameOverScreen(wtf, game));
			dispose();
		}

		// Affichage de la map
		mapRenderer.setView(camera);
		mapRenderer.render();

		// Affichages sur la partie affichée par la caméra
		wtf.getBatch().setProjectionMatrix(camera.combined);
		wtf.getBatch().begin();
		// Les points de vie
		entityRenderer.render(wtf.getFont(), wtf.getBatch(),
				game.getHealthPoints(), delta);
		// La nourriture
		for (Food food : foods) {
			entityRenderer.render(wtf.getBatch(), food, delta);
		}
		// Le personnage
		entityRenderer.render(wtf.getBatch(), game.getCharacter(), delta);
		wtf.getBatch().end();
	}

	// Mise à jour de la position de la caméra
	// Elle suit le personnage en abscisse dans la limite de la map
	// Le bord droit du personnage est placé en abscisse à 1/4 de la zone
	// d'affichage,
	// sachant que les coordonnées x, y et z correspondent au point de la map
	// qui sera affiché au milieu de l'écran
	private void updateCameraPosition() {
		float x = game.getCharacter().getX() + game.getCharacter().getWidth();
		float y = camera.viewportHeight / 2;

		// Si le personnage n'est pas encore arrivé à 1/4 de l'écran,
		// on limite l'affichage de la map sur son bord gauche
		if (x < camera.viewportWidth / 4)
			x = camera.viewportWidth / 2;
		// Si le personnage est arrivé à plus de 3/4 avant la fin de la map,
		// on limite l'affichage de la map sur son bord droit
		else if (x + (camera.viewportWidth / 4) > game.getLevel()
				.getMapPixelWidth() - (camera.viewportWidth * 3 / 4))
			x = game.getLevel().getMapPixelWidth()
					- (camera.viewportWidth * 3 / 4);
		// Sinon on affiche le personnage à 1/4 de l'écran
		else
			x += camera.viewportWidth / 4;

		camera.position.set(x, y, 0);
		camera.update();
	}

	// Mise à jour de la position des points de vie selon la position de la
	// caméra
	// Ils sont placés à 9/10 de l'écran en abscisse et en ordonnée
	// sachant que les coordonnées x et y correspondent au point de la map
	// qui sera affiché au milieu de l'écran
	private void updateHealthPointsPosition() {
		float x = camera.position.x + (camera.viewportWidth * 4 / 10);
		float y = camera.position.y + (camera.viewportHeight * 4 / 10);

		game.getHealthPoints().setX((int) x);
		game.getHealthPoints().setY((int) y);
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
		game.getCharacter().dispose();
		game.getLevel().dispose();
	}

}
