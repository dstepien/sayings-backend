package pl.dawidstepien.say;

import static pl.dawidstepien.say.model.SayingEntity.FIND_ALL_SAYINGS;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import pl.dawidstepien.say.model.SayingEntity;

@Path("/sayings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class SayRestService {

  @Inject
  private EntityManager entityManager;

  @Context
  private UriInfo uriInfo;

  @GET
  @Path("/random")
  public Response getRandomSaying() {
    List<SayingEntity> sayings = entityManager.createNamedQuery(FIND_ALL_SAYINGS, SayingEntity.class).getResultList();
    SayingEntity saying = sayings.get(new Random().nextInt(sayings.size()));
    return Response.ok(convertToJson(saying).toString()).build();
  }

  private JSONObject convertToJson(SayingEntity saying) {
    JSONObject json = new JSONObject();
    json.put("id", saying.getId());
    json.put("content", saying.getContent());
    json.put("author", saying.getAuthor());
    return json;
  }

  @GET
  public Response getAllSayings() {
    List<SayingEntity> sayingsEntities = entityManager.createNamedQuery(FIND_ALL_SAYINGS, SayingEntity.class).getResultList();
    JSONArray sayings = convertToJsonList(sayingsEntities);
    return Response.ok(sayings.toString()).build();
  }

  private JSONArray convertToJsonList(List<SayingEntity> sayingsEntities) {
    return new JSONArray(sayingsEntities.stream().map(this::convertToJson).collect(Collectors.toList()));
  }

  @GET
  @Path("{id}")
  public Response getSaying(@PathParam("id") long id) {
    SayingEntity saying = entityManager.find(SayingEntity.class, id);
    return Response.ok(convertToJson(saying).toString()).build();
  }

  @DELETE
  @Path("{id}")
  public Response removeSaying(@PathParam("id") long id) {
    entityManager.remove(entityManager.find(SayingEntity.class, id));
    return Response.noContent().build();
  }

  @POST
  public Response createSaying(@NotNull SayingEntity saying) {
    entityManager.persist(saying);
    return Response.created(uriInfo.getAbsolutePath()).build();
  }
}
