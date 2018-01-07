package my.launchpad;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class LaunchpadTestModule extends AbstractModule {

  @Override
  protected void configure() {
  }

  @Provides
  protected JmsEndpoint pizzaInput() {
    return new JmsEndpoint("test-queue", "queue:pizzas");
  }

  @Provides
  protected FileEndpoint pizzaOutput() {
    return new FileEndpoint("target/output/test", "consumed_pizzas.txt");
  }

}
