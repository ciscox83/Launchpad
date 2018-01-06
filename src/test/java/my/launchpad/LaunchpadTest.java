package my.launchpad;

import org.apache.activemq.broker.BrokerService;
import com.google.inject.Guice;
import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class LaunchpadTest extends CamelTestSupport {

  @Inject
  Launchpad launchpad;

  @Inject
  CamelContext camel;

  BrokerService brokerService;

  @Before
  public void before() {
    brokerService = new BrokerService();
    createGuiceTestInjector();
  }

  @After
  public void after() throws Exception {
    brokerService.stop();
  }

  @Test
  public void launchpadTest() throws Exception {
    givenTheQueueContainsAPizza();
    whenLaunchpadRan();
  }

  private void givenTheQueueContainsAPizza() throws Exception {
    brokerService.setBrokerName("brokerTest");
    brokerService.addConnector("tcp://localhost:61616");
    brokerService.start();
    camel.createProducerTemplate().sendBody("vm:queue:EVENTS", "<xml><pizza name=\"Margherita\" eat=\"false\"/></xml>");
  }

  private void whenLaunchpadRan() {
    assertTrue(launchpad.start());
  }

  private void createGuiceTestInjector() {
    Guice.createInjector(new LaunchpadTestModule()).injectMembers(this);
  }

}
