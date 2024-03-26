package com.example.adamexample;

public class Coin {

    private final int HEADS = 0;
    private int twoFace;

    public int flip () {
        twoFace = (int) (Math.random() * 2);
        return twoFace;
    }
    // REMOVED THE COIN ARGUMENT IN THE ISHEADS METHOD AND REPLACED THE CHECK TO BE ON TWOFACE
    // OLD public boolean isHeads(int coin) { return (coin == HEADS); }
    public boolean isHeads() {
        return (twoFace == HEADS);
    }

    public String toString () {
        return (twoFace == HEADS) ? "Heads" : "Tails";
    }


}
