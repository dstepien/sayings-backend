package pl.dawidstepien.sayings.service.saying;

import static pl.dawidstepien.sayings.model.SayingEntity.FIND_ALL_SAYINGS;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import pl.dawidstepien.sayings.model.SayingEntity;

public class GetRandomSayingService implements Service<SayingEntity> {

  private EntityManager entityManager;

  @Override
  public SayingEntity execute() {
    List<SayingEntity> sayings = entityManager.createNamedQuery(FIND_ALL_SAYINGS, SayingEntity.class).getResultList();
    return sayings.get(new Random().nextInt(sayings.size()));
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
}
