package my.launchpad;

import com.google.common.collect.Sets;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Set;

public class PizzaNameChecker implements Processor {

  Set<String> ProperNames = Sets.newHashSet("Margherita", "Quattro formaggi");

  PizzaNameChecker() {}

  public void process(Exchange exchange) throws Exception {
    String body = exchange.getIn().getMandatoryBody(String.class);
    if(nameIsAppropriate(body)) {
      exchange.getIn().setBody(body);
    } else {
      exchange.getIn().setBody("");
    }
  }

  private boolean nameIsAppropriate(String pizzaName) {
    return ProperNames.contains(pizzaName);
  }
}
