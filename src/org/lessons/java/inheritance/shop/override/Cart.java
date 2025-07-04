package org.lessons.java.inheritance.shop.override;

import java.util.Scanner;
import java.math.BigDecimal;

public class Cart {
  public static void main(String[] args) {

    // -- Creating Products
    // - Smartphones
    Smartphone smartphone1 = new Smartphone("Pixel 4A", "Google", new BigDecimal("399.99"), new BigDecimal("0.22"),
        16f);
    Smartphone smartphone2 = new Smartphone("Iphone 16", "Apple", new BigDecimal("1599.99"), new BigDecimal("0.22"),
        512f);

    // - Televisions
    Television television1 = new Television("OldTV100", "Panasonic", new BigDecimal("499.99"), new BigDecimal("0.22"),
        32, false);
    Television television2 = new Television("X1234G4K", "Samsung", new BigDecimal("1998.65"), new BigDecimal("0.22"),
        100, true);

    // - Headphones
    Headphones headphones1 = new Headphones("Wired Boom", "Sony", new BigDecimal("59.99"), new BigDecimal("0.22"),
        "Nero", true);
    Headphones headphones2 = new Headphones("Air Pods Max", "Apple", new BigDecimal("589"), new BigDecimal("0.22"),
        "Verde Metallico", false);

    int userChoice;
    String productGroupChoiceQuestion = "Quale tipo di prodotto vuoi aggiungere al tuo carrello?\nDigita il numero corrispondente al prodotto:\nTelefono(0) / Televisore(1) / Cuffie(2)";
    Smartphone[] smartphones = { smartphone1, smartphone2 };
    Television[] televisions = { television1, television2 };
    Headphones[] headphones = { headphones1, headphones2 };
    Product[][] productGroups = { smartphones, televisions, headphones };

    Scanner sc = new Scanner(System.in);

    do {
      System.out.println(productGroupChoiceQuestion);
      userChoice = sc.nextInt();
      productGroupChoiceQuestion = String.format("\"%d\" non è un tipo di prodotto valido!\n%s", userChoice,
          productGroupChoiceQuestion);
    } while (userChoice < 0 || userChoice > 2);

    System.out.println(String.format(
        "\nSeleziona quale prodotto vuoi aggiungere al carrello,\nper farlo digita il numero corrispondente al prodotto.\n\nLista prodotti:\n%s",
        "-".repeat(15)));
    Product[] selectedProductGroup = productGroups[userChoice];

    for (int i = 0; i < selectedProductGroup.length; i++) {
      Product product = selectedProductGroup[i];
      System.out.println(String.format("(%d) %s", i, product.toString()));
    }

    userChoice = sc.nextInt();

    System.out.println(userChoice);

    sc.close();
  }
}
