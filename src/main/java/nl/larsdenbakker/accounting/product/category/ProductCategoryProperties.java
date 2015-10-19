package nl.larsdenbakker.accounting.product.category;

import java.util.UUID;
import nl.larsdenbakker.accounting.core.tax.TaxCategory;
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
public class ProductCategoryProperties extends Properties {

   public final Property<UUID> UUID;
   public final Property<String> NAME;
   public final Property<TaxCategory> TAX_CATEGORY;

   public ProductCategoryProperties(PropertyModule propertyModule,
                                    Property<UUID> UUID,
                                    Property<String> NAME,
                                    Property<TaxCategory> TAX_CATEGORY) {
      super(propertyModule, UUID, NAME, TAX_CATEGORY);
      this.UUID = UUID;
      this.NAME = NAME;
      this.TAX_CATEGORY = TAX_CATEGORY;
   }

   public static ProductCategoryProperties create(PropertyModule propertyModule, Module module, String fileName) throws DataFileException {
      PropertyFactory propertyFactory = propertyModule.getPropertyFactory();
      Storage configuration = Properties.getPropertiesConfiguration(propertyModule.getConversionModule(), module, fileName);
      return new ProductCategoryProperties(propertyModule,
                                           propertyFactory.createProperty(configuration.getStorage("uuid", true), UUID.class),
                                           propertyFactory.createProperty(configuration.getStorage("name", true), String.class),
                                           propertyFactory.createProperty(configuration.getStorage("tax-category", true), TaxCategory.class));
   }

}
