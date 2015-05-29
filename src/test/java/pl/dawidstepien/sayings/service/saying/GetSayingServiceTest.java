package pl.dawidstepien.sayings.service.saying;

import static org.junit.Assert.assertEquals;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.SayingEntity;

@RunWith(MockitoJUnitRunner.class)
public class GetSayingServiceTest {

  private static final long ID = 123L;

  @Mock(answer = RETURNS_DEEP_STUBS)
  private EntityManager entityManager;

  @Mock
  private SayingEntity saying;

  @Test
  public void shouldGetSayingEntityWithSpecifiedId() {
    // given
    when(entityManager.find(SayingEntity.class, ID)).thenReturn(saying);

    GetSayingService service = new GetSayingService();
    service.setSayingId(ID);

    // when
    SayingEntity result = service.execute();

    // then
    assertEquals(saying, result);
  }
}