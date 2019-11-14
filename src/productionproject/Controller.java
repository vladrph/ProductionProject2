package productionproject;


import static productionproject.ItemType.AUDIO;
import static productionproject.ItemType.AUDIO_MOBILE;
import static productionproject.ItemType.VISUAL;
import static productionproject.ItemType.VISUAL_MOBILE;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.text.Font;


/**
 * This controller document has the contains the button and combo box events for the Sprint1 GUI
 * project.
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
  ObservableList<Product> productLine = FXCollections.observableArrayList();

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

  /**
   * This method prints " Test for button" for the print product button.
   *
   * @param event This method currently prints to the console and establishes a Database
   *              connection.
   */
  @FXML
  void addproduct(MouseEvent event) {

    System.out.println("Test for button for Add Product"); // Text button for print product
    populateList();
    String nameText = prodField1.getText();
    String manuText = manuField2.getText();
    ItemType type = itemType.getValue();

    productLine.add(new Widget(nameText, manuText, type));
    productionLog();
    //initializeDB();        //establish the database connection

  }
  public void productionLog(){                  // This method will show the production log in the Production Log tab TextArea

    String nameText = prodField1.getText();
    String manuText = manuField2.getText();
    ItemType type = itemType.getValue();
    textArea.setFont(new Font("Serif",  12));
    textArea.appendText(nameText +"  "+ manuText +"  "+type+"\n");

  }

  public void initialize() {

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
    //populateList();
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


  public void populateList() {

    //productLine.add(new Widget(nameText, manuText, type));
    //productLine.add(new Widget(nameText, manuText, type));
    //productLine.add(new Widget(nameText, manuText, type));

    //ItemType  =  itemType.getValue();

    //prodNameCol.setText(nameText);
    // manuNameCol.setText(manuText);

    // productTableView.setItems(productLine);
    //productLine.add(new Widget(txtField1.setText(nameText),txtField2.setText(manuText),itemType.getValue()) {
    //              }
    //  Product p1 = new Widget(nameText, manuText, itemType.getValue());

    //  productLine.add(p1);

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
    //comboBox.getSelectionModel().selectFirst(); // This line prints numbers out 2 times

  }

  /**
   * This method initalizes the database and connects the h2 driver.
   */
  @FXML

  public void initializeDB() {

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
          + " VALUES ( 'AUDIO', 'Apple', 'Iphone 11 Pro' )";

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