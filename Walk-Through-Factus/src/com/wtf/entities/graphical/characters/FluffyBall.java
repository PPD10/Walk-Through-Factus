package com.wtf.entities.graphical.characters;

import static com.wtf.assets.GameAssets.*;
import com.wtf.entities.graphical.foods.FoodEnum;

public class FluffyBall extends Character {

	public static final FoodEnum FOOD_NAME = FoodEnum.LOLLIPOP;

	public FluffyBall() {
		super(FLUFFY_BALL_FOLDER_NAME, FOOD_NAME);
	}

}
