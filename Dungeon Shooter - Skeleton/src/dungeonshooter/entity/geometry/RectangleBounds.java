package dungeonshooter.entity.geometry;

import dungeonshooter.entity.geometry.RectangleBounds;
import utility.Point;

/**
 * this object represent the boundaries of any shape in a shape of a rectangle.
 * 
 * @author Shahriar (Shawn) Emami
 * @version Jan 12, 2019
 */
public class RectangleBounds{

	/**
	 * the top left corner and dimension of this rectangle
	 */
	private Point start, dimension;

	/**
	 * create a new Object with all values at zero
	 */
	public RectangleBounds(){
		start = new Point();
		dimension = new Point();
	}

	/**
	 * create a new Object with given (x,y) and (h,w)
	 * @param x - x coordinate of top left
	 * @param y - y coordinate of top left
	 * @param w - width
	 * @param h - height
	 */
	public RectangleBounds( double x, double y, double w, double h){
		this();
		start.set( x, y);
		dimension.set( w, h);
	}

	/**
	 * move the rectangle by top left corner to a new location by the given distance
	 * @param dx - amount to move in x direction
	 * @param dy - amount to move in y direction
	 */
	public void translate( double dx, double dy){
		start.translate( dx, dy);
	}

	/**
	 * get the point with x and y value
	 * @return x and y as a point object
	 */
	public Point startPos(){
		return start;
	}

	/**
	 * move the top left corner to given x and y
	 * @param x - x coordinate of top left
	 * @param y - y coordinate of top left
	 * @return the current instance of this object
	 */
	public RectangleBounds move( double x, double y){
		start.set( x, y);
		return this;
	}

	/**
	 * move the top left corner to given point
	 * @param p - new point
	 * @return the current instance of this object
	 */
	public RectangleBounds move( Point p){
		return move( p.x(), p.y());
	}

	/**
	 * get the point with width and height value
	 * @return width and height as a point object
	 */
	public Point dimension(){
		return dimension;
	}

	/**
	 * set the new width and height
	 * @param w - width
	 * @param h - height
	 * @return the current instance of this object
	 */
	public RectangleBounds dimension( double w, double h){
		dimension.set( w, h);
		return this;
	}

	/**
	 * get x value of top left corner
	 * @return x of top left corner
	 */
	public double x(){
		return start.x();
	}

	/**
	 * get the center x of this rectangle
	 * @return center x
	 */
	public double centerX(){
		return start.x() + w() / 2;
	}

	/**
	 * get y value of top left corner
	 * @return y of top left corner
	 */
	public double y(){
		return start.y();
	}

	/**
	 * get the center y of this rectangle
	 * @return center y
	 */
	public double centerY(){
		return start.y() + h() / 2;
	}

	/**
	 * get the height of the rectangle
	 * @return height
	 */
	public double h(){
		return dimension.y();
	}

	/**
	 * get the y value of bottom right corner of rectangle.
	 * @return y bottom left corner
	 */
	public double hPos(){
		return h() + y();
	}

	/**
	 * get width of the rectangle
	 * @return width
	 */
	public double w(){
		return dimension.x();
	}

	/**
	 * get the x value of bottom right corner of rectangle.
	 * @return x bottom left corner
	 */
	public double wPos(){
		return w() + x();
	}

	/**
	 * check if given {@link RectangleBounds} overlaps with current {@link RectangleBounds}
	 * @see <a href="https://stackoverflow.com/a/32088787/764951">detecting ovelaping rectangles</a>
	 * @param rect - given {@link RectangleBounds}
	 * @return true if overlaps else false
	 */
	public boolean intersects( RectangleBounds b){
		return intersects( b.x(), b.y(), b.w(), b.h());
	}

	/**
	 * check if given (x,y) with (w, h) overlaps with current {@link RectangleBounds}
	 * @see <a href="https://stackoverflow.com/a/32088787/764951">detecting ovelaping rectangles</a>
	 * @param x - x coordinate of top left
	 * @param y - y coordinate of top left
	 * @param w - height of rectangle
	 * @param h - width of rectangle
	 * @return true if overlaps else false
	 */
	public boolean intersects( double x, double y, double w, double h){
		return !(x > wPos() || y > hPos() || x() > x + w || y() > y + h);
	}

	/**
	 * check if given {@link RectangleBounds} is with in this {@link RectangleBounds}
	 * @param rect - given {@link RectangleBounds}
	 * @return true if in else false
	 */
	public boolean contains( RectangleBounds rect){
		return contains( rect.x(), rect.y(), rect.w(), rect.h());
	}

	/**
	 * check if given (x,y) with (w, h) is with in this {@link RectangleBounds}
	 * @param x - x coordinate of top left
	 * @param y - y coordinate of top left
	 * @param w - height of rectangle
	 * @param h - width of rectangle
	 * @return true if in else false
	 */
	public boolean contains( double x, double y, double w, double h){
		// if given x and y are bigger and equal to x() and y()
		// and if x+w and y+h are smaller and equal to x()+w() and y+h()
		return x >= x() && x + w <= wPos() && y >= y() && y + h <= hPos();
	}

	/**
	 * check if given point is with in this {@link RectangleBounds}
	 * @param p - given point with x and y
	 * @return true if in else false
	 */
	public boolean contains( Point p){
		return contains( p.x(), p.y());
	}

	/**
	 * check if given (x,y) is with in this {@link RectangleBounds}
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return true if in else false
	 */
	public boolean contains( double x, double y){
		// if given x and y are bigger and equal to x() and y()
		// and if x and y are smaller and equal to x()+w() and y+h()
		return x >= x() && x <= wPos() && y >= y() && y <= hPos();
	}

	/**
	 * convert this object to a set of points starting from top right going clockwise.
	 * @param array - an array of size 2 by 4 must be provided
	 * @return the provided array with the points of rectangle in it
	 */
	public double[][] toArray( double[][] array){
		if( array == null || array.length < 2 || array[0].length < 4)
			throw new IllegalArgumentException( "array must be initilized to 2 rows and 4 columns");
		array[0][0] = array[0][3] = start.x();
		array[0][1] = array[0][2] = start.x() + dimension.x();
		array[1][0] = array[1][1] = start.y();
		array[1][2] = array[1][3] = start.y() + dimension.y();
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode( (start == null) ? 0 : x());
		result = prime * result + Double.hashCode( (start == null) ? 0 : y());
		result = prime * result + Double.hashCode( (dimension == null) ? 0 : w());
		result = prime * result + Double.hashCode( (dimension == null) ? 0 : h());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj){
		if( this == obj)
			return true;
		if( obj == null)
			return false;
		if( !(obj instanceof RectangleBounds))
			return false;
		RectangleBounds other = (RectangleBounds) obj;
		if( dimension == null){
			if( other.dimension != null)
				return false;
		}else if( !dimension.equals( other.dimension))
			return false;
		if( start == null){
			if( other.start != null)
				return false;
		}else if( !start.equals( other.start))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public String toString(){
		return String.format( "start:%s, size:%s", start, dimension);
	}
}
