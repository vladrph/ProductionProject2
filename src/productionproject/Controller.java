package productionproject;

import static productionproject.ItemType.AUDIO;
import static productionproject.ItemType.AUDIO_MOBILE;
import static productionproject.ItemType.VISUAL;
import static productionproject.ItemType.VISUAL_MOBILE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;

import javafx.scene.text.Font;


/**
 * This controller document has the contains the button and combo box events for the Production
 * Project2 GUI project.
 *
 * @author Vladimir Petit-Homme
 */
public class Controller {

  @FXML
  public ChoiceBox<ItemType> itemType;


  @FXML
  private ComboBox<String> comboBox = new ComboBox<>();


  @FXML
  private Button productButton;


  @FXML
  private Button recordButton;

  @FXML
  private TableView<Product> productTableView;

  @FXML
  ObservableList<Product> productLine = FXCollections
      .observableArrayList();  // Creates and observable list called product line
  @FXML
  private TableColumn<?, ?> prodNameCol;
  @FXML
  private TableColumn<?, ?> manuNameCol;
  @FXML
  private TableColumn<?, ?> typeNameCol;
  //private TableColumn<Product, ItemType> typeNameCol;
  @FXML
  private TextField prodField1; // change name of product field

  @FXML
  private TextField manuField2; // change name of manu field
  @FXML
  private TextArea textArea;
  @FXML
  private //ListView listView;
      ListView<Product> listView = new ListView<Product>();


  /**
   * This method prints " Test for button" for the print product button.
   *
   * @param event This method currently prints to the console and establishes a Database
   *              connection.
   */
  @FXML
  void addproduct(MouseEvent event) throws SQLException {

    System.out.println("Test for button for Add Product"); // Text button for print product
    //populateList();
    String nameText = prodField1.getText();   // Gets information from production field
    String manuText = manuField2.getText();   // Gets information from Manufacturer field
    ItemType type = itemType.getValue();      // Gets a value from the item type field

    productLine.add(new Widget(nameText, manuText, type));

    initializeDB();        //establish the database connection
    prodField1.setText("");   // clears text field
    manuField2.setText("");  // clears text field
    itemType.setValue(null); // clears Choice Box field

  }

  /**
   * This method will show the production log in the Production Log tab TextArea.
   */
  public void productionLog() {

    textArea.setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12
    String comboNumber = comboBox
        .getValue();   // this  will allow me to pull the number from the combo box

    System.out.println(
        "This is the comboNumber I choose " + comboNumber); // this is the test for the combo number

    Product listItems = listView.getSelectionModel()
        .getSelectedItem(); // this line will allow me to choose items from the list view

    System.out.println("This is the item on the list that I choose" + "\n"
        + listItems); // this is the test for the list Items

    String name = listItems.getName();
    String manu = listItems.getManufacturer();
    ItemType type = listItems.getType();

    int numProduced = Integer.parseInt(comboNumber); //this will come from the combobox in the UI

    Product productProduced = new Widget(name, manu, type);

    // test constructor used when creating production records from user interface

    int itemCount = 0;

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);

