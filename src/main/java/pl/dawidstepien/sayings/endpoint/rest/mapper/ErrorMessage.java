package pl.dawidstepien.sayings.endpoint.rest.mapper;

import javax.xml.bind.annotation.XmlRootElement;

import pl.dawidstepien.sayings.service.ServiceException;

@XmlRootElement
public class ErrorMessage {

  private int status;

  private String message;

  private String debugMessage;

  public ErrorMessage(ServiceException exception) {
    status = exception.getStatus();
    message = exception.getMessage();
    debugMessage = exception.getDebugMessage();
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getDebugMessage() {
    return debugMessage;
  }
}
