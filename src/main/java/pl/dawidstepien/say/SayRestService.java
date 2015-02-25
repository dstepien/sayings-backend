package pl.dawidstepien.say;

import static pl.dawidstepien.say.model.SayingEntity.FIND_ALL_SAYINGS;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.dawidstepien.say.model.SayingEntity;

@Path("/say")
@Produces(MediaType.TEXT_PLAIN)
@Stateless
public class SayRestService {

  @Inject
  private EntityManager entityManager;

  @GET
  public Response getSay() {
    List<SayingEntity> sayings = entityManager.createNamedQuery(FIND_ALL_SAYINGS, SayingEntity.class).getResultList();
    SayingEntity saying = sayings.get(new Random().nextInt(sayings.size()));
    return Response.ok(saying.getContent()).build();
  }
}
