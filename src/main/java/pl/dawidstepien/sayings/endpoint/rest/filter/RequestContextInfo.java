package pl.dawidstepien.sayings.endpoint.rest.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;

import com.google.common.io.ByteStreams;

public class RequestContextInfo {

  private static final String METHOD_WITH_URI_TEMPLATE = "%s %s";

  private static final String HEADER_TEMPLATE = "\n> %s: %s";

  private static final String ENTITY_TEMPLATE = "\n\n%s";

  private static final String NO_ENTITY_TEMPLATE = "\n";

  private final ContainerRequestContext requestContext;

  public RequestContextInfo(ContainerRequestContext requestContext) {
    this.requestContext = requestContext;
  }

  public String getMethodWithUri() {
    return String.format(METHOD_WITH_URI_TEMPLATE,
      requestContext.getMethod(), requestContext.getUriInfo().getAbsolutePath().getPath());
  }

  public String getHeaders() {
    StringBuilder headers = new StringBuilder();
    for(String header : requestContext.getHeaders().keySet()) {
      headers.append(String.format(HEADER_TEMPLATE, header, requestContext.getHeaderString(header)));
    }
    return headers.toString();
  }

  public String getEntity() throws IOException {
    return requestContext.hasEntity() ? String.format(ENTITY_TEMPLATE, getEntityFromRequestContext()) : NO_ENTITY_TEMPLATE;
  }

  private String getEntityFromRequestContext() throws IOException {
    InputStream entityStream = requestContext.getEntityStream();
    byte[] entityStreamBytes = getEntityStreamBytes(entityStream);
    return new String(entityStreamBytes);
  }

  private byte[] getEntityStreamBytes(InputStream entityStream) throws IOException {
    ByteArrayOutputStream entityStreamCopy = new ByteArrayOutputStream();
    ByteStreams.copy(entityStream, entityStreamCopy);
    byte[] entityStreamBytes = entityStreamCopy.toByteArray();
    requestContext.setEntityStream(new ByteArrayInputStream(entityStreamBytes));
    return entityStreamBytes;
  }
}
