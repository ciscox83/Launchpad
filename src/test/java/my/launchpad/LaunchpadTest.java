package my.launchpad;

import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

public class LaunchpadTest {

  @Inject
  Launchpad launchpad;

  @Before
  public void setUp() {
    createGuiceTestInjector();
  }

  @Test
  public void launchpadTest() {
    whenLaunchpadIsRunning();
  }

  private void whenLaunchpadIsRunning() {
    launchpad.start();
  }

  private void createGuiceTestInjector() {
    Guice.createInjector(new LaunchpadTestModule()).injectMembers(this);
  }

}
