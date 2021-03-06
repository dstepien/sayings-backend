package pl.dawidstepien.sayings.endpoint.rest;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import pl.dawidstepien.sayings.test.category.EndToEndTest;

@Category(EndToEndTest.class)
public class SayingsRestEndpointEndToEndTest {

  private static Client client = ClientBuilder.newClient();

  @Test
  public void shouldReturnRandomSaying() {
    // when
    URI uri = UriBuilder.fromUri("http://localhost/sayings-1.0-SNAPSHOT/rest/sayings/random").port(8080).build();
    Response response = client.target(uri).request().get();

    // then
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
  }

  @Test
  public void shouldReturnAllSayings() {
    // when
    URI uri = UriBuilder.fromUri("http://localhost/sayings-1.0-SNAPSHOT/rest/sayings").port(8080).build();
    Response response = client.target(uri).request().get();

    // then
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
  }
}
