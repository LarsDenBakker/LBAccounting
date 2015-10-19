package nl.larsdenbakker.accounting.product;

import java.util.UUID;
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
public class ProductRegistry extends PropertyHolderRegistry<UUID, Product> {

   private final ProductProperties PROPERTIES;

   public ProductRegistry(Module parentModule, RegistryModule registryHandler, ProductProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, UUID.class, Product.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   public ProductProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public String getDataValueDescription() {
      return "Product";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Products";
   }

   @Override
   public String getKey() {
      return "products";
   }

   public static ProductRegistry createAndInitializeRegistry(ProductModule module) throws DataFileException, PropertyHolderCreationException {
      ProductProperties properties = ProductProperties.create(module.getPropertyModule(), module, "product-properties.yml");
      return ProductRegistry.createAndInitializeRegistry(ProductRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "product-registry.yml");
   }
}
