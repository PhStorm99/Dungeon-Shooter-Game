package dungeonshooter.entity.property;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

/**
 * an interface used on drawable objects.
 * 
 * @param <T> - the type of class which implements this interface.
 * @author Shahriar (Shawn) Emami
 * @version Jan 12, 2019
 */
public interface Drawable<T extends Sprite>{
	/**
	 * set the {@link Paint} to be used when filling the shape
	 * @param paint - an object representing fill content like {@link Paint} or {@link ImagePattern} object
	 * @return the instance of current object
	 */
	T setFill( Paint paint);
	
	/**
	 * set the {@link Paint} to be used when stroking the shape
	 * @param paint - an obj		ect representing fill content like {@link Paint} or {@link ImagePattern} object
	 * @return the instance of current object
	 */
	T setStroke( Paint paint);
	
	/**
	 * set the stroke width to be used when stroking the shape
	 * @param width - stroke width
	 * @return the instance of current object
	 */
	T setWidth( double width);
	
	/**
	 * get the current fill {@link Color}
	 * @return {@link Paint}
	 */
	Paint getFill();

	/**
	 * get the current stroke {@link Color}
	 * @return {@link Paint}
	 */
	Paint getStroke();

	/**
	 * get the current stroke width
	 * @return stroke width
	 */
	double getWidth();
	
	/**
	 * draw the shape given the {@link GraphicsContext}
	 * @param gc - {@link GraphicsContext} object
	 */
	void draw( GraphicsContext gc);
}