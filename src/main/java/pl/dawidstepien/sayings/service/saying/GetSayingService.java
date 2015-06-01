package pl.dawidstepien.sayings.service.saying;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.AbstractQueryService;

public class GetSayingService extends AbstractQueryService<SayingEntity> {

  @Inject
  private SayingDao sayingDao;

  private long sayingId;

  @Override
  protected SayingEntity executeQuery() {
    return sayingDao.getSaying(sayingId);
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }
}
