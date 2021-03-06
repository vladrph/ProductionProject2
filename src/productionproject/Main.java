/**
 * Main class for project No current edits made in this class.
 *
 * @authur Vladimir Petit-Homme
 */

package productionproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Product Line");
    primaryStage.setScene(new Scene(root, 870, 670));
    primaryStage.show();




  }



  public static void main(String[] args) {
    launch(args);
  }
}
