package my.launchpad;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

public class LaunchpadModule extends AbstractModule {

  @Override
  protected void configure() {
  }

  @Provides
  protected ActiveMQConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
  }

  @Provides
  protected JmsEndpoint pizzaInput() {
    return new JmsEndpoint("prod-queue", "queue:pizzas");
  }

  @Provides
  protected FileEndpoint pizzaOutput() {
    return new FileEndpoint("target/output/prod", "consumed_pizzas.txt");
  }

  @Provides
  protected PizzaRoute pizzaRouteTest(JmsEndpoint endpointInput, FileEndpoint endpointOutput) {
    return new PizzaRoute(endpointInput, endpointOutput, new PizzaNameChecker());
  }

  @Provides
  CamelContext camelContext(PizzaRoute pizzaRoute) {
    CamelContext camel = new DefaultCamelContext();
    camel.addComponent(
            pizzaRoute.getEndpointInput().getJmsComponentName(),
            jmsComponentAutoAcknowledge(connectionFactory()));
    try {
      camel.addRoutes(pizzaRoute);
      camel.setAutoStartup(true);
      camel.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return camel;
  }

}
