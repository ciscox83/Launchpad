package my.launchpad;

import org.apache.camel.builder.RouteBuilder;

public class PizzaRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("production-queue:queue:pizzas").to("file://target/output/prod?fileName=consumed_pizzas.txt");
  }

}
