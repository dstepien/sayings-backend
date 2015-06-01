package pl.dawidstepien.sayings.service.saying;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.dawidstepien.sayings.model.SayingEntity.FIND_ALL_SAYINGS;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.ServiceException;

@RunWith(MockitoJUnitRunner.class)
public class GetAllSayingsServiceTest {

  private static List<SayingEntity> SAYINGS = Arrays.asList(mock(SayingEntity.class), mock(SayingEntity.class));

  @Mock(answer = RETURNS_DEEP_STUBS)
  private EntityManager entityManager;

  @Test
  public void shouldGetAllSayings() throws ServiceException {
    // given
    when(entityManager.createNamedQuery(FIND_ALL_SAYINGS, SayingEntity.class).getResultList()).thenReturn(SAYINGS);

    GetAllSayingsService service = new GetAllSayingsService();

    // when
    List<SayingEntity> result = service.execute();

    // then
    assertEquals(true, SAYINGS.equals(result));
  }
}