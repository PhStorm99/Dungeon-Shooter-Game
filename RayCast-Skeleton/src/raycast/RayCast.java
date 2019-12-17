package raycast;

import java.util.List;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import raycast.animator.AbstractAnimator;
import raycast.animator.MultiRayAnimator;
import raycast.animator.StaticShapes;
import raycast.animator.TextAnimator;

/**
 * this is the start of JavaFX application. this class must extend
 * {@link Application}.
 * 
 * @see <a href="https://stackoverflow.com/a/565282/764951">Two line segments
 *      intersect</a>
 * @see <a href="https://ncase.me/sight-and-light/">Sight and Light, simple ray
 *      cast tutorial</a>
 * 
 * @author Harsh Patel
 * @version Oct 13, 2019
 */
public class RayCast extends Application {

	/**
	 * size of the scene
	 */
	private double width = 700, height = 700;
	/**
	 * title of application
	 */
	private String title = "RayCast";
	/**
	 * background color of application
	 */
	private Color background = Color.LIGHTPINK;
	/**
	 * {@link BorderPane} is a layout manager that manages all nodes in 5 areas as
	 * below:
	 * 
	 * <pre>
	 * -----------------------
	 * |        top          |
	 * -----------------------
	 * |    |          |     |
	 * |left|  center  |right|
	 * |    |          |     |
	 * -----------------------
	 * |       bottom        |
	 * -----------------------
	 * </pre>
	 * 
	 * this object is passed to {@link Scene} object in {@link RayCast#start(Stage)}
	 * method.
	 */
	private BorderPane root;
	/**
	 * this object represents the canvas (drawing area)
	 */
	private CanvasMap board;
	/**
	 * this is a ChoiceBox that holds all different animators available
	 */
	private ChoiceBox<AbstractAnimator> animatorsBox;
	/**
	 * <p>
	 * a list of all animators available.<br>
	 * {@link ObservableList} is a wrapper of a normal {@link List} data structure.
	 * the difference being {@link ObservableList} is capable of notifying any
	 * observer that the list has been changed if elements have been added, removed
	 * or the list is changed in any manner. the best way to initialize any
	 * ObservableList is to use the utility class {@link FXCollections}.
	 * </p>
	 * 
	 * <pre>
	 * ObservableList<Integer> nums = FXCollections.observableArrayList();
	 * nums.addListener((Change<? extends Integer> c) -> {
	 * 	while (c.next()) {
	 * 		List<? extends Integer> added = c.getAddedSubList();
	 * 		System.out.println(added);
	 * 		List<? extends Integer> removed = c.getRemoved();
	 * 		System.out.println(removed);
	 * 	}
	 * });
	 * nums.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
	 * nums.removeAll(1, 3, 5, 7, 9);
	 * </pre>
	 * <p>
	 * every time values are added or removed they will be printed.<br>
	 * however, in this application we are not using this feature of list.
	 * </p>
	 */
	private ObservableList<AbstractAnimator> animators;

	/**
	 * this method is called at the very beginning of the JavaFX application and can
	 * be used to initialize all components in the application. however,
	 * {@link Scene} and {@link Stage} must not be created in this method. this
	 * method does not run JavaFX thread.
	 */

	@Override
	public void init() throws Exception {
		// Initialize the animators with FXCollections.observableArrayList and pass to
		// it a new TextAnimator and the new Static Shapes

		animators = FXCollections.observableArrayList(new TextAnimator(), new StaticShapes(), new MultiRayAnimator());

		board = new CanvasMap();// initialize the board object

		ToolBar optionsBar = createOptionsBar(); // create two ToolBar objects and store createStatusBar() and
													// createOptionsBar() in each
		ToolBar statusBar = createStatusBar();

		root = new BorderPane();// initialize root

		root.setTop(optionsBar);// call setTop on it and pass to it the options bar
		root.setCenter(board.getCanvas());// call setCenter on it and pass to it board.getCanvas()
		root.setBottom(statusBar);// call setBottom on it and pass to it the status bar

		// we need to bind the height and width of of canvas to root so if screen is
		// resized board is resized as well.
		board.getCanvas().widthProperty().bind(root.widthProperty());// to bind the width get the canvas from board
																		// first then call widthProperty on it and then
																		// bind root.widthProperty to it

		// board.getCanvas().widthProperty().bind( root.widthProperty());
		// to bind the height it is almost the same process however the height of
		// options and status bar need to be subtracted from
		board.getCanvas().heightProperty()
				.bind(root.heightProperty().subtract(optionsBar.heightProperty()).subtract(statusBar.heightProperty()));

		// root height. subtract can be done root.heightProperty().subtract(
		// statusBar.heightProperty()).
		// you also need to subtract optionsBar.heightProperty as well.

		animators.forEach(a -> a.setCanvas(board));
		// loop through all animators and setCanvas as boar

		board.addSampleShapes();

	}

