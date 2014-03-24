package com.wtf.levels;

public class LevelFactory {

	public static Level getLevel(LevelEnum levelName, String characterFolderName) {
		Level level;

		switch (levelName) {
		case LEVEL1:
			level = new Level1(characterFolderName);
			break;
		default:
			level = new Level1(characterFolderName);
			break;
		}

		return level;
	}

}
