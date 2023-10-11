package ch.sphynx.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class PublishMessage {

  @Value("${spring.rabbitmq.exchange-name}")
  private static String exchangeName;

  @Value("${spring.rabbitmq.host}")
  private static String rabbitHost;

  @Value("${spring.rabbitmq.username}")
  private static String rabbitUsername;

  @Value("${spring.rabbitmq.password}")
  private static String rabbitPassword;

  @Value("${spring.rabbitmq.virtual-host}")
  private static String rabbitVirtualHost;

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(rabbitHost);
    factory.setUsername(rabbitUsername);
    factory.setPassword(rabbitPassword);
    factory.setVirtualHost(rabbitVirtualHost);
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {

      channel.exchangeDeclare(exchangeName, "topic");

      String routingKey = argv[0];
      String message = argv[1];

      channel.basicPublish(exchangeName, routingKey, null, message.getBytes("UTF-8"));
      log.info(" [x] Sent '" + routingKey + "':'" + message + "'");
    }
  }
}
