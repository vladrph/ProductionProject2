package productionproject;

public enum ItemType {

  AUDIO("Au"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");


  public String code;


   ItemType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }


}
