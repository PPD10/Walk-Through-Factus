package com.wtf.entities.graphical.foods;

public class FoodFactory {

	public static Food getFood(FoodEnum foodName, int x, int y) {
		Food food;

		switch (foodName) {
		case CHOCOLATE_ECLAIR:
			food = new ChocolateEclair(x, y);
			break;
		case LOLLIPOP:
			food = new Lollipop(x, y);
			break;
		case SANDWICH:
			food = new Sandwich(x, y);
			break;
		default:
			food = new Sandwich(x, y);
		}

		return food;
	}

}
