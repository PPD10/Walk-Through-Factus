package com.wtf.entities.graphical.characters;

import com.wtf.entities.graphical.foods.Food;
import com.wtf.entities.graphical.foods.FoodEnum;
import com.wtf.entities.graphical.foods.FoodFactory;

public class FluffyBall extends Character {

	public static final String FOLDER_NAME = "fluffyBall/";

	public FluffyBall() {
		super(FOLDER_NAME);
	}

	@Override
	public Food getFood(int x, int y) {
		return FoodFactory.getFood(FoodEnum.LOLLIPOP, x, y);
	}

}
