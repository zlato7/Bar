import java.math.BigDecimal;

public class OrderType {

 private String name;
 private int size;
 private BigDecimal price;

  public OrderType(String name, int size, BigDecimal price) {
    this.name = name;
    this.size = size;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return
        "name=" + name +
        ", size=" + size +
        ", price=" + price;
  }
}
