package com.wtf.entities.graphical.characters;

import com.wtf.entities.graphical.foods.FoodEnum;

public class FluffyBall extends Character {

	private static final String FOLDER_NAME = "fluffyBall/";
	private static final FoodEnum FOOD_NAME = FoodEnum.LOLLIPOP;

	public FluffyBall() {
		super(FOLDER_NAME, FOOD_NAME);
	}

}
