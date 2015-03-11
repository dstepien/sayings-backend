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

import pl.dawidstepien.sayings.endpoint.rest.response.AllSayingsRestResponse;
import pl.dawidstepien.sayings.endpoint.rest.response.SingleSayingRestResponse;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.saying.CreateSayingService;
import pl.dawidstepien.sayings.service.saying.DeleteSayingService;
import pl.dawidstepien.sayings.service.saying.GetAllSayingsService;
import pl.dawidstepien.sayings.service.saying.GetRandomSayingService;
import pl.dawidstepien.sayings.service.saying.GetSayingService;
import pl.dawidstepien.sayings.service.saying.UpdateSayingService;

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
    return new SingleSayingRestResponse(service.execute()).build();
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
    GetSayingService service = new GetSayingService();
    service.setEntityManager(entityManager);
    service.setSayingId(id);
    return new SingleSayingRestResponse(service.execute()).build();
  }

  @DELETE
  @Path("{id}")
  public Response removeSaying(@PathParam("id") long sayingId) {
    DeleteSayingService service = new DeleteSayingService();
    service.setEntityManager(entityManager);
    service.setSayingId(sayingId);
    service.execute();
    return Response.noContent().build();
  }

  @PUT
  public Response updateSaying(@NotNull SayingEntity saying) {
    UpdateSayingService service = new UpdateSayingService();
    service.setEntityManager(entityManager);
    service.setSaying(saying);
    service.execute();
    return Response.noContent().build();
  }

  @POST
  public Response createSaying(@NotNull SayingEntity saying) {
    CreateSayingService service = new CreateSayingService();
    service.setEntityManager(entityManager);
    service.setSaying(saying);
    service.execute();
    return Response.created(uriInfo.getAbsolutePath()).build();
  }
}
