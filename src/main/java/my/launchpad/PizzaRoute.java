package my.launchpad;

import org.apache.camel.builder.RouteBuilder;

public class PizzaRoute extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("vm:queue:EVENTS").to("log:Events?showAll=true");
  }
}
