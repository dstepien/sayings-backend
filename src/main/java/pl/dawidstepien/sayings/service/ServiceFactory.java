package pl.dawidstepien.sayings.service;

import javax.enterprise.inject.spi.CDI;

public class ServiceFactory {

  public <T extends AbstractQueryService> T createQueryService(Class<T> serviceClass) {
    return CDI.current().select(serviceClass).get();
  }

  public <T extends AbstractCommandService> T createCommandService(Class<T> serviceClass) {
    return CDI.current().select(serviceClass).get();
  }
}
