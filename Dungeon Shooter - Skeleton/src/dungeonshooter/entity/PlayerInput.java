package dungeonshooter.entity;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utility.InputAdapter;

/**
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public class PlayerInput {

	// required field for this class
	private double x;
	private double y;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean leftClick = false;
	private boolean rightClick = false;
	private boolean middleClick = false;
	private boolean space = false;
	private boolean shift = false;

	/**
	 * This constructor - setting the mouse movements, keys, forces on adapter for
	 * PlayerInput
	 * 
	 * @param adapter
	 */
	public PlayerInput(InputAdapter adapter) {
		adapter.forceFocusWhenMouseEnters();
		adapter.registerMouseMovment(this::moved, this::dragged);
		adapter.registerMouseClick(this::mousePressed, this::mouseReleased);
		adapter.registerKey(this::keyPressed, this::keyReleased);

	}

	public boolean hasMoved() {
		return up || down || left || right;

	}

	// setting the conditions as mentioned in the document
	public int upOrDown() {
		if (down)
			return 1;

		else if (up)
			return -1;

		else
			return 0;

	}

	public int leftOrRight() {
		if (right)
			return 1;

		else if (left)
			return -1;

		else
			return 0;
	}

	/**
	 * 
	 * @return the left click when clicked
	 */
	public boolean leftClicked() {
		return leftClick;
	}

	public boolean rightClicked() {
		return rightClick;
	}

	public boolean middleClicked() {
		return middleClick;
	}

	/**
	 * Both method below returning the points x and y
	 * 
	 * @return x and y
	 */
	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	protected void mousePressed(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		leftClick = e.isPrimaryButtonDown();
		rightClick = e.isSecondaryButtonDown();
		middleClick = e.isMiddleButtonDown();
	}

	protected void mouseReleased(MouseEvent e) {
		leftClick = false;
		rightClick = false;
		middleClick = false;

	}

	// When any specific case is clicked it should do, what was assigned (KEY
	// STATUS)
	public void changeKeyStatus(KeyCode key, boolean isPressed) {

		switch (key) {
		case W:
			up = isPressed;
			break;
		case A:
			left = isPressed;
			break;
		case S:
			down = isPressed;
			break;
		case D:
			right = isPressed;
		case SPACE:

			space = isPressed;
			break;
		case SHIFT:
			shift = isPressed;
			break;

		default:
			break;

		}
	}

	/**
	 * true of pressed and false of released
	 * 
	 * @param key
	 */
	protected void keyPressed(KeyEvent key) {
		changeKeyStatus(key.getCode(), true);
	}

	protected void keyReleased(KeyEvent key) {
		changeKeyStatus(key.getCode(), false);
	}

	public boolean isSpace() {
		return space;
	}

	public boolean isShift() {
		return shift;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	protected void moved(MouseEvent e) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	protected void dragged(MouseEvent e) {
		this.x = x;
		this.y = y;
	}

}
