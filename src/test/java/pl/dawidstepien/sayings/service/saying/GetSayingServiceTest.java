package pl.dawidstepien.sayings.service.saying;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.ServiceException;
import pl.dawidstepien.sayings.service.validator.EntityExistsServiceValidator;

@RunWith(CdiRunner.class)
public class GetSayingServiceTest {

  private static final long SAYING_ID = 12345L;

  @Inject
  private GetSayingService service;

  @Mock
  @Produces
  private SayingDao sayingDao;

  @Mock
  @Produces
  private EntityExistsServiceValidator existsServiceValidator;

  @Mock
  private SayingEntity sayingEntity;

  @Test
  public void shouldGetSayingEntityWithSpecifiedId() throws ServiceException {
    // given
    when(sayingDao.getSaying(SAYING_ID)).thenReturn(sayingEntity);

    service.setSayingId(SAYING_ID);

    // when
    SayingEntity result = service.execute();

    // then
    assertThat(result, is(equalTo(sayingEntity)));
  }
}