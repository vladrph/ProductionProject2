package productionproject;


public abstract class Product implements Item {

  private int Id;
  private ItemType type;
  public String manufacturer;
  private String Name;


  /**
   * Product constructor holds a String of type name and manufacturer  and a Item type called type.
   *
   * @param name         the name of the specific product
   * @param manufacturer holds the manufacturer of a specific product
   * @param type         holds the specific ItemType of of the product
   */

  public Product(String name, String manufacturer, ItemType type) {
    this.Name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Created a new constructor that also holds a id parameter for future use
   *
   * @param id           holds id
   * @param name         holds name
   * @param manufacturer hold manufacturer
   * @param type         holds Item type
   */
  public Product(int id, String name, String manufacturer, ItemType type) {
    this.Id = id;
    this.Name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString() {
    return "Name: " + Name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type ;
  }

  public int setId(int id) {
   this.Id = id;
    return id;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public int getId() {
    return Id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }
}


class Widget extends Product {

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
    //setType(type);
  }

  Widget(int Id, String name, String manufacturer, ItemType type) {
    super(Id, name, manufacturer, type);
  }

}
