package my.launchpad;

import com.google.inject.Inject;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchpadImpl implements Launchpad {

  static final Logger logger = LoggerFactory.getLogger(LaunchpadImpl.class);

  public static final boolean STARTED = true;
  private static final boolean ERROR = false;

  private final CamelContext camel;

  @Inject
  public LaunchpadImpl(CamelContext camel) {
    this.camel = camel;
  }

  public boolean start() {

    try {
      camel.start();
    } catch (Exception e) {
      e.printStackTrace();
      return ERROR;
    }

    return STARTED;
  }
}