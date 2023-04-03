import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Dish extends OrderType{

  private Map<String, Integer> recipe;

  public Dish(String name, int size, BigDecimal price) {
    super(name, size, price);
    recipe=new HashMap<>();
  }

  public Map<String, Integer> getRecipe() {
    return recipe;
  }

  public void setRecipe(Map<String, Integer> recipe) {
    this.recipe = recipe;
  }
}
