package dungeonshooter.entity;

import dungeonshooter.entity.geometry.RectangleBounds;
import static com.sun.javafx.scene.control.skin.TextFieldSkin.BULLET;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import dungeonshooter.entity.property.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Bullet implementing the methods and properties of Entity like showing
 * bullets, giving them angle, updating the angle and translating
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public class Bullet implements Entity {

	private static final Image BULLET = new Image("file:assets\\bullet\\b_1.png");
	private double angle;
	private Sprite sprite;
	private HitBox hitbox;

	// angle with x and y
	public Bullet(double angle, double x, double y) {
		this(angle, x, y, 6, 6);

	}

	// angle with x, y, h,w
	public Bullet(double angle, double x, double y, double w, double h) {
		hitbox = new HitBox();
		hitbox.setBounds(x, y, w, h);
		sprite = new Sprite() {

			private RectangleBounds bounds = hitbox.getBound();

			public void draw(GraphicsContext gc) {

				gc.drawImage(BULLET, bounds.x(), bounds.y(), bounds.w(), bounds.h());
			}
		};

	}

	// updating the angle
	public void update() {
		double x = Math.cos(Math.toRadians(angle)) * 7;
		double y = Math.sin(Math.toRadians(angle)) * 7;
		hitbox.translate(x, y);
	}

	public boolean isDrawable() {
		return true;

	}

	public String toString() {
		return null;

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
}
