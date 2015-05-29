package pl.dawidstepien.sayings.endpoint.rest.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class EndpointRestLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

  // TODO: Podpiąć log4j
  private Logger logger = Logger.getLogger("RestEndpointLoggingFilter");

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    RequestContextInfo requestContextInfo = new RequestContextInfo(requestContext);
    StringBuilder message = new StringBuilder();
    message.append(requestContextInfo.getMethodWithUri());
    message.append(requestContextInfo.getHeaders());
    message.append(requestContextInfo.getEntity());
    logger.info(message.toString());
  }

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    ResponseContextInfo responseContextInfo = new ResponseContextInfo(responseContext);
    StringBuilder message = new StringBuilder();
    message.append(responseContextInfo.getHeaders());
    message.append(responseContextInfo.getEntity());
    logger.info(message.toString());
  }
}
