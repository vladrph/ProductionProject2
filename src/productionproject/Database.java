package productionproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
This  class creates a Database obejct using a  singleton design pattern
@author Vladimir Petit-Homme
 */

public class Database {

  Properties prop = new Properties();
  private static Database instance;
  final String jdbc_driver = "org.h2.Driver";
  final String db_url = "jdbc:h2:./res/ProductDatabase";
  final String user = "";
  final String pass = prop.getProperty("password");
  Connection conn = null;
  Statement stmt = null;
  String sql;


  public void openDatabase() throws IOException {



    try {

      Class.forName(jdbc_driver);

      this.conn = DriverManager.getConnection(db_url, user, pass);

      stmt = conn.createStatement();

      System.out.println("Open Database");


    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();

    }


  }

  public Properties getProp() {
    return prop;
  }

  public Connection getConn() {

    return conn;
  }

  public Statement getStmt() {
    return stmt;
  }

  public static Database getInstance() throws SQLException {

    if (instance == null) {
      instance = new Database();
    } else if (instance.getConn().isClosed()) {
      instance = new Database();
    }

    return instance;

  }

  public void closeDatabase() {

    try {


      stmt.close();
      conn.close();
      System.out.println("Closed Database ");
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  public ResultSet queryExecute(String sqlString) {
    try {
      PreparedStatement stmt = getConn().prepareStatement(sqlString);
      return queryExecute(sqlString);
    }
    catch (SQLException e) {

      return null;
    }
  }





}
