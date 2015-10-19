package nl.larsdenbakker.accounting.core;

import nl.larsdenbakker.accounting.AccountingApplication;
import nl.larsdenbakker.accounting.core.tax.TaxCategoryRegistry;
import nl.larsdenbakker.app.AbstractModule;
import nl.larsdenbakker.app.UserInputException;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.datafile.DataFileModule;
import nl.larsdenbakker.property.PropertyModule;
import nl.larsdenbakker.registry.RegistryModule;
import nl.larsdenbakker.operation.OperationFactory;
import nl.larsdenbakker.operation.OperationModule;
import nl.larsdenbakker.operation.command.CommandFactory;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class CoreModule extends AbstractModule {

   private TaxCategoryRegistry taxCategoryRegistry;

   public CoreModule(AccountingApplication app) {
      super(app);
   }

   @Override
   protected void _load() throws UserInputException {
      OperationFactory.registerProcedures(this, getOperationModule(), "operations.yml");
      CommandFactory.registerCommands(this, getOperationModule(), "commands.yml");

      taxCategoryRegistry = TaxCategoryRegistry.createAndInitializeRegistry(this);
   }

   @Override
   protected void _saveToDisk() throws UserInputException {
      taxCategoryRegistry.saveToDisk();
   }

   @Override
   public AccountingApplication getParentApplication() {
      return (AccountingApplication) super.getParentApplication(); //Safe cast ensured by constructor
   }

   public ConversionModule getConversionModule() {
      return getParentApplication().getConversionModule();
   }

   public PropertyModule getPropertyModule() {
      return getParentApplication().getPropertyModule();
   }

   public OperationModule getOperationModule() {
      return getParentApplication().getOperationModule();
   }

   public RegistryModule getRegistryModule() {
      return getParentApplication().getRegistryModule();
   }

   public DataFileModule getDataFileModule() {
      return getParentApplication().getDataFileModule();
   }

   public TaxCategoryRegistry getTaxCategoryRegistry() {
      return taxCategoryRegistry;
   }

   @Override
   public String getName() {
      return "core";
   }

}
