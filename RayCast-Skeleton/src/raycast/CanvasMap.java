package raycast;

import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import raycast.animator.AbstractAnimator;
import raycast.entity.geometry.PolyShape;

/**
 * this class represents the drawing area. it is backed by {@link Canvas} class.
 * this class itself does not handle any of the drawing. this task is
 * accomplished by the {@link AnimationTimer}.
 * 
 * @author Harsh Patel
 * @version Nov 9, 2019
 * @since 1.8
 * 
 */
public class CanvasMap {

	private Canvas board;
	private IntegerProperty rayCount;
	private AbstractAnimator animator;

	private BooleanProperty drawLightSource, drawIntersectPoint, drawShapeJoints, drawSectors, drawBounds, drawFPS;

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
	 * create: drawLightSource, drawIntersectPoint, drawShapeJoints, drawSectors,
	 * drawBounds, drawFPS<br>
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
		this.board = new Canvas();
		rayCount = new SimpleIntegerProperty();

		drawLightSource = new SimpleBooleanProperty();

		drawIntersectPoint = new SimpleBooleanProperty();
		drawShapeJoints = new SimpleBooleanProperty();
		drawSectors = new SimpleBooleanProperty();
		drawBounds = new SimpleBooleanProperty();
		drawFPS = new SimpleBooleanProperty();

