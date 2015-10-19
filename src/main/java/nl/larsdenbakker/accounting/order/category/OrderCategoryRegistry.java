package nl.larsdenbakker.accounting.order.category;

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
public class OrderCategoryRegistry extends PropertyHolderRegistry<UUID, OrderCategory> {

   private final OrderCategoryProperties PROPERTIES;

   public OrderCategoryRegistry(Module parentModule, RegistryModule registryHandler, OrderCategoryProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, UUID.class, OrderCategory.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public OrderCategoryProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public void loadDataFile() throws DataFileException, PropertyHolderCreationException {
      super.loadDataFile();
      if (getByDescription("uncategorized") == null) {
         createAndRegister("uncategorized");
      }
   }

   @Override
   public String getDataValueDescription() {
      return "Order Category";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Order Categories";
   }

   @Override
   public String getKey() {
      return "order-categories";
   }

   public static OrderCategoryRegistry createAndInitializeRegistry(OrderModule module) throws DataFileException, PropertyHolderCreationException {
      OrderCategoryProperties properties = OrderCategoryProperties.create(module.getPropertyModule(), module, "order-category-properties.yml");
      return OrderCategoryRegistry.createAndInitializeRegistry(OrderCategoryRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "order-category-registry.yml");
   }
}
