package pl.dawidstepien.sayings.service.saying;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
public class GetRandomSayingServiceTest {

  private static List<SayingEntity> SAYINGS = Arrays.asList(mock(SayingEntity.class), mock(SayingEntity.class));

  @Inject
  private GetRandomSayingService service;

  @Mock
  @Produces
  private SayingDao sayingDao;

  @Test
  public void shouldReturnRandomSaying() throws ServiceException {
    // given
    when(sayingDao.getAllSayings()).thenReturn(SAYINGS);

    // when
    SayingEntity result = service.execute();

    // then
    assertThat(SAYINGS, hasItem(result));
  }
}
