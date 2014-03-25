package com.wtf.games;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.wtf.assets.GameAssets;
import com.wtf.entities.Entity;
import com.wtf.entities.EntityRenderer;
import com.wtf.entities.graphical.characters.Character;
import com.wtf.entities.graphical.characters.CharacterEnum;
import com.wtf.entities.graphical.characters.CharacterFactory;
import com.wtf.entities.graphical.foods.Food;
import com.wtf.entities.infos.HealthPoints;
import com.wtf.entities.infos.Score;
import com.wtf.levels.Level;
import com.wtf.levels.LevelEnum;

public class Game {
	
	private static final String HEALTHPOINTS_KEY = "healthpoints";
	private static final String SCORE_KEY = "score";

	private Character character;
	private Level level;
	private Map<String, Entity> infos;
	private Vector<Food> foods;

	private Sound factusSound;
	private Sound loseSound;
	private Sound miamSound;
	private Sound winSound;
	
	public Game(CharacterEnum characterName, LevelEnum levelName) {
		character = CharacterFactory.getCharacter(characterName);
		level = character.getLevel(levelName);

		infos = new HashMap<String, Entity>();
		infos.put(HEALTHPOINTS_KEY, new HealthPoints());
		infos.put(SCORE_KEY, new Score());
		
		foods = new Vector<Food>();
		setFoods();
		
		factusSound = GameAssets.manager.get(GameAssets.FACTUS_SOUND);
		loseSound = GameAssets.manager.get(GameAssets.LOSE_SOUND);
		miamSound = GameAssets.manager.get(GameAssets.MIAM_SOUND);
		winSound = GameAssets.manager.get(GameAssets.WIN_SOUND);
	}

	// Initialise les foods selon les positions indiquées sur la map
	private void setFoods() {
		TiledMapTileLayer layer = (TiledMapTileLayer) level.getMap()
				.getLayers().get("collisions");
		int tileXMax = layer.getWidth();
		int tileYMax = layer.getHeight();

		for (int tileX = 0; tileX < tileXMax; ++tileX)
			for (int tileY = 0; tileY < tileYMax; ++tileY)
				if (cellHasLayerProperty(layer, tileX, tileY, "food"))
					foods.add(character.getFood(
							(int) (tileX * layer.getTileWidth()),
							(int) (tileY * layer.getTileHeight())));
	}

	public Character getCharacter() {
		return character;
	}

	public Level getLevel() {
		return level;
	}
	
	public Map<String, Entity> getInfos() {
		return infos;
	}

	public HealthPoints getHealthPoints() {
		return (HealthPoints) infos.get(HEALTHPOINTS_KEY);
	}
	
	public Score getScore() {
		return (Score) infos.get(SCORE_KEY);
	}

	public void render(EntityRenderer entityRenderer, BitmapFont font,
			SpriteBatch batch, float delta) {
		// Les infos
		for (Map.Entry<String, Entity> info : infos.entrySet())
			entityRenderer.render(font, batch, info.getValue(), delta);
		// La nourriture
		for (Food food : foods) {
			entityRenderer.render(batch, food, delta);
		}
		// Le personnage
		entityRenderer.render(batch, character, delta);
	}

	public void checkCollisions() {
		TiledMapTileLayer layer = (TiledMapTileLayer) level.getMap()
				.getLayers().get("collisions");

		// Position en pixels
		int leftX = character.getX();
		int centerX = character.getX() + character.getWidth() / 2;
		int rightX = character.getX() + character.getWidth();
		int upY = character.getY() + character.getHeight();
		int downY = character.getY();

		// Position en cellules
		int tileLeftX = (int) (leftX / layer.getTileWidth());
		int tileCenterX = (int) (centerX / layer.getTileWidth());
		int tileRightX = (int) (rightX / layer.getTileWidth());
		int tileUpY = (int) (upY / layer.getTileHeight());
		int tileDownY = (int) (downY / layer.getTileHeight());

		checkFoodCollision(layer, tileLeftX, tileRightX, tileUpY, tileDownY);
		checkSeaCollision(layer, tileCenterX, tileDownY);
		checkGroundCollision(layer, tileCenterX, tileDownY);
		checkWallCollision(layer, tileRightX, tileDownY);
		checkFactusCollision(layer, tileRightX, tileLeftX, tileDownY);
		checkEndCollision(layer, tileRightX, tileDownY);
	}

	private void checkFoodCollision(TiledMapTileLayer layer, int tileLeftX,
			int tileRightX, int tileUpY, int tileDownY) {
		for (int tileX = tileRightX - 1; tileX > tileLeftX - 1; --tileX)
			for (int tileY = tileUpY - 1; tileY > tileDownY - 1; --tileY)
				for (Food food : foods) {
					int foodTileX = (int) (food.getX() / layer.getTileWidth());
					int foodTileY = (int) (food.getY() / layer.getTileHeight());
					
					if (foodTileX == tileX && foodTileY == tileY) {
						getScore().add(Food.getPoints());
						foods.remove(food);
						
						miamSound.play();
						return;
					}
				}
	}

	private void checkSeaCollision(TiledMapTileLayer layer, int tileCenterX,
			int tileDownY) {
		if (cellHasLayerProperty(layer, tileCenterX, tileDownY - 1, "sea"))
			character.lose();
	}

	private void checkGroundCollision(TiledMapTileLayer layer, int tileCenterX,
			int tileDownY) {
		if (!cellHasLayerProperty(layer, tileCenterX, tileDownY - 1, "ground"))
			character.fallDown((int) ((tileDownY - 1) * layer.getTileHeight()));
	}

	private void checkWallCollision(TiledMapTileLayer layer, int tileRightX,
			int tileDownY) {
		if (!cellHasLayerProperty(layer, tileRightX, tileDownY, "wall"))
			character.moveForward();
	}

	private void checkFactusCollision(TiledMapTileLayer layer, int tileRightX,
			int tileLeftX, int tileDownY) {
		for (int tileX = tileRightX; tileX > tileLeftX; --tileX)
			if (cellHasLayerProperty(layer, tileX, tileDownY, "factus")) {
				// Déplacement du personnage après le factus
				character.setX((int) ((tileX + 1) * layer.getTileWidth()));
				getHealthPoints().lose();
				if (getHealthPoints().allLost())
					character.lose();
				
				factusSound.play();
				return;
			}
	}

	private void checkEndCollision(TiledMapTileLayer layer, int tileRightX,
			int tileDownY) {
		if (cellHasLayerProperty(layer, tileRightX, tileDownY, "end"))
			character.win();
	}

	// Retourne si la cellule en (x, y) de la couche layer a la propriété
	// property
	private boolean cellHasLayerProperty(TiledMapTileLayer layer, int x, int y,
			String property) {
		Cell cell = layer.getCell(x, y);
		return cell.getTile().getProperties().containsKey(property);
	}

	public boolean gameOver() {
		return character.hasFinished();
	}
	
	public void playGameOverSound() {
		if (character.hasWon())
			winSound.play();
		else
			loseSound.play();
	}

}
