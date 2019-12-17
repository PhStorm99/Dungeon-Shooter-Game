package dungeonshooter.entity.property;

import dungeonshooter.entity.geometry.RectangleBounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.IntersectUtil;
import utility.Point;

/**
 * This is the class which implementing the properties of entity
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public class HitBox implements dungeonshooter.entity.Entity{

	//Required fields
	private Point prev;
	private RectangleBounds bounds;
	private Sprite sprite;
	private double[][] points;
	private double[] result;
	
	public HitBox()
	{
	
		points = new double[2][4];
		result = new double[4];
		prev = new Point();
		sprite = new Sprite()
		{ 
			public void draw( GraphicsContext gc)	
			{
				gc.setStroke( getStroke()); 
				gc.setLineWidth( getWidth());
				gc.strokeRect( bounds.x(), bounds.y(), bounds.w(), bounds.h()); } 
			};
		sprite.setStroke(Color.RED).setWidth( 3);
		
	}
	public HitBox setBounds(RectangleBounds bounds)
	{
		this.bounds = bounds;
		return this;
	}
	public HitBox setBounds(double x, double y, double w, double h)
	{
		return setBounds(new RectangleBounds(x, y, w, h));
		
	} 
	//translating the x,y 
	public HitBox translate(double dx, double dy)
	{
		prev.move(bounds.startPos());
		bounds.translate(dx, 	dy);
		return this;
		
	}
	public HitBox undoTranslate()
	{
		bounds.move(prev); // this sets the position to the previous state	
		return this;		
	}
	
	/**
	 * containsBounds checks if a hitbox is completely within current hitbox
	 * 
	 * @param box
	 * @return the bounds
	 */
	public boolean containsBounds(HitBox box)
	{
		bounds.contains(box.getBound());
		return true;
	}
	public boolean intersectBounds(HitBox box)
	{
		if(bounds.intersects(box.getBound()))
		return true;
		
		return false;
		
	}
	public boolean intersectFull(HitBox box)
	{
		box.intersectFull(box.getPoints());
		
		return true;
		
	}
	protected boolean intersectFull(double[][] otherPoints)
	{

		// getting points and setting it to points variable
	      points = getPoints();

			for(int x = 0, y = points[0].length - 1; x < points[0].length; x++, y = x - 1)
			{
	                    for(int m = 0, n = otherPoints[0].length - 1; m < otherPoints[0].length; m++, n = m - 1)
	                    {
			
	                        boolean intersect = IntersectUtil.getIntersection(result, 
	                        		points[0][x], points[1][x],
	                        		points[0][y],points[1][y],
	                        		otherPoints[0][m], otherPoints[1][m],
	                        		otherPoints[0][n], otherPoints[1][n]);
	                        if (intersect && result[2] <= 1)
	                        return true;
	                    } 
	        }
	        return false;  		
	}
	protected boolean hasIntersectFull()
	{
		return false;		
	}
	protected double[][] getPoints()
	{
		return bounds.toArray(points);
	}
	public void update(){}
	
	public boolean hasHitbox()
	{
		return true;
	}
	public HitBox getHitBox()
	{
		return this;
	}
	public boolean isDrawable()
	{
		return true;
	}
	public Sprite getDrawable()
	{
		return sprite;
	}
	public String toString()
	{
		return "Game the Dungeon Shooter";
	}
	public RectangleBounds getBound()
	{
		return bounds;
	}
	public Point getPrev()
	{
		return prev;
	}
}