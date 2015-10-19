package nl.larsdenbakker.accounting.order;

import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.property.PropertyHolder;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class Order extends PropertyHolder<Integer> {

   public final OrderProperties PROPERTIES;

   public Order(Module parentModule, ConversionModule conversionHandler, Storage storage, OrderProperties PROPERTIES) {
      super(parentModule, conversionHandler, storage);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public OrderProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public Integer getKey() {
      return getPropertyValue(PROPERTIES.INDEX);
   }

   @Override
   public String getDescription() {
      return getKey().toString();
   }

}
