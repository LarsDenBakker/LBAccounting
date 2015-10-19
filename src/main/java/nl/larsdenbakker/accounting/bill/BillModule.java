package nl.larsdenbakker.accounting.bill;

import nl.larsdenbakker.accounting.AccountingApplication;
import nl.larsdenbakker.accounting.bill.category.BillCategoryRegistry;
import nl.larsdenbakker.accounting.bill.paymentsource.PaymentSourceRegistry;
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
public class BillModule extends AbstractModule {

   private BillRegistry billRegistry;
   private BillCategoryRegistry billCategoryRegistry;
   private PaymentSourceRegistry paymentSourceRegistry;

   public BillModule(AccountingApplication app) {
      super(app);
   }

   @Override
   protected void _load() throws UserInputException {
      CommandFactory.registerCommands(this, getOperationModule(), "commands.yml");

      paymentSourceRegistry = PaymentSourceRegistry.createAndInitializeRegistry(this);
      billCategoryRegistry = BillCategoryRegistry.createAndInitializeRegistry(this);
      billRegistry = BillRegistry.createAndInitializeRegistry(this);
   }

   @Override
   protected void _saveToDisk() throws UserInputException {
      billRegistry.saveToDisk();
      billCategoryRegistry.saveToDisk();
      paymentSourceRegistry.saveToDisk();
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

   public BillRegistry getBillRegistry() {
      return billRegistry;
   }

   public PaymentSourceRegistry getPaymentSourceRegistry() {
      return paymentSourceRegistry;
   }

   public BillCategoryRegistry getBillCategoryRegistry() {
      return billCategoryRegistry;
   }

   @Override
   public String getName() {
      return "bill";
   }

}
