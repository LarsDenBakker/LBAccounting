package nl.larsdenbakker.accounting.order;

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
public class OrderRegistry extends PropertyHolderRegistry<Integer, Order> {

   private final OrderProperties PROPERTIES;

   public OrderRegistry(Module parentModule, RegistryModule registryHandler, OrderProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, Integer.class, Order.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   public OrderProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public String getDataValueDescription() {
      return "Order";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Orders";
   }

   @Override
   public String getKey() {
      return "orders";
   }

   public static OrderRegistry createAndInitializeRegistry(OrderModule module) throws DataFileException, PropertyHolderCreationException {
      OrderProperties properties = OrderProperties.create(module.getPropertyModule(), module, "order-properties.yml");
      return OrderRegistry.createAndInitializeRegistry(OrderRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "order-registry.yml");
   }

}