	/**
	 * <p>
	 * this method is called when JavaFX application is started and it is running on
	 * JavaFX thread. this method must at least create {@link Scene} and finish
	 * customizing {@link Stage}. these two objects must be on JavaFX thread when
	 * created.
	 * </p>
	 * <p>
	 * {@link Stage} represents the frame of your application, such as minimize,
	 * maximize and close buttons.<br>
	 * {@link Scene} represents the holder of all your JavaFX {@link Node}s.<br>
	 * {@link Node} is the super class of every javaFX class.
	 * </p>
	 * 
	 * @param primaryStage
	 *            - primary stage of your application that will be rendered
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// scene holds all JavaFX components that need to be displayed in Stage
		Scene scene = new Scene(root, width, height, background);
		primaryStage.setScene(scene);
		primaryStage.setTitle(title);
		// when escape key is pressed close the application
		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			if (KeyCode.ESCAPE == event.getCode()) {
				primaryStage.hide();
			}
		});
		// display the JavaFX application
		primaryStage.show();
		// select first index of animatorsBox as start,
		// this will also sets the new animator as the lambda we setup will be triggered
		animatorsBox.getSelectionModel().select(2);
	}

	/**
	 * this method is called at the very end when the application is about to exit.
	 * this method is used to stop or release any resources used during the
	 * application.
	 */
	@Override
	public void stop() throws Exception {
		board.stop();
	}

	/**
	 * create a {@link ToolBar} that represent the menu bar at the top of the
	 * application.
	 * 
	 * @return customized {@link ToolBar}
	 */
	public ToolBar createOptionsBar() {
		// use the createButton method and create a start button with lambda that calls
		// board.start()
		Button startButton = createButton("start", e -> board.start());

		// use the createButton method and create a start button with lambda that calls
		// board.stop()
		Button stopButton = createButton("stop", e -> board.stop());

		// create 2 Pane object called filler1 and filler2
		Pane filler1 = new Pane();
		Pane filler2 = new Pane();

		// Pane class is a super class of all layout mangers. by itself it has no rules.
		// call the static method setHgrow from Hbox with Filler1, Priority.ALWAYS
		HBox.setHgrow(filler1, Priority.ALWAYS);

		// call the static method setHgrow from Hbox with Filler2, Priority.ALWAYS
		HBox.setHgrow(filler2, Priority.ALWAYS);

		// this will allow the filler to expand and fill the space between nodes

		// create a Spinner object called rayCount with generic type of Integer
		Spinner<Integer> rayCount = new Spinner<>(1, Integer.MAX_VALUE, 360 * 3); // in the constructor pass to it 1 as
																					// min, Integer.MAX_VALUE as max and
																					// 360*3 as current

		rayCount.setEditable(true); // call setEditable on it and set to true so the counter can be changed by
									// typing in it.
		rayCount.setMaxWidth(100);// call setMaxWidth on it and set 100, as default size it too big
		board.rayCountProperty().bind(rayCount.valueProperty());// get rayCount property from CanvasMap and bind it to
																// rayCount.valueProperty().
		// rayCount.valueProperty() should be passed as argument.

		MenuButton option = new MenuButton("Options", null, // create a MenuButton with argument "Options", null and all
															// of created CheckMenuItem.

				createCheckMenuItem("FPS", true, board.drawFPSProperty()),
				createCheckMenuItem("Intersects", false, board.drawIntersectPointProperty()),
				createCheckMenuItem("Lights", false, board.drawLightSourceProperty()),
				createCheckMenuItem("Joints", false, board.drawShapeJointsProperty()),
				createCheckMenuItem("Bounds", false, board.drawBoundsProperty()),
				createCheckMenuItem("Sectors", false, board.drawSectorsProperty()));
		// call createCheckMenuItem 6 times and use following names:
		// FPS, Intersects, Lights, Joints, Bounds, Sectors
		// only FPS is selected the rest are false.
		// as last argument get the equivalent property from CanvasMap

		animatorsBox = new ChoiceBox<>(animators);// Initialize animatorsBox with the animators list

		// finally as argument for addListener pass a lambda that sets the new animator
		// for CanvasMap.
		// this Lambda is called ChangeListener and it takes 3 arguments:
		// ObservableValue<? extends T> observable, it is an object that represent the
		// observable data
		// T oldValue, it is the old value before the change
		// T newValue, it is the new value after the change
		// this lambda will only use the newValue argument which passed to setAnimator
		// of CanvasMap
		animatorsBox.getSelectionModel().selectedItemProperty()
				.addListener((value, oldvalue, newvalue) -> board.setAnimator(newvalue));// call getSelectionModel on
																							// animatorsBox then call
																							// selectedItemProperty and
																							// then call addListener.

		// create a new ToolBar and as arguments of its constructor pass the create
		// nodes.
		// there should be 8:
		// startButton, stopButton, filler1, rayCount,
		// options, filler2, new Label( "Animators "), animatorsBox
		// return the created ToolBar

		ToolBar tb = new ToolBar(startButton, stopButton, filler1, rayCount, option, filler2, new Label("Animators"),
				animatorsBox);
		return tb;
	}

