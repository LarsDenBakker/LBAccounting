package nl.larsdenbakker.accounting.order.category;

import java.util.UUID;
import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.property.PropertyHolder;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class OrderCategory extends PropertyHolder<UUID> {

   public final OrderCategoryProperties PROPERTIES;

   public OrderCategory(Module parentModule, ConversionModule conversionHandler, Storage storage, OrderCategoryProperties PROPERTIES) {
      super(parentModule, conversionHandler, storage);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public OrderCategoryProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public UUID getKey() {
      return getPropertyValue(PROPERTIES.UUID);
   }

   @Override
   public String getDescription() {
      return getPropertyValue(PROPERTIES.NAME);
   }
}
