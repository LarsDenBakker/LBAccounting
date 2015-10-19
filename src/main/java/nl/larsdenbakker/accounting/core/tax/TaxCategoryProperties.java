package nl.larsdenbakker.accounting.core.tax;

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
public class TaxCategoryProperties extends Properties {

   public final Property<UUID> UUID;
   public final Property<String> NAME;
   public final Property<Short> AMOUNT;

   public TaxCategoryProperties(PropertyModule propertyModule,
                                Property<UUID> UUID,
                                Property<String> NAME,
                                Property<Short> AMOUNT) {
      super(propertyModule, UUID, NAME, AMOUNT);
      this.UUID = UUID;
      this.NAME = NAME;
      this.AMOUNT = AMOUNT;
   }

   public static TaxCategoryProperties create(PropertyModule propertyModule, Module module, String fileName) throws DataFileException {
      PropertyFactory propertyFactory = propertyModule.getPropertyFactory();
      Storage configuration = Properties.getPropertiesConfiguration(propertyModule.getConversionModule(), module, fileName);
      return new TaxCategoryProperties(propertyModule,
                                       propertyFactory.createProperty(configuration.getStorage("uuid", true), UUID.class),
                                       propertyFactory.createProperty(configuration.getStorage("name", true), String.class),
                                       propertyFactory.createProperty(configuration.getStorage("amount", true), Short.class));
   }

}
