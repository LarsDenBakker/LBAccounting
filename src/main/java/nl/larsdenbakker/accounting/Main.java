package nl.larsdenbakker.accounting;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.Stage;
import nl.larsdenbakker.app.UserInputException;

/**
 * TODO: Need to do more research into how the JavaFX Application.launch() works.
 * It needs to be separate from Main.
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class Main extends javafx.application.Application {

   private static AccountingApplication app;

   public static void main(String[] args) {
      try {
         app = new AccountingApplication();
         app.load();
         javafx.application.Application.launch(args);
      } catch (Exception ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "An exception occurred during exception", ex);
         Platform.exit();
      }
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      app.getRegistryGUIModule().addToStage(primaryStage);
   }

   @Override
   public void stop() throws UserInputException {
      app.saveToDisk();
      app.shutdown();
   }

}
