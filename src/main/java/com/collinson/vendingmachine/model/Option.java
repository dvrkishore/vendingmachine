package com.collinson.vendingmachine.model;

public enum Option {
    
    PURCHASE(1, "Purchase"),
    RESET(2, "Reset");

    private int option;
    private String name;

    Option(int option, String name){
        this.name = name;
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public int getOption() {
        return option;
    }

    
}
