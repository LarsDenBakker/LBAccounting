package nl.larsdenbakker.accounting.bill;

import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.property.PropertyHolder;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class Bill extends PropertyHolder<Integer> {

   public final BillProperties PROPERTIES;

   public Bill(Module parentModule, ConversionModule conversionHandler, Storage storage, BillProperties PROPERTIES) {
      super(parentModule, conversionHandler, storage);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public BillProperties getProperties() {
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
