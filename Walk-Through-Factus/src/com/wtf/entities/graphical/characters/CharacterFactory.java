package com.wtf.entities.graphical.characters;

public class CharacterFactory {

	public static Character getCharacter(CharacterEnum characterName) {
		Character character;

		switch (characterName) {
		case FLUFFY_BALL:
			character = new FluffyBall();
			break;
		case PUNK_GIRAFFE:
			character = new PunkGiraffe();
			break;
		case TELETOCTOPUS:
			character = new Teletoctopus();
			break;
		default:
			character = new PunkGiraffe();
		}

		return character;
	}

}
