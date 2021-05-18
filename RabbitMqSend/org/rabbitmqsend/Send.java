package org.rabbitmqsend;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
//user and password default init guest
public class Send {
	private final static String QUEUE_NAME = "hello";
	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("rabbitmq");
		factory.setPassword("JAmbg172*");
		try (Connection connection = factory.newConnection();
		     Channel channel = connection.createChannel()) {
			 channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			 String message = "Hello World JESUS VENCIO!";
			 channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			 System.out.println(" [x] Sent '" + message + "'");

		}	
		

	}

}
