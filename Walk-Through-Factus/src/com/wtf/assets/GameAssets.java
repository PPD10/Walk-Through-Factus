package com.wtf.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.wtf.entities.graphical.characters.CharacterEnum;
import com.wtf.entities.graphical.characters.FluffyBall;
import com.wtf.entities.graphical.characters.PunkGiraffe;
import com.wtf.entities.graphical.characters.Teletoctopus;
import com.wtf.entities.graphical.foods.FoodEnum;
import com.wtf.levels.LevelEnum;

public class GameAssets {
	
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

	public static final AssetManager manager = new AssetManager();

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
		
		manager.load(new AssetDescriptor<Texture>(folderPath + WALKING_FILENAME, Texture.class));
		manager.load(new AssetDescriptor<Texture>(folderPath + JUMPING_FILENAME, Texture.class));
		manager.load(new AssetDescriptor<Texture>(folderPath + DIVING_FILENAME, Texture.class));
		manager.load(new AssetDescriptor<Texture>(folderPath + LOSER_FILENAME, Texture.class));
		manager.load(new AssetDescriptor<Texture>(folderPath + WINNER_FILENAME, Texture.class));
	}

	public static void load(CharacterEnum characterName, LevelEnum levelName) {
		load(characterName);

	}

	public static void load(FoodEnum foodName) {

	}

	public static void dispose() {
		manager.dispose();
	}

}