      // using the iterator as the product id for testing
      System.out.println(pr.toString());
      textArea.appendText(pr.toString() + "\n");
    }

    System.out.println("numProduced is " + numProduced);


  }


  /**
   * This method initialize runs automatically as the program is started. This method populates
   * numbers to the comboBox This method populates enum values into the combo box.
   */


  public void initialize() throws SQLException {

    comboBox.getItems().add("1");
    comboBox.getItems().add("2");
    comboBox.getItems().add("3");
    comboBox.getItems().add("4");
    comboBox.getItems().add("5");
    comboBox.getItems().add("6");
    comboBox.getItems().add("7");
    comboBox.getItems().add("8");
    comboBox.getItems().add("9");
    comboBox.getItems().add("10");

    itemType.getItems()
        .addAll(AUDIO, ItemType.VISUAL, ItemType.AUDIO_MOBILE, ItemType.VISUAL_MOBILE);

    productTableView.setItems(productLine);
    populateList();
    prodNameCol.setCellValueFactory(new PropertyValueFactory("name"));
    manuNameCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    typeNameCol.setCellValueFactory(new PropertyValueFactory("type"));

    testMultimedia();


  }


  public ChoiceBox<ItemType> getItemType() {
    return itemType;
  }

  public void setItemType(ChoiceBox<ItemType> itemType) {
    this.itemType = itemType;
  }


  /**
   * This method populate list is currently an empty method that will be used populate information
   * from the database.
   */


  public void populateList() throws SQLException {

    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/ProductDatabase";

    //  Database credentials
    final String user = "";
    final String pass = "";
    Connection conn = null;
    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM Product";

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        // these lines correspond to the database table columns
        String id = rs.getString(1);
        String name = rs.getString(2);
        String manufacturer = rs.getString(4);
        String type = rs.getString(3); //

        ItemType temp;
        temp = null;
        int idTemp = Integer.parseInt(id);

        if (type.equals("AUDIO")) {
          temp = AUDIO;
          // System.out.println("Audio");
        } else if (type.equals("VISUAL")) {
          temp = VISUAL;
          //  System.out.println("Visual");

        } else if (type.equals("AUDIO_MOBILE")) {

          temp = AUDIO_MOBILE;
          //  System.out.println("Audio Mobile");

        } else if (type.equals("Visual Mobile")) {
          temp = VISUAL_MOBILE;
          // System.out.println("Visual Mobile");

        } else {
          System.out.println("null");
        }
        if (manufacturer.equals("")) {
          manufacturer = "ERROR";
        }
        if (name.equals("")) {
          name = "ERROR";
        }

        Product productFromDB = new Widget(name, manufacturer, temp); // Create object
        // save to observable list
        productLine.add(productFromDB); //adds info form database to product line
        //listView.getItems().add(String.valueOf(productFromDB));//another way to add products
        listView.getItems().add(productFromDB);
        //listView.setSelectionModel(productFromDB.toString());
        int productionNumber = rs.getRow(); // get row id
        System.out.println(productionNumber);


      }
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }


  /**
   * This method prints " Test for Record Production button" This methods prints to the console to
   * ensure that the method and button is working properly.
   *
   * @param event1 This method currently * prints to the console
   */
  @FXML
  void printproduct2(MouseEvent event1) {

    //String nameText = prodField1.getText();
    //String manuText = manuField2.getText();
    //  ItemType type = itemType.getValue();

    System.out.println("Test for Record Production button"); // test button for Record Production
    productionLog();
    //initializeProductionRecordDB();
    // Product product1 = new Widget(nameText, manuText, type);
    // System.out.println(product1.toString());  // prints out name manufacturer and type
    // listView.getItems().add(product1.toString() + "\n");

  }

  /**
   * This method initializes a value of 1-10 to the combo box.
   */
  @FXML
  public void initializeValue() { // adds value 1- 10 to to a combo box

    comboBox.setEditable(true);  // creates an editable comboBox
    // comboBox.getSelectionModel().selectFirst(); // This line prints numbers out 2 times

  }

  /**
   * created a new ProductionRecord datatase to store information the the Production record.
   */
  public void initializeProductionRecordDB() {

    // String nameText = prodField1.getText();   // Gets information from production field
    // String manuText = manuField2.getText();   // Gets information from Manufacturer field
    //  ItemType type = itemType.getValue();      // Gets information from Item Type choice box.

    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/ProductDatabase";

    //  Database credentials
    final String user = "";
    final String pass = "";
    Connection conn = null;
    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      System.out.println("Inserting records into the table...");

      textArea.setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12
      String comboNumber = comboBox
          .getValue();   // this  will allow me to pull the number from the combo box

      System.out.println("This is the comboNumber I choose "
          + comboNumber); // this is the test for the combo number

      Product listItems = listView.getSelectionModel()
          .getSelectedItem(); // this line will allow me to choose items from the list view

      System.out.println("This is the item on the list that I choose" + "\n"
          + listItems); // this is the test for the list Items

      int numProduced = Integer.parseInt(comboNumber); //this will come from the combobox in the UI

      Product productProduced = new Widget("name", "manu", ItemType.AUDIO);
      // Product productProduced = new Widget(listView.getTypeSelector(), "Apple", AUDIO);
      // test constructor used when creating production records from user interface

      int itemCount = 0;

      for (int productionRunProduct = 0; productionRunProduct < numProduced;
          productionRunProduct++) {
        ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);

        // using the iterator as the product id for testing

        textArea.appendText(pr.toString() + "\n");
      }
      // ProductionRecord pr = new ProductionRecord( 0);
      System.out.println("numProduced is " + numProduced);

      // String sql =
      // "INSERT INTO PRODUCTIONRECORD" + "(PRODUCT_ID, PRODUCTION_NUM, SERIAL_NUM,DATE_PRODUCED) "
      //  + " VALUES ( '" + productProduced.getId() + "', '" + numProduced + "', '" + pr
      //.getSerialNum() + "', '" + pr.getProdDate()
      //     + "' )";  // this sql statement gets information from the
      //  text fields and choice box and loads them into the database.

      // stmt.executeUpdate(sql);

      System.out.println("Inserted records into the table...");

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }


  /**
   * This method initializes the database and connects the h2 driver.
   */
  @FXML

  public void initializeDB() {

    String nameText = prodField1.getText();   // Gets information from production field
    String manuText = manuField2.getText();   // Gets information from Manufacturer field
    ItemType type = itemType.getValue();      // Gets information from Item Type choice box.

    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/ProductDatabase";

    //  Database credentials
    final String user = "";
    final String pass = "";
    Connection conn = null;
    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      System.out.println("Inserting records into the table...");

      String sql = "INSERT INTO Product" + "(type, manufacturer, name) "
          + " VALUES ( '" + type + "', '" + manuText + "', '" + nameText
          + "' )";  // this sql statement gets information from the
      // text fields and choice box and loads them into the database.

      stmt.executeUpdate(sql);

      System.out.println("Inserted records into the table...");

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  /**
   * This is a method called test multimedia to demonstrate functionality in the code.
   */
  public static void testMultimedia() {
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }


}