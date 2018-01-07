package my.launchpad;

import com.google.common.io.Files;
import com.google.inject.Guice;
import com.google.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class LaunchpadTest extends CamelTestSupport {

  @Inject
  CamelContext camel;

  @Before
  public void before() {
    createGuiceTestInjector();
  }

  @After
  public void after() throws Exception {
    camel.stop();
  }

  @Test
  public void launchpadTest() throws Exception {
    givenLaunchPadIsRunning();
    whenAPizzaIsSent();
    thenPizzaShouldBeEat();
  }

  private void givenLaunchPadIsRunning() {
    assertFalse(camel.getUptime().isEmpty());
    assertFalse(camel.isStartingRoutes());
  }

  private void whenAPizzaIsSent() throws InterruptedException {
//    "<xml><pizza name=\"Margherita\"/></xml>"
    camel.createProducerTemplate().sendBody("test-queue:queue:pizzas", "Margherita");
    Thread.sleep(3000); // Give Camel time to process route

  }

  private void thenPizzaShouldBeEat() throws IOException {
    String pizza = Files.readFirstLine(new File("target/output/test/consumed_pizzas.txt"), Charset.defaultCharset());
    assertEquals("Margherita", pizza);
  }

  private void createGuiceTestInjector() {
    Guice.createInjector(new MainTestModule()).injectMembers(this);
  }

}
