package pl.dawidstepien.sayings.service.validator;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.dawidstepien.sayings.service.validator.exception.EntityNotExistsException;

public class EntityExistsServiceValidator {

  @Inject
  private EntityManager entityManager;

  public void validate(Class<?> entityClass, long entityId) throws EntityNotExistsException {
    if(isEntityNotExists(entityClass, entityId)) {
      throw new EntityNotExistsException();
    }
  }

  private boolean isEntityNotExists(Class<?> entityClass, long entityId) {
    return entityManager.find(entityClass, entityId) == null;
  }
}
