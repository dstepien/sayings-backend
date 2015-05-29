package pl.dawidstepien.sayings.endpoint.rest.filter;

import javax.ws.rs.container.ContainerResponseContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class ResponseContextInfo {

  private static final String HEADER_TEMPLATE = "\n< %s: %s";

  private static final String ENTITY_TEMPLATE = "\n\n%s";

  public static final String NO_ENTITY_TEMPLATE = "\n";

  private final ContainerResponseContext responseContext;

  public ResponseContextInfo(ContainerResponseContext responseContext) {
    this.responseContext = responseContext;
  }

  public String getHeaders() {
    StringBuilder headers = new StringBuilder();
    for(String header : responseContext.getHeaders().keySet()) {
      headers.append(String.format(HEADER_TEMPLATE, header, responseContext.getHeaderString(header)));
    }
    return headers.toString();
  }

  public String getEntity() {
    return responseContext.hasEntity() ? String.format(ENTITY_TEMPLATE, getEntityFromResponseContext()) : NO_ENTITY_TEMPLATE;
  }

  private String getEntityFromResponseContext() {
    Object entityObject = responseContext.getEntity();
    XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
    xstream.setMode(XStream.NO_REFERENCES);
    xstream.alias(responseContext.getEntityClass().getSimpleName(), responseContext.getEntityClass());
    return xstream.toXML(entityObject);
  }
}
