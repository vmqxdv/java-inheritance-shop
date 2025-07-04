package org.lessons.java.inheritance.shop.override;

import java.math.BigDecimal;

public class Headphones extends Product {

  private String color;
  private boolean isWireless;

  public Headphones() {
    super();
    this.color = "";
    this.isWireless = false;
  }

  public Headphones(String name, String brand, BigDecimal price, BigDecimal iva, String color, boolean isWireless) {
    super(name, brand, price, iva);
    this.color = color;
    this.isWireless = isWireless;
  }

  // -- Color
  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    if (color.length() > 0)
      this.color = color;
  }

  // -- Is Wireless?
  public boolean getIsWireless() {
    return this.isWireless;
  }

  public void setIsWireless(boolean isWireless) {
    this.isWireless = isWireless;
  }

  // -- Additional
  @Override
  public String toString() {
    return String.format("%s\nColore: %s\nFunzionalità bluetooth: %b\n%s", super.toString(), this.color,
        this.isWireless, "-".repeat(15));
  }

  @Override
  public String toStringSimple() {
    return String.format("%s, Colore: %s\nFunzionalità bluetooth: %b", super.toStringSimple(), this.color,
        this.isWireless);
  }

  public float getDiscount(boolean isFidelityCardValid) {
    if (isFidelityCardValid)
      return !this.isWireless ? 0.07f : 0.02f;

    return 0f;
  };
}
