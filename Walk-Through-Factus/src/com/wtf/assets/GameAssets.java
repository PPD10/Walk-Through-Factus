package com.wtf.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

	// Sound effects
	public static final String FACTUS_SOUND = "worlds/soundEffects/factus.mp3";
	public static final String JUMP_SOUND = "worlds/soundEffects/jump.mp3";
	public static final String LOSE_SOUND = "worlds/soundEffects/lose.mp3";
	public static final String MIAM_SOUND = "worlds/soundEffects/miam.mp3";
	public static final String WIN_SOUND = "worlds/soundEffects/win.mp3";

	static {
		manager.setLoader(TiledMap.class, new TmxMapLoader(
				new InternalFileHandleResolver()));
	}

	public static void load(CharacterEnum characterName) {
		load(getFoodName(characterName));

		String folderPath = CHARACTER_FOLDER_PATH
				+ getCharacterFolderName(characterName);

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
		String folderPath = FOOD_FOLDER_PATH + getFoodFilename(foodName);

		manager.load(new AssetDescriptor<Texture>(folderPath, Texture.class));
	}

	public static void load(CharacterEnum characterName, LevelEnum levelName) {
		load(characterName);

		String mapFilenamePath = MAP_FOLDER_PATH
				+ getLevelFolderName(levelName)
				+ getCharacterFolderName(characterName) + MAP_FILENAME;

		manager.load(new AssetDescriptor<TiledMap>(mapFilenamePath,
				TiledMap.class));
	}

	public static String getCharacterFolderName(CharacterEnum characterName) {
		switch (characterName) {
		case FLUFFY_BALL:
			return FLUFFY_BALL_FOLDER_NAME;
		case PUNK_GIRAFFE:
			return PUNK_GIRAFFE_FOLDER_NAME;
		case TELETOCTOPUS:
			return TELETOCTOPUS_FOLDER_NAME;
		default:
			return PUNK_GIRAFFE_FOLDER_NAME;
		}
	}

	public static FoodEnum getFoodName(CharacterEnum characterName) {
		switch (characterName) {
		case FLUFFY_BALL:
			return FluffyBall.FOOD_NAME;
		case PUNK_GIRAFFE:
			return PunkGiraffe.FOOD_NAME;
		case TELETOCTOPUS:
			return Teletoctopus.FOOD_NAME;
		default:
			return PunkGiraffe.FOOD_NAME;
		}
	}

	public static String getFoodFilename(FoodEnum foodName) {
		switch (foodName) {
		case CHOCOLATE_ECLAIR:
			return CHOCOLATE_ECLAIR_FILENAME;
		case LOLLIPOP:
			return LOLLIPOP_FILENAME;
		case SANDWICH:
			return SANDWICH_FILENAME;
		default:
			return SANDWICH_FILENAME;
		}
	}

	public static String getLevelFolderName(LevelEnum levelName) {
		switch (levelName) {
		case LEVEL1:
			return LEVEL1_FOLDER_NAME;
		default:
			return LEVEL1_FOLDER_NAME;
		}
	}
	
	public static void loadSounds() {
		manager.load(new AssetDescriptor<Sound>(FACTUS_SOUND, Sound.class));
		manager.load(new AssetDescriptor<Sound>(JUMP_SOUND, Sound.class));
		manager.load(new AssetDescriptor<Sound>(LOSE_SOUND, Sound.class));
		manager.load(new AssetDescriptor<Sound>(MIAM_SOUND, Sound.class));
		manager.load(new AssetDescriptor<Sound>(WIN_SOUND, Sound.class));
	}

	public static void dispose() {
		manager.clear();
	}

}
