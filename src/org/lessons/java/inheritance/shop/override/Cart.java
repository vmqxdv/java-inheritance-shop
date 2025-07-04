package org.lessons.java.inheritance.shop.override;

import java.util.Scanner;

public class Cart {
  public static void main(String[] args) {

    int userItemChoice;
    String productChoiceQuestion = "Quale prodotto vuoi aggiungere al tuo carrello?\nDigita il numero corrispondente al prodotto:\nTelefono(0) / Televisore(1) / Cuffie(2)";
    Product[] products = { new Smartphone(), new Television(), new Headphones() };

    Scanner sc = new Scanner(System.in);

    do {
      System.out.println(productChoiceQuestion);
      userItemChoice = sc.nextInt();
      productChoiceQuestion = String.format("\"%d\" non è un prodotto valido\n%s", userItemChoice,
          productChoiceQuestion);
    } while (userItemChoice < 0 || userItemChoice > 2);

    System.out.println(userItemChoice);
  }
}
