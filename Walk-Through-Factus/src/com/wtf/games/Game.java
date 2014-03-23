package com.wtf.games;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.wtf.entities.graphical.characters.Character;
import com.wtf.entities.graphical.characters.CharacterEnum;
import com.wtf.entities.infos.HealthPoints;
import com.wtf.levels.Level;
import com.wtf.levels.LevelEnum;

public class Game {

	private Character character;
	private Level level;
	private HealthPoints healthPoints;

	public Game(CharacterEnum characterName, LevelEnum levelName) {
		character = Character.getCharacterFactory().getCharacter(characterName);
		level = Level.getLevelFactory().getLevel(levelName, character);

		healthPoints = new HealthPoints();
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

	public void checkCollisions() {
		TiledMapTileLayer layer = (TiledMapTileLayer) level.getMap()
				.getLayers().get("collisions");

		// Position en pixels
		int leftX = character.getX();
		int centerX = character.getX() + character.getWidth() / 2;
		int rightX = character.getX() + character.getWidth();
		int downY = character.getY();

		// Position en cellules
		int mapLeftX = (int) (leftX / layer.getTileWidth());
		int mapCenterX = (int) (centerX / layer.getTileWidth());
		int mapRightX = (int) (rightX / layer.getTileWidth());
		int mapDownY = (int) (downY / layer.getTileHeight());

		checkSeaCollision(layer, mapCenterX, mapDownY);
		checkGroundCollision(layer, mapCenterX, mapDownY);
		checkWallCollision(layer, mapRightX, mapDownY);
		checkFactusCollision(layer, mapRightX, mapLeftX, mapDownY);
		checkEndCollision(layer, mapRightX, mapDownY);
	}

	private void checkSeaCollision(TiledMapTileLayer layer, int mapCenterX,
			int mapDownY) {
		if (cellHasLayerProperty(layer, mapCenterX, mapDownY - 1, "sea"))
			character.lose();
	}

	private void checkGroundCollision(TiledMapTileLayer layer, int mapCenterX,
			int mapDownY) {
		if (!cellHasLayerProperty(layer, mapCenterX, mapDownY - 1, "ground"))
			character.fallDown((int) ((mapDownY - 1) * layer.getTileHeight()));
	}

	private void checkWallCollision(TiledMapTileLayer layer, int mapRightX,
			int mapDownY) {
		if (!cellHasLayerProperty(layer, mapRightX + 1, mapDownY, "wall"))
			character.moveForward();
	}

	private void checkFactusCollision(TiledMapTileLayer layer, int mapRightX,
			int mapLeftX, int mapDownY) {
		for (int mapX = mapRightX; mapX > mapLeftX; --mapX)
			if (cellHasLayerProperty(layer, mapX, mapDownY, "factus")) {
				// Thread.sleep(500); ?
				// Déplacement du personnage après le factus
				character.setX((int) ((mapX + 1) * layer.getTileWidth()));
				healthPoints.lose();
				if (healthPoints.allLost())
					character.lose();
				return;
			}
	}

	private void checkEndCollision(TiledMapTileLayer layer, int mapRightX,
			int mapDownY) {
		if (cellHasLayerProperty(layer, mapRightX, mapDownY, "end"))
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
