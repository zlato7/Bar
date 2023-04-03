
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuStatistic {

  private List<String> mostOrdered;
  private List<String> leastOrdered;

  private HashMap<String, Integer> allOrders;

  private int minCount = Integer.MAX_VALUE;
  private int maxCount = Integer.MIN_VALUE;

  public MenuStatistic() {

    mostOrdered = new ArrayList<>();
    leastOrdered = new ArrayList<>();
    allOrders = new HashMap<>();
  }

  public HashMap<String, Integer> getAllOrders() {
    return allOrders;
  }

  public List<String> getMostOrdered() {
    return mostOrdered;
  }

  public List<String> getLeastOrdered() {
    return leastOrdered;
  }

  public void ordersStats(List<OrderType> menu, List<Client> clients) {

    setAllOrders(menu, clients);//will added all ordered stuff with the count in allOrders

    setMinAndMax();//set max and min count

    //These are the least ordered
    removeMinimums(menu);
  }


  private void removeMinimums(List<OrderType> menu) {
    ArrayList<String> toRemove = new ArrayList<>();

    for (Map.Entry<String, Integer> entry : allOrders.entrySet()) {

      String name = entry.getKey();
      int count = entry.getValue();

      if (count == minCount) {
        leastOrdered.add(name);
        toRemove.add(name);
      }
      if (count == maxCount) {
        mostOrdered.add(name);
      }
    }

    for (String currentProductToRemove : toRemove) {

      for (OrderType currentProductInMenu : menu) {

        if (currentProductInMenu.getName().equals(currentProductToRemove)) {
          menu.remove(currentProductInMenu);
          break;
        }
      }
    }
  }


  private void setMinAndMax() {

    for (Map.Entry<String, Integer> entry : allOrders.entrySet()) {

      if (entry.getValue() < minCount) {
        minCount = entry.getValue();
      }
      if (entry.getValue() > maxCount) {
        maxCount = entry.getValue();
      }
    }
  }

  private void setAllOrders(List<OrderType> menu, List<Client> clients) {

    for (OrderType currentRecord : menu) {

      String orderName = currentRecord.getName();
      allOrders.put(orderName, 0);//fill all things in menu
    }

    for (Client client : clients) {//fill all orders

      for (OrderType currentOrder : client.getOrders()) {//List<OrderType>

        for (Map.Entry<String, Integer> entry : allOrders.entrySet()) {

          if (currentOrder.getName().equals(entry.getKey())) {

            allOrders.put(entry.getKey(), entry.getValue() + 1);
            break;
          }
        }
      }
    }
  }
}
