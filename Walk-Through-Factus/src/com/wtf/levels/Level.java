package com.wtf.levels;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public abstract class Level {

	private static final String FOLDER_PATH = "worlds/maps/";
	private static final String MAP_FILENAME = "map.tmx";

	private TiledMap map;

	public Level(String foldername, String characterFolderName) {
		map = new TmxMapLoader().load(formatPath(foldername,
				characterFolderName));
	}

	public String formatPath(String levelFolderName, String characterFolderName) {
		return FOLDER_PATH + levelFolderName + characterFolderName
				+ MAP_FILENAME;
	}

	public TiledMap getMap() {
		return map;
	}

	public int getMapPixelWidth() {
		MapProperties properties = map.getProperties();
		return (int) properties.get("width", Integer.class)
				* properties.get("tilewidth", Integer.class);
	}

	public int getMapPixelHeight() {
		MapProperties properties = map.getProperties();
		return (int) properties.get("height", Integer.class)
				* properties.get("tileheight", Integer.class);
	}

	public void dispose() {
		map.dispose();
	}

}
