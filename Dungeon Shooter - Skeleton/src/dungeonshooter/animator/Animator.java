package dungeonshooter.animator;

import java.util.Iterator;
import dungeonshooter.entity.Bullet;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.Player;
import dungeonshooter.entity.PolyShape;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Animator extends the AbstractAnimator by inherits some the property of
 * Anstract animator
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public class Animator extends AbstractAnimator {

	private Color background = Color.ANTIQUEWHITE;

	public void handle(GraphicsContext gc, long now) {
		updateEntities();
		clearAndFill(gc, background);
		drawEntities(gc);

	}

	// updating the entities same as i have done in the Abstract Animator -
	// drawEntities method
	// In here we are just updating the entities what are drawn
	public void updateEntities() {

		map.updateProjectilesList();

		for (Entity e : map.projectiles()) {
			e.update();
		}
		for (Entity e : map.players()) {
			e.update();
		}
		for (Entity e : map.shapes()) {
			e.update();
		}

		if (map.getDrawBounds()) {
			for (Entity e : map.projectiles()) {
				e.getHitBox().getDrawable().setStroke(Color.RED);
			}
			for (Entity e : map.players()) {
				e.getHitBox().getDrawable().setStroke(Color.RED);
			}
		}

		for (PolyShape shape : map.shapes()) {
			processEntitylist(map.projectiles().iterator(), shape.getHitBox());
			processEntitylist(map.players().iterator(), shape.getHitBox());

		}

	}

	/**
	 * Processing the entities by iterator and shapeHitbox and looping them to
	 * checking the conditions
	 * 
	 * @param iterator
	 * @param shapeHitBox
	 */
	public void processEntitylist(Iterator<Entity> iterator, HitBox shapeHitBox) {

		while (iterator.hasNext()) {
			Entity e = iterator.next();
			HitBox bounds = e.getHitBox();

			if (!map.inMap(bounds)) {
				if (e instanceof Player) {
					((Player) e).stepBack();
				} else if (e instanceof Bullet) {
					iterator.remove();
				}
			} else if (shapeHitBox.intersectBounds(bounds)) {
				if (map.getDrawBounds()) {
					bounds.getDrawable().setStroke(Color.BLUEVIOLET);
				}

				if (shapeHitBox.intersectFull(bounds)) {
					if (e instanceof Player) {
						((Player) e).stepBack();
					} else if (e instanceof Bullet) {
						iterator.remove();
					}
				}
			}
		}
	}

	// Just giving the name
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Shooter";
	}
}