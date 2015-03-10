package pl.dawidstepien.sayings.service.saying;

import javax.persistence.EntityManager;

import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.Service;

public class GetSayingService implements Service<SayingEntity> {

  private EntityManager entityManager;

  private long sayingId;

  @Override
  public SayingEntity execute() {
    return entityManager.find(SayingEntity.class, sayingId);
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }
}
