package nl.larsdenbakker.accounting.bill.category;

import java.util.UUID;
import nl.larsdenbakker.accounting.bill.BillModule;
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
public class BillCategoryRegistry extends PropertyHolderRegistry<UUID, BillCategory> {

   private final BillCategoryProperties PROPERTIES;

   public BillCategoryRegistry(Module parentModule, RegistryModule registryHandler, BillCategoryProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, UUID.class, BillCategory.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public BillCategoryProperties getProperties() {
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
      return "Bill Category";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Bill Categories";
   }

   @Override
   public String getKey() {
      return "bill-categories";
   }

   public static BillCategoryRegistry createAndInitializeRegistry(BillModule module) throws DataFileException, PropertyHolderCreationException {
      BillCategoryProperties properties = BillCategoryProperties.create(module.getPropertyModule(), module, "bill-category-properties.yml");
      return PropertyHolderRegistry.createAndInitializeRegistry(BillCategoryRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "bill-category-registry.yml");
   }

}
