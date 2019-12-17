package dungeonshooter.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;


/**
 * This method implemennts the method from the Entity class which has the
 * default contructor, Sprite instantiation in it and draw method declaration in
 * the Sprite and Setting the poinnts
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public class PolyShape implements dungeonshooter.entity.Entity {

	private GraphicsContext gc;
	private int pointCount;
	private double[][] points;
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	private HitBox hitbox;
	private Sprite sprite;

	public PolyShape() {
		
		hitbox = new HitBox()
			{
				@Override
				protected boolean hasIntersectFull(){
				return true;
				}
				@Override
				protected double[][] getPoints(){
				return points;
				}
			};
		sprite = new Sprite() {
			{
				setFill(new ImagePattern( new Image( "file:assets/concrete/dsc_1621.png")));
			
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
		return null;

	}
	/**
	 * I have taken this method from my lab - RayCast
	 * 
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
		hitbox.setBounds(minX, minY, maxX - minX, maxY - minY);
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
	
	public void update()
	{
		
	}

	public boolean hasHitbox() 
	{
		return true;
	}

	public HitBox getHitBox()
	{
		return hitbox;
	}

	public boolean isDrawable()
	{
		return true;
	}

	public Sprite getDrawable() {
		return sprite;
	}
}
