package productionproject;

public class Screen implements ScreenSpec {

  String resolution;
  int refreshRate;
  int responseTime;

  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   *  Prints information of resolution, refresh rate, and response time.
   *
   * @return
   */

  public String toString() {
    return "Resolution:" + resolution + "\n" + "Refresh rate:" + refreshRate + "\n"
        + "Response time:"
        + responseTime;
  }

  public void setResolution(String resolution) {
    this.resolution = resolution;
  }

  public void setRefreshRate(int refreshRate) {
    this.refreshRate = refreshRate;
  }

  public void setResponseTime(int responseTime) {
    this.responseTime = responseTime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }
}
