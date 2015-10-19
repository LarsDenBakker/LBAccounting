package nl.larsdenbakker.accounting.bill.paymentsource;

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
public class PaymentSourceRegistry extends PropertyHolderRegistry<UUID, PaymentSource> {

   private final PaymentSourceProperties PROPERTIES;

   public PaymentSourceRegistry(Module parentModule, RegistryModule registryHandler, PaymentSourceProperties PROPERTIES, DataFile dataFile) {
      super(parentModule, registryHandler, UUID.class, PaymentSource.class, dataFile);
      this.PROPERTIES = PROPERTIES;
   }

   @Override
   public PaymentSourceProperties getProperties() {
      return PROPERTIES;
   }

   @Override
   public String getDataValueDescription() {
      return "Payment Source";
   }

   @Override
   public String getPluralDataValueDescription() {
      return "Payment Sources";
   }

   @Override
   public String getKey() {
      return "payment-sources";
   }

   public static PaymentSourceRegistry createAndInitializeRegistry(BillModule module) throws DataFileException, PropertyHolderCreationException {
      PaymentSourceProperties properties = PaymentSourceProperties.create(module.getPropertyModule(), module, "payment-source-properties.yml");
      return PaymentSourceRegistry.createAndInitializeRegistry(PaymentSourceRegistry.class, module, module.getRegistryModule(), module.getDataFileModule(), properties, "payment-source-registry.yml");
   }
}
