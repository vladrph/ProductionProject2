package productionproject;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

/**
 * This document contains the Employee information for the Production Project 2 GUI project. This
 * class has th ability to create a user name for the user, validate a password and generate a
 * custom email address for the user.
 *
 * @author Vladimir Petit-Homme
 */
public class Employee {

  StringBuilder name;
  String userName;
  String password;
  String email;


  StringBuilder sb = new StringBuilder();

  Employee(String name, String password) {

    this.name = sb.append(name);

    this.password = password;

    isValidPassword();

    checkName();

    setUserName();

    setEmail();

  }  // end braces for employee constructor

  /**
   * This method verifies if the user name contains a space. If it does it sets the correct name. If
   * it does not it returns a default name and email address.
   *
   * @return returns the name if it is valid otherwise it returns default values.
   */

  private boolean checkName() {

    Pattern p = Pattern.compile("\\s");

    Matcher m = p.matcher(name);

    if (m.find()) {

      setUserName();

      return true;

    } else {

      userName = "default";

      email = "user@oracleacademy.Test";

      return false;
    }

  }

  /**
   * This method creates an email address for the user if the user enters a valid name.
   */
  private void setEmail() {
    int spacePos = name.indexOf(" ");

    int startPos = name.indexOf(String.valueOf(0));

    String firstName = name.substring(startPos + 1, spacePos + 1);

    String lastName = name.substring(spacePos + 1, name.length());

    if (checkName()) {

      email = firstName.toLowerCase().trim() + "." + lastName.toLowerCase() + "@oracleacademy.Test";

    }


  }

  /**
   * This method set a user name for the user if the user enters a valid name.
   */
  private void setUserName() {

    String firstInitial = String.valueOf(name.charAt(0));

    int spacePos = name.indexOf(" ");

    String lastName = name.substring(spacePos + 1, name.length());

    this.userName = firstInitial.toLowerCase() + lastName.toLowerCase();

  }

  /**
   * This method verifies if the password that was entered contains the appropriate parameters.
   *
   * @return If the parameters are correct it returns the password otherwise it returns the "pw".
   *
   */
  private boolean isValidPassword() {

    Pattern p = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*[!@#$%^&*])");

    Matcher m = p.matcher(password);

    if (m.find()) {      // gets the result of what I just matched

      return m.matches();

    } else {

      password = "wp";

      return Boolean.parseBoolean(password);

    }
  }


  /**
   * This method reverse the string of a password for the user to protect user information.
   *
   * @param id String information of the user password.
   * @return recursively reverse's the password.
   */
  public String reverseString(String id) {

    if (id.length() <= 1) {
      return id;
    } else {
      return reverseString(id.substring(1)) + id.charAt(0);
    }
  }

  /**
   * To string method to generate the Employee Details.
   *
   * @return Name, Username, Email and Password.
   */
  @Override
  public String toString() {
    return "\nEmployee Details"
        + "\nName : " + name
        + "\nUsername : " + userName
        + "\nEmail : " + email
        + "\nInitial Password : " + reverseString(password);

  }


}

