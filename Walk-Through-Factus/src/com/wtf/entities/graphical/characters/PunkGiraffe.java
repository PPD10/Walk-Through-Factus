package com.wtf.entities.graphical.characters;

import static com.wtf.assets.GameAssets.*;
import com.wtf.entities.graphical.foods.FoodEnum;

public class PunkGiraffe extends Character {

	public static final FoodEnum FOOD_NAME = FoodEnum.SANDWICH;

	public PunkGiraffe() {
		super(PUNK_GIRAFFE_FOLDER_NAME, FOOD_NAME);
	}

}