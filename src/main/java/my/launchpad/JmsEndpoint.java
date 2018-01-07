package my.launchpad;

public class JmsEndpoint {

  private final String jmsComponentName;
  private final String queueName;

  public JmsEndpoint(String jmsComponentName, String queueName) {
    this.jmsComponentName = jmsComponentName;
    this.queueName = queueName;
  }

  public String getJmsComponentName() {
    return jmsComponentName;
  }

  public String asString() {
    return jmsComponentName + ":" + queueName;
  }
}
