package com.wtf.entities.graphical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wtf.assets.GameAssets;
import com.wtf.entities.Entity;

public class GraphicalEntity extends Entity {

	private TextureRegion region;

	public GraphicalEntity(int x, int y) {
		super(x, y);
	}

	public GraphicalEntity(int x, int y, String regionPath) {
		this(x, y);

		region = new TextureRegion((Texture) GameAssets.manager.get(regionPath));
	}

	public TextureRegion getRegion() {
		return region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}

	public int getWidth() {
		return region.getRegionWidth();
	}

	public int getHeight() {
		return region.getRegionHeight();
	}

}
