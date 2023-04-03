import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class Bartender extends Employee {


  public Bartender(String name, BigDecimal dailyWage) {
    super(name, dailyWage);
  }

  public boolean prepareDrink(List<Product> storage, Drink drink) {
    /*
    receive drink order
    check are the products available in the storage,
     if yes- return true and remove the part from the storage
     if not - return false and throw exception
     */
    for (Product product : storage) {

      if (product.getName().equals(drink.getName())) {

        if (product.getQuantity() - 1 >= 0) {

          product.setQuantity(product.getQuantity() - 1);
          System.out.println(drink.getName()+" READY");
          return true;
        }
      }
    }
    System.err.println("No more " + drink.getName());
    return false;
  }

}
