package productionproject;


public abstract class Product implements Item {

  private int Id;
  private ItemType Type;
  public String manufacturer;
  private String Name;

  public void setId(int id) {
    Id = id;
  }

  public ItemType getType() {
    return Type;
  }

  public void setType(ItemType type) {
    Type = type;
  }

  /**
   * Product constructor holds a String of type name and manufacturer  and a Item type called type.
   *
   * @param name   the name of the specific product
   * @param manufacturer  holds the manufacturer of a specific product
   * @param type        holds the specific ItemType of of the product
   */

  public Product(String name, String manufacturer, ItemType type) {
    this.Name = name;
    this.manufacturer = manufacturer;
    Type = type;
  }


  public String toString() {
    return "Name: " + Name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + Type;
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
  }
}
