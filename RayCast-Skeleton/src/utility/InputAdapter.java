package utility;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

/**
 * <p>
 * an abstract adapter class for {@link KeyEvent}, {@link MouseEvent} and {@link TouchEvent} events.
 * when extending this class only override methods that are needed. register method for those event methods must also be called.
 * <pre>
 * 	public class CanvasInput extends InputAdapter&lt;Canvas&gt{
 * 		protected void touchMoved( TouchEvent e){
 * 			System.out.println("Touch moved");
 * 			//call super method at the end
 * 			super.touchMoved(e)
 * 		}
 * 	}
 * 
 * 	Canvas canvas = new Canvas();
 * 	InputAdapter< Canvas> input = new CanvasInput();
 * 	input.registerTouch(canvas);
 * </pre>
 * </p>
 * 
 * @author Shahriar (Shawn) Emami
 * @version January 9, 2019
 * @version September 15, 2019
 */
public class InputAdapter {
	
	/**
	 * the {@link Node} from which the event handlers are removed and/or added.
	 */
	private Node node;
	
	public InputAdapter( Node node){
		this.node = node;
	}

	/**
	 * <p>
	 * add the given {@link EventHandler} with {@link EventType} to given {@link InputAdapter#node}.
	 * </p>
	 * @param event - an event such as {@link MouseEvent#MOUSE_MOVED}.
	 * @param handler - a lambda to be used when registered event is triggered.
	 */
	public < E extends Event> void addEventHandler( EventType< E> event, EventHandler< E> handler){
		node.addEventHandler( event, handler);
	}

	/**
	 * <p>
	 * remove the given {@link EventHandler} registered with {@link EventType} on given {@link InputAdapter#node}.
	 * </p>
	 * @param event - an event such as {@link MouseEvent#MOUSE_MOVED}.
	 * @param handler - a lambda to be used when registered event is triggered.
	 */
	public < E extends Event> void removeEventHandler( EventType< E> event, EventHandler< E> handler){
		node.removeEventHandler( event, handler);
	}
	
	/**
	 * <p>
	 * when the mouse enters the area of given {@link InputAdapter#node} in constructor, {@link MouseEvent#MOUSE_ENTERED} is triggered 
	 * and subsequently {@link Node#requestFocus()} is called to force focus if {@link InputAdapter#node} is eligible.
	 * </p>
	 */
	public void forceFocusWhenMouseEnters(){
		addEventHandler( MouseEvent.MOUSE_ENTERED, e->node.requestFocus());
	}
	
	/**
	 * <p>
	 * add {@link MouseEvent#MOUSE_MOVED} and {@link MouseEvent#MOUSE_DRAGGED} event handlers to given {@link InputAdapter#node}.
	 * </p>
	 * @param move - lambda or method reference which takes one argument. to be called when mouse moves
	 * @param drag - lambda or method reference which takes one argument. to be called when mouse drags
	 */
	public void registerMouseMovment( EventHandler< MouseEvent> move, EventHandler< MouseEvent> drag){
		if(move!=null)
			addEventHandler( MouseEvent.MOUSE_MOVED, move);
		if(drag!=null)
			addEventHandler( MouseEvent.MOUSE_DRAGGED, drag);
	}
	
	/**
	 * <p>
	 * remove {@link MouseEvent#MOUSE_MOVED} and {@link MouseEvent#MOUSE_DRAGGED} event handlers from given {@link InputAdapter#node}.
	 * </p>
	 * @param move - lambda or method reference which takes one argument. to be called when mouse moves
	 * @param drag - lambda or method reference which takes one argument. to be called when mouse drags
	 */
	public void removeMouseMovment( EventHandler< MouseEvent> move, EventHandler< MouseEvent> drag){
		if(move!=null)
			removeEventHandler( MouseEvent.MOUSE_MOVED, move);
		if(drag!=null)
			removeEventHandler( MouseEvent.MOUSE_DRAGGED, drag);
	}
	
	/**
	 * <p>
	 * add {@link MouseEvent#MOUSE_ENTERED_TARGET} and {@link MouseEvent#MOUSE_EXITED_TARGET} event handlers to given {@link InputAdapter#node}.
	 * </p>
	 * @param move - lambda or method reference which takes one argument. to be called when mouse enters target
	 * @param drag - lambda or method reference which takes one argument. to be called when mouse leaves target
	 */
	public void registerMouseEnterExit( EventHandler< MouseEvent> move, EventHandler< MouseEvent> drag){
		if(move!=null)
			addEventHandler( MouseEvent.MOUSE_ENTERED_TARGET, move);
		if(drag!=null)
			addEventHandler( MouseEvent.MOUSE_EXITED_TARGET, drag);
	}
	
	/**
	 * <p>
	 * remove {@link MouseEvent#MOUSE_ENTERED_TARGET} and {@link MouseEvent#MOUSE_EXITED_TARGET} event handlers from given {@link InputAdapter#node}.
	 * </p>
	 * @param move - lambda or method reference which takes one argument. to be called when mouse enters target
	 * @param drag - lambda or method reference which takes one argument. to be called when mouse leaves target
	 */
	public void removeMouseEnterExit( EventHandler< MouseEvent> move, EventHandler< MouseEvent> drag){
		if(move!=null)
			removeEventHandler( MouseEvent.MOUSE_ENTERED_TARGET, move);
		if(drag!=null)
			removeEventHandler( MouseEvent.MOUSE_EXITED_TARGET, drag);
	}
	
