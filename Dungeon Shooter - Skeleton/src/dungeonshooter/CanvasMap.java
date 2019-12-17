package dungeonshooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import utility.IntersectUtil;
import dungeonshooter.animator.AbstractAnimator;
import dungeonshooter.animator.Animator;
import dungeonshooter.entity.Bullet;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.PolyShape;
import dungeonshooter.entity.property.HitBox;

/**
 * this class represents the drawing area. it is backed by {@link Canvas} class.
 * this class itself does not handle any of the drawing. this task is
 * accomplished by the {@link AnimationTimer}.
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public class CanvasMap {

	/**
	 * fields and properties declared to a private
	 */
	private Canvas map;
	private BooleanProperty drawBounds, drawFPS;
	private List<Entity> players;
	private List<Entity> projectiles;
	private PolyShape border;
	private List<Entity> buffer;
	private Animator animator;
	private List<PolyShape> shapes;

	/**
	 * <p>
	 * create a {@link Canvas} object call board. it provides the tools to draw in
	 * JavaFX. this is also a {@link Node} which means can be added to our JavaFX
	 * application. the object needed to draw on a {@link Canvas} is
	 * {@link GraphicsContext} which is retrieved using
	 * {@link Canvas#getGraphicsContext2D()} method.
	 * </p>
	 */

	/**
	 * create a {@link AbstractAnimator} called animator. {@link AnimationTimer}
	 * provides most common functionally needed to draw animations of ray casting.
	 */

	/**
	 * <p>
	 * create an {@link IntegerProperty} called rayCount to keep track of ray count
	 * changes.<br>
	 * this variable can be initialized with {@link SimpleIntegerProperty}
	 * </p>
	 * 
	 * <pre>
	 * IntegerProperty i1 = new SimpleIntegerProperty(1);
	 * IntegerProperty i2 = new SimpleIntegerProperty();
	 * i1.bind(i2);
	 * i2.set(100);
	 * System.out.println(i1.get()); // prints 100
	 * </pre>
	 * <p>
	 * create a getter that returns {@link IntegerProperty} and a method that
	 * returns {@link IntegerProperty#get()}
	 * </p>
	 */

	/**
	 * <p>
	 * create a set of {@link BooleanProperty}s to track some drawing options.<br>
	 * create drawBounds, drawFPS<br>
	 * these variables can be initialized with {@link SimpleBooleanProperty}
	 * </p>
	 * 
	 * <pre>
	 * BooleanProperty b1 = new SimpleBooleanProperty(false);
	 * BooleanProperty b2 = new SimpleBooleanProperty();
	 * b1.bind(b2);
	 * b2.set(true);
	 * System.out.println(b1.get()); // prints true
	 * </pre>
	 * <p>
	 * create a getter that returns {@link BooleanProperty} and a method that
	 * returns {@link BooleanProperty#get()} for each BooleanProperty.
	 * </p>
	 */
	public CanvasMap() {

		drawFPS = new SimpleBooleanProperty();
		drawBounds = new SimpleBooleanProperty();

		buffer = new ArrayList<Entity>(500);
		projectiles = new ArrayList<Entity>(500);
		shapes = new ArrayList<PolyShape>(50);
		players = new ArrayList<Entity>(1);

		border = new PolyShape().setPoints(0, 0, 0, 0, 0, 0, 0, 0);
		border.getDrawable().setFill(new ImagePattern(new Image("file:assets/floor/pavin.png"), 0, 0, 256, 256, false));
	}

	/**
	 * This is drawFPSProperty method
	 * 
	 * @return fps property
	 */
	public BooleanProperty drawFPSProperty() {
		return drawFPS;
	}

	public boolean getDrawFPS() {
		return this.drawFPSProperty().get();
	}

	public BooleanProperty drawBoundsProperty() {
		return drawBounds;
	}

	public boolean getDrawBounds() {
		return this.drawBoundsProperty().get();
	}

	/**
	 * It draws the canvas and set it. Setting the width and height property and
	 * setting the points to add Listener through the lambda
	 * 
	 * @param map
	 * @return this map
	 */
	public CanvasMap setDrawingCanvas(Canvas map) {
		if (map == null) {
			throw new NullPointerException("Null Pointer Exception");
		}

		this.map = map;
		map.widthProperty().addListener(e -> {
			border.setPoints(0, 0, w(), 0, w(), h(), 0, h());
		});

		map.heightProperty().addListener(e -> {
			border.setPoints(0, 0, w(), 0, w(), h(), 0, h());
		});
		return this;
	}

	/**
	 * parameterized setAnimator of type CanvasMap and assigning to reference
	 * animator
	 * 
	 * @param newAnimator
	 * @return reference
	 */
	public CanvasMap setAnimator(Animator newAnimator) {
		if (animator != null) {
			this.stop();
		}
		this.animator = newAnimator;
		// this.start();

		return this;
	}

	/**
	 * create a method called start. start the animator.
	 * {@link AnimationTimer#start()}
	 */
	public void start() {
		animator.start();
	}

	/**
	 * create a method called stop. stop the animator. {@link AnimationTimer#stop()}
	 */
	public void stop() {

		animator.stop();
	}

	/**
	 * create a method called getCanvas. get the JavaFX {@link Canvas} node
	 * 
	 * @return {@link Canvas} node
	 */

	public Canvas getCanvas() {

		return map;
	}

	/**
	 * create a method called gc. get the {@link GraphicsContext} of {@link Canvas}
	 * that allows direct drawing.
	 * 
	 * @return {@link GraphicsContext} of {@link Canvas}
	 */
	public GraphicsContext gc() {
		return map.getGraphicsContext2D();
	}

	/**
	 * create a method called h. get the height of the map,
	 * {@link Canvas#getHeight()}
	 * 
	 * @return height of canvas
	 */
	public double h() {
		return map.getHeight();
	}

	/**
	 * rn width of canvas
	 */

	public double w() {
		return map.getWidth();
	}

	/**
	 * create the shapes method of type PolyShape list
	 * 
	 * @return shape that will display on the screen
	 */
	public List<PolyShape> shapes() {
		return shapes;
	}

	/**
	 * players of type entity list
	 * 
	 * @return players
	 */
	public List<Entity> players() {
		return players;

	}

	public List<Entity> projectiles() {
		return projectiles;
	}

	/**
	 * creating the shapes and adding them and setting color and width in PolyShape
	 * 
	 * @return this
	 */
	public CanvasMap addSampleShapes() {
		PolyShape p1 = new PolyShape().setPoints(100, 100, 200, 100, 200, 200, 100, 200);
		p1.getDrawable().setFill(Color.ALICEBLUE).setStroke(Color.BROWN).setWidth(5);
		shapes.add(p1);

		PolyShape p2 = new PolyShape().setPoints(300, 500, 300, 400, 200, 300, 200, 400);
		p2.getDrawable().setFill(Color.CORAL).setStroke(Color.BROWN).setWidth(5);
		shapes.add(p2);

		PolyShape p3 = new PolyShape().setPoints(600, 400, 600, 500, 400, 600, 500, 200);
		p3.getDrawable().setFill(Color.GOLD).setStroke(Color.BROWN).setWidth(5);
		shapes.add(p3);

		return this;
	}

	/**
	 * Fire bullet method is adding the bullet to a buffer
	 * 
	 * @param bullet
	 */
	public void fireBullet(Bullet bullet) {
		buffer.add(bullet);
	}

	// updating the projectile list by adding and clearing the buffer
	public void updateProjectilesList() {

		projectiles.addAll(buffer); // adding the buffer list to the projectile list
		buffer.clear(); // clearing the buffer
	}

	// getting the shape of map
	public PolyShape getMapShape() {
		return border;

	}

	// getting the hitbox and see if it contains the hitbox to a border.
	public boolean inMap(HitBox hitbox) {
		return border.getHitBox().containsBounds(hitbox);

	}
}
