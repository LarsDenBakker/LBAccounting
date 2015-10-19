package nl.larsdenbakker.accounting.product;

import java.math.BigDecimal;
import java.util.UUID;
import nl.larsdenbakker.accounting.product.category.ProductCategory;
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
public class ProductProperties extends Properties {

   public final Property<UUID> UUID;
   public final Property<String> NAME;
   public final Property<ProductCategory> CATEGORY;
   public final Property<BigDecimal> PRICE;

   public ProductProperties(PropertyModule propertyModule,
                            Property<UUID> UUID,
                            Property<String> NAME,
                            Property<ProductCategory> CATEGORY,
                            Property<BigDecimal> PRICE) {
      super(propertyModule, UUID, NAME, CATEGORY, PRICE);
      this.UUID = UUID;
      this.NAME = NAME;
      this.CATEGORY = CATEGORY;
      this.PRICE = PRICE;
   }

   public static ProductProperties create(PropertyModule propertyModule, Module module, String fileName) throws DataFileException {
      PropertyFactory propertyFactory = propertyModule.getPropertyFactory();
      Storage configuration = Properties.getPropertiesConfiguration(propertyModule.getConversionModule(), module, fileName);
      return new ProductProperties(propertyModule,
                                   propertyFactory.createProperty(configuration.getStorage("uuid", true), UUID.class),
                                   propertyFactory.createProperty(configuration.getStorage("name", true), String.class),
                                   propertyFactory.createProperty(configuration.getStorage("category", true), ProductCategory.class),
                                   propertyFactory.createProperty(configuration.getStorage("price", true), BigDecimal.class));
   }

}
