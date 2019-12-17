package dungeonshooter;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utility.InputAdapter;
import java.io.IOException;
import java.util.List;
import dungeonshooter.CanvasMap;
import dungeonshooter.animator.*;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.Player;
import dungeonshooter.entity.PlayerInput;

/**
 * this is the start of JavaFX application. this class must extend
 * {@link Application}.
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */

public class DungeonShooter extends Application {
	/**
	 * size of the scene
	 */
	private double width = 700, height = 700;
	/**
	 * title of application
	 */
	private String title = "Dungeon Shooter";
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
	private ChoiceBox<AbstractAnimator> animatorsBox;

	private Canvas canvas;

	private CanvasMap board;
	private PlayerInput input;
	private Animator animator;
	private ObservableList<AbstractAnimator> animators;

	private Player player;

	private List<Entity> players;

	/**
	 * This is an init method which is defining all the variables like board,
	 * player, input, animator, setting the root and the overall layout and outlook
	 * of JavaFX by adjusting the height and width of the canvas.
	 */
	@Override
	public void init() throws Exception {
		canvas = new Canvas();

		input = new PlayerInput(new InputAdapter(canvas));

		board = new CanvasMap();

		player = new Player((width) / 2, (height) / 2, 70, 46);

		player.setInput(input);
		player.setMap(board);

		animator = new Animator();            // setting the animator
		animators = FXCollections.observableArrayList(animator);

		animator.setCanvas(board);

		board.setDrawingCanvas(canvas);

		board.setAnimator(animator);

		board.addSampleShapes();

		board.players().add(player);		// adding the player to board

		ToolBar optionsBar = createOptionsBar(); // create two ToolBar objects and store createStatusBar() and
													// createOptionsBar() in each
		ToolBar statusBar = createStatusBar();

		root = new BorderPane();// initialize root

		root.setTop(optionsBar);// call setTop on it and pass to it the options bar
		root.setCenter(board.getCanvas());// call setCenter on it and pass to it board.getCanvas()
		root.setBottom(statusBar);// call setBottom on it and pass to it the status bar

		// we need to bind the height and width of of canvas to root so if screen is
		// resized board is resized as well.
		board.getCanvas().heightProperty()
				.bind(root.heightProperty().subtract(optionsBar.heightProperty()).subtract(statusBar.heightProperty()));

		// to bind the width get the canvas from board
		// first then call widthProperty on it and then
		// bind root.widthProperty to it
		board.getCanvas().widthProperty().bind(root.widthProperty());

		// loop through all animators and setCanvas as boar
		animators.forEach((a) -> {
			a.setCanvas(board);
		});

	}

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
		animatorsBox.getSelectionModel().select(0);
		board.start();
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

	public ToolBar createOptionsBar() {
		Button startButton = createButton("start", e -> board.start());
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

		MenuButton option = new MenuButton("Options", null, // create a MenuButton with argument "Options", null and all
															// of created CheckMenuItem.

				createCheckBox("FPS", true, board.drawFPSProperty()),
				createCheckBox("Bounds", false, board.drawBoundsProperty()));

		// call createCheckMenuItem 6 times and use following names:
		// FPS,Bounds, Sectors
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
				.addListener((value, oldvalue, newvalue) -> board.setAnimator(animator));// call getSelectionModel on
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

		canvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			mouseCoordLabel.setText("(" + Double.toString(e.getX()) + "," + Double.toString(e.getY()) + ")");
		});
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			dragCoordLabel.setText("(" + Double.toString(e.getX()) + "," + Double.toString(e.getY()) + ")");
		});

		ToolBar tb = new ToolBar(new Label("Mouse: "), mouseCoordLabel, new Label("Drag: "), dragCoordLabel);

		return tb;

	}

	public CheckMenuItem createCheckBox(String text, boolean isSelected, BooleanProperty binding) {

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
