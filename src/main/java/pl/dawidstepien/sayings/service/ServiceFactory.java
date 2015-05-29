package pl.dawidstepien.sayings.service;

import javax.enterprise.inject.spi.CDI;

public class ServiceFactory {

  public <T extends QueryService> T createQueryService(Class<T> serviceClass) throws ServiceFactoryException {
    return CDI.current().select(serviceClass).get();
  }

  public <T extends CommandService> T createCommandService(Class<T> serviceClass) throws ServiceFactoryException {
    return CDI.current().select(serviceClass).get();
  }
}