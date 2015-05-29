package pl.dawidstepien.sayings.service.saying;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.QueryService;

public class GetRandomSayingService implements QueryService<SayingEntity> {

  @Inject
  private SayingDao sayingDao;

  @Override
  public SayingEntity execute() {
    return getRandomSaying(sayingDao.getAllSayings());
  }

  private SayingEntity getRandomSaying(List<SayingEntity> sayings) {
    return sayings.get(new Random().nextInt(sayings.size()));
  }
}
