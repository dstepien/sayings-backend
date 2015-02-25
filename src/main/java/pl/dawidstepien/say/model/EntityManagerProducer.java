package pl.dawidstepien.say.model;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

  @Produces
  @PersistenceContext(unitName = "sayPersistenceUnit")
  private EntityManager entityManager;
}
