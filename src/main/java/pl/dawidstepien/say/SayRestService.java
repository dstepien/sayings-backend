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

import org.json.JSONObject;

import pl.dawidstepien.say.model.SayingEntity;

@Path("/say")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class SayRestService {

  @Inject
  private EntityManager entityManager;

  @GET
  public Response getSay() {
    List<SayingEntity> sayings = entityManager.createNamedQuery(FIND_ALL_SAYINGS, SayingEntity.class).getResultList();
    SayingEntity saying = sayings.get(new Random().nextInt(sayings.size()));
    return Response.ok(convertToJson(saying).toString()).build();
  }

  private JSONObject convertToJson(SayingEntity saying) {
    JSONObject json = new JSONObject();
    json.put("content", saying.getContent());
    json.put("author", saying.getAuthor());
    return json;
  }
}
