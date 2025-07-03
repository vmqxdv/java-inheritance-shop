package org.lessons.java.inheritance.shop;

import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {

    Smartphone smartphone = new Smartphone("Iphone 16", "Apple", new BigDecimal(1599.99), new BigDecimal(0.22), 512f);

    System.out.println(smartphone.toString());

    Television television = new Television("X1234G4K", "Samsung", new BigDecimal(1998.65), new BigDecimal(0.22), 100,
        true);

    System.out.println(television.toString());
  }
}
