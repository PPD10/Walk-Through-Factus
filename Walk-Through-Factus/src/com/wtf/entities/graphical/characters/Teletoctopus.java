package com.wtf.entities.graphical.characters;

import com.wtf.entities.graphical.foods.FoodEnum;

public class Teletoctopus extends Character {

	private static final String FOLDER_NAME = "teletoctopus/";
	private static final FoodEnum FOOD_NAME = FoodEnum.CHOCOLATE_ECLAIR;

	public Teletoctopus() {
		super(FOLDER_NAME, FOOD_NAME);
	}

}