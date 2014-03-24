package com.wtf.entities.graphical.foods;

import com.wtf.entities.graphical.GraphicalEntity;
import static com.wtf.assets.GameAssets.*;

public abstract class Food extends GraphicalEntity {
		
	public Food(int x, int y, String fileName) {
		super(x, y, formatPath(fileName));
	}
	
	private static String formatPath(String fileName) {
		return FOOD_FOLDER_PATH + fileName;
	}

}
