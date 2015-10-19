package nl.larsdenbakker.accounting;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import nl.larsdenbakker.app.UserInputException;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class Main extends Application {

   private static AccountingApplication app;
   private static Main inst;

   public static void main(String[] args) {
      try {
         app = new AccountingApplication();
         app.load();
         Application.launch(args);
      } catch (Exception ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "An exception occurred during exception", ex);
         Platform.exit();
      }
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      inst = this;
      app.getRegistryGUIModule().addToStage(primaryStage);
   }

   @Override
   public void stop() throws UserInputException {
      app.saveToDisk();
      app.shutdown();
   }

}
