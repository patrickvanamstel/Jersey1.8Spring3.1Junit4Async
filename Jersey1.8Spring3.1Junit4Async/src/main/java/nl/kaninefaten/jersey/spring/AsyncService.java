package nl.kaninefaten.jersey.spring;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/bean/asyncsample")
public class AsyncService {
	
	@Autowired
	AsyncAnnotatedTask asyncAnnotatedTask = null;
	
	@GET
	@Path("/hello")
	public Response hello() {
		return Response
				.status(Response.Status.OK.getStatusCode())
				.entity("HelloworldService"
						+ asyncAnnotatedTask.getClass().getName()).build();
	}

	@GET
	@Path("/asynchello/{callId}")
	public Response asyncHello(@PathParam("callId") String callId) {
		
		// Lets you see the callers thread id
		System.out.println("Current thread id" + Thread.currentThread().getId());
		asyncAnnotatedTask.printMessage(callId);
		
		
		return Response
				.status(Response.Status.OK.getStatusCode())
				.entity("HelloworldService"
						+ asyncAnnotatedTask.getClass().getName()).build();
	}

	
	
	
	
	
	
	
	
}
