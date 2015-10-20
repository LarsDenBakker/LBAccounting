package nl.larsdenbakker.accounting.product;

import nl.larsdenbakker.accounting.AccountingApplication;
import nl.larsdenbakker.accounting.product.category.ProductCategoryRegistry;
import nl.larsdenbakker.app.AbstractModule;
import nl.larsdenbakker.app.UserInputException;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.datafile.DataFileModule;
import nl.larsdenbakker.property.PropertyModule;
import nl.larsdenbakker.registry.RegistryModule;
import nl.larsdenbakker.operation.OperationModule;
import nl.larsdenbakker.operation.command.CommandFactory;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class ProductModule extends AbstractModule {

   private ProductRegistry productRegistry;
   private ProductCategoryRegistry productCategoryRegistry;

   public ProductModule(AccountingApplication app) {
      super(app);
   }

   @Override
   protected void _load() throws UserInputException {
      CommandFactory.createAndRegisterCommands(this, getOperationModule(), "commands.yml");

      productCategoryRegistry = ProductCategoryRegistry.createAndInitializeRegistry(this);
      productRegistry = ProductRegistry.createAndInitializeRegistry(this);
   }

   @Override
   protected void _saveToDisk() throws UserInputException {
      productRegistry.saveToDisk();
      productCategoryRegistry.saveToDisk();
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

   public ProductCategoryRegistry getProductCategoryRegistry() {
      return productCategoryRegistry;
   }

   public ProductRegistry getProductRegistry() {
      return productRegistry;
   }

   @Override
   public String getName() {
      return "product";
   }

}
