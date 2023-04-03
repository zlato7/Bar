

public class Start {

  public static void runProgram() throws Exception {
    Bar bar = new Bar("The Spirits of Hell");
    bar.setDayOfWeek();
    System.out.println();

    bar.printMenu();

    System.out.println();
    MusicPlayer musicPlayer = new MusicPlayer();
    checkForBandToday(bar.getCurrentDay(), musicPlayer, bar);

    String today = bar.getCurrentDay();
    System.out.print("\u001B[92mThe day you chose is " + today + "!");

    try {
      if (today.equals(DayOfWeek.FRIDAY.toString())) {
        System.out.println(
            " " + bar.getTributeBand().getName() + " Will Play Tribute to "
                + bar.getTributeBand().getBandsOptionsForFridayAndSaturday().get(0));

      } else if (today.equals(DayOfWeek.SATURDAY.toString())) {
        System.out.println(
            " " + bar.getTributeBand().getName() + " Will Play Tribute to "
                + bar.getTributeBand().getBandsOptionsForFridayAndSaturday().get(1));
      } else {
        System.out.println();
      }
    } catch (NullPointerException ex) {
      System.out.println("You didn't choose a day!");
      return;
    }

    System.out.println();
    bar.setGuеstsOnTable();

    System.out.println();
    System.out.println("Daily Turnover: " + bar.getDailyTurnover());
    System.out.println(bar.getFirstWaiter());
    System.out.println(bar.getSecondWaiter());
    System.out.println(bar.getBartender());
    System.out.println(bar.getChef());
    System.out.println();

    if (today.equals(DayOfWeek.FRIDAY.toString()) || (today.equals(
        DayOfWeek.SATURDAY.toString()))) {

      System.out.println(bar.getSecurityMan());
      bar.allocationOfTicketFees();
      System.out.println("Money from tickets: " + bar.getSecurityMan().getWallet());
      System.out.println(bar.getSecurityMan());
      System.out.println("Band honorarium " + bar.getTributeBand().getBandHonorarium());
      System.out.println("Band members:");
      for (Employee emp : bar.getTributeBand().getBandMembers()) {
        System.out.println(emp);
      }
    }

    System.out.println();

    System.out.println(bar.getClients());

    System.out.println("num of clients: " + Bar.getClientsBeenInBarCounter());
    System.out.println();
    MenuStatistic menuStatistic = new MenuStatistic();
    menuStatistic.ordersStats(bar.getMenu(), bar.getClients());
    System.out.println("Most Ordered: " + menuStatistic.getMostOrdered());
    System.out.println("Least Ordered: " + menuStatistic.getLeastOrdered());
    System.out.println();
    bar.printMenu();

    System.out.println("Products left\n"+bar.getStorage());

    System.out.println();
    if (today.equals(DayOfWeek.FRIDAY.toString()) || (today.equals(
        DayOfWeek.SATURDAY.toString()))) {

      System.out.println("KEEP LISTENING!!!");
      Thread.sleep(1000000);
    }
  }

  private static void checkForBandToday(String day, MusicPlayer musicPlayer, Bar bar)
      throws NullPointerException {
    try {
      if (day
          .equals(DayOfWeek.FRIDAY.toString())) {

        String musicLocation = musicPlayer.musicLocation(
            bar.getTributeBand().getBandsOptionsForFridayAndSaturday().get(0));
        musicPlayer.turnTheMusic(musicLocation);
      }
      if (day.equals(DayOfWeek.SATURDAY.toString())) {

        String musicLocation = musicPlayer.musicLocation(
            bar.getTributeBand().getBandsOptionsForFridayAndSaturday().get(1));
        musicPlayer.turnTheMusic(musicLocation);
      }
    } catch (NullPointerException ex) {
      return;
    }
  }
}
