package pl.dawidstepien.sayings.service.saying;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.MockEntityManagerProducer;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.ServiceException;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class GetRandomSayingServiceTest {

  private static List<SayingEntity> SAYINGS = Arrays.asList(mock(SayingEntity.class), mock(SayingEntity.class));

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private static EntityManager entityManager;

  private Weld weld;

  private WeldContainer container;

  @Before
  public void setUp() throws Exception {
    initializeWeldContainer();
    setMockProducers();
  }

  private void setMockProducers() {
    MockEntityManagerProducer.setEntityManager(entityManager);
  }

  private void initializeWeldContainer() {
    weld = new Weld();
    container = weld.initialize();
  }

  @After
  public void tearDown() throws Exception {
    shutdownWeld();
    clearMockProducers();
  }

  private void clearMockProducers() {
    MockEntityManagerProducer.clear();
  }

  private void shutdownWeld() {
    weld.shutdown();
  }

  @Test
  public void shouldReturnRandomSaying() throws ServiceException {
    // given
    when(entityManager.createNamedQuery(anyString(), eq(SayingEntity.class)).getResultList()).thenReturn(SAYINGS);

    GetRandomSayingService service = container.instance().select(GetRandomSayingService.class).get();

    // when
    SayingEntity result = service.execute();

    // then
    assertNotNull(result);
    assertEquals(true, SAYINGS.contains(result));
  }
}
