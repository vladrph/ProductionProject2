package productionproject;

import static productionproject.ItemType.AUDIO;
import static productionproject.ItemType.AUDIO_MOBILE;
import static productionproject.ItemType.NULL;
import static productionproject.ItemType.VISUAL;
import static productionproject.ItemType.VISUAL_MOBILE;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
  private ComboBox<Integer> comboBox = new ComboBox<>();


  @FXML
  private Button productButton;


  @FXML
  private Button recordButton;

  @FXML
  private TableView<Product> productTableView;

  @FXML
  ObservableList<Product> productLine = FXCollections
      .observableArrayList();
  @FXML
  private TableColumn<?, ?> productNameCol;
  @FXML
  private TableColumn<?, ?> manufactureNameCol;
  @FXML
  private TableColumn<?, ?> typeNameCol;

  @FXML
  private TextField productInputField;

  @FXML
  private TextField manufactureInputField;
  @FXML
  private TextField employeeNameField;

  @FXML
  private PasswordField passwordInputField;
  @FXML
  private TextArea textArea;

  @FXML

  ObservableList<Product> produceline = FXCollections.observableArrayList();
  @FXML
  private
  ListView<Product> listView = new ListView<>(produceline);
  @FXML
  private TextArea mediaTextArea;
  @FXML
  private TextArea employeeTextArea;

  @FXML
  private Button mediaButton;
  private MouseEvent event;

  Database database = new Database();

  void retrieveInputDetails() {
    productInputField.getText();
    manufactureInputField.getText();
    itemType.getValue();
  }

  void addToProductLine() {
    productLine.add(new Widget(productInputField.getText(), manufactureInputField.getText(),
        itemType.getValue()));
  }

  void addToProductDB() throws IOException, SQLException {
    database.openDatabase();
    database.stmt.executeUpdate(
        "INSERT INTO PRODUCT" + "(NAME, TYPE, MANUFACTURER)" + "VALUES ('" + productInputField
            .getText() + "', '" + itemType.getValue() + "','" + manufactureInputField.getText()
            + "', ) ");
    database.closeDatabase();
  }

  void addToListViewProduceLine() {
    produceline.add(new Widget(productInputField.getText(), manufactureInputField.getText(),
        itemType.getValue()));
  }

  void clearInputField() {
    productInputField.setText("");   // clears text field

    manufactureInputField.setText("");  // clears text field

    itemType.setValue(null); // clears Choice Box field
  }

  /**
   * This method prints " Test for button" for the print product button.
   *
   * @param event This method currently prints to the console and establishes a Database
   *              connection.
   */
  @FXML
  void addProductButton(MouseEvent event) throws IOException, SQLException {
    this.event = event;
    addToProductDB();
    retrieveInputDetails();
    addToProductLine();
    addToListViewProduceLine();
    clearInputField();
  }
  /**
   * This will be a new method to receive the Employee details.
   */
  void formatEmpTextArea() {

    employeeTextArea.clear();

    employeeTextArea
        .setFont(new Font("Serif", 12));
  }
  void cleanUpInputField() {
    passwordInputField.clear();
    employeeNameField.clear();
  }

  void createEmployee() {
    String name = employeeNameField.getText();

    String passWord = passwordInputField.getText();

    if (employeeNameField.getText().equals("") || passwordInputField.getText().equals("")) {
      name = "ERROR";
      passWord = "ERROR";
    }

    Employee employee = new Employee(name, passWord);

    employeeTextArea.appendText(employee.toString());
  }
  /**
   * New Employee method that print out the employee information.
   */
  @FXML
  void getEmployeeInfo(MouseEvent event3) {
    this.event = event3;
    formatEmpTextArea();
    createEmployee();
    cleanUpInputField();
  }
  /**
   * This method prints the Production log info to the console.
   */

  void formatTextArea() {
    textArea.setFont(new Font("Serif", 12));
  }

  void productionTest() {
    int comboNumber = Integer.parseInt(String
        .valueOf(comboBox.getValue()));

    Product listItems = listView.getSelectionModel()
        .getSelectedItem();

    Product productProduced = new Widget(listItems.getId(), listItems.getName(),
        listItems.getManufacturer(), listItems.getType());

    int itemCount = 0;

    for (int productionRunProduct = 0; productionRunProduct < comboNumber; productionRunProduct++) {

      ProductionRecord productLog = new ProductionRecord(listItems.getId(), productProduced,
          itemCount++);

      System.out.println(productLog.toString());

    }
  }

  public ComboBox<Integer> getComboBox() {
    return comboBox;
  }

  public void setComboBox(ComboBox<Integer> comboBox) {
    this.comboBox = comboBox;
  }

  void retrieveComboBoxDetails() {
    int comboNumber = Integer.parseInt(String
        .valueOf(comboBox.getValue()));

    if (comboNumber < 0 || comboNumber > 10) {
      comboBox.setValue(0);
    }
  }

  public void productionLog() {
    formatTextArea();
    retrieveComboBoxDetails();
    productionTest();
  }

  /**
   * This method initialize runs automatically as the program is started. This method populates
   * numbers to the comboBox This method populates enum values into the combo box.
   */

  void setCellFactory() {
    productNameCol.setCellValueFactory(new PropertyValueFactory("name"));

    manufactureNameCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));

    typeNameCol.setCellValueFactory(new PropertyValueFactory("type"));
  }

  void comboBoxValues() {
    for (int i = 0; i <= 10; i++) {
      comboBox.getItems().add(i);
    }
    comboBox.setValue(5);
  }

  void setItemValues() {
    itemType.getItems()
        .addAll(AUDIO, ItemType.VISUAL, ItemType.AUDIO_MOBILE, ItemType.VISUAL_MOBILE);

    productTableView.setItems(productLine);
    listView.setItems(produceline);
  }

  public void initialize() throws IOException {
    comboBoxValues();
    setItemValues();
    populateList();
    setCellFactory();
    productionAreaLog();
  }
  /**
   * This method populate list is currently an empty method that will be used populate information
   * from the database.
   */

  public void populateList() throws IOException {

    database.getProp().load(new FileInputStream("res/properties"));

    try (OutputStream output = new FileOutputStream("res/properties")) {
      database.getProp().store(output, null);
      database.openDatabase();
      database.stmt.executeQuery("SELECT * FROM PRODUCT");
      ResultSet rs = database.getStmt().executeQuery("SELECT * FROM PRODUCT");

      while (rs.next()) {

        String name = rs.getString(2);

        String manufacturer = rs.getString(4);

        String type = rs.getString(3);

        ItemType temp;

        switch (type) {
          case "AUDIO":

            temp = AUDIO;

            break;
          case "VISUAL":

            temp = VISUAL;

            break;
          case "AUDIO_MOBILE":

            temp = AUDIO_MOBILE;

            break;
          case "VISUAL_MOBILE":

            temp = VISUAL_MOBILE;

            break;
          default:
            temp = NULL;

            break;
        }
        if (manufacturer.equals("")) {

          manufacturer = "ERROR";

        }
        if (rs.getString(2).equals("")) {

          name = "ERROR";
        }
        String id = rs.getString(1);

        Product productFromDB = new Widget(Integer.parseInt(id), name, manufacturer,
            temp);

        productLine.add(productFromDB);

        listView.getItems().add(productFromDB);
      }
      database.closeDatabase();
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
  void recordProductionButton(MouseEvent event1) throws IOException {
    productionLog();
    initializeProductionRecordDB();
  }
  /**
   * This method initializes the combo box.
   */
  @FXML
  public void initializeValue() {

    comboBox.setEditable(true);  // creates an editable comboBox
  }
  /**
   * created a new ProductionRecord Database to store information the the Production record.
   */

  public void initializeProductionRecordDB() throws IOException {

    database.openDatabase();
    database.getProp().load(new FileInputStream("res/properties"));

    try {

      formatTextArea();

      retrieveComboBoxDetails();

      Product listItems = listView.getSelectionModel()

          .getSelectedItem();

      ProductionRecord product = new ProductionRecord(listItems.getId());

      int numCount = 0;
      for (int productionRunProduct = 0; productionRunProduct < Integer.parseInt(String
          .valueOf(comboBox.getValue()));
          productionRunProduct++) {

        database.stmt.executeUpdate(
            "INSERT INTO PRODUCTIONRECORD"
                + "(PRODUCT_ID, PRODUCTION_NUM, SERIAL_NUM,DATE_PRODUCED)" + "VALUES ( '"
                + listItems.getId() + "', '" + numCount + "', '" + listItems.getManufacturer()
                .substring(0, 3)
                + listItems.getType().code + "0000" + product.itemCount + "', '" + product.ts
                + "' )");

        numCount++;
        textArea.appendText(

            " Prod. Num: " + numCount + " Product ID: " + listItems.getId() + " Serial Num: "
                + listItems.getManufacturer()
                .substring(0, 3)
                + listItems.getType().code + "0000" + product.itemCount + " Date: " + product.ts
                + "\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  /**
   * This will be a new method that updates the text area with the production record log. This
   * method reads information from the Production Record database
   */

  public void productionAreaLog() throws IOException {

    try {

      database.openDatabase();

      String sql = "SELECT * FROM PRODUCTIONRECORD";

      ResultSet rs = database.getStmt().executeQuery(sql);

      while (rs.next()) {

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
      database.closeDatabase();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void playMediainfo(MouseEvent event2) {
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
          .setFont(new Font("Serif", 12));
      mediaTextArea.appendText(String.valueOf(p));
    }
  }
}