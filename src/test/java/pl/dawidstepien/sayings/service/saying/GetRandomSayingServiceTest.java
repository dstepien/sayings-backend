package pl.dawidstepien.sayings.service.saying;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.SayingEntity;

@RunWith(MockitoJUnitRunner.class)
public class GetRandomSayingServiceTest {

  @Mock(answer = RETURNS_DEEP_STUBS)
  private EntityManager entityManager;

  private static List<SayingEntity> SAYINGS = Arrays.asList(
    mock(SayingEntity.class), mock(SayingEntity.class), mock(SayingEntity.class));

  @Test
  public void shouldReturnRandomSaying() {
    // given
    when(entityManager.createNamedQuery(anyString(), eq(SayingEntity.class)).getResultList()).thenReturn(SAYINGS);

    GetRandomSayingService service = new GetRandomSayingService();
    service.setEntityManager(entityManager);

    // when
    SayingEntity result = service.execute();

    // then
    assertNotNull(result);
    assertEquals(true, SAYINGS.contains(result));
  }
}