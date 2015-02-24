package pl.dawidstepien.say;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

public class SayRestServiceIT {

  private static URI uri = UriBuilder.fromUri("http://localhost/say-1.0-SNAPSHOT/rest/say").port(8080).build();

  private static Client client = ClientBuilder.newClient();

  @Test
  public void shouldResponseCorrectly() {
    // when
    Response response = client.target(uri).request().get();

    // then
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
  }
}
