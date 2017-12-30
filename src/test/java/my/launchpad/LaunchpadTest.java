package my.launchpad;

import org.junit.Test;

public class LaunchpadTest {

  @Test
  public void launchpadTest() {
    whenLaunchpadIsRunning();
  }

  private void whenLaunchpadIsRunning() {
    Launchpad launchpad = new Launchpad();
    launchpad.start();
  }

}
