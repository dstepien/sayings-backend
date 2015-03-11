package pl.dawidstepien.sayings.service.saying;

import javax.persistence.EntityManager;

import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.Service;

public class UpdateSayingService implements Service<Void> {

  private EntityManager entityManager;

  private SayingEntity saying;

  @Override
  public Void execute() {
    entityManager.merge(saying);
    return null;
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void setSaying(SayingEntity saying) {
    this.saying = saying;
  }
}
