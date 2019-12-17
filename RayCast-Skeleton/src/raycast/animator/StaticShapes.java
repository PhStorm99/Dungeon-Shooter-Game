package raycast.animator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import raycast.entity.geometry.PolyShape;

/**
 * This is the Static Shapes which inherits the handle method from the Abstract
 * Animator
 * 
 * @author hnpav
 * @version Nov 09,2019
 */
public class StaticShapes extends AbstractAnimator {

	/**
	 * Field color
	 */
	private Color BACKGROUND = Color.BISQUE;

	/**
	 * handle method which has gc and now which has for loop describing the shape
	 */
	public void handle(GraphicsContext gc, long now) {

		clearAndFill(gc, BACKGROUND);
		for (PolyShape shape : map.shapes()) {
			shape.getDrawable().draw(gc);
		}
	}

	/**
	 * toString method which describes the string reperentation of an object
	 * 
	 * @return static shapes
	 */
	public String toString() {
		return "Static Shapes";
	}

}
