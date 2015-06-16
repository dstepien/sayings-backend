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
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.ServiceException;

@RunWith(CdiRunner.class)
public class CreateSayingServiceTest {

  @Inject
  private CreateSayingService service;

  @Mock
  private SayingEntity sayingEntity;

  @Mock
  @Produces
  private SayingDao sayingDao;

  @Test
  public void shouldCreateSaying() throws ServiceException {
    // given
    service.setSaying(sayingEntity);

    // when
    service.execute();

    // then
    verify(sayingDao, times(1)).save(sayingEntity);
  }
}
