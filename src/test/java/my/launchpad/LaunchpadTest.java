package my.launchpad;

import org.junit.Test;

import javax.inject.Inject;

public class LaunchpadTest {

  @Inject
  Launchpad launchpad;

  @Test
  public void launchpadTest() {
    whenLaunchpadIsRunning();
  }

  private void whenLaunchpadIsRunning() {
    launchpad.start();
  }

}
