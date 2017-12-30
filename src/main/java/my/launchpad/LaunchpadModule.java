package my.launchpad;

import com.google.inject.AbstractModule;
import org.apache.camel.guice.CamelModuleWithRouteTypes;

public class LaunchpadModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new CamelModuleWithRouteTypes(PizzaRoute.class));
    bind(Launchpad.class).to(LaunchpadImpl.class);
  }
}
