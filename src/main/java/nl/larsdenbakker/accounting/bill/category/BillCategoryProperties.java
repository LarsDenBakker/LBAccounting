package nl.larsdenbakker.accounting.bill.category;

import java.util.UUID;
import nl.larsdenbakker.app.Module;
import nl.larsdenbakker.datafile.DataFileException;
import nl.larsdenbakker.property.PropertyFactory;
import nl.larsdenbakker.property.PropertyModule;
import nl.larsdenbakker.property.properties.Properties;
import nl.larsdenbakker.property.properties.Property;
import nl.larsdenbakker.storage.Storage;

/**
 *
 * @author Lars den Bakker<larsdenbakker@gmail.com>
 */
public class BillCategoryProperties extends Properties {

   public final Property<UUID> UUID;
   public final Property<String> NAME;
   public final Property<Short> SUBSTRACTABLE;

   public BillCategoryProperties(PropertyModule propertyModule,
                                 Property<UUID> UUID,
                                 Property<String> NAME,
                                 Property<Short> SUBSTRACTABLE) {
      super(propertyModule, UUID, NAME, SUBSTRACTABLE);
      this.UUID = UUID;
      this.NAME = NAME;
      this.SUBSTRACTABLE = SUBSTRACTABLE;
   }

   public static BillCategoryProperties create(PropertyModule propertyModule, Module module, String fileName) throws DataFileException{
      PropertyFactory propertyFactory = propertyModule.getPropertyFactory();
      Storage configuration = Properties.getPropertiesConfiguration(propertyModule.getConversionModule(), module, fileName);
      return new BillCategoryProperties(propertyModule,
                                        propertyFactory.createProperty(configuration.getStorage("uuid"), UUID.class),
                                        propertyFactory.createProperty(configuration.getStorage("name"), String.class),
                                        propertyFactory.createProperty(configuration.getStorage("substractable"), Short.class));
   }

}
