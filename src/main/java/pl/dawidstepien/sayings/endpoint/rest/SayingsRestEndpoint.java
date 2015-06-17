package pl.dawidstepien.sayings.endpoint.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.ServiceException;
import pl.dawidstepien.sayings.service.ServiceFactory;
import pl.dawidstepien.sayings.service.saying.CreateSayingService;
import pl.dawidstepien.sayings.service.saying.DeleteSayingService;
import pl.dawidstepien.sayings.service.saying.GetAllSayingsService;
import pl.dawidstepien.sayings.service.saying.GetRandomSayingService;
import pl.dawidstepien.sayings.service.saying.GetSayingService;
import pl.dawidstepien.sayings.service.saying.UpdateSayingService;

@Stateless
@Path("/sayings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SayingsRestEndpoint {

  @Context
  private UriInfo uriInfo;

  @Inject
  private ServiceFactory serviceFactory;

  @GET
  @Path("/random")
  public Response getRandomSaying() throws ServiceException {
    GetRandomSayingService service = serviceFactory.createQueryService(GetRandomSayingService.class);
    return Response.ok(service.execute()).build();
  }

  @GET
  public Response getAllSayings() throws ServiceException {
    GetAllSayingsService service = serviceFactory.createQueryService(GetAllSayingsService.class);
    return Response.ok(service.execute()).build();
  }

  @GET
  @Path("{id}")
  public Response getSaying(@PathParam("id") long sayingId) throws ServiceException {
    GetSayingService service = serviceFactory.createQueryService(GetSayingService.class);
    service.setSayingId(sayingId);
    return Response.ok(service.execute()).build();
  }

  @DELETE
  @Path("{id}")
  public Response removeSaying(@PathParam("id") long sayingId) throws ServiceException {
    DeleteSayingService service = serviceFactory.createCommandService(DeleteSayingService.class);
    service.setSayingId(sayingId);
    service.execute();
    return Response.noContent().build();
  }

  @PUT
  public Response updateSaying(SayingEntity saying) throws ServiceException {
    UpdateSayingService service = serviceFactory.createCommandService(UpdateSayingService.class);
    service.setSaying(saying);
    service.execute();
    return Response.noContent().build();
  }

  @POST
  public Response createSaying(@NotNull SayingEntity saying) throws ServiceException {
    CreateSayingService service = serviceFactory.createCommandService(CreateSayingService.class);
    service.setSaying(saying);
    service.execute();
    return Response.created(uriInfo.getAbsolutePath()).build();
  }
}
