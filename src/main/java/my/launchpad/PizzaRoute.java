package my.launchpad;

import org.apache.camel.builder.RouteBuilder;

public class PizzaRoute extends RouteBuilder {

  private final JmsEndpoint endpointInput;
  private final FileEndpoint endpointOutput;

  public PizzaRoute(JmsEndpoint endpointInput, FileEndpoint endpointOutput) {
    this.endpointInput = endpointInput;
    this.endpointOutput = endpointOutput;
  }

  @Override
  public void configure() throws Exception {
    from(endpointInput.asString()).to(endpointOutput.asString());
  }

  public JmsEndpoint getEndpointInput() {
    return endpointInput;
  }

}
