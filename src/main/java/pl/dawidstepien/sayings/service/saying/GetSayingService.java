package pl.dawidstepien.sayings.service.saying;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.QueryService;

public class GetSayingService implements QueryService<SayingEntity> {

  @Inject
  private SayingDao sayingDao;

  private long sayingId;

  @Override
  public SayingEntity execute() {
    return sayingDao.getSaying(sayingId);
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }
}
