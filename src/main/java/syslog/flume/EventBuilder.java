package syslog.flume;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class EventBuilder {

  /**
   * Instantiate an Event instance based on the provided body and headers.
   * If <code>headers</code> is <code>null</code>, then it is ignored.
   * @param body
   * @param headers
   * @return
   */
  public static Event withBody(byte[] body, Map<String, String> headers) {
    Event event = new SimpleEvent();

    if (body == null) {
      body = new byte[0];
    }
    event.setBody(body);

    if (headers != null) {
      event.setHeaders(new HashMap<String, String>(headers));
    }

    return event;
  }

  public static Event withBody(byte[] body) {
    return withBody(body, null);
  }

  public static Event withBody(String body, Charset charset,
      Map<String, String> headers) {

    return withBody(body.getBytes(charset), headers);
  }

  public static Event withBody(String body, Charset charset) {
    return withBody(body, charset, null);
  }

}
