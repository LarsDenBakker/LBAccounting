package nl.larsdenbakker.accounting.bill.paymentsource;

import java.util.UUID;
import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.property.PropertyHolder;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class PaymentSource extends PropertyHolder<UUID> {

   public final PaymentSourceProperties PROPERTIES;

   public PaymentSource(Module parentModule, ConversionModule conversionHandler, Storage storage, PaymentSourceProperties PROPERTIES) {
      super(parentModule, conversionHandler, storage);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public PaymentSourceProperties getProperties() {
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
