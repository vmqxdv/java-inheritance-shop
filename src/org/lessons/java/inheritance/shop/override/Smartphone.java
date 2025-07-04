package org.lessons.java.inheritance.shop.override;

import java.math.BigDecimal;
import java.util.Random;

public class Smartphone extends Product {

  private long imei;
  private float memoryQuantity;

  public Smartphone() {
    super();
    this.imei = 0;
    this.memoryQuantity = 0f;
  }

  public Smartphone(String name, String brand, BigDecimal price, BigDecimal iva, float memoryQuantity) {
    super(name, brand, price, iva);
    this.imei = generateImei();
    this.memoryQuantity = memoryQuantity;
  }

  // -- Imei
  public long getImei() {
    return this.imei;
  }

  public void setImei(long imei) {
    if (imei >= 100000000000000L && imei <= 999999999999999L)
      this.imei = imei;
  }

  // -- Memory Quantity
  public float getMemoryQuantity() {
    return this.memoryQuantity;
  }

  public void setMemoryQuantity(float memoryQuantity) {
    if (memoryQuantity > 0f)
      this.memoryQuantity = memoryQuantity;
  }

  // -- Private
  private long generateImei() {
    Random random = new Random();
    long min = 100000000000000L;
    long max = 999999999999999L;
    long randomLong = Math.abs(random.nextLong());
    long imei = min + (randomLong % (max - min + 1));
    return imei;
  }

  // -- Additional
  @Override
  public String toString() {
    return String.format("%s\nIMEI: %d\nMemoria: %.1f GB\n%s", super.toString(), imei, memoryQuantity, "-".repeat(15));
  }

  @Override
  public String toStringSimple() {
    return String.format("%s, IMEI: %d, Memoria: %.1f GB", super.toStringSimple(), imei, memoryQuantity);
  }

  public float getDiscount(boolean isFidelityCardValid) {
    if (isFidelityCardValid)
      return this.memoryQuantity < 32.0f ? 0.05f : 0.02f;

    return 0f;
  };
}
