package nl.larsdenbakker.accounting.core.tax;

import java.util.UUID;
import nl.larsdenbakker.accounting.core.CoreModule;
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
public class TaxCategoryRegistry extends PropertyHolderRegistry<UUID, TaxCategory> {

   private final TaxCategoryProperties PROPERTIES;

   public TaxCategoryRegistry(Module parentModule, RegistryModule registryModule, TaxCategoryProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryModule, UUID.class, TaxCategory.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public TaxCategoryProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public String getDataValueDescription() {
      return "Tax Category";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Tax Categories";
   }

   @Override
   public String getKey() {
      return "tax-categories";
   }

   @Override
   public void loadDataFile() throws DataFileException, PropertyHolderCreationException {
      super.loadDataFile();
      if (getByDescription("uncategorized") == null) {
         createAndRegister("uncategorized");
      }
   }

   public static TaxCategoryRegistry createAndInitializeRegistry(CoreModule module) throws DataFileException, PropertyHolderCreationException {
      TaxCategoryProperties properties = TaxCategoryProperties.create(module.getPropertyModule(), module, "tax-category-properties.yml");
      return TaxCategoryRegistry.createAndInitializeRegistry(TaxCategoryRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "tax-category-registry.yml");
   }

}
