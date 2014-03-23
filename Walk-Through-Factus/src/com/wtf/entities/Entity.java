package com.wtf.entities;

public abstract class Entity {

	private int x, y;

	public Entity() {

	}

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// Si deux classes Entity ont les mêmes coordonnées, retourne true,
	// Sinon false
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Entity entity = (Entity) obj;
		return (this.x == entity.x && this.y == entity.y);
	}

}
