package pl.dawidstepien.sayings.service.saying;

import javax.persistence.EntityManager;

import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.Service;

public class DeleteSayingService implements Service<Void> {

  private EntityManager entityManager;

  private long sayingId;

  @Override
  public Void execute() {
    entityManager.remove(entityManager.find(SayingEntity.class, sayingId));
    return null;
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }
}
