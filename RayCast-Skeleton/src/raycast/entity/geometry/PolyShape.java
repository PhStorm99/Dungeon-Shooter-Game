package raycast.entity.geometry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import raycast.entity.property.Sprite;

/**
 * This method implemennts the method from the Entity class which has the
 * default contructor, Sprite instantiation in it and draw method declaration in
 * the Sprite and Setting the poinnts
 * 
 * @author hnpav
 * @version Nov 02, 2019
 */
public class PolyShape implements raycast.entity.Entity {

	private GraphicsContext gc;
	private int pointCount;
	private double[][] points;
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	private RectangleBounds recBounds;
	private Sprite sprite;

	public PolyShape() {
		sprite = new Sprite() {
			{
				setFill(Color.CYAN);
				setStroke(Color.GREEN);

			}

			public void draw(GraphicsContext gc) {
				gc.setLineWidth(sprite.getWidth());

				if (sprite.getStroke() != null) {
					gc.setStroke(sprite.getStroke());
					gc.strokePolygon(points[0], points[1], pointCount);
				}
				if (sprite.getFill() != null) {
					gc.setFill(sprite.getFill());
					gc.fillPolygon(points[0], points[1], pointCount);
				}
			}
		};

	}

	/**
	 * SetPoints method which is updating the min and max and declaring the points
	 * to them
	 * 
	 * @param nums
	 * @return this
	 */
	public PolyShape setPoints(double... nums) {
		minX = maxX = nums[0];
		minY = maxY = nums[1];
		pointCount = nums.length / 2;
		points = new double[2][pointCount];
		for (int i = 0, j = 0; i < nums.length; j++, i += 2) {

			updateMinMax(nums[i], nums[i + 1]);
			points[0][j] = nums[i];
			points[1][j] = nums[i + 1];
		}
		recBounds = new RectangleBounds(minX, minY, maxX - minX, maxY - minY);
		return this;
	}

	/**
	 * randomiza method
	 * 
	 * @param centerX
	 * @param centerY
	 * @param size
	 * @param minPoints
	 * @param maxPoints
	 * @return
	 */
	public PolyShape randomize(double centerX, double centerY, double size, int minPoints, int maxPoints) {
		return this;

	}

	/**
	 * Update min max to x and y by putting the Conditions
	 * 
	 * @param x
	 * @param y
	 */
	private void updateMinMax(double x, double y) {
		if (x > maxX) {
			maxX = x;
		}
		if (x < minX) {
			minX = x;
		}
		if (y > maxY) {
			maxY = y;
		}
		if (y < minY) {
			minY = y;
		}
	}

	/**
	 * pointCount mwthod
	 * 
	 * @return pointCount
	 */
	public int pointCount() {
		return pointCount;
	}

	public double[][] points() {
		return points;
	}

	public double pX(int index) {
		return points[0][index];

	}

	public double pY(int index) {
		return points[1][index];

	}

	@Override
	public Sprite getDrawable() {
		// TODO Auto-generated method stub
		return sprite;
	}

	/**
	 * 
	 * for loop in the drawCorners doing the 2d array of x and y
	 * 
	 * @param gc
	 */
	public void drawCorners(GraphicsContext gc) {
		gc.fill();
		for (int i = 0; i < pointCount; i++) {
			gc.fillText(Integer.toString(i), points[0][i] - 5, points[1][i] - 5);
			gc.fillOval(points[0][i] - 5, points[1][i] - 5, 10, 10);
		}
	}

	/**
	 * Rectangle Bounds
	 * 
	 * @return recBounds var
	 */
	public RectangleBounds getBounds() {
		// TODO Auto-generated method stub
		return recBounds;
	}

	@Override
	public boolean isDrawable() {
		// TODO Auto-generated method stub
		return false;
	}

}
