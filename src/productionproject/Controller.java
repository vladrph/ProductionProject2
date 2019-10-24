package productionproject;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This controller document has the contains the button and combo box events for the Sprint1 GUI
 * project.
 *
 * @author Vladimir Petit-Homme
 */
public class Controller {

@FXML
private ChoiceBox<ItemType> itemType;


  @FXML
  private ComboBox<String> comboBox = new ComboBox<>();


  // @FXML
  // private TextArea ta = new TextArea();


  @FXML
  private Button productButton;


  @FXML
  private Button recordButton;


  /**
   * This method prints " Test for button" for the print product button.
   *
   * @param event This method currently prints to the console and establishes a Database
   *              connection.
   */
  @FXML
  void printproduct(MouseEvent event) {

    System.out.println("Test for button"); // Text button for print product

    initializeDB();        //establish the database connection


  }

public void initialize(){

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

 itemType.getItems().addAll(ItemType.AUDIO,ItemType.VISUAL,ItemType.AUDIO_MOBILE,ItemType.VISUAL_MOBILE);



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


}