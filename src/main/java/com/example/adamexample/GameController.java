package com.example.adamexample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.IOException;

public class GameController {
    @FXML
    private Button spinner;
    @FXML
    private Label rbSelection;
    @FXML
    private RadioButton hh;
    @FXML
    private RadioButton tt;
    @FXML
    private RadioButton ht;
    @FXML
    private ToggleGroup choices;

    Coin coin1 = new Coin();
    Coin coin2 = new Coin();

    boolean gameResults () throws IOException {
        int coinOne = coin1.flip();
        int coinTwo = coin2.flip();
        coinFlip(coin1, coin2);
        boolean winLoss = false;
        if ((coinOne == 0 && coinTwo == 0) && hh.isSelected()) {
            hh.setSelected(false);
            winLoss = true;
        } else if ((coinOne == 1 && coinTwo == 1) && tt.isSelected()) {
            tt.setSelected(false);
            winLoss = true;
        } else if (((coinOne == 0 && coinTwo == 1) || (coinOne == 1 && coinTwo == 0)) && ht.isSelected()) {
            ht.setSelected(false);
            winLoss = true;
        } else {
            if (hh.isSelected()) {
                hh.setSelected(false);
            } else if (tt.isSelected()) {
                tt.setSelected(false);
            } else {
                ht.setSelected(false);
            }
        }
        return winLoss;
    }

    @FXML
    public void coinFlip (Coin coinOne, Coin coinTwo) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("coin-flip.fxml"));
        Parent root = loader.load();
        CoinFlipController cfc = loader.getController();

        // set the coins in CoinFlipController.java to the states of coins in GameController.java
        cfc.setCoin1(coin1);
        cfc.setCoin2(coin2);

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Runs method from CoinflipController.java after the coin values have been set
        Platform.runLater(() -> {
            // Call a method in the controller after the scene is shown
            cfc.onSceneShown();
        });

    }

    @FXML
    public ActionEvent activateButton(ActionEvent event) {
        RadioButton rbChoice = (RadioButton) choices.getSelectedToggle();
        if (rbChoice != null) {
            rbSelection.setText(rbChoice.getText());
            spinner.setDisable(false);
        }
        return event;
    }

    // calls the gameResults method to play a game of two-up
    public void handleSpinnerButton(ActionEvent actionEvent) {
        try {
            boolean result = gameResults();

            // Debug output
            System.out.println("Game Result: " + result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
