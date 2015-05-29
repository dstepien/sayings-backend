package pl.dawidstepien.sayings.service.saying;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.SayingEntity;

@RunWith(MockitoJUnitRunner.class)
public class UpdateSayingServiceTest {

  @Mock
  private EntityManager entityManagerMock;

  @Mock
  private SayingEntity sayingEntityMock;

  @Test
  public void shouldUpdateSaying() {
    // given
    UpdateSayingService service = new UpdateSayingService();
    service.setSaying(sayingEntityMock);

    // when
    service.execute();

    // then
    verify(entityManagerMock, times(1)).merge(sayingEntityMock);
  }
}