		shapes = new ArrayList<PolyShape>(20);
	}
	/**
	 * This is drawFPSProperty
	 * 
	 * @return drawFPS
	 */
	public BooleanProperty drawFPSProperty() {
		return drawFPS;
	}
	/**
	 * 
	 * @return
	 */
	public boolean getDrawFPS() {
		return this.drawFPSProperty().get();
	}
	/**
	 * 
	 * @return
	 */
	public BooleanProperty drawBoundsProperty() {
		return drawBounds;
	}
	public boolean getDrawBounds() {
		return this.drawBoundsProperty().get();
	}

	public BooleanProperty drawSectorsProperty() {
		return drawSectors;
	}

	public boolean getDrawSectors() {
		return this.drawSectorsProperty().get();
	}

	public BooleanProperty drawLightSourceProperty() {
		return drawLightSource;
	}

	public boolean getDrawLightSource() {
		return this.drawLightSourceProperty().get();
	}

	public BooleanProperty drawIntersectPointProperty() {
		return drawIntersectPoint;
	}

	public boolean getDrawIntersectPoint() {
		return this.drawIntersectPointProperty().get();
	}

	public BooleanProperty drawShapeJointsProperty() {
		return drawShapeJoints;
	}

	public boolean getDrawShapeJoints() {
		return this.drawShapeJointsProperty().get();
	}

	public IntegerProperty rayCountProperty() {
		return rayCount;
	}

	public int getRayCount() {
		return this.rayCountProperty().get();
	}

	/**
	 * create a constructor and initialize all class variables.
	 */

	/**
	 * create the property class variables functions here
	 */

	/**
	 * create a method called setAnimator. set an {@link AbstractAnimator}. if an
	 * animator exists {@link CanvasMap#stop()} it and call
	 * {@link CanvasMap#removeMouseEvents()}. then set the new animator and call
	 * {@link CanvasMap#start()} and {@link CanvasMap#registerMouseEvents()}.
	 * 
	 * @param newAnimator
	 *            - new {@link AbstractAnimator} object
	 * @return the current instance of this object
	 */

	public CanvasMap setAnimator(AbstractAnimator newAnimator) {
		if (animator != null) {
			this.stop();
			this.removeMouseEvents();
		}
		this.animator = newAnimator;
		this.start();
		this.registerMouseEvents();

		return this;
	}

	/**
	 * <p>
	 * create a method called registerMouseEvents. register the mouse events for
	 * when the mouse is {@link MouseEvent#MOUSE_MOVED} or
	 * {@link MouseEvent#MOUSE_DRAGGED}.<br>
	 * call {@link CanvasMap#addEventHandler} twice and pass to it
	 * {@link MouseEvent#MOUSE_DRAGGED}, {@link animator#mouseDragged} and
	 * {@link MouseEvent#MOUSE_MOVED}, {@link animator#mouseMoved}.
	 * </p>
	 * <p>
	 * a method can be passed directly as an argument if the method signature
	 * matches the functional interface. in this example you will pass the animator
	 * method using object::method syntax.
	 * </p>
	 */

	public void registerMouseEvents() {
		board.addEventHandler(MouseEvent.MOUSE_MOVED, (e) -> {
			animator.mouseMoved(e);
		});

		board.addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
			animator.mouseDragged(e);
		});
	}

	/**
	 * <p>
	 * create a method called removeMouseEvents. remove the mouse events for when
	 * the mouse is {@link MouseEvent#MOUSE_MOVED} or
	 * {@link MouseEvent#MOUSE_DRAGGED}.<br>
	 * call {@link CanvasMap#removeEventHandler} twice and pass to it
	 * {@link MouseEvent#MOUSE_DRAGGED}, {@link animator#mouseDragged} and
	 * {@link MouseEvent#MOUSE_MOVED}, {@link animator#mouseMoved}.
	 * </p>
	 * <p>
	 * a method can be passed directly as an argument if the method signature
	 * matches the functional interface. in this example you will pass the animator
	 * method using object::method syntax.
	 * </p>
	 */

	public void removeMouseEvents() {
		board.removeEventHandler(MouseEvent.MOUSE_MOVED, (e) -> {
			animator.mouseMoved(e);
		});

		board.removeEventHandler(MouseEvent.MOUSE_DRAGGED, animator::mouseDragged);
	}

	// private Node node;

	/**
	 * <p>
	 * register the given {@link EventType} and {@link EventHandler}
	 * </p>
	 * 
	 * @param event
	 *            - an event such as {@link MouseEvent#MOUSE_MOVED}.
	 * @param handler
	 *            - a lambda to be used when registered event is triggered.
	 */
	public <E extends Event> void addEventHandler(EventType<E> event, EventHandler<E> handler) {
		board.addEventHandler(event, handler);
	}

	/**
	 * <p>
	 * remove the given {@link EventType} registered with {@link EventHandler}
	 * </p>
	 * 
	 * @param event
	 *            - an event such as {@link MouseEvent#MOUSE_MOVED}.
	 * @param handler
	 *            - a lambda to be used when registered event is triggered.
	 */
	public <E extends Event> void removeEventHandler(EventType<E> event, EventHandler<E> handler) {
		board.removeEventHandler(event, handler);
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

		return board;
	}

	/**
	 * create a method called gc. get the {@link GraphicsContext} of {@link Canvas}
	 * that allows direct drawing.
	 * 
	 * @return {@link GraphicsContext} of {@link Canvas}
	 */
	public GraphicsContext gc() {
		return board.getGraphicsContext2D();
	}

	/**
	 * create a method called h. get the height of the map,
	 * {@link Canvas#getHeight()}
	 * 
	 * @return height of canvas
	 */
	public double h() {
		return board.getHeight();
	}

	/**
	 * create a method called w. get the width of the map, {@link Canvas#getWidth()}
	 * 
	 * @return width of canvas
	 */

	public double w() {
		return board.getWidth();
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
	 * Adding the sample shapes by setting the points, filling the color and setting
	 * the width
	 */
	public void addSampleShapes() {
		PolyShape p1 = new PolyShape().setPoints(100, 100, 200, 100, 200, 200, 100, 200);
		p1.getDrawable().setFill(Color.CORAL).setStroke(Color.BLACK).setWidth(5);
		shapes.add(p1);

		PolyShape p2 = new PolyShape().setPoints(300, 500, 300, 400, 200, 300, 200, 400);
		p2.getDrawable().setFill(Color.CORAL).setStroke(Color.BLACK).setWidth(5);
		shapes.add(p2);

		PolyShape p3 = new PolyShape().setPoints(600, 400, 600, 500, 400, 600, 500, 200);
		p3.getDrawable().setFill(Color.CORAL).setStroke(Color.BLACK).setWidth(5);
		shapes.add(p3);

	}

}
