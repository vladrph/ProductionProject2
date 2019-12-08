package productionproject;

/**
 * This  document has the Movie Player class  which implements Multimedia Control for the Production
 * Project2 GUI project.
 *
 * @author Vladimir Petit-Homme
 */

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;


  MoviePlayer(String name, String manufactuer, Screen screen, MonitorType monitorType) {
    super(name, manufactuer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;


  }

  /**
   * String toString method that prints out th screen and monitor type.
   *
   * @return
   */

  public String toString() {
    return super.toString() + "\n" + "Screen:" + "\n" + screen + "\n" + "Monitor Type:"
        + monitorType;


  }

  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }
}
