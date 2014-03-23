package com.wtf.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wtf.entities.graphical.GraphicalEntity;
import com.wtf.entities.infos.HealthPoints;

public class EntityRenderer {

	public void render(SpriteBatch batch, GraphicalEntity entity, float delta) {
		batch.draw(entity.getRegion(), entity.getX(), entity.getY());
	}

	public void render(BitmapFont font, SpriteBatch batch,
			HealthPoints healthPoints, float delta) {
		font.draw(batch, healthPoints.toString(), healthPoints.getX(),
				healthPoints.getY());
	}

}
