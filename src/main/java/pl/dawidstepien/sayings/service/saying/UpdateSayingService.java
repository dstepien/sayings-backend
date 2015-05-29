package pl.dawidstepien.sayings.service.saying;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.CommandService;

public class UpdateSayingService implements CommandService {

  @Inject
  private SayingDao sayingDao;

  private SayingEntity saying;

  @Override
  public void execute() {
    sayingDao.update(saying);
  }

  public void setSaying(SayingEntity saying) {
    this.saying = saying;
  }
}
