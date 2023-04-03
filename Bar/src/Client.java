import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Client {

  private List<OrderType> orders;
  private BigDecimal bill;
  private BigDecimal tip;

  public Client() {
    this.orders = new ArrayList<>();
    this.bill = new BigDecimal(0);
    this.tip = new BigDecimal(0);
  }

  public BigDecimal getTip() {
    return tip;
  }

  public void setTip() {
    this.tip = tip.add(bill.divide(BigDecimal.TEN, RoundingMode.HALF_UP));
  }


  public List<OrderType> getOrders() {
    return orders;
  }

  public void setOrders(List<OrderType> orders) {
    this.orders = orders;
  }

  public BigDecimal getBill() {
    return bill;
  }

  public void setBill(BigDecimal billOrderType) {

    this.bill = bill.add(billOrderType);
  }


  public void setOrder(OrderType orderType) {

    orders.add(orderType);
  }

  @Override
  public String toString() {
    return "Client{" +
        "orders=" + orders +
        ", bill=" + bill +"\n";

  }
}
