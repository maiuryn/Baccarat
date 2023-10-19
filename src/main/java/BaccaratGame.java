import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.ComboBox;


public class BaccaratGame extends Application {

	private String currentSceneName;
	private Stage primaryStage;
	private Scene currentScene;
	private StackPane currentScreen;
	private boolean busy = false;

	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private BaccaratGameLogic gameLogic;
	private String betSelection;
	private double currentMoney;
	private double currentBet;
	private double totalWinnings;
	private GameHistory history;

	public void initGame() {
		this.playerHand = new ArrayList<>();
		this.bankerHand = new ArrayList<>();
		this.theDealer = new BaccaratDealer();
		this.gameLogic = new BaccaratGameLogic();
		this.currentMoney = 10000;
		this.currentBet = 0;
		this.totalWinnings = 0;
		this.history = new GameHistory();
	}

	public BaccaratGame() {
		initGame();
	}

	public double evaluateWinnings() {
		String result = gameLogic.whoWon(playerHand, bankerHand);
		double winnings = 0;
		if (result == betSelection) {
			if (result == "Player")
				winnings += currentBet * 2;
			else if (result == "Banker") 
				winnings += (currentBet * 2) * .95;
			else 
				winnings += currentBet * 8;
		}

		return winnings;
	}

	private Button titleButton() {
		Button titleButton = new Button("Back");
		titleButton.setFont(new Font(15));

		titleButton.setOnAction(e -> {
			changeCurrentScene("title");
		});

		return titleButton;
	}

	private Button closeButton() {
		Button closeButton = new Button("Close");

		closeButton.setOnAction(e -> {	
			int lastIndex = this.currentScreen.getChildren().size() - 1;
			this.currentScreen.getChildren().remove(lastIndex);
			busy = false;
		});
			
		return closeButton;
	}

	private BorderPane betPhase() {
		BorderPane root = new BorderPane();
		Text title = new Text("Place your bets!");
		TextField betAmount = new TextField("$0");
		Button confirmButton = new Button("Confirm");
		Slider betSlider = new Slider(0, currentMoney, 0.5 * currentMoney);
		ObservableList<String> options = 
			FXCollections.observableArrayList(
				"Player",
				"Draw",
				"Banker"
			);
			
		ComboBox<String> cb = new ComboBox<>(options);

		VBox vb1 = new VBox(betSlider, betAmount, cb);
		VBox vb2 = new VBox(title, vb1, confirmButton);

		currentBet = betSlider.getValue();
		betAmount.setText(String.format("%.2f", currentBet));	

		betSlider.setOnMouseReleased(e -> {
			currentBet = betSlider.getValue();
			betAmount.setText(String.format("%.2f", currentBet));
		});

		betAmount.setOnKeyTyped(e -> {
			currentBet = Double.parseDouble(betAmount.getText());
			betAmount.setText(String.format("%.2f", currentBet));
		});
		
		root.setCenter(vb2);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font(40));
		vb1.setMaxWidth(100);
		betSlider.setMinWidth(500);
		vb1.setAlignment(Pos.CENTER);
		vb2.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(betSlider, new Insets(10, 10, 10, 10));
		VBox.setMargin(title, new Insets(10, 10, 10, 10));
		VBox.setMargin(confirmButton, new Insets(20, 50, 50, 50));

