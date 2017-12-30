package my.launchpad;

import org.apache.activemq.broker.BrokerService;
import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

public class LaunchpadTest {

  @Inject
  Launchpad launchpad;

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
  }

  private void whenLaunchpadIsRunning() {
    assertTrue(launchpad.start());
  }

  private void createGuiceTestInjector() {
    Guice.createInjector(new LaunchpadTestModule()).injectMembers(this);
  }

}
