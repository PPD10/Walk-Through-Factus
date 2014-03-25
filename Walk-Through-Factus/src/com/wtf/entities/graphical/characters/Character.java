package com.wtf.entities.graphical.characters;

import static com.wtf.assets.GameAssets.CHARACTER_FOLDER_PATH;
import static com.wtf.assets.GameAssets.DIVING_FILENAME;
import static com.wtf.assets.GameAssets.JUMPING_FILENAME;
import static com.wtf.assets.GameAssets.LOSER_FILENAME;
import static com.wtf.assets.GameAssets.MUSIC_FILENAME;
import static com.wtf.assets.GameAssets.WALKING_FILENAME;
import static com.wtf.assets.GameAssets.WINNER_FILENAME;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wtf.assets.GameAssets;
import com.wtf.entities.graphical.GraphicalEntity;
import com.wtf.entities.graphical.foods.Food;
import com.wtf.entities.graphical.foods.FoodEnum;
import com.wtf.entities.graphical.foods.FoodFactory;
import com.wtf.levels.Level;
import com.wtf.levels.LevelEnum;
import com.wtf.levels.LevelFactory;

public abstract class Character extends GraphicalEntity {

	private static final int START_X = 100;
	private static final int START_Y = 150;

	private static final int SPEED = 6;
	private static final int JUMP_UP = 100;

	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 1;
	private static final float FRAME_DURATION = 0.15f;

	private String folderName;
	
	private FoodEnum foodName;

	private Texture walkingTexture;
	private Texture jumpingTexture;
	private Texture divingTexture;
	private Texture loserTexture;
	private Texture winnerTexture;

	private Animation walking;
	private Animation jumping;
	private Animation diving;

	private TextureRegion previousRegion;
	private Animation currentAnimation;
	private float stateTime; // Temps depuis le début de l'animation
	private int currentFrameNumber;

	private TextureRegion loser;
	private TextureRegion winner;

	private Music backgroundMusic;

	private Sound jumpSound;

	public Character(String folderName, FoodEnum foodName) {
		// Initialisation du personnage
		super(START_X, START_Y);

		this.folderName = folderName;
		this.foodName = foodName;

		// Initialisation des textures
		walkingTexture = GameAssets.manager.get(formatPath(WALKING_FILENAME));
		jumpingTexture = GameAssets.manager.get(formatPath(JUMPING_FILENAME));
		divingTexture = GameAssets.manager.get(formatPath(DIVING_FILENAME));
		loserTexture = GameAssets.manager.get(formatPath(LOSER_FILENAME));
		winnerTexture = GameAssets.manager.get(formatPath(WINNER_FILENAME));

		// Initialisation des animations et textureRegions
		walking = new Animation(FRAME_DURATION, getFrames(walkingTexture));
		jumping = new Animation(FRAME_DURATION, getFrames(jumpingTexture));
		diving = new Animation(FRAME_DURATION, getFrames(divingTexture));
		loser = new TextureRegion(loserTexture);
		winner = new TextureRegion(winnerTexture);

		// Initialisation de la musique de fond et du saut
		backgroundMusic = GameAssets.manager.get(formatPath(MUSIC_FILENAME));
		jumpSound = GameAssets.manager.get(GameAssets.JUMP_SOUND);
		
		// Initialisation du personnage en marche
		walk();
	}

	public Level getLevel(LevelEnum levelName) {
		return LevelFactory.getLevel(levelName, folderName);
	}

	public Food getFood(int x, int y) {
		return FoodFactory.getFood(foodName, x, y);
	}

	// Retourne le chemin d'accès du nom de fichier passé en paramètre
	private String formatPath(String filename) {
		return CHARACTER_FOLDER_PATH + folderName + filename;
	}

	// Retourne les frames de la texture d'une animation passée en paramètre
	private TextureRegion[] getFrames(Texture texture) {
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()
				/ FRAME_COLS, texture.getHeight() / FRAME_ROWS);
		TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		return frames;
	}

	private void setPreviousRegion(TextureRegion region) {
		previousRegion = region;
	}

	// Changement d'animation
	private void setCurrentAnimation(Animation animation) {
		this.currentAnimation = animation;
		stateTime = 0f;
		currentFrameNumber = 0;
	}

	// Animation du personnage
	public void move(float delta) {
		stateTime += delta;

		// Si saut ou plongeon terminé, remise en marche du personnage
		if (currentAnimation.isAnimationFinished(stateTime)) {
			walk();
		}

		// La textureRegion précédente affichée
		setPreviousRegion(getRegion());
		// La textureRegion actuelle de l'animation à afficher
		setRegion(currentAnimation.getKeyFrame(stateTime, true));

		setX();

		// Si en saut, déplacement en ordonnée selon le frame de l'animation
		// affiché
		if (isJumping()) {
			// Calcul du frame actuel entre 1 et le nombre de frames total de
			// l'animation
			int frame = (int) Math
					.ceil(((stateTime % currentAnimation.animationDuration) * (1 / FRAME_DURATION)));

			if (frame == 1 && currentFrameNumber != 1) {
				currentFrameNumber = 1;
				setY(getY() + JUMP_UP);
			} else if (frame == 2 && currentFrameNumber != 2) {
				currentFrameNumber = 2;
				setY(getY() + JUMP_UP);
			} else if (frame == 3 && currentFrameNumber != 3) {
				currentFrameNumber = 3;
				// Pas de modification de position ici, les frames 2 et 3 étant
				// identiques
			} else if (frame == 4 && currentFrameNumber != 4) {
				currentFrameNumber = 4;
				setY(getY() - JUMP_UP);
			}
		}
	}

	// Toujours faire en sorte que le bord droit de la textureRegion
	// affichée reste identique à la précédente, sachant que x représente
	// l'abscisse du bord gauche
	private void setX() {
		if (previousRegion != null)
			setX(getX() + previousRegion.getRegionWidth()
					- getRegion().getRegionWidth());
	}

	public String getFolderName() {
		return folderName;
	}

	public boolean isWalking() {
		return currentAnimation == walking;
	}

	public boolean isJumping() {
		return currentAnimation == jumping;
	}

	public boolean isDiving() {
		return currentAnimation == diving;
	}

	public boolean hasWon() {
		return getRegion() == winner;
	}

	public boolean hasLost() {
		return getRegion() == loser;
	}
	
	public boolean hasFinished() {
		return hasWon() || hasLost();
	}

	public void walk() {
		if (!isWalking() && !hasFinished())
			setCurrentAnimation(walking);
	}

	public void jump() {
		if (isWalking() && !hasFinished()) {
			setCurrentAnimation(jumping);
			jumpSound.play();
		}
	}

	public void dive() {
		if (isWalking() && !hasFinished())
			setCurrentAnimation(diving);
	}

	public void win() {
		setX();
		setRegion(winner);
	}

	public void lose() {
		setX();
		setRegion(loser);
	}

	public void moveForward() {
		setX(getX() + SPEED);
	}

	public void fallDown(int ground) {
		if (!isJumping())
			setY(ground);
	}

	public Music getBackgroundMusic() {
		return backgroundMusic;
	}

}
