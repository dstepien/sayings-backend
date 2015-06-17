package pl.dawidstepien.sayings.service.saying;

import javax.inject.Inject;

import pl.dawidstepien.sayings.dao.SayingDao;
import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.AbstractQueryService;
import pl.dawidstepien.sayings.service.ServiceException;
import pl.dawidstepien.sayings.service.validator.EntityExistsServiceValidator;

public class GetSayingService extends AbstractQueryService<SayingEntity> {

  @Inject
  private SayingDao sayingDao;

  @Inject
  private EntityExistsServiceValidator entityExistsValidator;

  private long sayingId;

  @Override
  protected SayingEntity executeQuery() {
    return sayingDao.getSaying(sayingId);
  }

  @Override
  protected void validate() throws ServiceException {
    entityExistsValidator.validate(SayingEntity.class, sayingId);
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }
}
