package pl.dawidstepien.sayings.service;

public abstract class AbstractCommandService extends AbstractService {

  public void execute() throws ServiceException {
    validate();
    executeCommand();
  }

  protected abstract void executeCommand();
}
