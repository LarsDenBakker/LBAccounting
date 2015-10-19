package nl.larsdenbakker.accounting.bill;

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
public class BillRegistry extends PropertyHolderRegistry<Integer, Bill> {

   private final BillProperties PROPERTIES;

   public BillRegistry(Module parentModule, RegistryModule registryHandler, BillProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, Integer.class, Bill.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public BillProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public String getDataValueDescription() {
      return "Bill";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Bills";
   }

   @Override
   public String getKey() {
      return "bills";
   }

   public static BillRegistry createAndInitializeRegistry(BillModule module) throws DataFileException, PropertyHolderCreationException {
      BillProperties properties = BillProperties.create(module.getPropertyModule(), module, "bill-properties.yml");
      return PropertyHolderRegistry.createAndInitializeRegistry(BillRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "bill-registry.yml");
   }

}
