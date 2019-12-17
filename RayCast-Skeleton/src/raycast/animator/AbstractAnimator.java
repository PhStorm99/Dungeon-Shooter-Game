package raycast.animator;

import java.util.Objects;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import raycast.CanvasMap;
import raycast.entity.FpsCounter;
import raycast.entity.geometry.PolyShape;
import utility.Point;

/**
 * this class must extend {@link AnimationTimer}. job of this class is to hold
 * common functionality among animators.
 * 
 * @author Harsh Patel
 * @version Nov 9, 2019
 */
public abstract class AbstractAnimator extends AnimationTimer {

	protected double[] intersectResult;
	private FpsCounter fps;

	/**
	 * create a protected class variable of type {@link CanvasMap} and name it map.
	 */
	protected CanvasMap map;

	/**
	 * create a protected class variable of type {@link Point} and name it mouse.
	 */
	protected Point mouse;

	/**
	 * create a protected constructor and initialize the
	 * {@link AbstractAnimator#mouse} variable
	 */
	public AbstractAnimator() {
		this.mouse = new Point();
		intersectResult = new double[4];
		fps = new FpsCounter(10, 25);

		fps.getDrawable().setWidth(1);// set width to 1
	}

	/**
	 * create a setter called setCanvas to inject (set) the {@link CanvasMap}
	 * 
	 * @param map
	 *            - {@link CanvasMap} object
	 * @return map
	 */
	public CanvasMap setCanvas(CanvasMap map) {
		this.map = map;
		return map;
	}

	public double[] intersect() {
		Objects.requireNonNull(intersectResult, "intersectResult array must be initilized in the constructor.");
		if (intersectResult.length != 4) {
			throw new IllegalStateException("intersectResult must have length of 4");
		}
		return intersectResult;
	}

	/**
	 * create a method called mouseDragged that is called every time the position of
	 * mouse changes.
	 * 
	 * @param e
	 *            - {@link MouseEvent} object that hold the details of the mouse.
	 *            use {@link MouseEvent#getX} and {@link MouseEvent#getY}
	 */
	public void mouseDragged(MouseEvent e) {
		mouse.set(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		mouse.set(e.getX(), e.getY());
	}

	/**
	 * <p>
	 * create a method called handle that is inherited from
	 * {@link AnimationTimer#handle(long)}. this method is called by JavaFX
	 * application, it should not be called directly.
	 * </p>
	 * <p>
	 * inside of this method call the abstract handle method
	 * {@link AbstractAnimator#handle(GraphicsContext, long)}.
	 * {@link GraphicsContext} can be retrieved from {@link CanvasMap#gc()}
	 * </p>
	 * 
	 * @param now
	 *            - current time in nanoseconds, represents the time that this
	 *            function is called.
	 */

	/**
	 * clear and Fill method setting the background
	 * 
	 * @param gc
	 * @param background
	 */
	public void clearAndFill(GraphicsContext gc, Color background) {

		gc.setFill(background);
		gc.clearRect(0, 0, map.w(), map.h());
		gc.fillRect(0, 0, map.w(), map.h());

	}

	/**
	 * handle method which describes the conditions of the DrawBounds and
	 * DrawShapeJoints etc.
	 * 
	 * @param now
	 */
	public void handle(long now) {
		GraphicsContext gc = map.gc();
		if (map.getDrawFPS() == true)
			fps.calculateFPS(now);
		handle(gc, now);
		
		if(map.getDrawLightSource()==true)
		{
			gc.setFill(Color.MAGENTA);
			gc.fillOval(mouse.x()-5, mouse.y()-5, 10, 10);
			
		}

		if (map.getDrawShapeJoints() || map.getDrawBounds() == true) {
			for (PolyShape shape : map.shapes()) {
				if (map.getDrawBounds() == true) {
					shape.getBounds().draw(gc);

				}
				if (map.getDrawShapeJoints() == true) {
					shape.drawCorners(gc);
				}
			}
		}

		if (map.getDrawFPS() == true) {
			fps.getDrawable().draw(gc);
		}
	}

	/**
	 * create a protected abstract method called handle, this method to be
	 * overridden by subclasses.
	 * 
	 * @param gc
	 *            - {@link GraphicsContext} object.
	 * @param now
	 *            - current time in nanoseconds, represents the time that this
	 *            function is called.
	 */

	protected abstract void handle(GraphicsContext gc, long now);

}
