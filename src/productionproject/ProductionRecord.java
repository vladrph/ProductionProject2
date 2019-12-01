package productionproject;

import java.util.Date;

public class ProductionRecord {

  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;
  int itemCount;
  private Product productProduced;

  ProductionRecord(int productID) {

    productionNumber = 0;
    String serialNumber = "0";
    dateProduced = new Date();
    this.serialNumber = serialNumber;


  }

  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;


  }

  /**
   * This method contains the fields used to increment the number of items for the serial number.
   *
   * @param productProduced produces a new product
   * @param itemCount       increments serial number
   */
  public ProductionRecord(Product productProduced, int itemCount) {

    this.productProduced = productProduced;
    this.itemCount = itemCount;

  }

  /**
   * A to string method that produces the production number, product ID, Serial number and current
   * date.
   *
   * @return
   */

  public String toString() {
    return
        "Prod. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: "
            + productProduced.getManufacturer().substring(0, 3) + productProduced.getType().code
            + "0000" + itemCount + " Date: " + new Date(); /* Mon Oct 14 10:29:48 UTC 2019*/
  }


  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }


  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

}
