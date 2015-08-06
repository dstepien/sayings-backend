package pl.dawidstepien.sayings.service.validator.exception;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import pl.dawidstepien.sayings.service.ServiceException;

public class EntityNotExistsException extends ServiceException {

  private static final String MESSAGE = "Entity %s with id %d doesn't exists in the database";

  private final String entityName;

  private final long entityId;

  public EntityNotExistsException(Class<?> entityClass, long entityId) {
    super();
    this.entityName = entityClass.getSimpleName();
    this.entityId = entityId;
  }

  @Override
  public int getStatus() {
    return NOT_FOUND.getStatusCode();
  }

  @Override
  public String getMessage() {
    return String.format(MESSAGE, entityName, entityId);
  }
}
