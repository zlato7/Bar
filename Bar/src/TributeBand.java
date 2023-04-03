import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TributeBand {

  private String name="BloodyCode";
  private ArrayList<Employee> bandMembers;
  private BigDecimal bandHonorarium;
  private ArrayList<Band> bandsOptionsForFridayAndSaturday;

  public TributeBand() {
    this.bandMembers = new ArrayList<>();
    this.bandsOptionsForFridayAndSaturday = generateRandomBandsForTribute();
    setBandMembers();
  }

  public void setName() {
    this.name = name;
  }

  public void setBandHonorarium(BigDecimal bandHonorarium) {
    this.bandHonorarium = bandHonorarium;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Employee> getBandMembers() {
    return bandMembers;
  }

  public BigDecimal getBandHonorarium() {
    return bandHonorarium;
  }

  public ArrayList<Band> getBandsOptionsForFridayAndSaturday() {
    return bandsOptionsForFridayAndSaturday;
  }

  public void setBandMembers() {

    Employee firstGuitarMan = new Employee("Mitko", new BigDecimal(0));
    Employee secondGuitarMan = new Employee("Dilyan", new BigDecimal(0));
    Employee drumsMan = new Employee("Toshko", new BigDecimal(0));
    Employee vocalMan = new Employee("Stefan", new BigDecimal(0));

    this.bandMembers = new ArrayList<>(List.of(firstGuitarMan,secondGuitarMan,drumsMan,vocalMan));
  }

  public static ArrayList<Band> generateRandomBandsForTribute() {
    Random random = new Random();

    int randomBandFridayIndex = random.nextInt(10);
    int randomBandSaturdayIndex = random.nextInt(10);

    while (randomBandFridayIndex == randomBandSaturdayIndex) {

      randomBandSaturdayIndex = random.nextInt(10);
    }

    Band fridayBand = Band.values()[randomBandFridayIndex];
    Band saturdayBand = Band.values()[randomBandSaturdayIndex];

    ArrayList<Band> bands = new ArrayList<>(List.of(fridayBand, saturdayBand));

    return bands;
  }
}