package pl.dawidstepien.sayings.service.saying;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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
public class GetAllSayingsServiceTest {

  private static List<SayingEntity> SAYINGS = Arrays.asList(mock(SayingEntity.class), mock(SayingEntity.class));

  @Inject
  private GetAllSayingsService service;

  @Mock
  @Produces
  private SayingDao sayingDao;

  @Test
  public void shouldGetAllSayings() throws ServiceException {
    // given
    when(sayingDao.getAllSayings()).thenReturn(SAYINGS);

    // when
    List<SayingEntity> result = service.execute();

    // then
    assertThat(result, is(equalTo(SAYINGS)));
  }
}