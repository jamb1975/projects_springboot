package io.spring.rabbit;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.ConnectionFactory;
import io.spring.rabbitmq.OutboundMessage;
import io.spring.rabbitmq.RabbitFlux;
import io.spring.rabbitmq.Sender;
import io.spring.rabbitmq.SenderOptions;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;




@SpringBootApplication
public class ReactiveRabbitMqApplication {
	private static final String QUEUE = "demo-queue";
	public static void main(String[] args) {
		SpringApplication.run(ReactiveRabbitMqApplication.class, args);
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.2.9");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("rabbitmq");
		connectionFactory.setPassword("JAmbg172*");
		connectionFactory.useNio();

		SenderOptions senderOptions =  new SenderOptions()
			    .connectionFactory(connectionFactory)
			    .connectionSupplier(cf -> cf.newConnection(                                  
			        new Address[] {new Address("192.168.2.1"), new Address("192.168.2.9")},
			        "reactive-sender"))
			    .resourceManagementScheduler(Schedulers.boundedElastic());
		Sender sender = RabbitFlux.createSender(senderOptions);
		Flux<OutboundMessage> outboundFlux  =
			    Flux.range(1, 10)
			        .map(i -> new OutboundMessage(
			            "amq.direct",
			            "routing.key", ("Message " + i).getBytes()
			        ));
		org.slf4j.Logger log = LoggerFactory.getLogger(ReactiveRabbitMqApplication.class);
		sender.send(outboundFlux)                         
	    .doOnError(e -> log.error("Send failed", e))  
	    .subscribe();
	}

}
