package nl.larsdenbakker.accounting.product.category;

import java.util.UUID;
import nl.larsdenbakker.accounting.product.ProductModule;
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
public class ProductCategoryRegistry extends PropertyHolderRegistry<UUID, ProductCategory> {

   private final ProductCategoryProperties PROPERTIES;

   public ProductCategoryRegistry(Module parentModule, RegistryModule registryHandler, ProductCategoryProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, UUID.class, ProductCategory.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   public ProductCategoryProperties getProperties() {
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
      return "Product Category";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Product Categories";
   }

   @Override
   public String getKey() {
      return "product-categories";
   }

   public static ProductCategoryRegistry createAndInitializeRegistry(ProductModule module) throws DataFileException, PropertyHolderCreationException {
      ProductCategoryProperties properties = ProductCategoryProperties.create(module.getPropertyModule(), module, "product-category-properties.yml");
      return ProductCategoryRegistry.createAndInitializeRegistry(ProductCategoryRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "product-category-registry.yml");
   }
}
