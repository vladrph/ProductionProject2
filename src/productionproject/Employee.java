package productionproject;

import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  private void setEmail() {
    int spacePos = name.indexOf(" ");

    int startPos = name.indexOf(String.valueOf(0));

    String firstName = name.substring(startPos + 1, spacePos + 1);
    String lastName = name.substring(spacePos + 1, name.length());
    if (checkName()) {
      email = firstName.toLowerCase().trim() + "." + lastName.toLowerCase() + "@oracleacademy.Test";
    }


  }

  private void setUserName() {
    String firstInitial = String.valueOf(name.charAt(0));
    int spacePos = name.indexOf(" ");
    String lastName = name.substring(spacePos + 1, name.length());
    this.userName = firstInitial.toLowerCase() + lastName.toLowerCase();

  }

  private boolean isValidPassword() {

    Pattern p = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*[!@#$%^&*])");
    Matcher m = p.matcher(password);
    if (m.find()) {      // gets the result of what I just matched
      return m.matches();
    } else {
      password = "pw";
      return false;

    }
  }

  @Override
  public String toString() {
    return "\nEmployee Details"
        + "\nName :" + name
        + "\nUsername :" + userName
        + "\nEmail :" + email
        + "\nInitial Password :" + password;

  }


}

