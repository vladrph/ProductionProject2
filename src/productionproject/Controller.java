package productionproject;

import static productionproject.ItemType.AUDIO;

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
  private TableColumn<Product, ItemType> typeNameCol;
  @FXML
  private TextField prodField1; // change name of product field

  @FXML
  private TextField manuField2; // change name of manu field
  @FXML
  private TextArea textArea;

  //private Product productProduced;

  /**
   * This method prints " Test for button" for the print product button.
   *
   * @param event This method currently prints to the console and establishes a Database
   *              connection.
   */
  @FXML
  void addproduct(MouseEvent event) {

    System.out.println("Test for button for Add Product"); // Text button for print product
    //populateList();
    String nameText = prodField1.getText();   // Gets information from production field
    String manuText = manuField2.getText();   // Gets information from Manufacturer field
    ItemType type = itemType.getValue();      // Gets a value from the item type field

    productLine.add(new Widget(nameText, manuText, type));
    productionLog();
    initializeDB();        //establish the database connection

    prodField1.setText("");   // clears text field
    manuField2.setText("");  // clears text field
    itemType.setValue(null); // clears Choice Box field
  }

  /**
   * This method will show the production log in the Production Log tab TextArea.
   */
  public void productionLog() {

    String nameText = prodField1.getText();
    String manuText = manuField2.getText();
    ItemType type = itemType.getValue();
    textArea.setFont(new Font("Serif", 12)); // sets text area font to Serif and font size to 12
    // textArea.appendText(nameText + "  " + manuText + "  " + type + "\n"); // adds information of

    Product productProduced = new Widget(nameText, manuText, type);

    // test constructor used when creating production records from user interface
    int numProduced = 1;  // this will come from the combobox in the UI
    int itemCount = 0;

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
      // using the iterator as the product id for testing
      System.out.println(pr.toString());
      textArea.appendText(pr.toString());
    }


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
        String name = rs.getString(2);
        String manufacturer = rs.getString(4);
        String type = rs.getString(3); // itemType.getValue();

        // create object

        Widget productFromDB = new Widget(name, manufacturer, itemType.getValue());

        // save to observable list
        productLine.add(productFromDB);

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
   * This method prints " Test for Record Prodution button" This methods prints to the console to
   * ensure that the method and button is working properly.
   *
   * @param event1 This method currently * prints to the console
   */
  @FXML
  void printproduct2(MouseEvent event1) {

    System.out.println("Test for Record Production button"); // test button for Record Prodution

  }

  /**
   * This method intitializes a value of 1-10 to the combo box.
   */
  @FXML
  public void initializeValue() { // adds value 1- 10 to to a combo box

    comboBox.setEditable(true);  // creates an editable comboBox
    comboBox.getSelectionModel().selectFirst(); // This line prints numbers out 2 times

  }

  /**
   * This method initalizes the database and connects the h2 driver.
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

      // String sql = "INSERT INTO Product" + "(type, manufacturer, name) "
      //  + " VALUES ( 'AUDIO', 'Apple', 'Iphone 11 Pro' )";

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