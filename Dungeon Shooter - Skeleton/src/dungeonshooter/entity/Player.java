package dungeonshooter.entity;

import dungeonshooter.CanvasMap;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import utility.Point;

/**
 * We can say this is a main class also implementing some Entity class property
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public class Player implements dungeonshooter.entity.Entity {

	private Rotate rotationPlayer;
	private double angle;

	private double playerFrame = 0;
	private double muzzleFrame = 0;
	private Point pos;
	private Point dimension;
	private Point prev;
	private Sprite sprite;
	private HitBox hitbox;
	private PlayerInput input;
	private CanvasMap map;

	public Player(double x, double y, double w, double h) {
		rotationPlayer = new Rotate();
		pos = new Point(x - w / 2, y - h / 2);
		prev = new Point(pos);
		dimension = new Point(w, h);

		// Code down below taken from the given document of Dungeon Shooter//
		sprite = new Sprite() {
			// player and muzzle each have 20 and 16 set of images than can be loaded
			private final Image[] PLAYER = new Image[20];
			private final Image[] MUZZLE = new Image[16];
			{
				// load the images
				for (int i = 0; i < PLAYER.length; i++) {
					PLAYER[i] = new Image("file:assets\\rifle\\idle\\survivor-idle_rifle_" + i + ".png");
				}
				for (int i = 0; i < MUZZLE.length; i++) {
					MUZZLE[i] = new Image("file:assets\\muzzle_flashs\\m_" + i + ".png");
				}
			}

			public void draw(GraphicsContext gc) {
				gc.save();
				// rotate gc for drawing
				gc.setTransform(rotationPlayer.getMxx(), rotationPlayer.getMyx(), rotationPlayer.getMxy(),
						rotationPlayer.getMyy(), rotationPlayer.getTx(), rotationPlayer.getTy());
				// if left click display fire animation
				if (input.leftClicked()) {
					gc.drawImage(MUZZLE[(int) muzzleFrame], getRifleMuzzleX() - 8, getRifleMuzzleY() - 25, 50, 50);
					// this number is how fast the next frame of fire animation will be drawn. The
					// higher the faster.
					muzzleFrame += .5;
				}
				// darw player image
				gc.drawImage(PLAYER[(int) playerFrame], pos.x(), pos.y(), dimension.x(), dimension.y());
				gc.restore();
				// this number is how fast the next frame of player animation will be drawn. The
				// higher the faster.
				playerFrame += 0.25;
				// reset frame counts if reach the max frame
				if (playerFrame >= PLAYER.length) {
					playerFrame = 0;
				}
				if (muzzleFrame >= MUZZLE.length || !input.leftClicked()) {
					muzzleFrame = 0;
				}
			}
		};
		double size = h * .74;
		hitbox = new HitBox().setBounds(pos.x() + dimension.x() * .303 - size / 2,
				pos.y() + dimension.y() * .58 - size / 2, size, size);
	}

	// setting the map
	public Player setMap(CanvasMap map) {
		this.map = map;
		return this;
	}

	public double getPlayerCenterX() {
		return pos.x() + dimension.x() * .303;
	}

	public double getPlayerCenterY() {
		return pos.y() + dimension.y() * .58;
	}

	public double getRifleMuzzleX() {
		return pos.x() + dimension.x() * .93;
	}

	public double getRifleMuzzleY() {
		return pos.y() + dimension.y() * .73;
	}

	// calculating the angles
	public void calculateAngles() {
		angle = Math.toDegrees(Math.atan2(input.y() - getPlayerCenterY(), input.x() - getPlayerCenterX()));
		rotationPlayer.setAngle(angle);
		rotationPlayer.setPivotX(getPlayerCenterX());
		rotationPlayer.setPivotY(getPlayerCenterY());

	}

	// stepping Back
	public void stepBack() {
		hitbox.undoTranslate();
		pos.move(prev);
	}

	// updating the player input by moving it and calling calculateAngles for player
	public void update() {
		Point2D muzzle = rotationPlayer.transform(getRifleMuzzleX(), getRifleMuzzleY());

		// if we click left, then only fire the bullet
		if (input.leftClicked()) {
			map.fireBullet(new Bullet(this.angle, muzzle.getX(), muzzle.getY()));
		}

		int x = input.leftOrRight();
		int y = input.upOrDown();

		prev.move(pos);
		pos.translate(x, y);
		hitbox.translate(x, y);

		calculateAngles();
	}

	public boolean isDrawable() {
		return true;
	}

	public boolean hasHitbox() {
		return true;
	}

	public HitBox getHitBox() {
		return hitbox;
	}

	public Drawable<?> getDrawable() {
		return sprite;
	}

	public void setInput(PlayerInput input) {
		this.input = input;
	}

}