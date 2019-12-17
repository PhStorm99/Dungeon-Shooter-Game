package raycast.entity.property;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Abstract class Sprite implementing the Drawable interface
 * 
 * @author hnpav
 * 
 */
public abstract class Sprite implements Drawable<Sprite> {

	// fields declaration
	private double strokeWidth;
	private Paint fillColor;
	private Paint strokeColor;

	public Sprite setFill(Paint color) {

		fillColor = color;
		return this;
	}

	public Sprite setStroke(Paint color) {

		strokeColor = color;
		return this;
	}

	public Sprite setWidth(double width) {
		strokeWidth = width;
		return this;
	}

	public double getWidth() {
		return strokeWidth;
	}

	@Override
	abstract public void draw(GraphicsContext gc);

	public Paint getFill() {
		return fillColor;
	}

	public Paint getStroke() {
		return strokeColor;
	}

}
