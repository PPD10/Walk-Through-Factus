package com.wtf.entities.graphical.foods;

import com.wtf.entities.graphical.GraphicalEntity;

public abstract class Food extends GraphicalEntity {
	
	private static final String FOLDER_PATH = "worlds/entities/foods/";
	
	public Food(int x, int y, String fileName) {
		super(x, y, formatPath(fileName));
	}
	
	private static String formatPath(String fileName) {
		return FOLDER_PATH + fileName;
	}

}
