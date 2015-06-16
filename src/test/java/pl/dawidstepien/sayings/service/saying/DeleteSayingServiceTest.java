package pl.dawidstepien.sayings.service.saying;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.service.ServiceException;

@RunWith(CdiRunner.class)
public class DeleteSayingServiceTest {

  private static final long SAYING_ID = 12345L;

  @Inject
  private DeleteSayingService service;

  @Mock
  @Produces
  private SayingDao sayingDao;

  @Test
  public void shouldCreateSaying() throws ServiceException {
    // given
    service.setSayingId(SAYING_ID);

    // when
    service.execute();

    // then
    verify(sayingDao, times(1)).delete(SAYING_ID);
  }
}
