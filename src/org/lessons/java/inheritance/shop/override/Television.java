package org.lessons.java.inheritance.shop.override;

import java.math.BigDecimal;

public class Television extends Product {

  private int inch;
  private boolean isSmart;

  public Television() {
    super();
    this.inch = 0;
    this.isSmart = false;
  }

  public Television(String name, String brand, BigDecimal price, BigDecimal iva, int inch, boolean isSmart) {
    super(name, brand, price, iva);
    this.inch = inch;
    this.isSmart = isSmart;
  }

  // -- Inch
  public int getInch() {
    return this.inch;
  }

  public void setInch(int inch) {
    if (inch > 0)
      this.inch = inch;
  }

  // -- Is Smart?
  public boolean getIsSmart() {
    return this.isSmart;
  }

  public void setIsSmart(boolean isSmart) {
    this.isSmart = isSmart;
  }

  // -- Additional
  @Override
  public String toString() {
    return String.format("%s\nPollici: %d\nFunzionalità smart: %b\n%s", super.toString(), this.inch, this.isSmart,
        "-".repeat(15));
  }

  @Override
  public String toStringSimple() {
    return String.format("%s, Pollici: %d, Funzionalità smart: %b", super.toStringSimple(), this.inch, this.isSmart);
  }

  public float getDiscount(boolean isFidelityCardValid) {
    if (isFidelityCardValid)
      return !this.isSmart ? 0.1f : 0.02f;

    return 0f;
  };
}
