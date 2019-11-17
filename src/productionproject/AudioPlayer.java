package productionproject;

/**
 * Audio player is a subclass of Product class and implements multimedia control interface.
 */
public class AudioPlayer extends Product implements MultimediaControl {

  String supportedAudioFormats;
  String supportedPlaylistFormats;


  AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);  // super from Product class constructor
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;

  }

  /**
   * public String toString that returns the supportedAudioFormats and supportedPlaylistFormat to a
   * String.
   *
   * @return
   */
  public String toString() {
    return super.toString() + "\n" + "Supported Audio Formats:" + this.supportedAudioFormats + "\n"
        + "Supported Playlist Formats:" + this.supportedPlaylistFormats;


  }


  @Override
  public void play() {
    System.out.println("Playing");

  }

  @Override
  public void stop() {
    System.out.println("Stopping");

  }

  @Override
  public void previous() {
    System.out.println("Previous");

  }

  @Override
  public void next() {
    System.out.println("Next");

  }
}



