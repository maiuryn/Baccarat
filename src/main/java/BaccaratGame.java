import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;


public class BaccaratGame extends Application {

	private String currentSceneName = "title";
	private Scene currentScene;
	private StackPane currentScreen;

	private int cash = 0;
	private int winnings = 0;
	private int losses = 0;

	// private BorderPane settings() {
	// 	BorderPane root = new BorderPane();
		

	// }

	private HBox menuBar() {
		Button settingsButton = new Button("Settings");
		Button statsButton = new Button("Stats");
		Button helpButton = new Button("Help");

		settingsButton.setFont(new Font(15));
		statsButton.setFont(new Font(15));
		helpButton.setFont(new Font(15));

		Text bruh = new Text("added");
        settingsButton.setOnAction(e -> {
			this.currentScreen.getChildren().add(bruh);
        });

        statsButton.setOnAction(e -> {
			this.currentScreen.getChildren().remove(bruh);
        });

        helpButton.setOnAction(e -> {

        });

		HBox menuBar = new HBox(helpButton, statsButton, settingsButton);
		HBox.setMargin(statsButton, new Insets(0, 20, 0, 20));
		return menuBar;
	}
	
	private Background background() {
		Background background = new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(0, 0, 0, 0)));
		return background;
	}

	private StackPane titleScreen() {
		BorderPane bp = new BorderPane();
		Text title = new Text("Baccarat");
		Button playButton = new Button("Play");
		
		VBox vb = new VBox(title, playButton);
		HBox menuBar = menuBar();
		StackPane root = new StackPane(bp, menuBar);

		bp.setCenter(vb);
		bp.setTop(menuBar);
		bp.setBackground(background());
		vb.setAlignment(Pos.TOP_CENTER);
		menuBar.setAlignment(Pos.TOP_RIGHT);
		title.setFont(new Font(80));
		playButton.setFont(new Font(50));

		BorderPane.setAlignment(menuBar, Pos.TOP_RIGHT);
		BorderPane.setAlignment(vb, Pos.CENTER);
		BorderPane.setMargin(vb, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(menuBar, new Insets(10, 10, 10, 10));
		VBox.setMargin(title, new Insets(150, 10, 150, 10));

		return root;
	}

	private StackPane gameScene() {
		BorderPane bp = new BorderPane();
		HBox menuBar = menuBar();
		Button beginButton = new Button("Begin Round");


		StackPane root = new StackPane(bp, menuBar);
		
		return root;
	}

	public void changeCurrentScene(String newScene) {
		this.currentSceneName = newScene;
	}

	private void setScene(Stage primaryStage, String sceneName) {;
		switch (sceneName) {
			case "title":
				this.currentScreen = titleScreen();
			case "game":
				this.currentScreen = gameScene();
			default:
				this.currentScreen = titleScreen();
		}

		this.currentScene = new Scene(this.currentScreen, 1280, 768);
		primaryStage.setScene(this.currentScene);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Baccarat");
		
		// Rectangle rect = new Rectangle (100, 40, 100, 100);
		// rect.setArcHeight(50);
		// rect.setArcWidth(50);
		// rect.setFill(Color.VIOLET);

		// RotateTransition rt = new RotateTransition(Duration.millis(5000), rect);
		// rt.setByAngle(270);
		// rt.setCycleCount(4);
		// rt.setAutoReverse(true);
		// SequentialTransition seqTransition = new SequentialTransition (
		// 	new PauseTransition(Duration.millis(500)),
		// 	rt
		// );
		// seqTransition.play();
		
		// FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
		// ft.setFromValue(1.0);
		// ft.setToValue(0.3);
		// ft.setCycleCount(4);
		// ft.setAutoReverse(true);

		// ft.play();
		// // root.setCenter(rect);
		// BorderPane root = new BorderPane();
		
		setScene(primaryStage, currentSceneName);
		primaryStage.show();
	}

}
