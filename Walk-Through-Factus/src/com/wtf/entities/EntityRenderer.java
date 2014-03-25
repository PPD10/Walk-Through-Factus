package com.wtf.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wtf.entities.graphical.GraphicalEntity;

public class EntityRenderer {

	public void render(SpriteBatch batch, GraphicalEntity entity, float delta) {
		batch.draw(entity.getRegion(), entity.getX(), entity.getY());
	}

	public void render(BitmapFont font, SpriteBatch batch,
			Entity entity, float delta) {
		font.draw(batch, entity.toString(), entity.getX(),
				entity.getY());
	}

}
