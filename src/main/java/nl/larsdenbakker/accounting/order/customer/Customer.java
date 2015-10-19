package nl.larsdenbakker.accounting.order.customer;

import java.util.UUID;
import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.property.PropertyHolder;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class Customer extends PropertyHolder<UUID> {

   public final CustomerProperties PROPERTIES;

   public Customer(Module parentModule, ConversionModule conversionHandler, Storage storage, CustomerProperties PROPERTIES) {
      super(parentModule, conversionHandler, storage);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public CustomerProperties getProperties() {
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
