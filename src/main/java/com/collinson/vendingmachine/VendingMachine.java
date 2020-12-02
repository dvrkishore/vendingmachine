package com.collinson.vendingmachine;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import com.collinson.vendingmachine.model.Coin;
import com.collinson.vendingmachine.model.Option;
import com.collinson.vendingmachine.model.Product;

public class VendingMachine {

    public static void main(String[] args) {
        System.out.println("Welcome to Vending Machine");
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.initialize();
        vendingMachine.welcome();
    }

    public void welcome() {
        final Scanner scanner = new Scanner(System.in);
        
        this.displayUserOptions();
        
        System.out.println("\nChoose your option: ");
        int option = scanner.nextInt();
        if (option == 1) {
            purchase();
        } else {
            reset();
        }
    }

    private void displayUserOptions() {
        System.out.println("|=====================================|");
        System.out.println("|    Collinson Vending Machine        |");
        System.out.println("|=====================================|");
        Option[] values = Option.values();
        for (Option option : values) {
            System.out.println(option.getOption() + " - " + option.getName());
        }
        System.out.println("|=====================================|");
    }

    public void initialize() {
        System.out.println("Vending machine initializing....\n\n");
        ProductsDB.addProduct(new Product("Coke", "A1", BigDecimal.valueOf(0.25)));
        ProductsDB.addProduct(new Product("Pepsi", "A2", BigDecimal.valueOf(0.35)));
        ProductsDB.addProduct(new Product("Soda", "A3", BigDecimal.valueOf(0.45)));
    }

    public void reset() {
        System.out.println("Vending machine resetting....");
        ProductsDB.clear();
    }

    public void purchase() {

        this.displayProducts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Product code: ");
        String code = scanner.nextLine();
        while (code == null || code.isEmpty()) {
            System.out.println("Enter Product code: ");
        }

        Optional<Product> productOptional = ProductsDB.getProduct(code);
        if (productOptional.isPresent()) {

            Product product = productOptional.get();
            System.out.println("\nYou've selected: " + product.getName());

            BigDecimal paid = BigDecimal.ZERO;
            BigDecimal toPay = product.getPrice();
            while (paid.compareTo(toPay) < 0) {
                paid = collectPayment(scanner, paid, toPay);
            }
            BigDecimal balance = paid.subtract(toPay);
            if (balance.compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("Take your  : \t " + product.getName());
            } else {
                System.out.println("Take your '" + product.getName() + "' and your balance: '" + balance + "'");
            }

        } else {
            System.out.println("Enter valid code: ");
        }
        scanner.close();
    }

    private BigDecimal collectPayment(Scanner scanner, BigDecimal paid, BigDecimal toPay) {
        System.out.println("Pay: " + toPay.subtract(paid));
        System.out.println("|=====================================|");
        System.out.println("|    How you want to Pay              |");
        System.out.println("|=====================================|");
        Coin[] coins = Coin.values();
        int i =1;
        for (Coin coin : coins) {
            System.out.println("| "+ i+ " = (" + coin.getCoinWorth()+ " - "+ coin.getName()+")");
            i++;
        }
        System.out.println("| To Cancel: Press 0");
        System.out.println("|=====================================|");
        int coinOption = scanner.nextInt();
        if (coinOption == 0) {
            exit(paid);
        }
        switch (coinOption) {
            case 0:
                exit(paid);
            case 1:
                paid = getNumberOfCoins(scanner, paid, Coin.ONE);
                break;
            case 2:
                paid = getNumberOfCoins(scanner, paid, Coin.FIVE);
                break;
            case 3:
                paid = getNumberOfCoins(scanner, paid, Coin.TEN);
                break;
            case 4:
                paid = getNumberOfCoins(scanner, paid, Coin.TWENTY_FIVE);
                break;
            default:
                System.out.println("Invalid option. ");
                break;
        }
        System.out.println("\nYou've paid: \t " + paid);
        return paid;
    }

    private void displayProducts() {
        Set<Product> all = ProductsDB.getAll();
        System.out.println("\n|=======================|");
        System.out.println("|    Products           |");
        System.out.println("|=======================|");
        for (Product product : all) {
            System.out.println("|" + product.toString() + "|");
        }
        System.out.println("|=======================|\n");
    }

    private BigDecimal getNumberOfCoins(Scanner scanner, BigDecimal paid, Coin coin) {
        System.out.println("How many of " + coin.getName() + " ?");
        int count = scanner.nextInt();
        BigDecimal paidNow = coin.getCoinWorth().multiply(BigDecimal.valueOf(count));
        paid = paid.add(paidNow);
        return paid;
    }

    private void exit(BigDecimal paid) {
        if (paid.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Good bye. And take your coins " + paid);
        } else {
            System.out.println("Good bye.");
        }
        System.exit(0);
    }

    public void vend(Product product) {

    }

    public void select(String code) {

    }

}
