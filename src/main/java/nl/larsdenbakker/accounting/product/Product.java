package nl.larsdenbakker.accounting.product;

import java.util.UUID;
import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.property.PropertyHolder;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class Product extends PropertyHolder<UUID> {

   public final ProductProperties PROPERTIES;

   public Product(Module parentModule, ConversionModule conversionHandler, Storage storage, ProductProperties PROPERTIES) {
      super(parentModule, conversionHandler, storage);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public ProductProperties getProperties() {
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
