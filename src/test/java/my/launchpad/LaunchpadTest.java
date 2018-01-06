package my.launchpad;

import org.apache.activemq.broker.BrokerService;
import com.google.inject.Guice;
import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class LaunchpadTest extends CamelTestSupport {

  @Inject
  Launchpad launchpad;

  @Inject
  CamelContext camel;

  @Before
  public void setUp() {
    createGuiceTestInjector();
  }

  @Test
  public void launchpadTest() throws Exception {
    givenTheQueueContainsPizzas();
    whenLaunchpadIsRunning();
  }

  private void givenTheQueueContainsPizzas() throws Exception {
    BrokerService brokerService = new BrokerService();
    brokerService.setBrokerName("brokerTest");
    brokerService.addConnector("tcp://localhost:61616");
    brokerService.start();
    camel.createProducerTemplate().sendBody("vm:queue:EVENTS", "Margherita");
  }

  private void whenLaunchpadIsRunning() {
    assertTrue(launchpad.start());
  }

  private void createGuiceTestInjector() {
    Guice.createInjector(new LaunchpadTestModule()).injectMembers(this);
  }

}
