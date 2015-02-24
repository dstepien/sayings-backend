package pl.dawidstepien.say;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/say")
@Produces(MediaType.TEXT_PLAIN)
@Stateless
public class SayRestService {

  @GET
  public Response getSay() {
    return Response.ok("Hello!").build();
  }
}
