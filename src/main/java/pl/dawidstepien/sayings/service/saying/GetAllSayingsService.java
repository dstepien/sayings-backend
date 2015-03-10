package pl.dawidstepien.sayings.service.saying;

import static pl.dawidstepien.sayings.model.SayingEntity.FIND_ALL_SAYINGS;

import java.util.List;

import javax.persistence.EntityManager;

import pl.dawidstepien.sayings.model.SayingEntity;

public class GetAllSayingsService implements Service<List<SayingEntity>> {

  private EntityManager entityManager;

  @Override
  public List<SayingEntity> execute() {
    return entityManager.createNamedQuery(FIND_ALL_SAYINGS, SayingEntity.class).getResultList();
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
}
