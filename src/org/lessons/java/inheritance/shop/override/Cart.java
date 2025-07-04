package org.lessons.java.inheritance.shop.override;

import java.util.Scanner;
import java.math.BigDecimal;

public class Cart {

  // -- Class Cart
  private Product[] cart;

  public Cart() {
    this.cart = new Product[0];
  }

  // -- Items
  public Product[] getItems() {
    return cart;
  }

  // -- Additional
  @Override
  public String toString() {
    if (cart.length == 0)
      return "Il carrello è vuoto.";

    String result = "\nCarrello:";
    for (int i = 0; i < cart.length; i++) {
      result = result + String.format("\n(%d) %s", i, cart[i].toStringSimple());
    }

    return result;
  }

  public void addItem(Product product) {
    Product[] newCart = new Product[this.cart.length + 1];

    for (int i = 0; i < this.cart.length; i++) {
      newCart[i] = this.cart[i];
    }

    newCart[newCart.length - 1] = product;

    this.cart = newCart;
  }

  // -- Main
  public static void main(String[] args) {

    Cart cart = new Cart();

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
    Smartphone[] smartphones = { smartphone1, smartphone2 };
    Television[] televisions = { television1, television2 };
    Headphones[] headphones = { headphones1, headphones2 };
    Product[][] productGroups = { smartphones, televisions, headphones };

    Scanner sc = new Scanner(System.in);

    boolean isShoppingComplete = false;

    do {
      String nextChoiceText = "Quale tipo di prodotto vuoi aggiungere al tuo carrello?\nDigita il numero corrispondente al prodotto:\nTelefono(0) / Televisore(1) / Cuffie(2)";
      do {
        System.out.println(nextChoiceText);
        userChoice = sc.nextInt();
        nextChoiceText = String.format("\u001B[31m\"%d\" non è un tipo di prodotto valido!\u001B[0m\n%s",
            userChoice,
            nextChoiceText);
      } while (userChoice < 0 || userChoice > 2);

      Product[] selectedProductGroup = productGroups[userChoice];

      nextChoiceText = "\nSeleziona quale prodotto vuoi aggiungere al carrello,\nper farlo digita il numero corrispondente al prodotto.\n\nLista prodotti:";

      for (int i = 0; i < selectedProductGroup.length; i++) {
        Product product = selectedProductGroup[i];
        nextChoiceText = (String.format("%s\n(%d) %s", nextChoiceText, i, product.toStringSimple()));
      }

      System.out.println(nextChoiceText);

      userChoice = sc.nextInt();

      while (userChoice < 0 || userChoice > (selectedProductGroup.length - 1)) {
        nextChoiceText = String.format("\n\u001B[31m\"%d\" non è un prodotto valido!\u001B[0m\n%s", userChoice,
            nextChoiceText);
        System.out.println(nextChoiceText);

        userChoice = sc.nextInt();
      }

      cart.addItem(selectedProductGroup[userChoice]);

      nextChoiceText = "\nVuoi continuare ad aggiungere articoli o andare al pagamento?\n\nContinua lo shopping(0) / Vai al pagamento(1)";
      System.out.println(nextChoiceText);

      userChoice = sc.nextInt();

      while (userChoice < 0 || userChoice > 1) {
        nextChoiceText = String.format("\n\u001B[31m\"%d\" non è una risposta valida!\u001B[0m\n%s", userChoice,
            nextChoiceText);
        System.out.println(nextChoiceText);

        userChoice = sc.nextInt();
      }

      isShoppingComplete = userChoice != 0 ? true : false;

    } while (!isShoppingComplete);

    sc.close();
  }
}
