package my.launchpad;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

public class MainTestModule extends AbstractModule {

  @Override
  protected void configure() {
  }

  @Provides
  protected ActiveMQConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
  }

  @Provides
  CamelContext camelContext() {
    CamelContext camel = new DefaultCamelContext();
    camel.addComponent("test-queue", jmsComponentAutoAcknowledge(connectionFactory()));
    try {
      camel.addRoutes(new PizzaRoute());
      camel.setAutoStartup(true);
      camel.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return camel;
  }

}
