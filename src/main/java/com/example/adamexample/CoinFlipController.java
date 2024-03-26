package com.example.adamexample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.Objects;

public class CoinFlipController {
    @FXML
    public ImageView coinLeft;
    @FXML
    public Label labelLeft;
    @FXML
    public ImageView coinRight;
    @FXML
    public Label labelRight;

    @FXML
    Coin c1;
    @FXML
    Coin c2;

    public void setCoin1(Coin coin) {
        c1 = coin;

        // Debug output
        System.out.println("is Coin 1 Heads: " +c1.isHeads());
    }
    public void setCoin2(Coin coin) {
        c2 = coin;

        // Debug output
        System.out.println("is Coin 2 Heads: " + c2.isHeads());
    }

    public void onSceneShown() {
        // set ImageView Images to the state of the Coins from GameController.java
        updateCoinImage(coinLeft,c1);
        updateCoinImage(coinRight,c2);

        // play animation on ImagesViews
        coinRotation(coinLeft);
        coinRotation(coinRight);

        // set Label text to coin face values
        labelLeft.setText(c1.toString());
        labelRight.setText(c2.toString());
    }

    private void updateCoinImage(ImageView coinImageView, Coin coin) {
        // Get the image path based on the result of the flipped coin
        String imagePath = coin.isHeads() ? "img/AusPennyHeads.png" : "img/AusPennyTails.png";

        // Load and set the image to the ImageView
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        coinImageView.setImage(image);
    }

    public void coinRotation (ImageView image) {

        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(image);
        translate1.setDuration(Duration.millis(1000));
        translate1.setCycleCount(2);
        translate1.setByY(-250);
        translate1.setAutoReverse(true);
        translate1.play();

        RotateTransition rotate1 = new RotateTransition();
        rotate1.setNode(image);
        rotate1.setDuration(Duration.millis(500));
        rotate1.setCycleCount(4);
        rotate1.setInterpolator(Interpolator.LINEAR);
        rotate1.setByAngle(360);
        rotate1.setAxis(Rotate.X_AXIS);
        rotate1.play();

    }
}
