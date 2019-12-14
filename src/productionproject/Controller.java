package productionproject;

import static productionproject.ItemType.AUDIO;
import static productionproject.ItemType.AUDIO_MOBILE;
import static productionproject.ItemType.NULL;
import static productionproject.ItemType.VISUAL;
import static productionproject.ItemType.VISUAL_MOBILE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
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
  private TextField employeeNameField; // Field to input Employee name

  @FXML
  private TextField passwordField; // Field to input Password

  @FXML
  private PasswordField passwordField1; // Field to input Password
  @FXML
  private TextArea textArea;

  @FXML

  ObservableList<Product> produceline = FXCollections.observableArrayList();
  @FXML
  private //ListView listView;
      ListView<Product> listView = new ListView<>(produceline);
  @FXML
  private TextArea mediaTextArea;
  @FXML
  private TextArea employeeTextArea;

  @FXML
  private Button mediaButton;


  /**
   * This method prints " Test for button" for the print product button.
   *
   * @param event This method currently prints to the console and establishes a Database
   *              connection.
   */
  @FXML
  void addproduct(MouseEvent event) throws SQLException, IOException {

    System.out.println("Test for button for Add Product"); // Text button for print product

    //populateList();

    String nameText = prodField1.getText();   // Gets information from production field

    String manuText = manuField2.getText();   // Gets information from Manufacturer field

    ItemType type = itemType.getValue();      // Gets a value from the item type field

    productLine.add(new Widget(nameText, manuText, type)); // adds to product line
    produceline.add(new Widget(nameText, manuText, type)); //adds to list view

    initializeDB();        //establish the database connection

    prodField1.setText("");   // clears text field

    manuField2.setText("");  // clears text field

    itemType.setValue(null); // clears Choice Box field

  }

  /**
   * This will be a new method to receive the Employee details.
   */

  public void newEmployee() {

    employeeTextArea
        .setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12

    String name = employeeNameField.getText(); // gets the Employee name field information

    String passWord = passwordField1.getText(); // gets the Employee Password information
    if (employeeNameField.getText().equals("") || passwordField1.getText().equals("")) {
      name = "ERROR";
      passWord = "ERROR";
    }

    Employee employee = new Employee(name, passWord);

    employeeTextArea.appendText(employee.toString());

    employeeNameField.setText("");   // clears text field

    passwordField1.setText("");  // clears text field

  }

  /**
   * New Employee method that print out the employee information.
   */
  @FXML
  void getEmployeeinfo(MouseEvent event3) {

    System.out.println("Test for Employee button"); // test button for Employee Production

    newEmployee();

  }

  /**
   * This method prints the Production log info to the console.
   */
  public void productionLog() {

    textArea.setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12

    String comboNumber = comboBox
        .getValue();   // this  will allow me to pull the number from the combo box

    if (comboNumber.equals("1")) {
      comboNumber = "1";
    } else if (comboNumber.equals("2")) {
      comboNumber = "2";
    } else if (comboNumber.equals("3")) {
      comboNumber = "3";
    } else if (comboNumber.equals("4")) {
      comboNumber = "4";
    } else if (comboNumber.equals("5")) {
      comboNumber = "5";
    } else if (comboNumber.equals("6")) {
      comboNumber = "6";
    } else if (comboNumber.equals("7")) {
      comboNumber = "7";
    } else if (comboNumber.equals("8")) {
      comboNumber = "8";
    } else if (comboNumber.equals("9")) {
      comboNumber = "9";
    } else if (comboNumber.equals("10")) {
      comboNumber = "10";
    } else {
      comboBox.setValue("0");
    }

    System.out.println(
        "This is the comboNumber I choose " + comboNumber); // this is the test for the combo number

    Product listItems = listView.getSelectionModel()
        .getSelectedItem(); // this line will allow me to choose items from the list view

    System.out.println("This is the item on the list that I choose" + "\n"
        + listItems); // this is the test for the list Items
    int id = listItems.getId();

    String name = listItems.getName();

    String manu = listItems.getManufacturer();

    ItemType type = listItems.getType();

    int numProduced = Integer.parseInt(comboNumber); //this will come from the combobox in the UI

    Product productProduced = new Widget(id, name, manu, type);

    // test constructor used when creating production records from user interface

    int itemCount = 0;

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {

      ProductionRecord pr = new ProductionRecord(id, productProduced, itemCount++);

      // using the iterator as the product id for testing
      System.out.println(pr.toString());
      // textArea.appendText(pr.toString() + "\n");
    }

    System.out.println("numProduced is " + numProduced);


  }


  /**
   * This method initialize runs automatically as the program is started. This method populates
   * numbers to the comboBox This method populates enum values into the combo box.
   */


  public void initialize() throws SQLException, IOException {

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

    comboBox.setValue("5");

    itemType.getItems()
        .addAll(AUDIO, ItemType.VISUAL, ItemType.AUDIO_MOBILE, ItemType.VISUAL_MOBILE);

    productTableView.setItems(productLine);
    listView.setItems(produceline);

    populateList();

    prodNameCol.setCellValueFactory(new PropertyValueFactory("name"));

    manuNameCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));

    typeNameCol.setCellValueFactory(new PropertyValueFactory("type"));

    productionAreaLog();

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


  public void populateList() throws SQLException, IOException {

    Properties prop = new Properties();
    prop.load(new FileInputStream("res/properties"));
    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/ProductDatabase";

    //  Database credentials
    final String user = "";
    final String pass = prop.getProperty("password");
    Connection conn = null;
    Statement stmt = null;
    // Employee employee = new Employee(user, pass);
    try (OutputStream output = new FileOutputStream("res/properties")) {
      prop.setProperty("db_url", "jdbc:h2:./res/ProductDatabase;");
      prop.setProperty("user", "");
      prop.setProperty("pass", "dbpw");
      prop.store(output, null);
      System.out.println(prop);
      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM Product";

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        // these lines correspond to the database table columns

        String name = rs.getString(2);

        String manufacturer = rs.getString(4);

        String type = rs.getString(3);

        ItemType temp;

        if (type.equals("AUDIO")) {

          temp = AUDIO;

        } else if (type.equals("VISUAL")) {

          temp = VISUAL;

        } else if (type.equals("AUDIO_MOBILE")) {

          temp = AUDIO_MOBILE;

        } else if (type.equals("VISUAL_MOBILE")) {

          temp = VISUAL_MOBILE;


        } else {
          System.out.println("null");

          temp = NULL;

        }
        if (manufacturer.equals("")) {

          manufacturer = "ERROR";

        }
        if (name.equals("")) {

          name = "ERROR";
        }
        String id = rs.getString(1);

        int idTemp = Integer.parseInt(id);

        Product productFromDB = new Widget(idTemp, name, manufacturer, temp); // Create object
        // save to observable list

        productLine.add(productFromDB); //adds info form database to product line

        //listView.getItems().add(String.valueOf(productFromDB));//another way to add products
        // produceline.add(productFromDB);

        // listView.setItems(produceline);
        listView.getItems().add(productFromDB);

        //listView.getItems().add(productFromDB);
        //listView.getItems().add(produceline.toString());
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
  void printproduct2(MouseEvent event1) throws IOException {

    System.out.println("Test for Record Production button"); // test button for Record Production

    productionLog();

    initializeProductionRecordDB();

  }

  /**
   * This method initializes a value of 1-10 to the combo box.
   */
  @FXML
  public void initializeValue() { // adds value 1- 10 to to a combo box

    comboBox.setEditable(true);  // creates an editable comboBox
    //comboBox.getSelectionModel().selectFirst(); // This line prints numbers out 2 times

  }

  /**
   * created a new ProductionRecord datatase to store information the the Production record.
   */
  public void initializeProductionRecordDB() throws IOException {
    Properties prop = new Properties();
    prop.load(new FileInputStream("res/properties"));
    Date date = new Date();

    long time = date.getTime();

    Timestamp ts = new Timestamp(time);

    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/ProductDatabase";

    //  Database credentials
    final String user = "";
    final String pass = prop.getProperty("password");
    Connection conn = null;
    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      System.out.println("Inserting production records into the table...");

      textArea.setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12

      String comboNumber = comboBox
          .getValue();   // this  will allow me to pull the number from the combo box



      /*if (Objects.equals(comboNumber, p)){
        comboNumber = "0";
      }
      else {
        return comboNumber;
      }*/

      System.out.println("This is the comboNumber I choose "
          + comboNumber); // this is the test for the combo number

      Product listItems = listView.getSelectionModel()
          .getSelectedItem(); // this line will allow me to choose items from the list view

      System.out.println("This is the item on the list that I choose" + "\n"
          + listItems); // this is the test for the list Items

      int numProduced = Integer.parseInt(comboNumber); //this will come from the combobox in the UI

      int id = listItems.getId();

      String manu = listItems.getManufacturer();

      ItemType type = listItems.getType();

      int itemCount = 0;
      int numCount = 0;
      for (int productionRunProduct = 0; productionRunProduct < numProduced;
          productionRunProduct++) {
        //ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);

        // using the iterator as the product id for testing

        //textArea.appendText(pr.toString() + "\n");
        //} // 1st end brace for production run project
        // ProductionRecord pr = new ProductionRecord( 0);
        System.out.println("numProduced is " + numProduced);

        System.out.println("num count is " + numCount);

        numCount++;

        String sql =
            "INSERT INTO PRODUCTIONRECORD"
                + "(PRODUCT_ID, PRODUCTION_NUM, SERIAL_NUM,DATE_PRODUCED) "
                + " VALUES ( '" + id + "', '" + numCount + "', '" + manu.substring(0, 3)
                + type.code + "0000" + itemCount + "', '" + ts
                + "' )";  // this sql statement gets information from the
        //  text fields and choice box and loads them into the database.

        textArea.appendText(

            " Prod. Num: " + numCount + " Product ID: " + id + " Serial Num: " + manu
                .substring(0, 3)
                + type.code + "0000" + itemCount + " Date: " + ts + "\n");

        System.out.println("Inserted production records into the table...");

        System.out.println(sql);

        stmt.executeUpdate(sql);


      }
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
   * This will be a new method that updates the text area with the production record log. This
   * method reads information from the Production Record database
   */
  public void productionAreaLog() throws IOException {
    Properties prop = new Properties();
    prop.load(new FileInputStream("res/properties"));

    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/ProductDatabase";

    //  Database credentials
    final String user = "";
    final String pass = prop.getProperty("password");
    Connection conn = null;
    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCTIONRECORD";

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        // these lines correspond to the database table columns
        String prodId = rs.getString(1);

        String prodNum = rs.getString(2);

        String serialNum = rs.getString(3);

        String prodDate = rs.getString(4);

        textArea.appendText(

            " Prod. Num: " + prodNum + " Product ID: " + prodId + " Serial Num:" + serialNum
                + " Date: "
                + prodDate + "\n");

        textArea.setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12

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
   * This method initializes the database and connects the h2 driver.
   */
  @FXML

  public void initializeDB() throws IOException {
    Properties prop = new Properties();
    prop.load(new FileInputStream("res/properties"));

    String nameText = prodField1.getText();   // Gets information from production field

    String manuText = manuField2.getText();   // Gets information from Manufacturer field

    ItemType type = itemType.getValue();      // Gets information from Item Type choice box.

    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/ProductDatabase";

    //  Database credentials
    final String user = "";
    final String pass = prop.getProperty("password");
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

  @FXML
  void playMediainfo(MouseEvent event2) {

    System.out.println("Test for Media Production button"); // test button for Media Production

    testMultimedia();


  }


  /**
   * This is a method called test multimedia to demonstrate functionality in the code.
   */
  public void testMultimedia() {
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
      mediaTextArea
          .setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12
      mediaTextArea.appendText(String.valueOf(p));

    }

  }


}