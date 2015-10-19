package nl.larsdenbakker.accounting.order;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import nl.larsdenbakker.accounting.order.category.OrderCategory;
import nl.larsdenbakker.accounting.order.customer.Customer;
import nl.larsdenbakker.accounting.product.Product;
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
public class OrderProperties extends Properties {

   public final Property<Integer> INDEX;
   public final Property<OrderCategory> CATEGORY;
   public final Property<Customer> CUSTOMER;
   public final Property<String> DESCRIPTION;
   public final Property<Map<Product, Integer>> PRODUCTS;
   public final Property<LocalDate> INVOICE_DATE;
   public final Property<LocalDate> PAID_DATE;
   public final Property<Integer> QUARTER;

   public OrderProperties(PropertyModule propertyModule,
                          Property<Integer> INDEX,
                          Property<OrderCategory> CATEGORY,
                          Property<Customer> CUSTOMER,
                          Property<String> DESCRIPTION,
                          Property<Map<Product, Integer>> PRODUCTS,
                          Property<LocalDate> INVOICE_DATE,
                          Property<LocalDate> PAID_DATE,
                          Property<Integer> QUARTER) {
      super(propertyModule, INDEX, CATEGORY, CUSTOMER, DESCRIPTION, PRODUCTS, INVOICE_DATE, PAID_DATE, QUARTER);
      this.INDEX = INDEX;
      this.CATEGORY = CATEGORY;
      this.CUSTOMER = CUSTOMER;
      this.DESCRIPTION = DESCRIPTION;
      this.PRODUCTS = PRODUCTS;
      this.INVOICE_DATE = INVOICE_DATE;
      this.PAID_DATE = PAID_DATE;
      this.QUARTER = QUARTER;
   }

   public static OrderProperties create(PropertyModule propertyModule, Module module, String fileName) throws DataFileException {
      PropertyFactory propertyFactory = propertyModule.getPropertyFactory();
      Storage configuration = Properties.getPropertiesConfiguration(propertyModule.getConversionModule(), module, fileName);
      return new OrderProperties(propertyModule,
                                 propertyFactory.createProperty(configuration.getStorage("index", true), Integer.class),
                                 propertyFactory.createProperty(configuration.getStorage("category", true), OrderCategory.class),
                                 propertyFactory.createProperty(configuration.getStorage("customer", true), Customer.class),
                                 propertyFactory.createProperty(configuration.getStorage("description", true), String.class),
                                 propertyFactory.createMapProperty(configuration.getStorage("products", true), HashMap.class, Product.class, Integer.class),
                                 propertyFactory.createProperty(configuration.getStorage("invoice-date", true), LocalDate.class),
                                 propertyFactory.createProperty(configuration.getStorage("paid-date", true), LocalDate.class),
                                 propertyFactory.createProperty(configuration.getStorage("quarter", true), Integer.class));
   }

}