	/**
	 * create a {@link ToolBar} that will represent the status bar of the
	 * application.
	 * 
	 * @return customized {@link ToolBar}
	 */
	public ToolBar createStatusBar() {
		// create two Label objects and with value of "(0,0)".
		// one label keeps track of mouse movement and other mouse drag.

		// call addEventHandler on canvas for MouseEvent.MOUSE_MOVED event and pass a
		// lambda to
		// it that will update the text in one of the labels with the new coordinate of
		// the mouse.
		// the lambda will take one argument of type MouseEvent and two methods
		// getX and getY from MouseEvnet can be used to get position of the mouse

		// call addEventHandler on canvas for MouseEvent.MOUSE_DRAGGED event and pass a
		// lambda to
		// it that will update the text in one of the labels with the new coordinate of
		// the mouse.
		// the lambda will take one argument of type MouseEvent and two methods
		// getX and getY from MouseEvnet can be used to get position of the mouse

		// create a new ToolBar and as arguments of its constructor pass the create
		// labels to it.
		// there should be 4 labels: new Label( "Mouse: "), mouseCoordLabel, new Label(
		// "Drag: "), dragCoordLabel
		// return the created ToolBar

		Label mouseCoordLabel = new Label("(0,0)");
		Label dragCoordLabel = new Label("(0,0)");

		board.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			mouseCoordLabel.setText("(" + Double.toString(e.getX()) + "," + Double.toString(e.getY()) + ")");
		});
		board.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			dragCoordLabel.setText("(" + Double.toString(e.getX()) + "," + Double.toString(e.getY()) + ")");
		});

		ToolBar tb = new ToolBar(new Label("Mouse: "), mouseCoordLabel, new Label("Drag: "), dragCoordLabel);

		return tb;

	}

	/**
	 * <p>
	 * create a {@link CheckMenuItem} with given text, initial state and
	 * {@link BooleanProperty} binding. {@link BooleanProperty} is a special class
	 * that can be binded to another {@link BooleanProperty}. this means every time
	 * bindee changes the other {@link BooleanProperty} changes as well. for
	 * example:
	 * </p>
	 * 
	 * <pre>
	 * BooleanProperty b1 = new SimpleBooleanProperty(false);
	 * BooleanProperty b2 = new SimpleBooleanProperty();
	 * b1.bind(b2);
	 * b2.set(true);
	 * System.out.println(b1.get()); // prints true
	 * IntegerProperty i1 = new SimpleIntegerProperty(1);
	 * IntegerProperty i2 = new SimpleIntegerProperty();
	 * i1.bind(i2);
	 * i2.set(100);
	 * System.out.println(i1.get()); // prints 100
	 * </pre>
	 * 
	 * <p>
	 * if p2 changes p1 changes as well. this relationship is NOT bidirectional.
	 * </p>
	 * 
	 * @param text
	 *            - String to be displayed
	 * @param isSelected
	 *            - initial state, true id selected
	 * @param binding
	 *            - {@link BooleanProperty} that will be notified if state of this
	 *            {@link CheckMenuItem} is changed
	 * @return customized {@link CheckMenuItem}
	 */
	public CheckMenuItem createCheckMenuItem(String text, boolean isSelected, BooleanProperty binding) {

		CheckMenuItem check = new CheckMenuItem(text); // create a new CheckMenuItem object with given text.

		binding.bind(check.selectedProperty()); // call bind on binding and pass to it check.selectedProperty(),

		// this will notify the binding object every time check.selectedProperty() is
		// changed.

		check.setSelected(isSelected); // call setSelected and pass to it isSelected.

		return check; // return the created CheckMenuItem.
	}

	/**
	 * create a {@link Button} with given text and lambda for when it is clicked
	 * 
	 * @param text
	 *            - String to be displayed
	 * @param action
	 *            - lambda for when the button is clicked, the lambda will take one
	 *            argument of type ActionEvent
	 * @return customized {@link Button}
	 */
	public Button createButton(String text, EventHandler<ActionEvent> action) {
		// create a new Button object with given text
		// call setOnAction on created button and give it action
		// return the created button

		Button btn = new Button(text);
		btn.setOnAction(action);
		return btn;
	}

	/**
	 * main starting point of the application
	 * 
	 * @param args
	 *            - arguments provided through command line, if any
	 */
	public static void main(String[] args) {
		// launch( args); method starts the javaFX application.
		// some IDEs are capable of starting JavaFX without this method.
		launch(args);
	}
}
