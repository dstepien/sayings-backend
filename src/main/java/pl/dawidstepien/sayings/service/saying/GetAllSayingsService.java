package pl.dawidstepien.sayings.service.saying;

import java.util.List;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.AbstractQueryService;

public class GetAllSayingsService extends AbstractQueryService<List<SayingEntity>> {

  @Inject
  private SayingDao sayingDao;

  @Override
  protected List<SayingEntity> executeQuery() {
    return sayingDao.getAllSayings();
  }
}
