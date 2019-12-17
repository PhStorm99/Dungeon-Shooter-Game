package raycast.entity.property;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * This is an interface named Drawable which has generic declaring the methods
 * which Sprite class has.
 * 
 * @author hnpav
 * @version Nov 02,2019
 * @param <T>
 *            extending the Sprite
 */
public interface Drawable<T extends Sprite> {

	public T setFill(Paint paint);

	public T setStroke(Paint paint);

	public T setWidth(double width);

	public void draw(GraphicsContext gc);

	public Paint getFill();

	public Paint getStroke();

	public double getWidth();
}
