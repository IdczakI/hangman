package pl.idczak.newHangman.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.idczak.newHangman.model.Game;

public class MainPaneController {

    Game game = new Game();
    private boolean gameStarted = false;
    private int winsCounter = 0;

    @FXML
    private MenuItem newGameMenuItem;

    @FXML
    private MenuItem resetMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TextArea mainTextArea;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField playerTextField;

    @FXML
    private Label mistakesLabel;

    @FXML
    private Label allowableMistakesLabel;

    @FXML
    private Label winsLabel;

    @FXML
    private Label lossLabel;

    @FXML
    private Button newGameButton;

    @FXML
    private Button enterLetterButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button exitButton;

    public TextArea getMainTextArea() {
        return mainTextArea;
    }

    public void initialize() {
        newGameButton.setOnAction(this::newGameButtonOperation);
        enterLetterButton.setOnAction(this::enterLetterButtonOperation);
        exitButton.setOnAction(event -> Platform.exit());
        resetButton.setOnAction(this::resetButtonOperation);
        mainTextArea.setOnMouseMoved(this::mainTextFillAtStart);
        mainTextArea.setOnMouseMoved(event -> {
            if (passwordTextField.getText().equals(game.getPassword())) {
                mainTextArea.setText("win text");
                winsCounter += 1;
                winsLabel.setText(String.valueOf(winsCounter));
            }
        });

    }

    private void mainTextFillAtStart(MouseEvent event) {
        if (!gameStarted)
            mainTextArea.setText(game.getResetText());
    }

    private void resetButtonOperation(ActionEvent event) {
        mainTextArea.setText(game.getResetText());
        game.resetPasswordQueue();
    }

    private void enterLetterButtonOperation(ActionEvent event) {
        if (gameStarted) {
            game.guessPassword(playerTextField.getText());
            mainTextArea.setText(game.getNewPasswordText());
            passwordTextField.setText(game.getGuessingPassword());
            playerTextField.clear();
            mistakesLabel.setText(String.valueOf(game.getErrorCounter()));
        }
    }

    private void newGameButtonOperation(ActionEvent event) {
        mainTextArea.setText(game.getNewGameText() + game.getNewPasswordText());
        game.createFirstPassword();
        passwordTextField.setText(game.getCodedPassword());
        gameStarted = true;
        allowableMistakesLabel.setText(String.valueOf(Game.MAX_MISTAKES));
    }

}
