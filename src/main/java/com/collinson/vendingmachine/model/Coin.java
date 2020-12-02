package com.collinson.vendingmachine.model;

import java.math.BigDecimal;

public enum Coin {

    ONE(1, "penny"),
    FIVE(5, "nickel"),
    TEN(10, "dime"),
    TWENTY_FIVE(25, "quarter");
    
    private int value;
    private String name;
    private BigDecimal coinWorth;

    private Coin(int value, String name) {
        this.value = value;
        this.name = name;
        coinWorth = BigDecimal.valueOf(value).divide(BigDecimal.valueOf(100));
    }

    public int getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public BigDecimal getCoinWorth() {
        return coinWorth.setScale(2);
    }

    public static Coin getByValue(int value){
        Coin c = null;
        Coin[] values = values();
        for (Coin coin : values) {
            if ( coin.value == value){
                c = coin;
                break;
            }
        }
        return c;
    }

    public static String display(){
        StringBuilder sb = new StringBuilder();
        Coin[] values = values();
        int i = 1;
        for (Coin coin : values) {
            if ( sb.length() >0){
                sb.append(", ");
            }
            sb.append(i).append(" (").append(coin.getCoinWorth()).append("-").append(coin.name).append(")");
            i++;
        }
        return sb.toString();
    }

}
