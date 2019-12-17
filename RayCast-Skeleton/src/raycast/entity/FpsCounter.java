package raycast.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.scene.text.FontWeight;
import raycast.entity.property.Drawable;
import raycast.entity.property.Sprite;
import utility.Point;

/**
 * FpsCounter methods implements the entity method
 * 
 * @author hnpav
 * @version Nov 04,2019
 */
public class FpsCounter implements Entity {

	public double ONE_SECOND = 1000000000L;
	public double HALF_SECOND = ONE_SECOND / 2F;

	private Font fpsFont;
	private String fpsDisplay;
	private int frameCount;
	private double lastTime;
	private Point pos;
	private Sprite sprite;
	private GraphicsContext gc;
	private Font temp;

	/**
	 * Constructor which describes the x and y and also instantiating the sprite
	 * variable and the draw method which sets the font, fill, text, line width,
	 * stroke and storing them all int the temp variable
	 * 
	 * @param x
	 * @param y
	 */
	public FpsCounter(double x, double y) {
		pos = new Point();
		setPos(x, y);
		setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24));

		sprite = new Sprite() {
			public void draw(GraphicsContext gc) {
				temp = gc.getFont();
				gc.setFont(fpsFont);
				gc.setFill(sprite.getFill());
				gc.fillText(fpsDisplay, pos.x(), pos.y());
				gc.setStroke(sprite.getStroke());
				gc.setLineWidth(sprite.getWidth());
				gc.strokeText(fpsDisplay, pos.x(), pos.y());
				gc.setFont(temp);
			}
		};

	}

	// Calculating the FPs through the now parameter counting the frame and
	// increamenting them
	public void calculateFPS(long now) {
		if (now - lastTime > HALF_SECOND) {
			fpsDisplay = Integer.toString(frameCount * 2);
			frameCount = 0;
			lastTime = now;
		}
		frameCount++;
	}

	/**
	 * Setting the font
	 * 
	 * @param font
	 * @return this reference variable
	 */
	public FpsCounter setFont(Font font) {
		fpsFont = Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24);
		return this;
	}

	/**
	 * Creating the setting the x and y
	 * 
	 * @param x
	 * @param y
	 * @return this variable
	 */
	public FpsCounter setPos(double x, double y) {
		pos.set(x, y);
		return this;
	}

	/**
	 * This method is just an declaration
	 * 
	 * return the false
	 */
	public boolean isDrawable() {
		return false;
	}

	/**
	 * getDrawable method of type Sprite
	 * 
	 * @return sprite variable
	 */
	public Sprite getDrawable() {
		return sprite;

	}

}
