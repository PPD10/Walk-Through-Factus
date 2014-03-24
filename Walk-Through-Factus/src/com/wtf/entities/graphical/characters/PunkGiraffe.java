package com.wtf.entities.graphical.characters;

import com.wtf.entities.graphical.foods.FoodEnum;

public class PunkGiraffe extends Character {

	private static final String FOLDER_NAME = "punkGiraffe/";
	private static final FoodEnum FOOD_NAME = FoodEnum.SANDWICH;

	public PunkGiraffe() {
		super(FOLDER_NAME, FOOD_NAME);
	}

}