package com.wtf.entities.infos;

import com.wtf.entities.Entity;

public class Score extends Entity {

	private int score = 0;

	public Score() {
		super();
	}

	public Score(int x, int y) {
		super(x, y);
	}

	public void add(int points) {
		score += points;
	}

	@Override
	public String toString() {
		return "Score : " + score;
	}

}
