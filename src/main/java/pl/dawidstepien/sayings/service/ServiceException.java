package pl.dawidstepien.sayings.service;

abstract public class ServiceException extends Exception {

  private static final String EMPTY_DEBUG_MESSAGE = "";

  abstract public int getStatus();

  abstract public String getMessage();

  public String getDebugMessage() {
    return EMPTY_DEBUG_MESSAGE;
  }
}
