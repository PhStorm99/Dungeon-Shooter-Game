
package raycast;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import raycast.animator.AbstractAnimator;
import raycast.entity.geometry.PolyShape;

/**
 * This is the CanvasMapInterface which just has only method with no body.
 * 
 * @author hnpav
 * @version Nov 09,2019
 */
public interface CanvasMapInterface {

	/**
	 * return the property value
	 * 
	 * @return value when implemented should not return null, unless specified as
	 *         not needed for lab.
	 */
	public BooleanProperty drawFPSProperty();

	/**
	 * return the value in the property. you can use the get method.
	 * 
	 * @return value stored inside property.
	 */
	public boolean getDrawFPS();

	/**
	 * return the property value
	 * 
	 * @return value when implemented should not return null, unless specified as
	 *         not needed for lab.
	 */
	public BooleanProperty drawBoundsProperty();

	/**
	 * return the value in the property. you can use the get method.
	 * 
	 * @return value stored inside property.
	 */
	public boolean getDrawBounds();

	/**
	 * return the property value
	 * 
	 * @return value when implemented should not return null, unless specified as
	 *         not needed for lab.
	 */
	public BooleanProperty drawSectorsProperty();

	/**
	 * return the value in the property. you can use the get method.
	 * 
	 * @return value stored inside property.
	 */
	public boolean getDrawSectors();

	/**
	 * return the property value
	 * 
	 * @return value when implemented should not return null, unless specified as
	 *         not needed for lab.
	 */
	public BooleanProperty drawLightSourceProperty();

	/**
	 * return the value in the property. you can use the get method.
	 * 
	 * @return value stored inside property.
	 */
	public boolean getDrawLightSource();

	/**
	 * return the property value
	 * 
	 * @return value when implemented should not return null, unless specified as
	 *         not needed for lab.
	 */
	public BooleanProperty drawIntersectPointProperty();

	/**
	 * return the value in the property. you can use the get method.
	 * 
	 * @return value stored inside property.
	 */
	public boolean getDrawIntersectPoint();

	/**
	 * return the property value
	 * 
	 * @return value when implemented should not return null, unless specified as
	 *         not needed for lab.
	 */
	public BooleanProperty drawShapeJointsProperty();

	/**
	 * return the value in the property. you can use the get method.
	 * 
	 * @return value stored inside property.
	 */
	public boolean getDrawShapeJoints();

	/**
	 * return the property value
	 * 
	 * @return value when implemented should not return null, unless specified as
	 *         not needed for lab.
	 */
	public IntegerProperty rayCountProperty();

	/**
	 * return the value in the property. you can use the get method.
	 * 
	 * @return value stored inside property.
	 */
	public int getRayCount();

	/**
	 * set an {@link AbstractAnimator}. if an animator exists
	 * {@link CanvasMap#stop()} it and {@link CanvasMap#removeMouseEvents()}
	 * registered events. then set the new animator and call
	 * {@link CanvasMap#start()} and {@link CanvasMap#registerMouseEvents()}.
	 * 
	 * @param newAnimator
	 *            - new {@link AbstractAnimator} object
	 * @return the current instance of this object
	 */
	public CanvasMap setAnimator(AbstractAnimator newAnimator);

	/**
	 * start the animator
	 */
	public void start();

	/**
	 * stop the animator
	 */
	public void stop();

	/**
	 * get the JavaFX {@link Canvas} node
	 * 
	 * @return {@link Canvas} node
	 */
	public Canvas getCanvas();

	/**
	 * get the {@link GraphicsContext} of {@link Canvas} that allows direct drawing.
	 * 
	 * @return {@link GraphicsContext} of {@link Canvas}
	 */
	public GraphicsContext gc();

	/**
	 * get the height of the map
	 * 
	 * @return
	 */
	public double h();

	/**
	 * get the width of the map
	 * 
	 * @return
	 */
	public double w();

	/**
	 * get the list of all shapes.
	 * 
	 * @return list of shapes
	 */
	public List<PolyShape> shapes();

	/**
	 * load a set of sample {@link PolyShapes}
	 */
	public void addSampleShapes();
}
