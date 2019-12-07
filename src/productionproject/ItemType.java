package productionproject;

/**
 * An enum called item type that will store 4 individual types and their corresponding code.
 */

 enum ItemType {  // creates enum item to store type and code information

  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");

  public String code;

  public void setCode(String code) {
    this.code = code;
  }

  ItemType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;

  }

}
