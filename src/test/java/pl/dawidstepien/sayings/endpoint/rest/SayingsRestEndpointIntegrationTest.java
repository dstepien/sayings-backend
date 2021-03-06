package pl.dawidstepien.sayings.endpoint.rest;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import pl.dawidstepien.sayings.model.SayingEntity;
import pl.dawidstepien.sayings.service.ServiceException;
import pl.dawidstepien.sayings.service.saying.CreateSayingService;
import pl.dawidstepien.sayings.service.saying.DeleteSayingService;
import pl.dawidstepien.sayings.service.saying.GetAllSayingsService;
import pl.dawidstepien.sayings.service.saying.GetSayingService;
import pl.dawidstepien.sayings.service.saying.UpdateSayingService;
import pl.dawidstepien.sayings.test.category.IntegrationTest;

@Ignore
@RunWith(Arquillian.class)
@Category(IntegrationTest.class)
public class SayingsRestEndpointIntegrationTest {

  private static final String AUTHOR = "John Doe";

  private static final String CONTENT = "Lorem ipsum dolor";

  @Inject
  private EntityManager entityManager;

  @Inject
  private GetAllSayingsService getAllSayingsService;

  @Inject
  private CreateSayingService createSayingService;

  @Inject
  private DeleteSayingService deleteSayingService;

  @Inject
  private GetSayingService getSayingService;

  @Inject
  private UpdateSayingService updateSayingService;

  @Inject
  private UserTransaction userTransaction;

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
      .addPackages(true, "pl.dawidstepien.sayings")
      .addAsResource("persistence.xml", "META-INF/persistence.xml")
      .addAsResource("META-INF/beans.xml", "META-INF/beans.xml");
  }

  @Before
  public void setUp() throws Exception {
    userTransaction.begin();
    entityManager.joinTransaction();
  }

  @After
  public void tearDown() throws Exception {
    userTransaction.rollback();
    clearData();
  }

  private void clearData() throws Exception {
    userTransaction.begin();
    entityManager.joinTransaction();
    entityManager.createQuery("DELETE FROM SayingEntity").executeUpdate();
    userTransaction.commit();
  }

  @Test
  public void shouldCreateOneSaying() throws ServiceException {
    // given
    int expectedSayingsSize = getSayingsSize() + 1;

    // when
    createSaying();

    // then
    assertEquals(expectedSayingsSize, getSayingsSize());
  }

  @Test
  public void shouldGetOneSaying() throws ServiceException {
    // given
    SayingEntity saying = createSaying();

    // when
    SayingEntity result = getSayingBy(saying.getId());

    // then
    assertEquals(result.getId(), saying.getId());
  }

  @Test
  public void shouldUpdateOneSaying() throws ServiceException {
    // given
    SayingEntity saying = createSaying();
    String content = "New content";
    saying.setContent(content);

    // when
    updateSaying(saying);

    // then
    assertEquals(content, getSayingBy(saying.getId()).getContent());
  }

  @Test
  public void shouldDeleteOneSaying() throws ServiceException {
    // given
    SayingEntity saying = createSaying();
    int expectedSayingsSize = getSayingsSize() - 1;

    // when
    deleteSaying(saying.getId());

    // then
    assertEquals(expectedSayingsSize, getSayingsSize());
  }

  private int getSayingsSize() throws ServiceException {
    return getAllSayingsService.execute().size();
  }

  private SayingEntity createSaying() throws ServiceException {
    SayingEntity saying = getSayingEntity();
    createSayingService.setSaying(saying);
    createSayingService.execute();
    return saying;
  }

  private SayingEntity getSayingEntity() {
    SayingEntity saying = new SayingEntity();
    saying.setAuthor(AUTHOR);
    saying.setContent(CONTENT);
    return saying;
  }

  private SayingEntity getSayingBy(long sayingId) throws ServiceException {
    getSayingService.setSayingId(sayingId);
    return getSayingService.execute();
  }

  private void updateSaying(SayingEntity saying) throws ServiceException {
    updateSayingService.setSaying(saying);
    updateSayingService.execute();
  }

  private void deleteSaying(long sayingId) throws ServiceException {
    deleteSayingService.setSayingId(sayingId);
    deleteSayingService.execute();
  }
}