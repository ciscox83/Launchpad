package my.launchpad;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.activemq.broker.BrokerService;
import com.google.inject.Guice;
import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

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
    thenPizzaShouldBeEat();
  }

  private void givenTheQueueContainsAPizza() throws Exception {
    brokerService.setBrokerName("brokerTest");
    brokerService.addConnector("tcp://localhost:61616");
    brokerService.start();
    camel.createProducerTemplate().sendBody("vm:queue:EVENTS", "<xml><pizza name=\"Margherita\"/></xml>");
  }

  private void whenLaunchpadRan() {
    assertTrue(launchpad.start());
  }

  private void thenPizzaShouldBeEat() throws IOException {
    String pizza = Files.readFirstLine(new File("consumed_pizzas.txt"), Charset.defaultCharset());
    assertEquals(pizza, "Margherita");
  }

  private void createGuiceTestInjector() {
    Guice.createInjector(new LaunchpadTestModule()).injectMembers(this);
  }

}
