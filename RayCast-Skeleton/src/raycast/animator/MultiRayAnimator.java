package raycast.animator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import raycast.entity.geometry.PolyShape;
import utility.IntersectUtil;

/**
 * This is an MultiRayAnimator which draw the rays taking the smallest point
 * from the shapes and connect where the cursor is.
 * 
 * @author hnpav
 * @version Nov 13,2019
 */
public class MultiRayAnimator extends AbstractAnimator {

	// fields
	private Color background = Color.BEIGE;

	public double[] intersectPoint = new double[4];

	private boolean doesIntersect;

	/**
	 * drawLine method which reference the parameter and condition
	 * 
	 * @param gc
	 * @param color
	 * @param sx
	 * @param sy
	 * @param ex
	 * @param ey
	 */
	public void drawLine(GraphicsContext gc, Color color, double sx, double sy, double ex, double ey) {

		gc.setLineWidth(1);
		gc.setStroke(color);
		gc.setFill(Color.MAGENTA);
		gc.strokeLine(sx, sy, ex, ey);
		if (map.getDrawIntersectPoint()) {
			gc.fillOval(ex - 5, ey - 5, 10, 10);
		}
	}

	/**
	 * This methods draw the rays as define by the angle
	 * 
	 * @param gc
	 * @param startX
	 * @param startY
	 * @param lightColor
	 */
	public void drawRays(GraphicsContext gc, double startX, double startY, Color lightColor) {
		double endX;
		double endY;
		double rayIncrementer = 360d / map.getRayCount();

		// if rayAngel is int, it will doesn't do anything (0.36)
		// instead it will do to infinite loop

		for (double rayAngel = 0; rayAngel < 360; rayAngel += rayIncrementer) {
			endX = Math.cos(Math.toRadians(rayAngel));

			endY = Math.sin(Math.toRadians(rayAngel));

			for (PolyShape shape : map.shapes()) {

				for (int i = 0, j = shape.pointCount() - 1; i < shape.pointCount(); i++, j = i - 1) {

					doesIntersect = IntersectUtil.getIntersection(intersectResult, startX, startY, startX + endX,
							startY + endY, shape.pX(i), shape.pY(i), shape.pX(j), shape.pY(j));

					if (doesIntersect && intersectPoint[2] > intersectResult[2]) {
						System.arraycopy(intersectResult, 0, intersectPoint, 0, intersectPoint.length);
					}

				}
			}

			drawLine(gc, lightColor, startX, startY, intersectPoint[0], intersectPoint[1]);
			intersectPoint[2] = Double.MAX_VALUE;
		}
	}

	/**
	 * String representation of an object which is shape
	 * 
	 * @return the animator
	 */
	public String toString() {
		return "Multi-Ray Animator";
	}

	/**
	 * handle method handles the background and loop the shape and color
	 */
	protected void handle(GraphicsContext gc, long now) {
		clearAndFill(gc, background);
		for (PolyShape shape : map.shapes()) {
			shape.getDrawable().draw(gc);
		}

		drawRays(gc, mouse.x(), mouse.y(), Color.LIGHTCORAL);

	}

}
