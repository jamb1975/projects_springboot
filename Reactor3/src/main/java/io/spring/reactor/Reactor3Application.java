package io.spring.reactor;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Reactor3Application {

	
	 @Bean
	  CommandLineRunner demo() {
	    return args -> {
	    	System.out.println("Hola mundo3");
			System.out.println("Hola mundo4");
	    	List<String> iterable = Arrays.asList("foo", "bar", "foobar");
			Flux<String> seq2 = Flux.fromIterable(iterable);
			seq2.doOnNext(p -> System.out.println(p)).doOnComplete(() -> System.out.println("The end!"));
		    seq2.subscribe();
	    };
	  }
	 
	 private Flux<String> testfluxmono()
	 {
		 List<String> iterable = Arrays.asList("foo", "bar", "foobar");
			Flux<String> seq2 = Flux.fromIterable(iterable);
		 return seq2;
	 }
	public static void main(String[] args) {
		Reactor3Application reactor3Application=new Reactor3Application();
		System.out.println("Hola mundo");
		System.out.println(reactor3Application.testfluxmono());
		
		Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

		List<String> iterable = Arrays.asList("foo", "bar", "foobar");
		Flux<String> seq2 = Flux.fromIterable(iterable);
		seq2.subscribe(p -> System.out.println(p));
		Mono<String> noData = Mono.empty(); 

		Mono<String> data = Mono.just("foo");

		Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3); 
		SpringApplication.run(Reactor3Application.class, args);
	}

}
