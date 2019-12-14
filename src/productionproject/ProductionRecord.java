package productionproject;

import java.sql.Timestamp;
import java.util.Date;

/**
 * This  document has the contains the production record information for the Production Project2 GUI
 * project. This class can generate a TimeStamp, Serial Number and  increment the item count. It also
 * can pull the productID and generate a Production Number.
 *
 * @author Vladimir Petit-Homme
 */

public class ProductionRecord {

  int productionNumber;
  int productID;
  int itemCount;
  private Product productProduced;
  Date date = new Date();
  long time = date.getTime();
  Timestamp ts = new Timestamp(time);

  ProductionRecord(int productID) {

    productionNumber = 0;
    String serialNumber = "0";
    //dateProduced = new Date();
    //this.serialNumber = serialNumber;

  }

  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    //this.serialNumber = serialNumber;
    //this.dateProduced = dateProduced;

  }

  ProductionRecord(int productionNumber, String serialNumber, Date dateProduced) {

    this.productionNumber = productionNumber;
    //this.productID = productID;
    //this.serialNumber = serialNumber;
    //this.dateProduced = dateProduced;

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
   * Production record field that contains the fields used to increment the number of items for the
   * serial number. Along with an ProductId field.
   *
   * @param productID       Holds the productID  information
   * @param productProduced produces new product
   * @param itemCount       Increments the serial number
   */
  public ProductionRecord(int productID, Product productProduced, int itemCount) {
    this.productID = productID;
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
            + "0000" + itemCount + " Date: " + ts; /* Mon Oct 14 10:29:48 UTC 2019*/
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
    return productProduced.getManufacturer().substring(0, 3) + productProduced.getType().code
        + "0000" + itemCount;
  }

  public Date getProdDate() {
    return new Date();
  }


}
