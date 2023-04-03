
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chef extends Employee {

  public Chef(String name, BigDecimal dailyWage) {
    super(name, dailyWage);
  }

  public boolean cooking(List<Product> storage, Dish dish) {
    /*
    receive dish order
    check are the products available in the storage,
     if can cook this - return true and remove the part from the storage
     if not - return false
     */

    //this map keeps all products that we'll remove in case we need them back
    HashMap<String, Integer> productsToReturnInStorage = new HashMap<>();

    for (Map.Entry<String , Integer> currentProductRecipe : dish.getRecipe()
        .entrySet()) {//goes through the recipe

      String productName = currentProductRecipe.getKey();
      Integer quantityNeeded = currentProductRecipe.getValue();

      //goes through the storage
      for (Product currentProductInStorage : storage) {

        String productNameInStorage = currentProductInStorage.getName();
        Integer quantityInStorage = currentProductInStorage.getQuantity();

        if (productNameInStorage.equals(
            productName)) {//check for the current product in the storage

          if (quantityInStorage - quantityNeeded >= 0) {

            currentProductInStorage.setQuantity(quantityInStorage - quantityNeeded);

            productsToReturnInStorage.put(currentProductRecipe.getKey(), quantityNeeded);
            break;

          } else {
            //return unused products in the storage
            for (Map.Entry<String, Integer> entryToReturn : productsToReturnInStorage.entrySet()) {

              for (Product currentProduct : storage) {

                if (currentProduct.getName().equals(entryToReturn.getKey())) {

                  currentProduct.setQuantity(
                      currentProduct.getQuantity() + entryToReturn.getValue());
                }
              }
            }
            System.err.println("No more " + dish.getName());
            return false;
          }
        }
      }
    }
    System.out.println(dish.getName()+" READY");
    return true;
  }
}
