package com.wtf.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.wtf.entities.graphical.characters.CharacterEnum;
import com.wtf.entities.graphical.characters.FluffyBall;
import com.wtf.entities.graphical.characters.PunkGiraffe;
import com.wtf.entities.graphical.characters.Teletoctopus;
import com.wtf.entities.graphical.foods.FoodEnum;
import com.wtf.levels.LevelEnum;

public class GameAssets {

	public static final AssetManager manager = new AssetManager();

	// Character
	public static final String CHARACTER_FOLDER_PATH = "worlds/entities/characters/";

	public static final String FLUFFY_BALL_FOLDER_NAME = "fluffyBall/";
	public static final String PUNK_GIRAFFE_FOLDER_NAME = "punkGiraffe/";
	public static final String TELETOCTOPUS_FOLDER_NAME = "teletoctopus/";

	public static final String WALKING_FILENAME = "walking.png";
	public static final String JUMPING_FILENAME = "jumping.png";
	public static final String DIVING_FILENAME = "diving.png";
	public static final String LOSER_FILENAME = "loser.png";
	public static final String WINNER_FILENAME = "winner.png";

	public static final String MUSIC_FILENAME = "music.mp3";
	
	// Level
	public static final String MAP_FOLDER_PATH = "worlds/maps/";
	public static final String MAP_FILENAME = "map.tmx";
	
	public static final String LEVEL1_FOLDER_NAME = "level1/";

	// Food
	public static final String FOOD_FOLDER_PATH = "worlds/entities/foods/";

	public static final String CHOCOLATE_ECLAIR_FILENAME = "chocolateEclair.png";
	public static final String LOLLIPOP_FILENAME = "lollipop.png";
	public static final String SANDWICH_FILENAME = "sandwich.png";

	public static void load(CharacterEnum characterName) {
		String folderName;
		FoodEnum foodName = null;

		switch (characterName) {
		case FLUFFY_BALL:
			folderName = FLUFFY_BALL_FOLDER_NAME;
			foodName = FluffyBall.FOOD_NAME;
			break;
		case PUNK_GIRAFFE:
			folderName = PUNK_GIRAFFE_FOLDER_NAME;
			foodName = PunkGiraffe.FOOD_NAME;
			break;
		case TELETOCTOPUS:
			folderName = TELETOCTOPUS_FOLDER_NAME;
			foodName = Teletoctopus.FOOD_NAME;
			break;
		default:
			folderName = PUNK_GIRAFFE_FOLDER_NAME;
			foodName = PunkGiraffe.FOOD_NAME;
		}

		load(foodName);

		String folderPath = CHARACTER_FOLDER_PATH + folderName;

		manager.load(new AssetDescriptor<Texture>(
				folderPath + WALKING_FILENAME, Texture.class));
		manager.load(new AssetDescriptor<Texture>(
				folderPath + JUMPING_FILENAME, Texture.class));
		manager.load(new AssetDescriptor<Texture>(folderPath + DIVING_FILENAME,
				Texture.class));
		manager.load(new AssetDescriptor<Texture>(folderPath + LOSER_FILENAME,
				Texture.class));
		manager.load(new AssetDescriptor<Texture>(folderPath + WINNER_FILENAME,
				Texture.class));
		
		manager.load(new AssetDescriptor<Music>(folderPath + MUSIC_FILENAME,
				Music.class));
	}
	
	public static void load(FoodEnum foodName) {
		String fileName;

		switch (foodName) {
		case CHOCOLATE_ECLAIR:
			fileName = CHOCOLATE_ECLAIR_FILENAME;
			break;
		case LOLLIPOP:
			fileName = LOLLIPOP_FILENAME;
			break;
		case SANDWICH:
			fileName = SANDWICH_FILENAME;
			break;
		default:
			fileName = SANDWICH_FILENAME;
		}

		String folderPath = FOOD_FOLDER_PATH + fileName;

		manager.load(new AssetDescriptor<Texture>(folderPath, Texture.class));
	}

	public static void load(CharacterEnum characterName, LevelEnum levelName) {
		load(characterName);
		
		String levelFolderName;
		
		switch(levelName) {
		case LEVEL1:
			levelFolderName = LEVEL1_FOLDER_NAME;
			break;
		default:
			levelFolderName = LEVEL1_FOLDER_NAME;
		}
		
		String characterFolderName;

		switch (characterName) {
		case FLUFFY_BALL:
			characterFolderName = FLUFFY_BALL_FOLDER_NAME;
			break;
		case PUNK_GIRAFFE:
			characterFolderName = PUNK_GIRAFFE_FOLDER_NAME;
			break;
		case TELETOCTOPUS:
			characterFolderName = TELETOCTOPUS_FOLDER_NAME;
			break;
		default:
			characterFolderName = PUNK_GIRAFFE_FOLDER_NAME;
		} 
		
		String mapFilenamePath = MAP_FOLDER_PATH + levelFolderName + characterFolderName + MAP_FILENAME;
		
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load(new AssetDescriptor<TiledMap>(mapFilenamePath, TiledMap.class));
	}
	
	public static void dispose() {
		manager.dispose();
	}

}
