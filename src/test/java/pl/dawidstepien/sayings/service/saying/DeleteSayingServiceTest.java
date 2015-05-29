package pl.dawidstepien.sayings.service.saying;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.SayingEntity;

@RunWith(MockitoJUnitRunner.class)
public class DeleteSayingServiceTest {

  private static final long ID = 12345L;

  @Mock
  private EntityManager entityManagerMock;

  @Mock
  private SayingEntity sayingEntityMock;

  @Test
  public void shouldDeleteSaying() {
    // given
    when(entityManagerMock.find(SayingEntity.class, ID)).thenReturn(sayingEntityMock);

    DeleteSayingService service = new DeleteSayingService();
    service.setSayingId(ID);

    // when
    service.execute();

    // then
    verify(entityManagerMock, times(1)).remove(sayingEntityMock);
  }
}
