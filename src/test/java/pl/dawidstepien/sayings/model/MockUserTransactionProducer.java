package pl.dawidstepien.sayings.model;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.transaction.UserTransaction;

@Alternative
public class MockUserTransactionProducer {

  @Produces
  private UserTransaction userTransaction;
}