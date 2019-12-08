package productionproject;

/**
 * This class enum types for the Production Project2 GUI project. An enum called item type that will
 * store 4 individual types and their corresponding code.
 *
 * @author Vladimir Petit-Homme
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
