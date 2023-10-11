package ch.sphynx.receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class ReceiveMessage {

  @Value("${spring.rabbitmq.exchange-name}")
  private static String exchangeName ;

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
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(exchangeName, "topic");
    String queueName = channel.queueDeclare().getQueue();

    if (argv.length < 1) {
      log.error("Usage: receiver.ReceiveLogsTopic [binding_key]...");
      System.exit(1);
    }

    for (String bindingKey : argv) {
      channel.queueBind(queueName, exchangeName, bindingKey);
    }

   log.info(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback =
        (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), "UTF-8");
         log.info(
              " [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
  }
}
