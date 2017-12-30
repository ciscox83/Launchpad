package my.launchpad;

public class LaunchpadImpl implements Launchpad {

  public static final boolean STARTED = true;

  LaunchpadImpl() {}

  public boolean start() {
    return STARTED;
  }
}