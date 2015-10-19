package nl.larsdenbakker.accounting.order.customer;

import java.util.UUID;
import nl.larsdenbakker.accounting.order.OrderModule;
import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.datafile.DataFile;
import nl.larsdenbakker.datafile.DataFileException;
import nl.larsdenbakker.property.PropertyHolderCreationException;
import nl.larsdenbakker.property.PropertyHolderRegistry;
import nl.larsdenbakker.registry.RegistryModule;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class CustomerRegistry extends PropertyHolderRegistry<UUID, Customer> {

   private final CustomerProperties PROPERTIES;

   public CustomerRegistry(Module parentModule, RegistryModule registryHandler, CustomerProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, UUID.class, Customer.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   public CustomerProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public String getDataValueDescription() {
      return "Customer";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Customers";
   }

   @Override
   public String getKey() {
      return "customers";
   }

   public static CustomerRegistry createAndInitializeRegistry(OrderModule module) throws DataFileException, PropertyHolderCreationException {
      CustomerProperties properties = CustomerProperties.create(module.getPropertyModule(), module, "customer-properties.yml");
      return PropertyHolderRegistry.createAndInitializeRegistry(CustomerRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "customer-registry.yml");
   }
}
