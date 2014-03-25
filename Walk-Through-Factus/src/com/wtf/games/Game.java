package com.wtf.games;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.wtf.entities.EntityRenderer;
import com.wtf.entities.graphical.characters.Character;
import com.wtf.entities.graphical.characters.CharacterEnum;
import com.wtf.entities.graphical.characters.CharacterFactory;
import com.wtf.entities.graphical.foods.Food;
import com.wtf.entities.infos.HealthPoints;
import com.wtf.levels.Level;
import com.wtf.levels.LevelEnum;

public class Game {

	private Character character;
	private Level level;
	private HealthPoints healthPoints;
	private ArrayList<Food> foods;

	public Game(CharacterEnum characterName, LevelEnum levelName) {
		character = CharacterFactory.getCharacter(characterName);
		level = character.getLevel(levelName);

		healthPoints = new HealthPoints();
		foods = new ArrayList<Food>();
		setFoods();
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

	public HealthPoints getHealthPoints() {
		return healthPoints;
	}

	public void render(EntityRenderer entityRenderer, BitmapFont font,
			SpriteBatch batch, float delta) {
		// Les points de vie
		entityRenderer.render(font, batch, healthPoints, delta);
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
		int downY = character.getY();

		// Position en cellules
		int tileLeftX = (int) (leftX / layer.getTileWidth());
		int tileCenterX = (int) (centerX / layer.getTileWidth());
		int tileRightX = (int) (rightX / layer.getTileWidth());
		int tileDownY = (int) (downY / layer.getTileHeight());

		checkSeaCollision(layer, tileCenterX, tileDownY);
		checkGroundCollision(layer, tileCenterX, tileDownY);
		checkWallCollision(layer, tileRightX, tileDownY);
		checkFactusCollision(layer, tileRightX, tileLeftX, tileDownY);
		checkEndCollision(layer, tileRightX, tileDownY);
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
				// Thread.sleep(500); ?
				// Déplacement du personnage après le factus
				character.setX((int) ((tileX + 1) * layer.getTileWidth()));
				healthPoints.lose();
				if (healthPoints.allLost())
					character.lose();
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
		return character.hasWon() || character.hasLost();
	}

}
