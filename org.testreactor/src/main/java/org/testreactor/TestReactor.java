package org.testreactor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//autor: JAVIER MURCIA
public  class TestReactor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestReactor.testreactor_filterandmap();
	}
	
	public static void testreactor_filterandmap() {
		Function<Flux<String>, Flux<String>> filterAndMap =
	    		f -> f.filter(color -> !color.equals("orange"))
	    		      .map(String::toUpperCase);

	    		Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
	    			.doOnNext(System.out::println)
	    			.transform(filterAndMap);
	    			//.subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: "+d));
	    
	    		AtomicInteger ai = new AtomicInteger();
	    		Function<Flux<String>, Flux<String>> filterAndMap2 = f -> {
	    			if (ai.incrementAndGet() == 1) {
	    		return f.filter(color -> !color.equals("orange"))
	    		        .map(String::toUpperCase);
	    			}
	    			return f.filter(color -> !color.equals("purple"))
	    			        .map(String::toUpperCase);
	    		};

	    		Flux<String> composedFlux =
	    		Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
	    		    .doOnNext(System.out::println)
	    		    .transformDeferred(filterAndMap2);
                
	    		//composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :"+d));
	    		//composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: "+d));
	    		Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
	        		    .handle((i, sink) -> {
	        		        String letter = alphabet(i); 
	        		        if (letter != null) 
	        		            sink.next(letter); 
	        		    });

	        		//alphabet.subscribe(System.out::println);
	    		
	    		Flux<List<Integer>> flux = Flux.just(1,2,3,4).buffer(4);
	        		     			
	        		     				    
	        		  
	    		 Flux<Integer> flux1 = Flux.just(1, 2);

	    		String cade=    
	    				flux1
	    		   // .zipWith(flux1, (i, msg) ->msg)
	    		    .flatMap(s-> Mono.just(s)).buffer().toString();
	    		    //.subscribe(System.out::println);
	    		
	    		List<Orden> numbers = Arrays.asList(new Orden(1,1), new Orden(2,2), new Orden(3,3), new Orden(4,4), new Orden(5,5), new Orden(6,6));
	    		String str = numbers.stream().map(i->String.valueOf(i.num)).collect(Collectors.joining(", "));
	        System.out.println("cade->"+str);	
	}
	public static String alphabet(int letterNumber) {
		if (letterNumber < 1 || letterNumber > 26) {
			return null;
		}
		int letterIndexAscii = 'A' + letterNumber - 1;
		return "" + (char) letterIndexAscii;
	}
	public static class Orden{
		int num;
		int orden;
		public Orden(int num, int orden) {
			super();
			this.num = num;
			this.orden = orden;
		}
		
	}
	
}