	/**
	 * <p>
	 * add {@link MouseEvent#MOUSE_PRESSED} and {@link MouseEvent#MOUSE_RELEASED} event handlers to given {@link InputAdapter#node}.
	 * </p>
	 * @param pressed - lambda or method reference which takes one argument. to be called when mouse is pressed
	 * @param released - lambda or method reference which takes one argument. to be called when mouse is released
	 */
	public void registerMouseClick( EventHandler< MouseEvent> pressed, EventHandler< MouseEvent> released){
		if(pressed!=null)
			addEventHandler( MouseEvent.MOUSE_PRESSED, pressed);
		if(released!=null)
			addEventHandler( MouseEvent.MOUSE_RELEASED, released);
	}
	
	/**
	 * <p>
	 * remove {@link MouseEvent#MOUSE_PRESSED} and {@link MouseEvent#MOUSE_RELEASED} event handlers from given {@link InputAdapter#node}.
	 * </p>
	 * @param pressed - lambda or method reference which takes one argument. to be called when mouse is pressed
	 * @param released - lambda or method reference which takes one argument. to be called when mouse is released
	 */
	public void removeMouseClick( EventHandler< MouseEvent> pressed, EventHandler< MouseEvent> released){
		if(pressed!=null)
			removeEventHandler( MouseEvent.MOUSE_PRESSED, pressed);
		if(released!=null)
			removeEventHandler( MouseEvent.MOUSE_RELEASED, released);
	}
	
	/**
	 * <p>
	 * add {@link MouseEvent#MOUSE_PRESSED} and {@link MouseEvent#MOUSE_RELEASED} event handlers to given {@link InputAdapter#node}.
	 * </p>
	 * @param pressed - lambda or method reference which takes one argument. to be called when screen is touched
	 * @param dragged - lambda or method reference which takes one argument. to be called when screen is dragged
	 * @param released - lambda or method reference which takes one argument. to be called when screen is released
	 */
	public void registerMouseClick( EventHandler< TouchEvent> pressed, EventHandler< TouchEvent> dragged, EventHandler< TouchEvent> released){
		if(pressed!=null)
			addEventHandler( TouchEvent.TOUCH_PRESSED, pressed);
		if(dragged!=null)
			addEventHandler( TouchEvent.TOUCH_MOVED, dragged);
		if(released!=null)
			addEventHandler( TouchEvent.TOUCH_RELEASED, released);
	}
	
	/**
	 * <p>
	 * remove {@link TouchEvent#TOUCH_PRESSED}, {@link TouchEvent#TOUCH_MOVED} and {@link TouchEvent#TOUCH_RELEASED}
	 * event handlers from given {@link InputAdapter#node}.
	 * </p>
	 * @param pressed - lambda or method reference which takes one argument. to be called when screen is touched
	 * @param dragged - lambda or method reference which takes one argument. to be called when screen is dragged
	 * @param released - lambda or method reference which takes one argument. to be called when screen is released
	 */
	public void removeTouch( EventHandler< TouchEvent> pressed, EventHandler< TouchEvent> dragged, EventHandler< TouchEvent> released){
		if(pressed!=null)
			removeEventHandler( TouchEvent.TOUCH_PRESSED, pressed);
		if(dragged!=null)
			removeEventHandler( TouchEvent.TOUCH_MOVED, dragged);
		if(released!=null)
			removeEventHandler( TouchEvent.TOUCH_RELEASED, released);
	}
	
	/**
	 * <p>
	 * add {@link KeyEvent#KEY_PRESSED} and {@link KeyEvent#KEY_RELEASED} event handlers to given {@link InputAdapter#node}. 
	 * for full functionality {@link InputAdapter#forceFocusWhenMouseEnters} should be called as {@link KeyEvent} needs focus. 
	 * </p>
	 * @param pressed - lambda or method reference which takes one argument. to be called when key is pressed
	 * @param released - lambda or method reference which takes one argument. to be called when key is released
	 */
	public void registerKey( EventHandler< KeyEvent> pressed, EventHandler< KeyEvent> released){
		if(pressed!=null)
			addEventHandler( KeyEvent.KEY_PRESSED, pressed);
		if(released!=null)
			addEventHandler( KeyEvent.KEY_RELEASED, released);
	}
	
	/**
	 * <p>
	 * remove {@link KeyEvent#KEY_PRESSED} and {@link KeyEvent#KEY_RELEASED} event handlers from given {@link InputAdapter#node}.
	 * </p>
	 * @param pressed - lambda or method reference which takes one argument. to be called when key is pressed
	 * @param released - lambda or method reference which takes one argument. to be called when key is released
	 */
	public void removeKey( EventHandler< KeyEvent> pressed, EventHandler< KeyEvent> released){
		if(pressed!=null)
			removeEventHandler( KeyEvent.KEY_PRESSED, pressed);
		if(released!=null)
			removeEventHandler( KeyEvent.KEY_RELEASED, released);
	}
}
