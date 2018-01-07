package my.launchpad;

import org.apache.camel.builder.RouteBuilder;

public class PizzaRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("test-queue:queue:pizzas").to("file://target/?fileName=consumed_pizzas.txt");
  }

}
