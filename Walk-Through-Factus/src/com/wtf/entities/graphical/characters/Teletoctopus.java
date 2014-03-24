package com.wtf.entities.graphical.characters;

import com.wtf.entities.graphical.foods.Food;
import com.wtf.entities.graphical.foods.FoodEnum;
import com.wtf.entities.graphical.foods.FoodFactory;

public class Teletoctopus extends Character {

	public static final String FOLDER_NAME = "teletoctopus/";

	public Teletoctopus() {
		super(FOLDER_NAME);
	}

	@Override
	public Food getFood(int x, int y) {
		return FoodFactory.getFood(FoodEnum.CHOCOLATE_ECLAIR, x, y);
	}

}