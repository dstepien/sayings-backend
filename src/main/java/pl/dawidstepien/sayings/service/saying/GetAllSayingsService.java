package pl.dawidstepien.sayings.service.saying;

import java.util.List;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.QueryService;

public class GetAllSayingsService implements QueryService<List<SayingEntity>> {

  @Inject
  private SayingDao sayingDao;

  @Override
  public List<SayingEntity> execute() {
    return sayingDao.getAllSayings();
  }
}
