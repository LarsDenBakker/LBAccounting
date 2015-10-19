package nl.larsdenbakker.accounting.bill;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import nl.larsdenbakker.accounting.bill.category.BillCategory;
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
public class BillProperties extends Properties {

   public final Property<Integer> INDEX;
   public final Property<String> DESCRIPTION;
   public final Property<Map<TaxCategory, BigDecimal>> AMOUNT;
   public final Property<LocalDate> PAID_DATE;
   public final Property<BillCategory> CATEGORY;
   public final Property<Short> QUARTER;
   public final Property<Map<String, Integer>> TEST;
   public final Property<ArrayList<Integer>> NUMBERS;

   public BillProperties(PropertyModule propertyModule,
                         Property<Integer> INDEX,
                         Property<String> DESCRIPTION,
                         Property<Map<TaxCategory, BigDecimal>> AMOUNT,
                         Property<LocalDate> PAID_DATE,
                         Property<BillCategory> CATEGORY,
                         Property<Short> QUARTER,
                         Property<Map<String, Integer>> TEST,
                         Property<ArrayList<Integer>> NUMBERS) {
      super(propertyModule, INDEX, DESCRIPTION, AMOUNT, PAID_DATE, CATEGORY, QUARTER, TEST, NUMBERS);
      this.INDEX = INDEX;
      this.DESCRIPTION = DESCRIPTION;
      this.AMOUNT = AMOUNT;
      this.PAID_DATE = PAID_DATE;
      this.CATEGORY = CATEGORY;
      this.QUARTER = QUARTER;
      this.TEST = TEST;
      this.NUMBERS = NUMBERS;
   }

   public static BillProperties create(PropertyModule propertyModule, Module module, String fileName) throws DataFileException {
      PropertyFactory propertyFactory = propertyModule.getPropertyFactory();
      Storage configuration = Properties.getPropertiesConfiguration(propertyModule.getConversionModule(), module, fileName);
      return new BillProperties(propertyModule,
                                propertyFactory.createProperty(configuration.getStorage("index", true), Integer.class),
                                propertyFactory.createProperty(configuration.getStorage("description", true), String.class),
                                propertyFactory.createMapProperty(configuration.getStorage("amount", true), HashMap.class, TaxCategory.class, BigDecimal.class),
                                propertyFactory.createProperty(configuration.getStorage("paid-date", true), LocalDate.class),
                                propertyFactory.createProperty(configuration.getStorage("category", true), BillCategory.class),
                                propertyFactory.createProperty(configuration.getStorage("quarter", true), Short.class),
                                propertyFactory.createMapProperty(configuration.getStorage("test", true), HashMap.class, String.class, Integer.class),
                                propertyFactory.createCollectionProperty(configuration.getStorage("numbers", true), (Class) ArrayList.class, Integer.class));
   }
}
