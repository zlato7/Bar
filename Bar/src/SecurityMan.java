import java.math.BigDecimal;

public class SecurityMan extends Employee {

  private BigDecimal wallet;

  public SecurityMan(String name, BigDecimal dailyWage) {
    super(name, dailyWage);
    this.wallet = new BigDecimal(0);
  }


  public BigDecimal getWallet() {
    return wallet;
  }

  public void setWallet(BigDecimal wallet) {
    this.wallet = wallet;
  }


}
