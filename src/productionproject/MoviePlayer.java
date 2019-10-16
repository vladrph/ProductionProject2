package productionproject;

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;
  String resolution;
  int refreshRate;
  int responseTime;


  MoviePlayer(String name, String manufactuer, Screen screen, MonitorType monitorType) {
    super(name, manufactuer, ItemType.VISUAL);
    //super(resolution,refreshRate,responseTime);
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
    this.resolution = resolution;
    this.screen = screen;
    this.monitorType = monitorType;


  }

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
