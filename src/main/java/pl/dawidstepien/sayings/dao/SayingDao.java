package pl.dawidstepien.sayings.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pl.dawidstepien.sayings.model.SayingEntity;

public class SayingDao {

  @Inject
  private EntityManager entityManager;

  public List<SayingEntity> getAllSayings() {
    TypedQuery<SayingEntity> query = createNamedQuery(SayingEntity.FIND_ALL_SAYINGS);
    return query.getResultList();
  }

  private TypedQuery<SayingEntity> createNamedQuery(String namedQuery) {
    return entityManager.createNamedQuery(namedQuery, SayingEntity.class);
  }

  public void save(SayingEntity saying) {
    entityManager.persist(saying);
  }

  public SayingEntity getSaying(long sayingId) {
    return entityManager.find(SayingEntity.class, sayingId);
  }

  public void delete(long sayingId) {
    entityManager.remove(entityManager.find(SayingEntity.class, sayingId));
  }

  public void update(SayingEntity saying) {
    entityManager.merge(saying);
  }
}
