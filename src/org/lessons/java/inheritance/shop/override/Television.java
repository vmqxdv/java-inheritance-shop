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
    return String.format("%s\nPollici: %d\nFunzionalità smart: %b", super.toString(), this.inch, this.isSmart);
  }
}
