package com.wtf.entities.graphical.characters;

import com.wtf.entities.graphical.foods.Food;
import com.wtf.entities.graphical.foods.FoodEnum;
import com.wtf.entities.graphical.foods.FoodFactory;

public class PunkGiraffe extends Character {

	public static final String FOLDER_NAME = "punkGiraffe/";

	public PunkGiraffe() {
		super(FOLDER_NAME);
	}

	@Override
	public Food getFood(int x, int y) {
		return FoodFactory.getFood(FoodEnum.SANDWICH, x, y);
	}

}