package com.wtf.entities.graphical.foods;

import com.wtf.entities.graphical.GraphicalEntity;
import static com.wtf.assets.GameAssets.*;

public abstract class Food extends GraphicalEntity {
	
	private static final int POINTS = 10;
		
	public Food(int x, int y, String fileName) {
		super(x, y, formatPath(fileName));
	}
	
	private static String formatPath(String fileName) {
		return FOOD_FOLDER_PATH + fileName;
	}
	
	public static int getPoints() {
		return POINTS;
	}

}
