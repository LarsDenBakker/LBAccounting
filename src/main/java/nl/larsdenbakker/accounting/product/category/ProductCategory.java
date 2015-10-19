package nl.larsdenbakker.accounting.product.category;

import java.util.UUID;
import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.property.PropertyHolder;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class ProductCategory extends PropertyHolder<UUID> {

   public final ProductCategoryProperties PROPERTIES;

   public ProductCategory(Module parentModule, ConversionModule conversionHandler, Storage storage, ProductCategoryProperties PROPERTIES) {
      super(parentModule, conversionHandler, storage);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public ProductCategoryProperties getProperties() {
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