		Background background = new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(10), new Insets(0, 0, 0, 0)));
		root.setBackground(background);

		confirmButton.setOnAction(e -> {
			if (betAmount.getText() != null && cb.getValue() != null) {
				currentBet = Double.parseDouble(betAmount.getText());
				int lastIndex = this.currentScreen.getChildren().size() - 1;
				this.currentScreen.getChildren().remove(lastIndex);
				busy = false;
			}
		});

		return root;
	}

	private BorderPane settings() {
		BorderPane root = new BorderPane();
		Button exitButton = new Button("Exit");
		Button closeButton = closeButton();
		Button freshButton = new Button("Fresh Start");
		Text title = new Text("Settings");

		HBox hb1 = new HBox(exitButton, freshButton);
		VBox vb1 = new VBox(title, hb1);
		
		root.setCenter(vb1);
		root.setTop(closeButton);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font(40));
		hb1.setAlignment(Pos.CENTER);
		vb1.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(title, new Insets(10, 10, 50, 10));
		BorderPane.setAlignment(closeButton, Pos.TOP_RIGHT);
		BorderPane.setMargin(closeButton, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(exitButton, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(freshButton, new Insets(10, 10, 10, 10));

		Background background = new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(10), new Insets(0, 0, 0, 0)));
		root.setBackground(background);

		exitButton.setOnAction(e -> {
			Stage stage = (Stage) exitButton.getScene().getWindow();
    		stage.close();
		});

		freshButton.setOnAction(e -> {
			initGame();
			busy = false;
			changeCurrentScene("title");
		});

		exitButton.setFont(new Font(30));
		freshButton.setFont(new Font(30));
		HBox.setMargin(exitButton, new Insets(0, 20, 0, 20));

		return root;
	}

	private BorderPane stats() {
		BorderPane root = new BorderPane();
		Button closeButton = closeButton();
		Text title = new Text("Stats");
		ListView<String> log = new ListView<>();

		VBox vb1 = new VBox(title, log);	
		
		root.setCenter(vb1);
		root.setTop(closeButton);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font(40));
		vb1.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(title, new Insets(10, 10, 10, 10));
		VBox.setMargin(log, new Insets(20, 50, 50, 50));
		BorderPane.setAlignment(closeButton, Pos.TOP_RIGHT);
		BorderPane.setMargin(closeButton, new Insets(10, 10, 10, 10));

		Background background = new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(10), new Insets(0, 0, 0, 0)));
		root.setBackground(background);

		// fill stats

		return root;
	}

	private BorderPane help() {
		BorderPane root = new BorderPane();
		Button closeButton = closeButton();
		Text title = new Text("Help");

		Text helpText = new Text(
			"Baccarat is a fast and simple game with very high stakes.\n" 
			+ "The goal of the game is to make a bet on the winning hand, or a draw.\n"
			+ "A hand is winning if it has a total score of 8 or 9. This is called a natural win.\n" 
			+ "The score of a card is as follows:\n"
			+ "\tThe suite of the card does not matter.\n\tAce is 1.\n\t2-9 have the same score as the number.\n\t10, J, Q, and K, have score 0.\n\n"
			+ "Before the game starts, there is a betting phase. Place a bet on what you expect the game to result in.\n"
			+ "The game starts by dealing two cards to the player. At this point, if the player has a natural win, the player will win.\n"
			+ "Then, two cards are dealt to the banker. If the banker has a natural win, the banker will win.\n"
			+ "Next, the player will go first. If the player's hand totals to 5 or less, The Player gets one more card. If the hand totals to 6 or 7 points, no more cards are given.\n"
			+ "Points will be totaled up to 9. If points go over 9, only the remaining points past 9 will be counted as points.\n"
			+ "For example, if the player has 5 points, and they draw a 9, the resulting score will be 4 + 9 = 13, and we will drop the tens place, resulting in a score of 3.\n"
			+ "Finally, the banker will see if they will draw a card. If the banker's first two cards total 7 or more, no more cards\n" 
			+ "are dealt. If The banker’s cards total 2 or less, the banker gets one additional card. If \n"
			+ "the banker's first two cards total 3, 4, 5, or 6, it depends on if the player drew another \n"
			+ "card and if so, the value of that card to determine if the banker receives another card."
		);

		VBox vb1 = new VBox(title, helpText);	
		
		root.setCenter(vb1);
		root.setTop(closeButton);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font(40));
		vb1.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(title, new Insets(10, 10, 10, 10));
		VBox.setMargin(helpText, new Insets(20, 50, 50, 50));
		BorderPane.setAlignment(closeButton, Pos.TOP_RIGHT);
		BorderPane.setMargin(closeButton, new Insets(10, 10, 10, 10));

		Background background = new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(10), new Insets(0, 0, 0, 0)));
		root.setBackground(background);

		return root;
	}

	private HBox menuBar() {
		Button settingsButton = new Button("Settings");
		Button statsButton = new Button("Stats");
		Button helpButton = new Button("Help");

		settingsButton.setFont(new Font(15));
		statsButton.setFont(new Font(15));
		helpButton.setFont(new Font(15));

		
        settingsButton.setOnAction(e -> {
			if (!busy) {
				BorderPane settings = settings();
				this.currentScreen.getChildren().add(settings);
				StackPane.setMargin(settings, new Insets(100, 100, 100, 100));
				busy = true;
			}
        });

        statsButton.setOnAction(e -> {
			if (!busy) {
				BorderPane stats = stats();
				this.currentScreen.getChildren().add(stats);
				StackPane.setMargin(stats, new Insets(100, 100, 100, 100));
				busy = true;
			}
        });

        helpButton.setOnAction(e -> {
			if (!busy) {
				BorderPane help = help();
				this.currentScreen.getChildren().add(help);
				StackPane.setMargin(help, new Insets(100, 100, 100, 100));
				busy = true;
			}
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
		StackPane root = new StackPane(bp);

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

		playButton.setOnAction(e -> {
			changeCurrentScene("game");
		});

		return root;
	}

	private StackPane gameScene() {
		BorderPane bp = new BorderPane();
		HBox menuBar = menuBar();
		Button beginButton = new Button("Begin Round");
		Button titleButton = titleButton();
		BorderPane top = new BorderPane();

		StackPane root = new StackPane(bp);
		
		beginButton.setFont(new Font(40));
		top.setLeft(titleButton);
		top.setRight(menuBar);
		
		bp.setCenter(beginButton);
		bp.setTop(top);
		bp.setBackground(background());

		titleButton.setAlignment(Pos.TOP_LEFT);
		BorderPane.setAlignment(titleButton, Pos.TOP_LEFT);
		BorderPane.setMargin(titleButton, new Insets(10, 10, 10, 10));

		menuBar.setAlignment(Pos.TOP_RIGHT);
		BorderPane.setAlignment(menuBar, Pos.TOP_RIGHT);
		BorderPane.setMargin(menuBar, new Insets(10, 10, 10, 10));

		beginButton.setOnAction(e -> {
			if (!busy) {
				beginButton.setVisible(false);
				BorderPane betPane = betPhase();
				this.currentScreen.getChildren().add(betPane);
				StackPane.setMargin(betPane, new Insets(100, 100, 100, 100));
				busy = true;
			}
			
		});
		
		return root;
	}

	public void changeCurrentScene(String newScene) {
		this.currentSceneName = newScene;

		if (currentSceneName == "title")
			this.currentScreen = titleScreen();
		else if (currentSceneName == "game")
			this.currentScreen = gameScene();
		else 
			this.currentScreen = titleScreen();
		
		if (currentScene == null)
			this.currentScene = new Scene(currentScreen, 1280, 768);

		// System.out.println(currentScreen);
		// System.out.println(currentSceneName);
		currentScene.setRoot(currentScreen);
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

		initGame();

		this.primaryStage = primaryStage;
		changeCurrentScene("title");
		this.primaryStage.setScene(this.currentScene);
		this.primaryStage.show();
	}

}
