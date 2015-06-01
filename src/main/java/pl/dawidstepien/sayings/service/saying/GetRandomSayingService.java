package pl.dawidstepien.sayings.service.saying;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.AbstractQueryService;

public class GetRandomSayingService extends AbstractQueryService<SayingEntity> {

  @Inject
  private SayingDao sayingDao;

  @Override
  protected SayingEntity executeQuery() {
    return getRandomSaying(sayingDao.getAllSayings());
  }

  private SayingEntity getRandomSaying(List<SayingEntity> sayings) {
    return sayings.get(new Random().nextInt(sayings.size()));
  }
}
