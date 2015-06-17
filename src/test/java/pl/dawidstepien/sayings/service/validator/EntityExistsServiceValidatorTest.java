package pl.dawidstepien.sayings.service.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import pl.dawidstepien.sayings.service.validator.exception.EntityNotExistsException;

@RunWith(CdiRunner.class)
public class EntityExistsServiceValidatorTest {

  private static final long ENTITY_ID = 12345L;

  @Inject
  private EntityExistsServiceValidator validator;

  @Mock
  @Produces
  private EntityManager entityManager;

  @Test
  public void shouldNotThrowExceptionWhenEntityExists() throws EntityNotExistsException {
    // given
    when(entityManager.find(Object.class, ENTITY_ID)).thenReturn(mock(Object.class));

    // when
    validator.validate(Object.class, ENTITY_ID);
  }

  @Test(expected = EntityNotExistsException.class)
  public void shouldThrowExceptionWhenEntityNotExists() throws EntityNotExistsException {
    // given
    when(entityManager.find(Object.class, ENTITY_ID)).thenReturn(null);

    // when
    validator.validate(Object.class, ENTITY_ID);
  }
}