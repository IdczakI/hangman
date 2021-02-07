package pl.idczak.newHangman.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.idczak.newHangman.model.Game;

import java.io.IOException;

public class MainPaneController {

    Game game = new Game();
    private boolean gameStarted = false;

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

    public void initialize() {
        newGameButton.setOnAction(this::newGameOperation);
        enterLetterButton.setOnAction(this::enterLetterOperation);
        exitButton.setOnAction(event -> Platform.exit());
        resetButton.setOnAction(this::resetOperation);

        newGameMenuItem.setOnAction(this::newGameOperation);
        resetMenuItem.setOnAction(this::resetOperation);
        exitMenuItem.setOnAction(event -> Platform.exit());
        aboutItemOperation();

        mainTextArea.setText(game.getStartText());
        playerTextField.setOnAction(this::enterLetterOperation);
        allowableMistakesLabel.setText(String.valueOf(Game.MAX_MISTAKES));

    }

    private void resetOperation(ActionEvent event) {
        mainTextArea.setText(game.getStartText());
        game.resetPasswordQueue();
        passwordTextField.clear();
        gameStarted = false;
        game.setWinCounter(0);
        winsLabel.setText(String.valueOf(game.getWinCounter()));
        game.setLossCounter(0);
        lossLabel.setText(String.valueOf(game.getLossCounter()));
        game.setMistakeCounter(0);
        mistakesLabel.setText(String.valueOf(game.getMistakeCounter()));
    }

    private void enterLetterOperation(ActionEvent event) {
        if (gameStarted) {
            game.guessPassword(playerTextField.getText());
            mainTextArea.setText(game.getNewPasswordText());
            passwordTextField.setText(game.getGuessingPassword());
            playerTextField.clear();
            playerTextField.requestFocus();
            mistakesLabel.setText(String.valueOf(game.getMistakeCounter()));
            if (passwordTextField.getText().equals(game.getPassword())) {
                winsLabel.setText(String.valueOf(game.getWinCounter()));
                mainTextArea.setText(game.getWinText());
                game.setWinCounter(game.getWinCounter() + 1);
                winsLabel.setText(String.valueOf(game.getWinCounter()));
                gameStarted = false;
            } else if (game.getMistakeCounter() > Game.MAX_MISTAKES) {
                lossLabel.setText(String.valueOf(game.getLossCounter()));
                mainTextArea.setText(game.getLossText());
                game.setLossCounter(game.getLossCounter() + 1);
                lossLabel.setText(String.valueOf(game.getLossCounter()));
                gameStarted = false;
            }
        }
    }

    private void newGameOperation(ActionEvent event) {
        mainTextArea.setText(game.getNewGameText() + game.getNewPasswordText());
        game.createFirstPassword();
        passwordTextField.setText(game.getCodedPassword());
        gameStarted = true;
        playerTextField.requestFocus();
        game.setMistakeCounter(0);
        mistakesLabel.setText(String.valueOf(game.getMistakeCounter()));
    }
    private void aboutItemOperation(){
        aboutMenuItem.setOnAction(event -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/aboutPane.fxml"));
                Scene scene = new Scene(pane);
                Stage stage = new Stage();
                stage.setTitle("New Hangman - about");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                mainTextArea.setText("aboutPane error");
            }
        });
    }
}
