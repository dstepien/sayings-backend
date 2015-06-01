package pl.dawidstepien.sayings.service.saying;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.AbstractCommandService;

public class UpdateSayingService extends AbstractCommandService {

  @Inject
  private SayingDao sayingDao;

  private SayingEntity saying;

  @Override
  protected void executeCommand() {
    sayingDao.update(saying);
  }

  public void setSaying(SayingEntity saying) {
    this.saying = saying;
  }
}
