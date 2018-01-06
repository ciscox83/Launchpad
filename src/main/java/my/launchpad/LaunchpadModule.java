package my.launchpad;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class LaunchpadModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Launchpad.class).to(LaunchpadImpl.class);
  }

  @Provides
  CamelContext camelContext() {
    CamelContext camel = new DefaultCamelContext();
    try {
      camel.addRoutes(new PizzaRoute());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return camel;
  }
}
