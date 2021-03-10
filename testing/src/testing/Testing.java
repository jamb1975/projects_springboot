package testing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class Testing 
{
	
	public List<Integer> findmissinginteger;
	public int i=1;
	public static void main(String args[])
	{
		Testing test=new Testing();
		 //int[] a= {1, 3, 6, 4, 1, 2};
		//int[] a= {-1,-3};
		int[] a= {1, 2, 3};
		int missingint=1;
		test.findmissinginteger = Arrays.stream(a)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
		test.i=1;
		for( ; test.i<a.length; test.i++)
		{	
		  	boolean is_missing=test.findmissinginteger.stream().sorted().anyMatch(n -> n.equals(test.i));
		  	if(!is_missing)
		  	{	
		  		missingint=test.i;
		  		break;
		  	}
		  	
		  	if(test.i+1 == a.length)
		  	{
		  		missingint = a.length+1;
		  	}
		  	
		 }
		System.out.println(missingint);
}	

	}
