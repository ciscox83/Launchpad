package my.launchpad;

import org.apache.camel.builder.RouteBuilder;

public class PizzaRoute extends RouteBuilder {

  private final JmsEndpoint endpointInput;
  private final FileEndpoint endpointOutput;
  private final PizzaNameChecker pizzaNameChecker;

  public PizzaRoute(JmsEndpoint endpointInput, FileEndpoint endpointOutput, PizzaNameChecker pizzaNameChecker) {
    this.endpointInput = endpointInput;
    this.endpointOutput = endpointOutput;
    this.pizzaNameChecker = pizzaNameChecker;
  }

  @Override
  public void configure() throws Exception {
    from(endpointInput.asString())
            .process(pizzaNameChecker)
            .to(endpointOutput.asString());
  }

  public JmsEndpoint getEndpointInput() {
    return endpointInput;
  }

}
