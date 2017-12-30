package my.launchpad;

import com.google.inject.AbstractModule;

public class LaunchpadTestModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new LaunchpadModule());
  }
}
