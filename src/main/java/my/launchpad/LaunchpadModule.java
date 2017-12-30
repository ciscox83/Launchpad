package my.launchpad;

import com.google.inject.AbstractModule;

public class LaunchpadModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Launchpad.class).to(LaunchpadImpl.class);
  }
}
