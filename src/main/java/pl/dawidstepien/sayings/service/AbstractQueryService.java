package pl.dawidstepien.sayings.service;

public abstract class AbstractQueryService<T> extends AbstractService {

  public T execute() throws ServiceException {
    validate();
    return executeQuery();
  }

  protected abstract T executeQuery();
}
