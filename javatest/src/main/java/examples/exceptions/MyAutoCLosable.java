package examples.exceptions;

public class MyAutoCLosable implements AutoCloseable {

  private String name = null;
  private boolean throwExceptionOnClose = false;

  public MyAutoCLosable(String name, boolean throwExceptionOnClose) {
    this.name = name;
    this.throwExceptionOnClose = throwExceptionOnClose;
  }

  public void doOp(boolean throwException) {
    System.out.println("Resource " + this.name + " doing operation");
    if (throwException) {
      throw new RuntimeException("Error when calling doOp() on resource " + this.name);
    }
  }

  @Override
  public void close() {
    System.out.println("Resource " + this.name + " close() called");
    if (this.throwExceptionOnClose) {
      throw new RuntimeException("Error when trying to close resource " + this.name);
    }
  }
}
