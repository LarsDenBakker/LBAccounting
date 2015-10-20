package nl.larsdenbakker.accounting.order;

import java.util.List;
import nl.larsdenbakker.accounting.AccountingApplication;
import nl.larsdenbakker.accounting.order.category.OrderCategoryRegistry;
import nl.larsdenbakker.accounting.order.customer.CustomerRegistry;
import nl.larsdenbakker.app.AbstractModule;
import nl.larsdenbakker.app.UserInputException;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.datafile.DataFileModule;
import nl.larsdenbakker.property.PropertyModule;
import nl.larsdenbakker.registry.RegistryModule;
import nl.larsdenbakker.operation.OperationModule;
import nl.larsdenbakker.operation.command.Command;
import nl.larsdenbakker.operation.command.CommandFactory;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class OrderModule extends AbstractModule {

   private OrderRegistry orderRegistry;
   private OrderCategoryRegistry orderCategoryRegistry;
   private CustomerRegistry customerRegistry;
   private List<Command> commands;

   public OrderModule(AccountingApplication app) {
      super(app);
   }

   @Override
   protected void _load() throws UserInputException {
      CommandFactory.createAndRegisterCommands(this, getOperationModule(), "commands.yml");

      orderCategoryRegistry = OrderCategoryRegistry.createAndInitializeRegistry(this);
      orderRegistry = OrderRegistry.createAndInitializeRegistry(this);
      customerRegistry = CustomerRegistry.createAndInitializeRegistry(this);
   }

   @Override
   protected void _saveToDisk() throws UserInputException {
      orderRegistry.saveToDisk();
      orderCategoryRegistry.saveToDisk();
      customerRegistry.saveToDisk();
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

   public OrderRegistry getOrderRegistry() {
      return orderRegistry;
   }

   public OrderCategoryRegistry getOrderCategoryRegistry() {
      return orderCategoryRegistry;
   }

   public CustomerRegistry getCustomerRegistry() {
      return customerRegistry;
   }

   @Override
   public String getName() {
      return "order";
   }

}
