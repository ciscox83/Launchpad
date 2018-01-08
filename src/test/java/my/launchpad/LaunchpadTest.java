package my.launchpad;

import com.google.common.io.Files;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.util.Modules;
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
  public void aPizzaWithAProperNameShouldBeEat() throws Exception {
    givenLaunchPadIsRunning();
    whenAPizzaWithProperNameIsDelivered();
    thenPizzaShouldBeEat();
  }

  @Test
  public void aPizzaWithAnImproperNameShouldNotBeEat() throws Exception {
    givenLaunchPadIsRunning();
    whenAPizzaWithImproperNameIsDelivered();
    thenPizzaShouldNotBeEat();
  }

  private void givenLaunchPadIsRunning() {
    assertFalse(camel.getUptime().isEmpty());
    assertFalse(camel.isStartingRoutes());
  }

  private void whenAPizzaWithProperNameIsDelivered() throws InterruptedException {
    // "<xml><pizza name=\"Margherita\"/></xml>"
    camel.createProducerTemplate().sendBody("test-queue:queue:pizzas", "Margherita");
    Thread.sleep(100); // Give Camel time to process route
  }

  private void whenAPizzaWithImproperNameIsDelivered() throws InterruptedException {
    camel.createProducerTemplate().sendBody("test-queue:queue:pizzas", "Pepperoni");
    Thread.sleep(100); // Give Camel time to process route
  }

  private void thenPizzaShouldBeEat() throws IOException {
    String pizza = Files.readFirstLine(new File("target/output/test/consumed_pizzas.txt"), Charset.defaultCharset());
    assertEquals("Margherita", pizza);
  }

  private void thenPizzaShouldNotBeEat() throws IOException {
    String pizza = Files.readFirstLine(new File("target/output/test/consumed_pizzas.txt"), Charset.defaultCharset());
    assertNull(pizza);
  }

  private void createGuiceTestInjector() {
    Guice.createInjector(Modules.override(new LaunchpadModule()).with(new LaunchpadTestModule())).injectMembers(this);
  }

}
