package pl.dawidstepien.sayings.service.saying;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.service.AbstractCommandService;

public class DeleteSayingService extends AbstractCommandService {

  @Inject
  private SayingDao sayingDao;

  private long sayingId;

  @Override
  protected void executeCommand() {
    sayingDao.delete(sayingId);
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }
}
