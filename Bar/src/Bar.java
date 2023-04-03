import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Bar {

  private final BigDecimal startingMoney = new BigDecimal(200);
  private static int clientsBeenInBarCounter;

  private String name;
  private BigDecimal money;
  private BigDecimal dailyTurnover;
  private List<OrderType> menu;
  private List<Client> clients;
  private int tables;
  private List<Employee> employees;
  private List<Product> storage;
  private TributeBand tributeBand;
  private String currentDay;

  private Waiter firstWaiter;
  private Waiter secondWaiter;
  private Bartender bartender;
  private SecurityMan securityMan;
  private Chef chef;

  Product bun = new Product("Bread", 30);
  Product burgerMeat = new Product("Meat", 30);
  Product dough = new Product("Dough", 30);
  Product cheese = new Product("Cheese", 30);
  Product potatoes = new Product("Potatoes", 30);
  Product water = new Product("Water", 50);
  Product juice = new Product("Juice", 50);
  Product cola = new Product("Cola", 50);
  Product vodka = new Product("Vodka", 50);
  Product whiskey = new Product("Whiskey", 50);
  Product rum = new Product("Rum", 50);
  Product beer = new Product("Beer", 50);


  public Bar(String name) {
    this.name = name;
    this.clients = new ArrayList<>();
    this.money = startingMoney;
    this.tributeBand = new TributeBand();
    this.tables = 0;
    this.dailyTurnover = new BigDecimal(0);
    setEmployees();
    setMenu();
    setStorage();
  }

  public Waiter getFirstWaiter() {
    return firstWaiter;
  }

  public Waiter getSecondWaiter() {
    return secondWaiter;
  }

  public Bartender getBartender() {
    return bartender;
  }

  public SecurityMan getSecurityMan() {
    return securityMan;
  }

  public Chef getChef() {
    return chef;
  }

  public static int getClientsBeenInBarCounter() {
    return clientsBeenInBarCounter;
  }

  public String getCurrentDay() {
    return currentDay;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public List<Product> getStorage() {
    return storage;
  }

  public void setStorage() {

    this.storage = new ArrayList<>(
        List.of(bun, burgerMeat, dough, cheese, potatoes, water, juice, cola, vodka, whiskey, rum,
            beer));

  }

  public void setEmployees() {

    firstWaiter = new Waiter("Mariq", new BigDecimal(30));
    secondWaiter = new Waiter("Tanq", new BigDecimal(30));
    bartender = new Bartender("Nikolay", new BigDecimal(35));
    securityMan = new SecurityMan("Ivan", new BigDecimal(30));
    chef = new Chef("Konstantin", new BigDecimal(40));

    this.employees = new ArrayList<>(
        List.of(firstWaiter, secondWaiter, bartender, securityMan, chef));
  }

  public BigDecimal getStartingMoney() {
    return startingMoney;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public BigDecimal getDailyTurnover() {
    return dailyTurnover;
  }

  public void setDailyTurnover(BigDecimal dailyTurnover) {
    this.dailyTurnover = dailyTurnover;
  }

  public List<OrderType> getMenu() {
    return menu;
  }

  public void setMenu() {

    Dish burger = new Dish("Burger", 350, new BigDecimal(13));
    Map<String, Integer> burgerRecipe = new HashMap<>();
    burgerRecipe.put(bun.getName(), 1);
    burgerRecipe.put(burgerMeat.getName(), 1);
    burger.setRecipe(new HashMap<>(burgerRecipe));

    Dish fries = new Dish("Fries", 200, new BigDecimal(7));
    Map<String, Integer> friesRecipe = new HashMap<>();
    friesRecipe.put(potatoes.getName(), 2);
    fries.setRecipe(new HashMap<>(friesRecipe));

    Dish friesWithCheese = new Dish("FriesAndCheese", 250, new BigDecimal(8));
    Map<String, Integer> friesWithCheeseRecipe = new HashMap<>();
    friesWithCheeseRecipe.put(potatoes.getName(), 2);
    friesWithCheeseRecipe.put(cheese.getName(), 1);
    friesWithCheese.setRecipe(friesWithCheeseRecipe);

    Drink water = new Drink("Water", 500, new BigDecimal(2));
    Drink juice = new Drink("Juice", 330, new BigDecimal(1));
    Drink cola = new Drink("Cola", 330, new BigDecimal(2));
    Drink vodka = new Drink("Vodka", 50, new BigDecimal(3));
    Drink whiskey = new Drink("Whiskey", 50, new BigDecimal(3));
    Drink rum = new Drink("Rum", 50, new BigDecimal(3));
    Drink beer = new Drink("Beer", 500, new BigDecimal(3));

    this.menu = new ArrayList<>(List.of(burger, fries, friesWithCheese,
        water, juice, cola, vodka, whiskey, rum, beer));

  }

  public List<Client> getClients() {
    return clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }

  public TributeBand getTributeBand() {
    return tributeBand;
  }

  public void setGuеstsOnTable() {
    Queue<Integer> clientsQueue = createQueueWithClients();//filling the queue with clients in groups
    System.out.println(clientsQueue);
    //each table is for max 6 people
    ArrayBlockingQueue<Integer> clientAtTables = new ArrayBlockingQueue<>(10);
    //tables
    while ((!clientsQueue.isEmpty()) && (tables < 10)) {
      int guestGroup = clientsQueue.poll();
      clientAtTables.offer(guestGroup);
      tables++;

    }
    while (!clientAtTables.isEmpty()) {
      int currentGroup = clientAtTables.poll();
      tableService(currentGroup);
      if ((tables < 10) && (clientAtTables.size() != 10) && (!clientsQueue.isEmpty())) {
        clientAtTables.offer(clientsQueue.poll());
        tables++;
      }
    }
  }

  public void tableService(int currentGroup) {
    Random random = new Random();
    Waiter waiter = chooseRandomWaiter(random);

    for (int i = 0; i < currentGroup; i++) {

      Client client = new Client();
      int countOfOrders = random.nextInt(5) + 1;

      for (int j = 0; j < countOfOrders; j++) {

        int randomOrder = random.nextInt(menu.size());

        boolean isItPrepared = true;
        if (menu.get(randomOrder) instanceof Drink) {

          if (!bartender.prepareDrink(storage, (Drink) menu.get(randomOrder))) {
            isItPrepared = false;
            countOfOrders--;
          }
        } else {
          if (!chef.cooking(storage, (Dish) menu.get(randomOrder))) {
            isItPrepared = false;
            countOfOrders--;
          }
        }
        if (isItPrepared) {
          client.setOrder(menu.get(randomOrder));
          client.setBill(menu
              .get(randomOrder)
              .getPrice());
        }
      }
      clientsBeenInBarCounter++;
      this.dailyTurnover = this.dailyTurnover.add(client.getBill());
      if (clientsBeenInBarCounter % 2 == 0) {
        client.setTip();
        waiter.setTip(client.getTip());
      }
      clients.add(client);

    }
    tables--;
  }

  private Waiter chooseRandomWaiter(Random random) {
    Waiter waiter;
    int randomWaiter = random.nextInt(2) + 1;
    if (randomWaiter == 1) {
      waiter = firstWaiter;
    } else {
      waiter = secondWaiter;
    }
    return waiter;
  }

  private Queue<Integer> createQueueWithClients() {
    Random random = new Random();

    int clientsCount = random.nextInt(100) + 1;
    System.out.println("clients: " + clientsCount);
    Queue<Integer> clientsQueue = new LinkedList<>();

    while (clientsCount > 0) {

      int groupOfPeople = random.nextInt(6) + 1;

      if (clientsCount - groupOfPeople < 0) {

        clientsQueue.add(clientsCount);
        break;
      }
      clientsQueue.add(groupOfPeople);

      clientsCount -= groupOfPeople;
    }
    return clientsQueue;
  }

  public void setDayOfWeek() {

    ArrayList<String> allOptionsForTheDay = new ArrayList<>();//create a list with all options for the week

    for (DayOfWeek day : DayOfWeek.values()) {

      if (day.equals(DayOfWeek.MONDAY) || day.equals(DayOfWeek.TUESDAY)) {
        continue;
      }

      if (day.equals(DayOfWeek.FRIDAY)) {

        allOptionsForTheDay.add(
            day + " - " + tributeBand.getBandsOptionsForFridayAndSaturday().get(0)
                + " tribute - 10 lv entrance");
        continue;
      }
      if (day.equals(DayOfWeek.SATURDAY)) {

        allOptionsForTheDay.add(
            day + " - " + tributeBand.getBandsOptionsForFridayAndSaturday().get(1)
                + " tribute - 10 lv entrance");
        continue;
      }

      allOptionsForTheDay.add(day.toString());

    }

    ImageIcon callendarPhoto = new ImageIcon("src/photos/callendar_photo.jpg");

    try {
      String setDay = (String) JOptionPane.showInputDialog(//create dropdown menu for the day
          null,
          "Choose day, Monday and Tuesday is closed",
          "Day",
          JOptionPane.QUESTION_MESSAGE,
          callendarPhoto,
          allOptionsForTheDay.toArray(),
          allOptionsForTheDay.toArray()[0]);

      currentDay = setDay.split(" ")[0];
    } catch (NullPointerException ex) {
      System.out.println("You didn't choose a day!");
      return;
    }

    firstWaiter.setOnShift(true);
    secondWaiter.setOnShift(true);
    bartender.setOnShift(true);
    chef.setOnShift(true);

    if (currentDay.equals(DayOfWeek.FRIDAY.toString()) || currentDay.equals(
        DayOfWeek.SATURDAY.toString())) {

      securityMan.setOnShift(true);

      for (Employee bandMember : tributeBand.getBandMembers()) {

        bandMember.setOnShift(true);
      }
    }
  }

  public void allocationOfTicketFees() {

    BigDecimal moneyFromTickets = new BigDecimal(10).multiply(
        new BigDecimal(clientsBeenInBarCounter));
    securityMan.setWallet(moneyFromTickets);
    BigDecimal half = moneyFromTickets.divide(new BigDecimal(2), RoundingMode.HALF_UP);
    dailyTurnover = dailyTurnover.add(securityMan.getWallet());
    tributeBand.setBandHonorarium(half);

    for (Employee emp : tributeBand.getBandMembers()) {
      emp.setDailyWage(half.divide(new BigDecimal(4), 2, RoundingMode.HALF_UP));
    }
  }

  public void printMenu() {

    System.out.println("Menu");
    for (OrderType orderType : menu) {
      System.out.println(orderType);
    }
  }
}