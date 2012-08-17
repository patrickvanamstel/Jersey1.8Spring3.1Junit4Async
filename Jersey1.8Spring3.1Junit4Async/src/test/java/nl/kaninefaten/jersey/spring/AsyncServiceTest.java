package nl.kaninefaten.jersey.spring;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class AsyncServiceTest extends JerseyTest {

	public AsyncServiceTest() throws Exception {
		super(
				new WebAppDescriptor.Builder(
						"com.sun.jersey.samples.springannotations.resources.jerseymanaged")
						.contextPath("spring")
						.contextParam("contextConfigLocation",
								"classpath:/nl/kaninefaten/jersey/spring/applicationContext.xml")
						.servletClass(SpringServlet.class)
						.contextListenerClass(ContextLoaderListener.class)
						.build());
	}

	@Test
	public void doTestApplicationWadl() {
		WebResource webResource = resource();

		String wadl = webResource.path("application.wadl")
				.accept(MediaTypes.WADL).get(String.class);

		assertTrue(
				"Method: doTestApplicationWadl \nMessage: Something wrong, the returned "
						+ "WADL's length is not > 0", wadl.length() > 0);

	}

	@Test
	public void doTestHello() {

		WebResource webResource = resource();
		String item = webResource.path("/bean/asyncsample/hello")
				.accept(MediaType.TEXT_PLAIN).get(String.class);

		assertTrue(item.startsWith("Helloworld"));

	}

	
	@Test
	public void doTestAsyncHello() {

		WebResource webResource = resource();
		String item = webResource.path("/bean/asyncsample/asynchello/A")
				.accept(MediaType.TEXT_PLAIN).get(String.class);

		 item = webResource.path("/bean/asyncsample/asynchello/B")
					.accept(MediaType.TEXT_PLAIN).get(String.class);
		
		 item = webResource.path("/bean/asyncsample/asynchello/C")
					.accept(MediaType.TEXT_PLAIN).get(String.class);
		 
		assertTrue(item.startsWith("Helloworld"));

	}
	
}
