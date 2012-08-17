package nl.kaninefaten.jersey.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncAnnotatedTask {

	
	@Async
	public void printMessage(String message){
		
		// Lets you see this thread's id
		for (int i = 0 ; i < 100 ; i++)
		{
			System.out.println( message + ":" + i + ":" + Thread.currentThread().getId());
		}
		
		
		System.out.println("End of call method " + Thread.currentThread().getId());
		
	
	}
	
}
