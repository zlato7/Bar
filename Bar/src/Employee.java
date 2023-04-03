import java.math.BigDecimal;

public class Employee {

  private String name;
  private BigDecimal dailyWage;
  private boolean isOnShift;


  public Employee(String name,BigDecimal dailyWage) {
    this.name = name;
    this.dailyWage=dailyWage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getDailyWage() {
    return dailyWage;
  }

  public void setDailyWage(BigDecimal dailyWage) {
    this.dailyWage = dailyWage;
  }

  public boolean isOnShift() {
    return isOnShift;
  }

  public void setOnShift(boolean onShift) {
    isOnShift = onShift;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName()+
        " " + name +
        " dailyWage = " + dailyWage;
  }
}
