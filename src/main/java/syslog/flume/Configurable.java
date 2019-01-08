package syslog.flume;

public interface Configurable {
  /**
   * <p>
   * Request the implementing class to (re)configure itself.
   * </p>
   * <p>
   * When configuration parameters are changed, they must be
   * reflected by the component asap.
   * </p>
   * <p>
   * There are no thread safety guarantees on when configure might be called.
   * </p>
   * @param context
   */
  public void configure(Context context);

}
