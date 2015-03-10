package pl.dawidstepien.sayings.endpoint.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import pl.dawidstepien.sayings.endpoint.rest.response.AllSayingsRestResponse;
import pl.dawidstepien.sayings.endpoint.rest.response.RandomSayingRestResponse;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.saying.GetAllSayingsService;
import pl.dawidstepien.sayings.service.saying.GetRandomSayingService;

@Path("/sayings")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SayingsRestEndpoint {

  @Inject
  private EntityManager entityManager;

  @Context
  private UriInfo uriInfo;

  @GET
  @Path("/random")
  public Response getRandomSaying() {
    GetRandomSayingService service = new GetRandomSayingService();
    service.setEntityManager(entityManager);
    return new RandomSayingRestResponse(service.execute()).build();
  }

  @GET
  public Response getAllSayings() {
    GetAllSayingsService service = new GetAllSayingsService();
    service.setEntityManager(entityManager);
    return new AllSayingsRestResponse(service.execute()).build();
  }

  @GET
  @Path("{id}")
  public Response getSaying(@PathParam("id") long id) {
    SayingEntity saying = entityManager.find(SayingEntity.class, id);
    return Response.ok(convertToJson(saying).toString()).build();
  }

  private JSONObject convertToJson(SayingEntity saying) {
    JSONObject json = new JSONObject();
    json.put("id", saying.getId());
    json.put("content", saying.getContent());
    json.put("author", saying.getAuthor());
    return json;
  }

  @DELETE
  @Path("{id}")
  public Response removeSaying(@PathParam("id") long id) {
    entityManager.remove(entityManager.find(SayingEntity.class, id));
    return Response.noContent().build();
  }

  @PUT
  public Response updateSaying(@NotNull SayingEntity saying) {
    entityManager.merge(saying);
    return Response.noContent().build();
  }

  @POST
  public Response createSaying(@NotNull SayingEntity saying) {
    entityManager.persist(saying);
    return Response.created(uriInfo.getAbsolutePath()).build();
  }
}
