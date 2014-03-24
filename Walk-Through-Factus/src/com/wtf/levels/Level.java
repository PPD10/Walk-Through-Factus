package com.wtf.levels;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.wtf.assets.GameAssets;

import static com.wtf.assets.GameAssets.*;

public abstract class Level {

	private TiledMap map;

	public Level(String foldername, String characterFolderName) {
		map = GameAssets.manager.get(formatPath(foldername, characterFolderName));
	}

	public String formatPath(String levelFolderName, String characterFolderName) {
		return MAP_FOLDER_PATH + levelFolderName + characterFolderName
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

}
