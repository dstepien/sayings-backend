package pl.dawidstepien.sayings.model;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

  @Produces
  @PersistenceContext(unitName = "sayPersistenceUnit")
  private EntityManager entityManager;
}
