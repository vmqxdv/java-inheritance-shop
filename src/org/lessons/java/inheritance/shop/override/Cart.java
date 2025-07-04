package org.lessons.java.inheritance.shop.override;

import java.util.Arrays;
import java.util.Locale;
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

    StringBuilder result = new StringBuilder("\nCarrello:");
    for (int i = 0; i < cart.length; i++) {
      result.append(String.format("\n(%d) %s", i, cart[i].toStringSimple()));
    }

    return result.toString();
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
    String[] validCardIds = { "FID123ABC", "VIP456XYZ", "LOY789QWE" };

    Scanner sc = new Scanner(System.in);

    boolean isShoppingComplete = false;

    // -- Products selection
    do {
      userChoice = getValidatedIntInput(sc,
          "Quale tipo di prodotto vuoi aggiungere al tuo carrello?\nDigita il numero corrispondente al prodotto:\nTelefono(0) / Televisore(1) / Cuffie(2)",
          "non è un tipo di prodotto valido!", 0, 2);

      Product[] selectedProductGroup = productGroups[userChoice];

      StringBuilder nextChoiceText = new StringBuilder(
          "\nSeleziona quale prodotto vuoi aggiungere al carrello,\nper farlo digita il numero corrispondente al prodotto.\n\nLista prodotti:");

      for (int i = 0; i < selectedProductGroup.length; i++) {
        Product product = selectedProductGroup[i];
        nextChoiceText.append(String.format("\n(%d) %s", i, product.toStringSimple()));
      }

      userChoice = getValidatedIntInput(sc, nextChoiceText.toString(), "non è un prodotto valido!", 0,
          selectedProductGroup.length - 1);

      cart.addItem(selectedProductGroup[userChoice]);

      userChoice = getValidatedIntInput(sc,
          "\nVuoi continuare ad aggiungere articoli o andare al pagamento?\n\nContinua lo shopping(0) / Vai al pagamento(1)",
          "non è una risposta valida!", 0, 1);

      isShoppingComplete = userChoice == 1;

    } while (!isShoppingComplete);

    // -- Fidelity Card Controll
    int userFidelityCardAnswer = getValidatedIntInput(sc, "\nSei in possesso di una fidelity card? Si(0) / No(1)",
        "non è una risposta valida!", 0, 1);

    sc.nextLine();

    boolean isFidelityCardValid = false;

    if (userFidelityCardAnswer == 0) {
      String userFidelityCardCode;
      String errorText = "";

      do {
        System.out.println(errorText + "\nScrivi il codice della tua fidelity card:");
        userFidelityCardCode = sc.nextLine();

        isFidelityCardValid = Arrays.stream(validCardIds)
            .anyMatch(userFidelityCardCode.toUpperCase()::equals);

        if (!isFidelityCardValid && !userFidelityCardCode.equals("0")) {
          errorText = String.format(
              "\n\u001B[31m\"%s\" non è una fidelity card valida!\u001B[0m\nSe non ne possiedi una digita \"0\" e continua senza.\n\n",
              userFidelityCardCode);
        } else
          errorText = "";

      } while (!isFidelityCardValid && !userFidelityCardCode.equals("0"));
    }

    // -- Order preview
    System.out.println("+----------------+---------------+");
    System.out.println("| Nome Prodotto  | Prezzo (€)    |");
    System.out.println("+----------------+---------------+");

    BigDecimal total = new BigDecimal(0);

    for (Product product : cart.getItems()) {
      BigDecimal discountedPrice = product.getPriceAfterDiscount(product.getDiscount(isFidelityCardValid));
      System.out.println(String.format(Locale.ITALY, "| %-14s | %13.2f |",
          product.getName(), discountedPrice));

      if (product.getPriceAfterTax().compareTo(discountedPrice) > 0) {
        BigDecimal discountValue = discountedPrice.subtract(product.getPriceAfterTax());
        System.out.println(String.format(Locale.ITALY, "| %-14s | %13.2f |",
            "Sconto", discountValue));
      }

      total = total.add(discountedPrice);
    }

    System.out.println("+----------------+---------------+");
    System.out.println(String.format(Locale.ITALY, "| %-14s | %13.2f |", "Totale", total));
    System.out.println("+----------------+---------------+");

    sc.close();
  }

  private static int getValidatedIntInput(Scanner sc, String prompt, String errorMsg, int min, int max) {
    int input;

    while (true) {
      System.out.println(prompt);

      if (sc.hasNextInt()) {
        input = sc.nextInt();

        if (input >= min && input <= max)
          break;
      } else
        sc.next();

      System.out.printf("\n\u001B[31m\"%s\" %s\u001B[0m\n", sc.hasNext() ? sc.next() : "", errorMsg);
    }

    return input;
  }
}
