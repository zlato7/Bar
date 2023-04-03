import java.math.BigDecimal;

public class Waiter extends Employee {

  private BigDecimal tip;



  public Waiter(String name, BigDecimal dailyWage) {
    super(name, dailyWage);
    this.tip=new BigDecimal(0);
  }


  public BigDecimal getTip() {
    return tip;
  }

  public void setTip(BigDecimal tip1) {
    this.tip = tip.add(tip1);
  }

  @Override
  public String toString() {
    return super.toString()+
        " tip = " + tip;
  }
}
