package dungeonshooter.animator;

import java.util.function.Consumer;
import dungeonshooter.CanvasMap;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.FpsCounter;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.Point;

/**
 * This is an abstract class inherits the property of AnimationTimer which will
 * declare the constructor setting the Canvas, clearing and filling the
 * background, drawing the entities like what should be in the game and finally
 * handling it by drawing and calculating the Fps by calling draw and passing
 * variable of graphics context in it.
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public abstract class AbstractAnimator extends AnimationTimer {
	
	/**
	 * Fields assigned according to the instruction given in the UML
	 */
	protected Point mouse;
	protected CanvasMap map;
	private FpsCounter fps;

	// Default constructor
	public AbstractAnimator() {
		this.mouse = new Point();
		fps = new FpsCounter(10, 25);
		fps.getDrawable().setFill(Color.BLACK).setStroke(Color.WHITE).setWidth(1);// set width to 1
	}
	
	public void setCanvas(CanvasMap map) {
		this.map = map;
	}

	public void clearAndFill(GraphicsContext gc, Color background) {
		gc.setFill(background);
		gc.clearRect(0, 0, map.w(), map.h());
		gc.fillRect(0, 0, map.w(), map.h());
	}

	// To draw entities - declaring lambda and foreach loop which draw shapes, bullets and a player 
	public void drawEntities(GraphicsContext gc) {
		Consumer<Entity> draw = e -> {
			if (e.isDrawable()) {
				e.getDrawable().draw(gc);

				if (map.getDrawBounds()) {
					e.getHitBox().getDrawable().draw(gc);
				}
			}
		};

		draw.accept(map.getMapShape());

		for (Entity e : map.shapes()) {
			draw.accept(e);
		}
		for (Entity e : map.projectiles()) {
			draw.accept(e);
		}
		for (Entity e : map.players()) {
			draw.accept(e);
		}
	}

	// handle method for Fps 
	public void handle(long now) {
		GraphicsContext gc = map.gc();

		if (map.getDrawFPS() == true)
			fps.calculateFPS(now);

		handle(gc, now);

		if (map.getDrawFPS() == true)
			fps.getDrawable().draw(gc);

	}

	protected abstract void handle(GraphicsContext gc, long now);

}
