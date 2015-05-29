package pl.dawidstepien.sayings.service.saying;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.service.CommandService;

public class DeleteSayingService implements CommandService {

  @Inject
  private SayingDao sayingDao;

  private long sayingId;

  @Override
  public void execute() {
    sayingDao.delete(sayingId);
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }
}
