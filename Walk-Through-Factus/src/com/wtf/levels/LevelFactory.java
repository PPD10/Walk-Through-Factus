package com.wtf.levels;

import com.wtf.entities.graphical.characters.Character;

public class LevelFactory {

	public Level getLevel(LevelEnum levelName, Character character) {
		Level level;

		switch (levelName) {
		case LEVEL1:
			level = new Level1(character);
			break;
		default:
			level = new Level1(character);
			break;
		}

		return level;
	}

}
