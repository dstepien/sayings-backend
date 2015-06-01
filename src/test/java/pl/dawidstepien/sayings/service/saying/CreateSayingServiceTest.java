package pl.dawidstepien.sayings.service.saying;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.ServiceException;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class CreateSayingServiceTest {

  @Mock
  private EntityManager entityManagerMock;

  @Mock
  private SayingEntity sayingEntityMock;

  @Test
  public void shouldCreateSaying() throws ServiceException {
    // given
    CreateSayingService service = new CreateSayingService();
    service.setSaying(sayingEntityMock);

    // when
    service.execute();

    // then
    verify(entityManagerMock, times(1)).persist(sayingEntityMock);
  }
}
