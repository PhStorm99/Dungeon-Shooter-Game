package utility;

import java.util.Objects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * this class represent a point of x and y. it is back by {@link DoubleProperty}.
 * it also also capable of producing random points.
 * 
 * @author Shahriar (Shawn) Emami
 * @version Jan 12, 2019
 */
public class Point{

	/**
	 * {@link DoubleProperty} value of x and y
	 */
	private DoubleProperty x, y;
	
	/**
	 * create a new point at (0,0)
	 */
	public Point(){
		this( new SimpleDoubleProperty(), new SimpleDoubleProperty());
	}

	/**
	 * create a new point with {@link DoubleProperty} for x and y
	 * @param x - value of x
	 * @param y - value of y
	 */
	private Point( DoubleProperty x, DoubleProperty y){
		Objects.requireNonNull( x, "x property cannot be null");
		Objects.requireNonNull( y, "y property cannot be null");
		this.x = x;
		this.y = y;
	}

	/**
	 * create a new point with given x and y
	 * @param x - value of x
	 * @param y - value of y
	 */
	public Point( double x, double y){
		this( new SimpleDoubleProperty( x), new SimpleDoubleProperty( y));
	}
	
	/**
	 * create a new point from given point
	 */
	public Point( Point p){
		this( p.x(), p.y());
	}
	
	public Point bind(Point p){
		x.bind( p.x);
		y.bind( p.y);
		return this;
	}
	
	public Point unbind(){
		x.unbind();
		y.unbind();
		return this;
	}
	
	public Point bindBidirectional(Point p){
		x.bindBidirectional( p.x);
		y.bindBidirectional( p.y);
		return this;
	}
	
	public Point unbindBidirectional(Point p){
		x.unbindBidirectional( p.x);
		y.unbindBidirectional( p.y);
		return this;
	}
	
	/**
	 * translate the point to a new location by the given distance
	 * @param dx - amount to move in x direction
	 * @param dy - amount to move in y direction
	 */
	public void translate( double dx, double dy){
		x(x()+dx);
		y(y()+dy);
	}

	/**
	 * move the current point to x and y
	 * @param x - new value of x
	 * @param y - new value of y
	 */
	public void move( double x, double y){
		set( x, y);
	}

	/**
	 * move the current point to a new point p 
	 * @param p - new point
	 */
	public void move( Point p){
		set( p.x(), p.y());
	}

	/**
	 * set the value of x and y
	 * @param x - new value of x
	 * @param y - new value of y
	 */
	public void set( double x, double y){
		x(x);
		y(y);
	}

	/**
	 * get value of x
	 * @return value of x
	 */
	public double x(){
		return x.get();
	}

	/**
	 * get the {@link DoubleProperty} of x value
	 * @return {@link DoubleProperty} of x value
	 */
	public DoubleProperty xProperty(){
		return x;
	}

	/**
	 * get value of y
	 * @return value of y
	 */
	public double y(){
		return y.get();
	}

	/**
	 * get the {@link DoubleProperty} of y value
	 * @return {@link DoubleProperty} of y value
	 */
	public DoubleProperty yProperty(){
		return y;
	}

	/**
	 * set value of x
	 * @param x - new value of x
	 * @return return the current instance of this point
	 */
	public Point x( double x){
		this.x.set( x);
		return this;
	}

	/**
	 * set value of y
	 * @param y - new value of y
	 * @return return the current instance of this point
	 */
	public Point y( double y){
		this.y.set( y);
		return this;
	}
	
	/**
	 * get the angle between this point and given point
	 * @param p - a point which to find the angle
	 * @return angle between two points
	 */
	public double angle( Point p){
		return Math.atan2( y()-p.y(), x()-p.x());
	}
	
	/**
	 * convert point to an array;
	 * @return return an array of [x,y]
	 */
	public double[] toArray(){
		return new double[]{x(),y()};
	}

	/**
	 * create a random point around this point with given max radius.
	 * @param maxRadius - absolute value of max radius
	 * @return a new random point
	 */
	public Point randomPoint( double maxRadius){
		double angle = RandUtil.getDouble( 360);
		double randR = RandUtil.getDouble( maxRadius);
		double dx = x() + randR * Math.cos( Math.toRadians( angle));
		double dy = y() - randR * Math.sin( Math.toRadians( angle));
		return new Point( dx, dy);
	}

	/**
	 * create a random point around this point with given max distance on x and y.
	 * @param maxDx - absolute value of max distance in x direction
	 * @param maxDy - absolute value of max distance in y direction
	 * @return a new random point
	 */
	public Point randomPoint( double maxDx, double maxDy){
		double dx = x() + RandUtil.getDouble( maxDx) * RandUtil.getPosNeg();
		double dy = y() + RandUtil.getDouble( maxDy) * RandUtil.getPosNeg();
		return new Point( dx, dy);
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
		result = prime * result + Double.hashCode( (x == null) ? 0 : x());
		result = prime * result + Double.hashCode( (y == null) ? 0 : y());
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
		if( !(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if( x == null){
			if( other.x != null)
				return false;
		}else if( x.get() != other.x.get())
			return false;
		if( y == null){
			if( other.y != null)
				return false;
		}else if( y.get() != other.y.get())
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "(" + x() + "," + y() + ")";
	}

	/**
	 * get a random point in the area of given height and with minus the given margin
	 * @param w - width of the rectangle which in point will spawn
	 * @param h - height of the rectangle which in point will spawn
	 * @param margin - the margin around the rectangle which point will not spawn
	 * @return a new point between the given height and width
	 */
	public static Point random( double w, double h, double margin){
		return random( w, h, margin, margin);
	}

	/**
	 * get a random point in the area of given height and with minus the given margin
	 * @param w - width of the rectangle which in point will spawn
	 * @param h - height of the rectangle which in point will spawn
	 * @param marginX - the margin on the width of rectangle which point will not spawn
	 * @param marginY - the margin on the height of rectangle which point will not spawn
	 * @return a new point between the given height and width
	 */
	public static Point random( double w, double h, double marginX, double marginY){
		return new Point()
				.x( RandUtil.getDouble( marginX, w - marginX))
				.y( RandUtil.getDouble( marginY, h - marginY));
	}
}
