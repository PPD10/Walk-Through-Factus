package com.wtf.inputs;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.wtf.entities.graphical.characters.Character;

public class PlayerGestureListener implements GestureListener {

	Character character;

	public PlayerGestureListener(Character character) {
		this.character = character;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// Si glissement du doigt vers le haut avec relâchement, saut
		if (velocityY < 0)
			character.jump();
		// Si glissement du doigt vers le bas avec relâchement, plongeon
		else if (velocityY > 0)
			character.dive();
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

}
