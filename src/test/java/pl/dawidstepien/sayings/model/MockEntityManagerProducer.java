package pl.dawidstepien.sayings.model;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

@Alternative
public class MockEntityManagerProducer {

  @Produces
  private static EntityManager entityManager;

  public static void setEntityManager(EntityManager entityManager) {
    MockEntityManagerProducer.entityManager = entityManager;
  }

  public static void clear() {
    entityManager = null;
  }
}