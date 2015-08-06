package pl.dawidstepien.sayings.endpoint.rest.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pl.dawidstepien.sayings.service.ServiceException;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

  @Override
  public Response toResponse(ServiceException exception) {
    return Response.status(exception.getStatus())
      .entity(new ErrorMessage(exception))
      .type(MediaType.APPLICATION_JSON_TYPE)
      .build();
  }
}
