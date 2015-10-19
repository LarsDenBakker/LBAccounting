package nl.larsdenbakker.accounting.bill.paymentsource;

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
public class PaymentSourceProperties extends Properties {

   public final Property<UUID> UUID;
   public final Property<String> NAME;

   public PaymentSourceProperties(PropertyModule propertyModule,
                                  Property<UUID> UUID,
                                  Property<String> NAME) {
      super(propertyModule, UUID, NAME);
      this.UUID = UUID;
      this.NAME = NAME;
   }

   public static PaymentSourceProperties create(PropertyModule propertyModule, Module module, String fileName) throws DataFileException {
      PropertyFactory propertyFactory = propertyModule.getPropertyFactory();
      Storage configuration = Properties.getPropertiesConfiguration(propertyModule.getConversionModule(), module, fileName);
      return new PaymentSourceProperties(propertyModule,
                                         propertyFactory.createProperty(configuration.getStorage("uuid", true), UUID.class),
                                         propertyFactory.createProperty(configuration.getStorage("name", true), String.class));
   }

}
