package nl.larsdenbakker.accounting;

import java.util.LinkedHashMap;
import java.util.List;
import nl.larsdenbakker.accounting.bill.BillModule;
import nl.larsdenbakker.accounting.core.CoreModule;
import nl.larsdenbakker.accounting.order.OrderModule;
import nl.larsdenbakker.accounting.product.ProductModule;
import nl.larsdenbakker.app.AbstractApplication;
import nl.larsdenbakker.app.UserInputException;
import nl.larsdenbakker.app.menu.RegistryGUIModule;
import nl.larsdenbakker.configuration.ConfigurationModule;
import nl.larsdenbakker.conversion.ConversionModule;
import nl.larsdenbakker.datafile.DataFileModule;
import nl.larsdenbakker.datapath.DataPathModule;
import nl.larsdenbakker.property.PropertyModule;
import nl.larsdenbakker.registry.RegistryModule;
import nl.larsdenbakker.operation.OperationModule;
import nl.larsdenbakker.util.CollectionUtils;
import nl.larsdenbakker.util.MapUtils;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class AccountingApplication extends AbstractApplication {

   public AccountingApplication() {
      super();
   }

   public void load() throws UserInputException {
      loadModule(ConversionModule.class);
      loadModule(RegistryModule.class);
      loadModule(DataPathModule.class);
      loadModule(ConfigurationModule.class);
      loadModule(PropertyModule.class);
      loadModule(OperationModule.class);
      loadModule(DataFileModule.class);
      BillModule billModule = loadModule(BillModule.class);
      CoreModule coreModule = loadModule(CoreModule.class);
      ProductModule productModule = loadModule(ProductModule.class);
      OrderModule orderModule = loadModule(OrderModule.class);

      loadModule(RegistryGUIModule.class, MapUtils.of(LinkedHashMap.class,
                                                      String.class, "LB Accounting",
                                                      Boolean.class, false,
                                                      List.class, CollectionUtils.listOf(billModule.getBillRegistry(),
                                                                                         billModule.getBillCategoryRegistry(),
                                                                                         billModule.getPaymentSourceRegistry(),
                                                                                         orderModule.getOrderRegistry(),
                                                                                         orderModule.getOrderCategoryRegistry(),
                                                                                         orderModule.getCustomerRegistry(),
                                                                                         productModule.getProductCategoryRegistry(),
                                                                                         productModule.getProductRegistry(),
                                                                                         coreModule.getTaxCategoryRegistry())));
   }

   public RegistryGUIModule getRegistryGUIModule() {
      return getModule(RegistryGUIModule.class);
   }

   public ConfigurationModule getConfigurationModule() {
      return getModule(ConfigurationModule.class);
   }

   public ConversionModule getConversionModule() {
      return getModule(ConversionModule.class);
   }

   public PropertyModule getPropertyModule() {
      return getModule(PropertyModule.class);
   }

   public OperationModule getOperationModule() {
      return getModule(OperationModule.class);
   }

   public RegistryModule getRegistryModule() {
      return getModule(RegistryModule.class);
   }

   public DataFileModule getDataFileModule() {
      return getModule(DataFileModule.class);
   }

   public CoreModule getCoreModule() {
      return getModule(CoreModule.class);
   }

   public ProductModule getProductModule() {
      return getModule(ProductModule.class);
   }

   public BillModule getBillModule() {
      return getModule(BillModule.class);
   }

   public OrderModule getOrderModule() {
      return getModule(OrderModule.class);
   }

   @Override
   public String getName() {
      return "accounting";
   }

}
