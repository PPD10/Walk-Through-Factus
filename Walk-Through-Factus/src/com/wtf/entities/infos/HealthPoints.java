package com.wtf.entities.infos;

import com.wtf.entities.Entity;

public class HealthPoints extends Entity {

	private int healthPoints = 3;

	public HealthPoints() {
		super();
	}

	public HealthPoints(int x, int y) {
		super(x, y);
	}

	public void gain() {
		healthPoints++;
	}

	public void lose() {
		healthPoints--;
	}

	public boolean allLost() {
		return healthPoints <= 0 ? true : false;
	}

	@Override
	public String toString() {
		return "HP : " + healthPoints;
	}

}
