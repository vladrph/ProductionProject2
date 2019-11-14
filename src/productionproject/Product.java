package productionproject;



public abstract class Product implements Item {

  private int Id;



  private ItemType Type;
  public  String manufacturer;
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

  public Product(String name, String manufacturer, ItemType type) {
    this.Name = name;
    this.manufacturer = manufacturer;
    Type = type;
  }


  public String toString() {
    return  "Name: " + Name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + Type ;
  }

  public int getId() {
    return Id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    manufacturer = manufacturer;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }
}



class Widget extends Product {
  /* Widget(); */


  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}
